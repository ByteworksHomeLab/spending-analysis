package com.byteworksinc.spendinganalysis.entities;

import com.byteworksinc.spendinganalysis.models.CreditCard;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table("credit_card_transaction")
public record CreditCardTransaction(@Id String id,
                                    @NotEmpty Date transactionDate,
                                    @NotEmpty int statementYear,
                                    @NotEmpty int statementMonth,
                                    @NotEmpty CreditCard chargeCard,
                                    @NotEmpty String description,
                                    @NotEmpty BigDecimal transactionAmount,
                                    String transactionCategoryId,
                                    @Version
                                    Integer version) {

    public CreditCardTransaction(String id,
                                 @NotEmpty Date transactionDate,
                                 @NotEmpty int statementYear,
                                 @NotEmpty int statementMonth,
                                 @NotEmpty CreditCard chargeCard,
                                 @NotEmpty String description,
                                 @NotEmpty BigDecimal transactionAmount,
                                 String transactionCategoryId) {
        this(id, transactionDate, statementYear, statementMonth, chargeCard, description, transactionAmount, transactionCategoryId, null);
    }

    public CreditCardTransaction(String id,
                                 @NotEmpty Date transactionDate,
                                 @NotEmpty int statementYear,
                                 @NotEmpty int statementMonth,
                                 @NotEmpty CreditCard chargeCard,
                                 @NotEmpty String description,
                                 @NotEmpty BigDecimal transactionAmount) {
        this(id, transactionDate, statementYear, statementMonth, chargeCard, description, transactionAmount, null, null);
    }
}
