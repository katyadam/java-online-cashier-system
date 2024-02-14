package com.example.onlinecashiersystem.rest;

import com.example.onlinecashiersystem.api.TransactionDto;
import com.example.onlinecashiersystem.data.model.Transaction;
import com.example.onlinecashiersystem.service.api.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionRestController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionService.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.createTransaction(transactionDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, transactionDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Transaction> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionService.deleteTransaction(id));
    }
}
