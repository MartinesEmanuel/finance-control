package com.emanuel.finance_control.controller;

import com.emanuel.finance_control.dto.BalanceResponse;
import com.emanuel.finance_control.model.Transaction;
import com.emanuel.finance_control.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public List<Transaction> listar() {
        return service.listarTodas();
    }

    @GetMapping("/balance")
    public BalanceResponse buscarSaldo() {
        return new BalanceResponse(service.calcularSaldo());
    }

    @GetMapping("/balance/period")
    public BalanceResponse buscarSaldoPorPeriodo(@RequestParam String start, @RequestParam String end) {
        return new BalanceResponse(service.calcularSaldoPorPeriodo(LocalDate.parse(start), LocalDate.parse(end)));
    }

    @GetMapping("/{id}")
    public Transaction buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Transaction criar(@RequestBody Transaction transaction) {
        return service.criar(transaction);
    }

    @PutMapping("/{id}")
    public Transaction atualizar(@PathVariable Long id, @RequestBody Transaction transaction) {
        return service.atualizar(id, transaction);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @GetMapping("/period")
    public List<Transaction> listarPorPeriodo(@RequestParam String start, @RequestParam String end) {
        return service.listarPorPeriodo(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/simplePeriod")
    public List<Transaction> listarPorPeriodoSimples(@RequestParam int month, @RequestParam int year) {
        return service.listarPorPeriodoSimples(month,year);
    }

    @GetMapping("/category")
    public List<Transaction> listarPorCategoria(@RequestParam String category) {
        return service.listarPorCategoria(category);
    }


}
