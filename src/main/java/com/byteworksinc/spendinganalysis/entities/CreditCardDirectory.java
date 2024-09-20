package com.byteworksinc.spendinganalysis.entities;

import com.byteworksinc.spendinganalysis.models.CreditCard;
import com.byteworksinc.spendinganalysis.models.FileExtension;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record CreditCardDirectory(@Id String id, @NotEmpty CreditCard creditCard, @NotEmpty String classpath, @NotEmpty FileExtension fileExtension, @NotEmpty String namePattern, @Version Integer version) {

    public CreditCardDirectory(String id, @NotEmpty CreditCard creditCard, @NotEmpty String classpath, @NotEmpty FileExtension fileExtension, @NotEmpty String namePattern) {
        this(id, creditCard, classpath, fileExtension, namePattern, null);
    }
}

