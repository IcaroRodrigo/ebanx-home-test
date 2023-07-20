package br.com.ebanx.home.test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Table(name = "account")
@Entity(name = "AccountEntity")
@Getter
@Setter
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private Integer accountCode;
    @Column(nullable = false)
    private BigDecimal amount;
}
