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
//    @Autowired
     private Map<String, Event> eventMap;
//
//    @Autowired
      private Map<String, DtoInterface> eventDtoMap;

    @Autowired
    private ApplicationContext applicationContext;

//    private final Map<String, Event> eventMaps;
//    private final Map<String, DtoInterface> eventDtoMaps;

    public AccountController(){

    }

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

        eventMap = new HashMap<>();
        eventMap.put("deposit", applicationContext.getBean(Deposit.class));
        eventMap.put("withdraw", applicationContext.getBean(Withdraw.class));
        eventMap.put("transfer", applicationContext.getBean(Withdraw.class));

        eventDtoMap = new HashMap<>();
        eventDtoMap.put("deposit", applicationContext.getBean(DepositDto.class));
        eventDtoMap.put("withdraw", applicationContext.getBean(WithdrawDto.class));
        eventDtoMap.put("transfer", applicationContext.getBean(TransferDto.class));

        String type = eventDto.type();
        Event event = eventMap.get(type);
        DtoInterface dtoOut = eventDtoMap.get(type);
        Integer accountCode = (eventDto.destination() == null) ? eventDto.origin() : eventDto.destination();
        AccountEntity account = event.action(accountCode, eventDto.amount());
        Optional<AccountEntity> accountOptional = Optional.ofNullable(account);

        return accountOptional.map(data -> ResponseEntity.status(HttpStatus.CREATED).body(dtoOut.factory(new BalanceDto(data)))).orElse(ResponseEntity.notFound().build());
    }

}
