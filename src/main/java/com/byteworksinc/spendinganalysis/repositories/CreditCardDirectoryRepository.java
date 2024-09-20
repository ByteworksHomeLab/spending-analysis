package com.byteworksinc.spendinganalysis.repositories;


import com.byteworksinc.spendinganalysis.entities.CreditCardDirectory;
import com.byteworksinc.spendinganalysis.entities.TransactionCategory;
import com.byteworksinc.spendinganalysis.models.CreditCard;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CreditCardDirectoryRepository extends ListCrudRepository<CreditCardDirectory, String> {

    CreditCardDirectory findByCreditCard(@Param("creditCard") CreditCard creditCard);
}
