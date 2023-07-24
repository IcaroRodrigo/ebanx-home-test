package br.com.ebanx.home.test.dto.imp;

import br.com.ebanx.home.test.dto.BalanceDto;
import br.com.ebanx.home.test.dto.DtoInterface;
import br.com.ebanx.home.test.entity.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferDto implements DtoInterface {
    public BalanceDto origin;
    public BalanceDto destination;

    @Override
    public DtoInterface factory(List<AccountEntity> accountEntityList) {
        this.origin = new BalanceDto(accountEntityList.get(0));
        this.destination = new BalanceDto(accountEntityList.get(1));
        return this;
    }
}
