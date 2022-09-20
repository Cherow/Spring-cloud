package com.kcbgroup.main.proxy;

import com.kcbgroup.main.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ AUTHOR MKOECH
 **/

//@FeignClient(name = "currency-exchange",url = "localhost:8000")

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
     CurrencyConversion retriveExchangeValue(@PathVariable String from
            , @PathVariable String to);
}
