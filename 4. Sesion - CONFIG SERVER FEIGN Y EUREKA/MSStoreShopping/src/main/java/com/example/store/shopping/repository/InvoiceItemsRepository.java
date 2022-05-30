package com.example.store.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.store.shopping.bean.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem, Long> {

}
