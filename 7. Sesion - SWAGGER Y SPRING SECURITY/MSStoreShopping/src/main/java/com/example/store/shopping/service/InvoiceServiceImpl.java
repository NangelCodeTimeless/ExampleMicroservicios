package com.example.store.shopping.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.store.shopping.bean.Invoice;
import com.example.store.shopping.bean.InvoiceItem;
import com.example.store.shopping.repository.InvoiceItemsRepository;
import com.example.store.shopping.repository.InvoiceRepository;
import com.example.store.shopping.service.client.CustomerClient;
import com.example.store.shopping.service.client.ProductClient;
import com.example.store.shopping.service.model.Customer;
import com.example.store.shopping.service.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
	@NonNull
	// @Autowired
	private final InvoiceRepository invoiceRepository;
	@NonNull
	// @Autowired
	private final InvoiceItemsRepository invoiceItemsRepository;
	// @Autowired
	@NonNull
	private final CustomerClient customerClient;

	// @Autowired
	@NonNull
	private final ProductClient productClient;

	@Override
	@HystrixCommand(fallbackMethod = "defaultfindInvoiceAll", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
			// es una propiedad que establece el número mínimo de solicitudes en una ventana
			// móvil que disparará el circuito y su valor
			// predeterminado es 20 y su valor se puede cambiar en el archivo de propiedades
			// o en nuestro @HystrixCommandmétodo anotado.
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "500"),
			// El período de tiempo de la ventana inactiva establece la cantidad de tiempo,
			// después de disparar el circuito, para rechazar las
			// solicitudes antes de permitir intentos nuevamente para determinar si el
			// circuito debe cerrarse nuevamente. Su valor está predeterminado en 5000
			// milisegundos.
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "50000") })
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	public List<Invoice> defaultfindInvoiceAll() {
		return Arrays.asList(new Invoice());
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		if (invoiceDB != null) {
			return invoiceDB;
		}
		invoice.getItems().forEach(details -> {
			details.setSubTotal(details.getPrice() * details.getQuantity());
		});
		invoice.setState("CREATED");
		invoiceDB = invoiceRepository.save(invoice);
		invoiceDB.getItems().forEach(items -> {
			productClient.updateStockProduct(items.getProductId(), items.getQuantity() * -1);
		});
		return invoice;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if (invoiceDB == null) {
			return null;
		}
		invoiceDB.setCustomerId(invoice.getCustomerId());
		invoiceDB.setDescription(invoice.getDescription());
		invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
		invoiceDB.getItems().clear();
		invoiceDB.setItems(invoice.getItems());
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if (invoiceDB == null) {
			return null;
		}
		invoiceDB.setState("DELETED");
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice getInvoice(Long id) {
		Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
		if (invoiceOptional.isPresent()) {
			Invoice invoice = invoiceOptional.get();
			ResponseEntity<Customer> customerEntity = customerClient.getCustomer(invoice.getCustomerId());
			Customer customer = Optional.ofNullable(customerEntity.getBody()).orElse(new Customer());
			invoice.setCustomer(customer);
			List<InvoiceItem> invoiceItems = invoice.getItems().stream().map(items -> {
				ResponseEntity<Product> productEntity = productClient.getProduct(items.getProductId());
				Product product = Optional.ofNullable(productEntity.getBody()).orElse(new Product());
				items.setProduct(product);
				return items;
			}).collect(Collectors.toList());
			invoice.setItems(invoiceItems);
			return invoice;
		}
		return null;
	}

	@Override
	public void validateStockAndReturnProduct(List<String> productsWithZeroStock, Long id) {
		ResponseEntity<Product> productEntity = productClient.getProduct(id);
		if (productEntity.getBody().getStock() == 0) {
			productsWithZeroStock.add(productEntity.getBody().getName());
		}
	}
}
