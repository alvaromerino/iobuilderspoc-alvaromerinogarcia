package io.builders.poc.alvaromerinogarcia.depositsapp.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountCreateDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDepositDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountDto;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.data.DepositAccountWithdrawDto;

public interface DepositsAccountsController {
	
	@PostMapping
	ResponseEntity<DepositAccountDto> createDepositAccount(@Valid @RequestBody DepositAccountCreateDto dto);
	
	@GetMapping
    ResponseEntity<List<DepositAccountDto>> getAllDepositAccounts();
	
	@GetMapping("/{depositName}")
    ResponseEntity<DepositAccountDto> getDepositAccountByName(@PathVariable("depositName") String name);
	
	@PostMapping("/deposit")
	ResponseEntity<DepositAccountDto> depositAmount(@Valid @RequestBody DepositAccountDepositDto dto);
	
	@PostMapping("/withdraw")
	ResponseEntity<DepositAccountDto> withdrawAmount(@Valid @RequestBody DepositAccountWithdrawDto dto);
	

}
