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
public class TransactionCategoryRepositoryTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @Autowired
    private TransactionCategoryRepository transactionCategoryRepository;

    @Test
    public void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void testInsertTransactionCategory() {
        TransactionCategory transactionCategory = new TransactionCategory("006e0d8d-545e-4f0c-9e0b-693fef975ad8", "BodyArmor", "Safety purchases");
        TransactionCategory saved = transactionCategoryRepository.save(transactionCategory);
        assertNotNull(saved, "Expected a transactionCategory but found none.");
        Optional<TransactionCategory> result = transactionCategoryRepository.findById(saved.id());
        assertTrue(result.isPresent(), "Expected to find a TransactionCategory by ID. but found none.");
        assertEquals(result.get().category(), transactionCategory.category());
        assertEquals(result.get().description(), transactionCategory.description());
    }

    @Test
    public void testFindTransactionCategoriesByCategory() {
        TransactionCategory transactionCategory = new TransactionCategory("a24352a3-b488-4dcf-b0bc-ecc88999fb19", "Spacesuits", "Space travel");
        TransactionCategory saved = transactionCategoryRepository.save(transactionCategory);
        assertNotNull(saved, "Expected a transactionCategory but found none.");
        Optional<TransactionCategory> result = transactionCategoryRepository.findTransactionCategoriesByCategory("Spacesuits");
        assertTrue(result.isPresent(), "Expected to find a TransactionCategory by category. but found none.");
        assertEquals(result.get().category(), transactionCategory.category());
        assertEquals(result.get().description(), transactionCategory.description());
    }
}
