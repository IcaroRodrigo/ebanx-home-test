package br.com.ebanx.home.test.dto.imp;

import br.com.ebanx.home.test.dto.BalanceDto;
import br.com.ebanx.home.test.dto.DtoInterface;
import br.com.ebanx.home.test.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepositDto  implements DtoInterface {
   public BalanceDto destination;

    @Override
    public DtoInterface factory(List<AccountEntity> accountEntityList) {
        this.destination = new BalanceDto(accountEntityList.get(0));
        return this;
    }
}
