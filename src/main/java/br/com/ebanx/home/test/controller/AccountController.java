package br.com.ebanx.home.test.controller;

import br.com.ebanx.home.test.dto.AccountDto;
import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.service.Event;
import br.com.ebanx.home.test.service.impl.AccountService;
import br.com.ebanx.home.test.service.impl.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {
    private final Map<String, Event> eventMap;

    @Autowired
    AccountService accountService;
    public AccountController(Map<String, Event> eventMap) {
        this.eventMap = eventMap;
    }
    @GetMapping("/{id}")
    public ResponseEntity balance (@RequestParam(name = "account_id") Integer accountCode){

        AccountEntity account = accountService.balance(accountCode);
        Optional<AccountEntity> accountOptional = Optional.ofNullable(account);

       return accountOptional.map(data -> ResponseEntity.status(HttpStatus.OK).body(new AccountDto(data))).orElse(ResponseEntity.notFound().build());

    }
    @PostMapping("/event")
    public ResponseEntity event (@RequestBody @Valid EventDto eventDto){
        String type = eventDto.type();
        Event event = eventMap.get(type);
        AccountEntity account = event.action(eventDto.destination(), eventDto.amount());

        return ResponseEntity.status(HttpStatus.CREATED).body(new AccountDto(account));
    }

}
