package br.com.ebanx.home.test.service.impl;

import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.service.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class Withdraw implements Event {
    @Override
    public AccountEntity action(Integer accountCode, BigDecimal amount) {
        return null;
    }
}
