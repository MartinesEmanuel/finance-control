package com.emanuel.finance_control.service;

import com.emanuel.finance_control.model.Transaction;
import com.emanuel.finance_control.model.TransactionType;
import com.emanuel.finance_control.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public List<Transaction> listarTodas() {
        return repository.findAll();
    }

    public Optional<Transaction> buscarPorId(Long id) {
        return repository.findById(id);
    }


    public Transaction criar(Transaction transaction)
    {
        if(transaction.getAmount() == null || transaction.getType() == null) {
            throw new IllegalArgumentException("Amount must not be null");
        }

        if(transaction.getDescription() == null || transaction.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description must not be null or empty");
        }
        if(transaction.getType() == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
        return repository.save(transaction);
    }

    public Transaction atualizar(Long id, Transaction transaction) {
        transaction.setId(id);
        return repository.save(transaction);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public double calcularSaldo() {
        return repository.findAll().stream()
                .filter(transaction -> transaction.getAmount() != null && transaction.getType() != null)
                .mapToDouble(transaction -> transaction.getType() == TransactionType.INCOME
                        ? transaction.getAmount()
                        : -transaction.getAmount())
                .sum();
    }

    public List<Transaction> listarPorPeriodo(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }

    public double calcularSaldoPorPeriodo(LocalDate start, LocalDate end) {
        return repository.SumByPeriod(start, end);
    }

    public List<Transaction> listarPorPeriodoSimples(int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return repository.findByDateBetween(start, end);
    }

    public List<Transaction> listarPorCategoria(String category) {
        return repository.findByCategory(category);
    }
}


