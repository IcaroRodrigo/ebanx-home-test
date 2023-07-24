package br.com.ebanx.home.test.service;

import br.com.ebanx.home.test.dto.EventDto;
import br.com.ebanx.home.test.entity.AccountEntity;

import java.util.List;

public interface Event {
    public List<AccountEntity> action(EventDto eventDto);
}
