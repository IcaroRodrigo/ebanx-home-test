package br.com.ebanx.home.test.configuration;

import br.com.ebanx.home.test.dto.DepositDto;
import br.com.ebanx.home.test.dto.DtoInterface;
import br.com.ebanx.home.test.dto.TransferDto;
import br.com.ebanx.home.test.dto.WithdrawDto;
import br.com.ebanx.home.test.service.Event;
import br.com.ebanx.home.test.service.impl.Deposit;
import br.com.ebanx.home.test.service.impl.Withdraw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EventStrategyConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Map<String, Event> eventMap() {
        Map<String, Event> eventMap = new HashMap<>();
        eventMap.put("deposit", applicationContext.getBean(Deposit.class));
        eventMap.put("withdraw", applicationContext.getBean(Withdraw.class));
        eventMap.put("transfer", applicationContext.getBean(Withdraw.class));
        return eventMap;
    }

    @Bean
    public Map<String, DtoInterface> eventDtoMap() {
        Map<String, DtoInterface> eventDtoMap = new HashMap<>();
        eventDtoMap.put("deposit", applicationContext.getBean(DepositDto.class));
        eventDtoMap.put("withdraw", applicationContext.getBean(WithdrawDto.class));
        eventDtoMap.put("transfer", applicationContext.getBean(TransferDto.class));
        return eventDtoMap;
    }
}