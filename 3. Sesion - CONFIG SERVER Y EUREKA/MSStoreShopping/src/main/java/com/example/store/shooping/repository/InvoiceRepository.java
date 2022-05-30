package com.example.store.shooping.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.store.shooping.bean.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	public List<Invoice> findByCustomerId(Long customerId);

	public Invoice findByNumberInvoice(String numberInvoice);
}
