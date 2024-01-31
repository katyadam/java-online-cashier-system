package com.example.onlinecashiersystem.service.api;

import com.example.onlinecashiersystem.api.TransactionDto;
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
    private final UserService userService;

    @Autowired
    public TransactionService(
            TransactionRepository transactionRepository,
            UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
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
        newTransaction.setRecord(transactionDto.record());
        newTransaction.setUser(userService.findById(transactionDto.userId()));

        transactionRepository.save(newTransaction);
        return newTransaction;
    }

    @Transactional
    public Transaction updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction toUpdate = findById(id);
        toUpdate.setRecord(transactionDto.record());

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
