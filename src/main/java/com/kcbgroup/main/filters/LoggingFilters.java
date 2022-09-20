package com.kcbgroup.main.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ AUTHOR MKOECH
 **/
@Component
public class LoggingFilters  implements GlobalFilter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilters.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    logger.info("path of the request received-->{}",exchange
            .getRequest().getPath());
    logger.info("Id of the Request reciuved----->{}",exchange.getRequest().getId());



//TO:DO
        //ADDING SECURITY ON THE MICROSERVICE

        return chain.filter(exchange);
    }
}
