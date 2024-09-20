package com.byteworksinc.spendinganalysis.repositories;


import com.byteworksinc.spendinganalysis.entities.TransactionCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;
import java.util.UUID;

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
        UUID uuid = UUID.randomUUID();
        TransactionCategory transactionCategory = new TransactionCategory(uuid.toString(), "BodyArmor", "Safety purchases");
        TransactionCategory saved = transactionCategoryRepository.save(transactionCategory);
        assertNotNull(saved, "Expected a transactionCategory but found none.");
        Optional<TransactionCategory> result = transactionCategoryRepository.findById(saved.id());
        assertTrue(result.isPresent(), "Expected to find a TransactionCategory by ID. but found none.");
        assertEquals(result.get().category(), transactionCategory.category());
        assertEquals(result.get().description(), transactionCategory.description());
    }

    @Test
    public void testFindTransactionCategoriesByCategory() {
        UUID uuid = UUID.randomUUID();
        TransactionCategory transactionCategory = new TransactionCategory(uuid.toString(), "Spacesuits", "Space travel");
        TransactionCategory saved = transactionCategoryRepository.save(transactionCategory);
        assertNotNull(saved, "Expected a transactionCategory but found none.");
        TransactionCategory result = transactionCategoryRepository.findByCategory("Spacesuits");
        assertNotNull(result, "Expected to find a TransactionCategory by category. but found none.");
        assertEquals(result.category(), transactionCategory.category());
        assertEquals(result.description(), transactionCategory.description());
    }
}
