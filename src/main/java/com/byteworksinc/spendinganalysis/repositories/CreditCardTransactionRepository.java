package com.byteworksinc.spendinganalysis.repositories;

import com.byteworksinc.spendinganalysis.entities.CreditCardTransaction;
import com.byteworksinc.spendinganalysis.models.Category;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface CreditCardTransactionRepository extends ListCrudRepository<CreditCardTransaction, String> {

    @Modifying
    @Query("UPDATE public.credit_card_transaction SET category = :category WHERE id = :id ")
    void updateCategory(@Param("id") String id, @Param("category") Category category);

}
