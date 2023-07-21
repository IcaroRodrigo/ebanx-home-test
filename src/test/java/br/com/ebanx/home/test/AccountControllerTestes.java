package br.com.ebanx.home.test;

import br.com.ebanx.home.test.entity.AccountEntity;
import br.com.ebanx.home.test.service.impl.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTestes {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    @Test
    public void shouldReturnAccountBalance() throws Exception {
        Mockito.when(accountService.balance(100)).thenReturn(new AccountEntity(UUID.randomUUID(),100, new BigDecimal(100)));
        this.mockMvc.perform(get("/balance?account_id=100")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("100"));
    }

    @Test
    public void shouldReturnStatus400InBalance() throws Exception {
        Mockito.when(accountService.balance(100)).thenReturn(new AccountEntity(UUID.randomUUID(),100, new BigDecimal(100)));
        this.mockMvc.perform(get("/balance")).andDo(print()).andExpect(status().isBadRequest());
    }

}
