package com.bus.trans.controller;
import com.bus.trans.dto.TransactionDTO;
import com.bus.trans.model.Transaction;
import com.bus.trans.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Créer une nouvelle transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setClientId(transactionDTO.getClientId());
        transaction.setClientRfid(transactionDTO.getClientRfid());
        transaction.setNom(transactionDTO.getNom());
        transaction.setPrenom(transactionDTO.getPrenom());
        transaction.setMontant(transactionDTO.getMontant());
        transaction.setDateTransaction(transactionDTO.getDateTransaction());

        Transaction savedTransaction = transactionService.saveTransaction(
                transaction.getClientId(),
                transaction.getClientRfid(),
                transaction.getNom(),
                transaction.getPrenom(),
                transaction.getMontant()
        );

        return ResponseEntity.ok(savedTransaction);
    }

    // Récupérer toutes les transactions
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // Récupérer une transaction par ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.notFound().build();
    }
}
