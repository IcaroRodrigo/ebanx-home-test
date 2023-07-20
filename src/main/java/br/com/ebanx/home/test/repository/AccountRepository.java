package br.com.ebanx.home.test.repository;

import br.com.ebanx.home.test.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    AccountEntity findByAccountCode(Integer accountCode);
}
