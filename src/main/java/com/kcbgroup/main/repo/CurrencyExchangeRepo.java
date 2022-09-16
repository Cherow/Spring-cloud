package com.kcbgroup.main.repo;

import com.kcbgroup.main.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ AUTHOR MKOECH
 **/
@Repository
//entity to manage
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange,Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
