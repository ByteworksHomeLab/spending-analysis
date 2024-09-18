package com.byteworksinc.spendinganalysis.entities;

import com.byteworksinc.spendinganalysis.models.Category;
import com.byteworksinc.spendinganalysis.models.ChargeCard;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.math.BigDecimal;
import java.util.Date;

public record CreditCardTransaction(@Id String id,
                                    @NotEmpty Date transactionDate,
                                    @NotEmpty int statementYear,
                                    @NotEmpty int statementMonth,
                                    @NotEmpty ChargeCard chargeCard,
                                    @NotEmpty String description,
                                    @NotEmpty BigDecimal transactionAmount,
                                    Category category,
                                    @Version
                                    Integer version) {
}
