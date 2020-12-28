package io.builders.poc.alvaromerinogarcia.depositsapp.domain.port;

import java.util.List;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;

public interface DepositAccountPersistencePort {

	DepositAccountDto createDepositAccount(DepositAccountCreateDto createDto);
    List<DepositAccountDto>getAllDepositAccounts();
    DepositAccountDto getDepositAccountByName(String name);
    DepositAccountCreateDto getDepositAccountFullByName(String name);

}
