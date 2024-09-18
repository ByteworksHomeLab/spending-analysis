package com.byteworksinc.spendinganalysis.repositories;

import com.byteworksinc.spendinganalysis.CardTransactionTestBuilder;
import com.byteworksinc.spendinganalysis.entities.CreditCardTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreditCardTransactionRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }



    @Test
    @Sql({"/schema.sql"})
    public void testInsertTransaction() throws ParseException {
        CreditCardTransaction cardTransaction = CardTransactionTestBuilder.build();
        CreditCardTransaction result = creditCardTransactionRepository.save(cardTransaction);
        assertNotNull(result, "Expected a cardTransactionTestBuilder but found none.");
        assertNotNull(creditCardTransactionRepository.findById(result.id()), "Expected to find a CreditCardTransaction by ID. but found none.");
        assertEquals(result.statementYear(), cardTransaction.statementYear());
        assertEquals(result.statementMonth(), cardTransaction.statementMonth());
        assertEquals(result.chargeCard(), cardTransaction.chargeCard());
        assertEquals(result.description(), cardTransaction.description());
    }

}
