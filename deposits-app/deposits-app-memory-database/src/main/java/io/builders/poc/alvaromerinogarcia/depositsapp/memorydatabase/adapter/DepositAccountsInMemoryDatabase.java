package io.builders.poc.alvaromerinogarcia.depositsapp.memorydatabase.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.exception.DepositAccountNotFoundException;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountPersistencePort;
import io.builders.poc.alvaromerinogarcia.depositsapp.memorydatabase.model.DepositAccountEntity;


public class DepositAccountsInMemoryDatabase implements DepositAccountPersistencePort {

	private Set<DepositAccountEntity> depositAccounts = new HashSet<DepositAccountEntity>();

	@Override
	public DepositAccountDto createDepositAccount(DepositAccountCreateDto createDto) {
		DepositAccountDto dto = new DepositAccountDto();
		try {
			dto = getDepositAccountByName(createDto.getDepositName());
			return null;
		}
		catch(DepositAccountNotFoundException danfe) {
			depositAccounts.add(getDepositAccountEntity(createDto));
			dto.setDepositName(createDto.getDepositName());
			dto.setContractAddress(createDto.getContractAddress());
			dto.setWeiAmount(null);
			return dto;
		}
	}

	@Override
	public List<DepositAccountDto> getAllDepositAccounts() {
		List<DepositAccountDto> accounts = depositAccounts
				.stream()
				.map(item -> getDepositAccountDto(item))
				.collect(Collectors.toList());
		
		return accounts;
	}

	@Override
	public DepositAccountDto getDepositAccountByName(String name) throws DepositAccountNotFoundException {
		Optional<DepositAccountEntity> optDepositAccount = depositAccounts
				.stream()
				.filter(item -> item.getName().equals(name))
				.findFirst();
		
		if (!optDepositAccount.isPresent()) {
			throw new DepositAccountNotFoundException(name);
		}
		
		return getDepositAccountDto(optDepositAccount.get());
	}
	
	@Override
	public DepositAccountCreateDto getDepositAccountFullByName(String name) throws DepositAccountNotFoundException {
		Optional<DepositAccountEntity> optDepositAccount = depositAccounts
				.stream()
				.filter(item -> item.getName().equals(name))
				.findFirst();
		
		if (!optDepositAccount.isPresent()) {
			throw new DepositAccountNotFoundException(name);
		}
		
		DepositAccountEntity entity = optDepositAccount.get();
		return new DepositAccountCreateDto(
			entity.getName(), entity.getContractAddress(), entity.getPrivateKeyOwner(), entity.getPin()
		);
	}

	private DepositAccountDto getDepositAccountDto(DepositAccountEntity entity) {
		if (entity == null)
			return null;
		
		return new DepositAccountDto(entity.getName(), entity.getContractAddress(), null);
	}
	
	private DepositAccountCreateDto getDepositAccountCreateDto(DepositAccountEntity entity) {
		if (entity == null)
			return null;
		
		return new DepositAccountCreateDto(
				entity.getName(), entity.getContractAddress(), entity.getPrivateKeyOwner(), entity.getPin()
		);
	}
	
	private DepositAccountEntity getDepositAccountEntity(DepositAccountCreateDto dto) {
		if (dto == null)
			return null;
		
		return new DepositAccountEntity(dto.getDepositName(), dto.getContractAddress(), dto.getPrivateKeyOwner(), dto.getPinNumber());
	}

	
}
