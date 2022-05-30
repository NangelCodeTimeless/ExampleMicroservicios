package com.example.store.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.store.shopping.bean.Invoice;
import com.example.store.shopping.bean.InvoiceItem;
import com.example.store.shopping.client.CustomerClient;
import com.example.store.shopping.client.ProductClient;
import com.example.store.shopping.client.model.Customer;
import com.example.store.shopping.client.model.Product;
import com.example.store.shopping.repository.InvoiceItemsRepository;
import com.example.store.shopping.repository.InvoiceRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

	// @Autowired
	private final InvoiceRepository invoiceRepository;

	// @Autowired
	private final InvoiceItemsRepository invoiceItemsRepository;

	// @NonNull
	private final CustomerClient customerClient;

	// @Autowired
	// @NonNull
	private final ProductClient productClient;

	@Override
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		if (invoiceDB != null) {
			return invoiceDB;
		}
		invoice.setState("CREATED");
		return invoiceRepository.save(invoice);
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
			//Customer customerEntity = new RestTemplate().getForObject("http://localhost:8088/customers/"+ invoice.getCustomerId(), Customer.class);
			Customer customer = Optional.ofNullable(customerEntity.getBody()).orElse(new Customer());
			invoice.setCustomer(customer);
			List<InvoiceItem> invoiceItems = invoice.getItems().stream().map(items -> {
				ResponseEntity<Product> productEntity = productClient.getProduct(items.getProductId());
				Product product = Optional.ofNullable(productEntity.getBody()).orElse(new Product());
				items.setProduct(product);
				return items;
			}).collect(Collectors.toList());
			
//			List<InvoiceItem> invoiceItems2 = new ArrayList<>();
//			for (InvoiceItem items : invoice.getItems()) {
//				ResponseEntity<Product> productEntity = productClient.getProduct(items.getProductId());
//				items.setProduct(productEntity.getBody());
//				invoiceItems2.add(items);
//			}
			invoice.setItems(invoiceItems);
			return invoice;
		}
		return null;
		//return invoiceRepository.findById(id).orElse(null);
	}
}
