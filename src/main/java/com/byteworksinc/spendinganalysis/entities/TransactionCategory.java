package com.byteworksinc.spendinganalysis.entities;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("transaction_category")
public record TransactionCategory(@Id String id, @NotEmpty String category, String description, @Version Integer version) {

    public TransactionCategory(String id, @NotEmpty String category) {
        this(id, category, null, null);
    }

    public TransactionCategory(String id, @NotEmpty String category, String description) {
        this(id, category, description, null);
    }

}
