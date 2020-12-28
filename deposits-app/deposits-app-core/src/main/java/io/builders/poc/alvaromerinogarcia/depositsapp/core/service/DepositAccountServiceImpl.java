package io.builders.poc.alvaromerinogarcia.depositsapp.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountPersistencePort;

@Service
public class DepositAccountServiceImpl implements DepositAccountService {

	@Autowired
    private DepositAccountPersistencePort depositAccountPersistencePort;

    public DepositAccountServiceImpl() {
		super();
	}
    
	public DepositAccountServiceImpl(DepositAccountPersistencePort depositAccountPersistencePort) {
        this.depositAccountPersistencePort = depositAccountPersistencePort;
    }

	@Override
	public DepositAccountDto createDepositAccount(DepositAccountCreateDto createDto) {
		return this.depositAccountPersistencePort.createDepositAccount(createDto);
	}

	@Override
	public List<DepositAccountDto> getAllDepositAccounts() {
		return this.depositAccountPersistencePort.getAllDepositAccounts();
	}

	@Override
	public DepositAccountDto getDepositAccountByName(String name) {
		return this.depositAccountPersistencePort.getDepositAccountByName(name);
	}

	@Override
	public DepositAccountCreateDto getDepositAccountFullByName(String name) {
		return this.depositAccountPersistencePort.getDepositAccountFullByName(name);
	}

}
