package io.builders.poc.alvaromerinogarcia.depositsapp.core.service;

import java.util.List;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;

public interface DepositAccountService {
	
	DepositAccountDto createDepositAccount(DepositAccountCreateDto createDto);
    List<DepositAccountDto> getAllDepositAccounts();
    DepositAccountDto getDepositAccountByName(String name);
    DepositAccountCreateDto getDepositAccountFullByName(String name);

}
