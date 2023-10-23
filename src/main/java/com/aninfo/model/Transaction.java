package com.aninfo.model;

import com.aninfo.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cbu")
    private Account account;

    private Double amount;

    private TransactionType type;

    @JsonIgnore
    public Account getAccount() {
        return account;
    }

    @JsonProperty
    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction(Account account, Double amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
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
}
