package com.byteworksinc.spendinganalysis.repositories;

import com.byteworksinc.spendinganalysis.entities.CreditCardDirectory;
import com.byteworksinc.spendinganalysis.models.CreditCard;
import com.byteworksinc.spendinganalysis.models.FileExtension;
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
public class CreditCardDirectoryRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @Autowired
    private CreditCardDirectoryRepository creditCardDirectoryRepository;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    public void testInsertCreditCardDirectory() {
        UUID uuid = UUID.randomUUID();
        CreditCardDirectory creditCardDirectory = new CreditCardDirectory(uuid.toString(), CreditCard.Discover, "data/Discovers", FileExtension.CSV, "yyyy-MM");
        CreditCardDirectory saved = creditCardDirectoryRepository.save(creditCardDirectory);
        assertNotNull(saved, "Expected a creditCardDirectory but found none.");
        Optional<CreditCardDirectory> result = creditCardDirectoryRepository.findById(saved.id());
        assertTrue(result.isPresent(), "Expected to find a CreditCardDirectory by ID. but found none.");
        assertEquals(result.get().creditCard(), creditCardDirectory.creditCard());
        assertEquals(result.get().classpath(), creditCardDirectory.classpath());
        assertEquals(result.get().fileExtension(), creditCardDirectory.fileExtension());
        assertEquals(result.get().namePattern(), creditCardDirectory.namePattern());
    }

    @Test
    public void testFindByCreditCard() {
        CreditCardDirectory creditCardDirectory = creditCardDirectoryRepository.findByCreditCard(CreditCard.AppleCard);
        assertNotNull(creditCardDirectory, "Expected to find a CreditCardDirectory by creditCard. but found none.");
    }

}
