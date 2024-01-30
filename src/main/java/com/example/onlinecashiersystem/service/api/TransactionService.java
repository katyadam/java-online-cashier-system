package com.example.onlinecashiersystem.service.api;

import com.example.onlinecashiersystem.api.TransactionDto;
import com.example.onlinecashiersystem.data.model.Product;
import com.example.onlinecashiersystem.data.model.Transaction;
import com.example.onlinecashiersystem.data.repository.TransactionRepository;
import com.example.onlinecashiersystem.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductService productService;

    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository,
            ProductService productService
    ) {
        this.transactionRepository = transactionRepository;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with id: " + id + " was not found!"));
    }

    @Transactional(readOnly = true)
    public Page<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Transactional
    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDto.amount());
        newTransaction.setProduct(productService.findById(transactionDto.productId()));

        transactionRepository.save(newTransaction);
        return newTransaction;
    }

    @Transactional
    public Transaction updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction toUpdate = findById(id);
        toUpdate.setAmount(transactionDto.amount());

        transactionRepository.save(toUpdate);
        return toUpdate;
    }

    @Transactional
    public Transaction deleteTransaction(Long id) {
        Transaction toDelete = findById(id);
        transactionRepository.delete(toDelete);

        return toDelete;
    }
}
