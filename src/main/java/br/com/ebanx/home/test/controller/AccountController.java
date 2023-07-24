package br.com.ebanx.home.test.controller;

import br.com.ebanx.home.test.configuration.JsonManipulator;
import br.com.ebanx.home.test.dto.BalanceDto;
import br.com.ebanx.home.test.dto.DtoInterface;
import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.service.Event;
import br.com.ebanx.home.test.service.impl.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {
    private Map<String, Event> eventMap;
    private Map<String, DtoInterface> eventDtoMap;
    private final ObjectMapper objectMapper;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    AccountService accountService;

    @Autowired
    public AccountController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/balance")
    public ResponseEntity balance (@RequestParam(name = "account_id") Integer accountCode){
        AccountEntity account = accountService.balance(accountCode);
        Optional<AccountEntity> accountOptional = Optional.ofNullable(account);
        if(accountOptional.isPresent()) {
            return accountOptional.map(data -> ResponseEntity.status(HttpStatus.OK).body(new BalanceDto(data).balance)).orElse(ResponseEntity.notFound().build());
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
    }
    @PostMapping(value = "/event", produces = "application/json")
    public ResponseEntity<String> event(@RequestBody @Valid EventDto eventDto) throws Exception {
        Map<String, Event> eventMap = accountService.eventMap();
        Map<String, DtoInterface> eventDtoMap = accountService.eventDtoMap();
        String type = eventDto.type();
        Event event = eventMap.get(type);
        DtoInterface dtoOut = eventDtoMap.get(type);

        List<AccountEntity> account = event.action(eventDto);
        Optional<List<AccountEntity>> accountOptional = Optional.ofNullable(account);
        JsonManipulator jsonManipulator = new JsonManipulator();
        if (accountOptional.isPresent()) {
            String jsonString = objectMapper.writeValueAsString(dtoOut.factory(accountOptional.get()));
            jsonString = jsonManipulator.addSpacesToJSON(jsonString);
            return ResponseEntity.status(HttpStatus.CREATED).body(jsonString);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("0");

    }

}
