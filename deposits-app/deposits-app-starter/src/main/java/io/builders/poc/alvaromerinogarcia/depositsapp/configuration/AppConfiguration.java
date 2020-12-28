package io.builders.poc.alvaromerinogarcia.depositsapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.builders.poc.alvaromerinogarcia.depositsapp.blockchainconnector.adapter.DepositAccountsBlockchainConnector;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountBlockchainPort;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountPersistencePort;
import io.builders.poc.alvaromerinogarcia.depositsapp.memorydatabase.adapter.DepositAccountsInMemoryDatabase;

@Configuration
public class AppConfiguration {

    @Bean
    public DepositAccountPersistencePort depositAccountPersistencePort() {
        return new DepositAccountsInMemoryDatabase();
    }
    
    @Bean DepositAccountBlockchainPort depositAccountBlockchainPort() {
    	return new DepositAccountsBlockchainConnector();
    }

}