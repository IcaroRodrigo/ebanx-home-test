package br.com.ebanx.home.test.service;

import br.com.ebanx.home.test.entity.AccountEntity;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface Event {
    public AccountEntity action(Integer accountCode, BigDecimal amount);
}
