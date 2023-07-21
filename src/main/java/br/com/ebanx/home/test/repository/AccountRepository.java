package br.com.ebanx.home.test.repository;

import br.com.ebanx.home.test.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    AccountEntity findByAccountCode(Integer accountCode);
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE account", nativeQuery = true)
    void truncateTable();

}
