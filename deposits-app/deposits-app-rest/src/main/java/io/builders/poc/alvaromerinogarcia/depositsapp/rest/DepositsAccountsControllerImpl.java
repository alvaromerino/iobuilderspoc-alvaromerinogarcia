package io.builders.poc.alvaromerinogarcia.depositsapp.rest;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.web3j.crypto.Credentials;

import io.builders.poc.alvaromerinogarcia.depositsapp.core.service.DepositAccountBlockchainService;
import io.builders.poc.alvaromerinogarcia.depositsapp.core.service.DepositAccountService;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDepositDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountWithdrawDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.exception.DepositAccountNotFoundException;

@RestController("/")
public class DepositsAccountsControllerImpl implements DepositsAccountsController {

	private final DepositAccountService accountService;
	private final DepositAccountBlockchainService blockchainService;

    public DepositsAccountsControllerImpl(
    		DepositAccountService accountService,
    		DepositAccountBlockchainService blockchainService) {

        this.accountService = accountService;
        this.blockchainService = blockchainService;
    }
	
	@Override
	public ResponseEntity<DepositAccountDto> createDepositAccount(@Valid DepositAccountCreateDto dto) {
		boolean shouldDeployContract = dto.getContractAddress() == null;
		if (shouldDeployContract) {
			String contractAddress = blockchainService.deployContract(dto.getPrivateKeyOwner());
			dto.setContractAddress(contractAddress);
		}
		
		if (dto.getContractAddress() == null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Se ha producido algun error", null);
		}
		else {
			DepositAccountDto outDto = accountService.createDepositAccount(dto);
			return new ResponseEntity<>(outDto, HttpStatus.OK);	
		}
		
	}

	@Override
	public ResponseEntity<List<DepositAccountDto>> getAllDepositAccounts() {

		List<DepositAccountDto> accounts = accountService.getAllDepositAccounts();
		accounts.stream().forEach(new Consumer<DepositAccountDto>() {

			@Override
			public void accept(DepositAccountDto t) {
				t.setWeiAmount(blockchainService.getBalance(t.getContractAddress()));
			}
		});
		
		return new ResponseEntity<>(accounts, HttpStatus.OK);	
	}

	@Override
	public ResponseEntity<DepositAccountDto> getDepositAccountByName(String name) {

		try {
			DepositAccountDto account = accountService.getDepositAccountByName(name);
			return new ResponseEntity<>(account, HttpStatus.OK);
		}
		catch(DepositAccountNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<DepositAccountDto> depositAmount(@Valid DepositAccountDepositDto dto) {

		DepositAccountDto accountDto = null;
		
		try {
			accountDto = accountService.getDepositAccountByName(dto.getDepositName());
			String toAddress = accountDto.getContractAddress();
			String privateKeySender = dto.getPrivateKeySender();
			Credentials c = Credentials.create(privateKeySender);
			String fromAddress = c.getAddress();
			blockchainService.deposit(privateKeySender, fromAddress, toAddress, dto.getWeiAmount()); 
		}
		catch(DepositAccountNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Se ha producido algun error", null);
		}
		
		accountDto.setWeiAmount(blockchainService.getBalance(accountDto.getContractAddress()));
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DepositAccountDto> withdrawAmount(@Valid DepositAccountWithdrawDto dto) {

		DepositAccountCreateDto accountDto = null;
		try {
			accountDto = accountService.getDepositAccountFullByName(dto.getDepositName());
		}
		catch(DepositAccountNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Se ha producido algun error", null);
		}
		
		
		if (!dto.getPinNumber().equals(accountDto.getPinNumber())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Pin incorrecto", null);
		}
		
		blockchainService.withdraw(
				accountDto.getContractAddress(),
				accountDto.getPrivateKeyOwner(),
				dto.getWeiAmount());

		
		DepositAccountDto outDto = new DepositAccountDto();
		outDto.setDepositName(accountDto.getDepositName());
		outDto.setContractAddress(accountDto.getContractAddress());
		outDto.setWeiAmount(blockchainService.getBalance(accountDto.getContractAddress()));
		return new ResponseEntity<>(outDto, HttpStatus.OK);
	}
	
}
