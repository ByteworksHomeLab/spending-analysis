package com.byteworksinc.spendinganalysis.repositories;

import com.byteworksinc.spendinganalysis.CardTransactionTestBuilder;
import com.byteworksinc.spendinganalysis.entities.CreditCardTransaction;
import com.byteworksinc.spendinganalysis.entities.TransactionCategory;
import com.byteworksinc.spendinganalysis.models.Category;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreditCardTransactionRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @Autowired
    private CreditCardTransactionRepository creditCardTransactionRepository;

    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }


    @Test
    public void testInsertTransaction() {
        creditCardTransactionRepository.deleteAll();
        CreditCardTransaction cardTransaction = CardTransactionTestBuilder.build();
        CreditCardTransaction saved = creditCardTransactionRepository.save(cardTransaction);
        assertNotNull(saved, "Expected a cardTransactionTestBuilder but found none.");
        Optional<CreditCardTransaction> result = creditCardTransactionRepository.findById(saved.id());
        assertTrue(result.isPresent(), "Expected to find a CreditCardTransaction by ID. but found none.");
        assertEquals(result.get().statementYear(), cardTransaction.statementYear());
        assertEquals(result.get().statementMonth(), cardTransaction.statementMonth());
        assertEquals(result.get().chargeCard(), cardTransaction.chargeCard());
        assertEquals(result.get().description(), cardTransaction.description());
    }

    @Test
    public void testUpdateCategory() {
        creditCardTransactionRepository.deleteAll();
        CreditCardTransaction cardTransaction = CardTransactionTestBuilder.build();
        CreditCardTransaction saved = creditCardTransactionRepository.save(cardTransaction);
        assertNotNull(saved, "Expected a cardTransactionTestBuilder but found none.");

        TransactionCategory transactionCategory = new TransactionCategory("006e0d8d-545e-4f0c-9e0b-693fef975yt3", "Hats", "Hat purchases");
        TransactionCategory groceries = transactionCategoryRepository.save(transactionCategory);
        creditCardTransactionRepository.updateCategory(saved.id(), groceries.id());
        Optional<CreditCardTransaction> result = creditCardTransactionRepository.findById(saved.id());
        assertTrue(result.isPresent(), "Expected to find a CreditCardTransaction by ID. but found none.");
        assertEquals(groceries.id(), result.get().transactionCategoryId());
    }

}
