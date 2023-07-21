package br.com.ebanx.home.test.controller;

import br.com.ebanx.home.test.dto.*;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.service.Event;
import br.com.ebanx.home.test.service.impl.AccountService;
import br.com.ebanx.home.test.service.impl.Deposit;
import br.com.ebanx.home.test.service.impl.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {
    private Map<String, Event> eventMap;
    private Map<String, DtoInterface> eventDtoMap;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    AccountService accountService;
    @GetMapping("/balance")
    public ResponseEntity balance (@RequestParam(name = "account_id") Integer accountCode){
        AccountEntity account = accountService.balance(accountCode);
        Optional<AccountEntity> accountOptional = Optional.ofNullable(account);
        return accountOptional.map(data -> ResponseEntity.status(HttpStatus.OK).body(new BalanceDto(data).balance)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/event")
    public ResponseEntity event (@RequestBody @Valid EventDto eventDto){

        Map<String, Event> eventMap = accountService.eventMap();
        Map<String, DtoInterface> eventDtoMap = accountService.eventDtoMap();
        String type = eventDto.type();
        Event event = eventMap.get(type);
        DtoInterface dtoOut = eventDtoMap.get(type);
        Integer accountCode = (eventDto.destination() == null) ? eventDto.origin() : eventDto.destination();
        AccountEntity account = event.action(accountCode, eventDto.amount());
        Optional<AccountEntity> accountOptional = Optional.ofNullable(account);

        return accountOptional.map(data -> ResponseEntity.status(HttpStatus.CREATED).body(dtoOut.factory(new BalanceDto(data)))).orElse(ResponseEntity.notFound().build());
    }

}
