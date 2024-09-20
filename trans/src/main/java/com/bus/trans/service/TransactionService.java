package com.bus.trans.service;
import com.bus.trans.model.Transaction;
import com.bus.trans.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Enregistrer une nouvelle transaction
    public Transaction saveTransaction(Long clientId, String clientRfid, String nom, String prenom, double montant) {
        Transaction transaction = new Transaction();
        transaction.setClientId(clientId);
        transaction.setClientRfid(clientRfid);
        transaction.setNom(nom);
        transaction.setPrenom(prenom);
        transaction.setMontant(montant);
        transaction.setDateTransaction(new Date());
        return transactionRepository.save(transaction);
    }

    // Récupérer toutes les transactions
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Récupérer une transaction par son ID
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
}
