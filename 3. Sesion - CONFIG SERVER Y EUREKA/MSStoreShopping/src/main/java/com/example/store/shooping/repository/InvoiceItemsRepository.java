package com.example.store.shooping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.store.shooping.bean.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
