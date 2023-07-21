package br.com.ebanx.home.test.dto.imp;

import br.com.ebanx.home.test.dto.BalanceDto;
import br.com.ebanx.home.test.dto.DtoInterface;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DepositDto  implements DtoInterface {
   public BalanceDto destination;

    @Override
    public DtoInterface factory(BalanceDto balanceDto) {
        this.destination = balanceDto;
        return this;
    }
}
