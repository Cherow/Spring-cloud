package com.kcbgroup.main.controller;

import com.kcbgroup.main.model.CurrencyConversion;
import com.kcbgroup.main.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @ AUTHOR MKOECH
 **/
@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeProxy proxy;
    @Autowired
    private  Environment environment;


    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity
    ){
        CurrencyConversion conversion = new CurrencyConversion();
      String port = environment.getProperty("local.server.port");
      conversion.setEnvironment(port);
        Map<String,String> map = new HashMap<>();
        map.put("from",from);
        map.put("to",to);

//      ResponseEntity<String> response =template.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",String.class,map);
//
//      response.getBody();
//        System.out.println(response.getBody());
        RestTemplate template = new RestTemplate();

        ResponseEntity<CurrencyConversion> response = template.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class, map);
        response.getBody();

        conversion.setFrom(Objects.requireNonNull(response.getBody()).getFrom());
        conversion.setId(response.getBody().getId());
        conversion.setEnvironment(response.getBody().getEnvironment());
        conversion.setTo(response.getBody().getTo());
        conversion.setConversionMultiple(response.getBody().getConversionMultiple());
        conversion.setQuantity(quantity);
        conversion.setTotalCalculatedAmount(quantity.multiply(response.getBody().getConversionMultiple()));


        return conversion;

    }








    @GetMapping("-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable BigDecimal quantity
    ){



        CurrencyConversion conversion = proxy.retriveExchangeValue(from, to);
        return new CurrencyConversion(conversion.getId(),
                from,to,quantity,conversion.getConversionMultiple(),
                quantity.multiply(conversion.getConversionMultiple()),
                conversion.getEnvironment());


    }
}
