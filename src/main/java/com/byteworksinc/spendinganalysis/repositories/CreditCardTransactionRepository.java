package com.byteworksinc.spendinganalysis.repositories;

import com.byteworksinc.spendinganalysis.entities.CreditCardTransaction;
import org.springframework.data.repository.ListCrudRepository;

public interface CreditCardTransactionRepository extends ListCrudRepository<CreditCardTransaction, String> {

}
