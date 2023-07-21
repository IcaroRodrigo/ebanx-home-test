package br.com.ebanx.home.test.controller;

import br.com.ebanx.home.test.dto.BalanceDto;
import br.com.ebanx.home.test.dto.DepositDto;
import br.com.ebanx.home.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetController {

    @Autowired
    AccountRepository accountRepository;
    @PostMapping("/reset")
    public ResponseEntity reset(){
        accountRepository.truncateTable();
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }
}
