package com.byteworksinc.spendinganalysis.repositories;


import com.byteworksinc.spendinganalysis.entities.TransactionCategory;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransactionCategoryRepository extends ListCrudRepository<TransactionCategory, String> {

    @Query("SELECT * FROM transaction_category WHERE category = :category")
    Optional<TransactionCategory> findTransactionCategoriesByCategory(@Param("category") String category);
}
