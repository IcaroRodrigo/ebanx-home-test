package br.com.ebanx.home.test.dto;

import br.com.ebanx.home.test.entity.AccountEntity;

import java.util.List;

public interface DtoInterface {
    public DtoInterface factory(List<AccountEntity> accountEntityList);
}
