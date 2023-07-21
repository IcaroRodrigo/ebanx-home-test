package br.com.ebanx.home.test.dto;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class TransferDto implements DtoInterface{
    public BalanceDto origin;

    @Override
    public DtoInterface factory(BalanceDto balanceDto) {
        this.origin = balanceDto;
        return this;
    }
}
