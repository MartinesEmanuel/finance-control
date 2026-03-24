package com.emanuel.finance_control.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
// imports aqui (você vai precisar adicionar conforme usa)

@Entity // Anotação para indicar que esta classe é uma entidade JPA, que vai virar tabela no banco de dados
@Table(name = "transactions") // Anotação para definir o nome da tabela no banco de dados (opcional, mas recomendado)
public class Transaction {

    // 1. CAMPOS (Atributos)
    @Id // Anotação para indicar que este campo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Anotação para indicar que o valor do ID será gerado automaticamente pelo banco de dados
    private Long id; // Identificador único da transação

    @NotNull
    @NotBlank
    private String description; // Descrição da transação

    @NotNull
    @Positive
    private Double amount; // Valor da transação

    @NotNull
    @Enumerated(EnumType.STRING) // Anotação para indicar que o tipo da transação será armazenado como string no banco de dados
    private TransactionType type;

    @NotNull
    private LocalDate date; // Data da transação

    private String category; // Categoria da transação (opcional, pode ser uma string ou uma entidade separada)

    private String createdby; // Usuário que criou a transação (opcional, pode ser uma string ou uma entidade separada)

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt; // Data de criação do registro (preenchida automaticamente)

    // 2. CONSTRUTORES
    public Transaction() {
        // Construtor padrão necessário para JPA
    }

    public Transaction(String description, Double amount, TransactionType type, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    // 3. MÉTODO DO CICLO DE VIDA JPA
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        if (date == null) {
            date = LocalDate.now();
        }
    }

    // 4. GETTERS E SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}

