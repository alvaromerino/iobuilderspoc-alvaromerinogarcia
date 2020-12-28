package io.builders.poc.alvaromerinogarcia.depositsapp.blockchainconnector.adapter;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ManagedTransaction;
import org.web3j.utils.Numeric;

import io.builders.poc.alvaromerinogarcia.depositsapp.blockchainconnector.contracts.DepositAccount;
import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountBlockchainPort;


public class DepositAccountsBlockchainConnector implements DepositAccountBlockchainPort {

	private static Web3j web3jInstance;
	 
	public static Web3j getWeb3jInstance() {
	 
		if (web3jInstance == null) {
			web3jInstance = Web3j.build(new HttpService("http://127.0.0.1:7545"));	
		}
		
	 	return web3jInstance;
	}
	
	@Override
	public String deployContract(String privateKeyOwner) {
		Credentials credentials = Credentials.create(privateKeyOwner);

		try {
			DepositAccount depositAccountSmartContract = DepositAccount.deploy(
			        getWeb3jInstance(),
			        credentials,
			        ManagedTransaction.GAS_PRICE,
			        DepositAccount.GAS_LIMIT
			).send();
			
			return depositAccountSmartContract.getContractAddress();

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BigInteger getBalance(String address) {
		EthGetBalance ethGetBalance;
		try {
			ethGetBalance = getWeb3jInstance()
					.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
		} catch (IOException e) {
			return null;
		}

		return ethGetBalance.getBalance();
	}

	@Override
	public String deposit(String privateKeySender, String fromAddress, String toAddress, BigInteger weiAmountToDeposit) {
		 
		Credentials credentialsSender = Credentials.create(privateKeySender);
		
		EthGetTransactionCount ethGetTransactionCount;
		try {
			ethGetTransactionCount = getWeb3jInstance()
				.ethGetTransactionCount(fromAddress, DefaultBlockParameterName.LATEST)
				.sendAsync()
				.get();
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
        
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
            nonce,
            ManagedTransaction.GAS_PRICE,
            DepositAccount.GAS_LIMIT,
            toAddress,
            weiAmountToDeposit
        );
        
	    byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentialsSender);
        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction;
		try {
			ethSendTransaction = getWeb3jInstance().ethSendRawTransaction(hexValue).send();
		} catch (IOException e) {
			return null;
		}

        return ethSendTransaction.getResult();
	}

	@Override
	public String withdraw(String smartContractAddress, String privateKeyOwner, BigInteger weiAmountToWithdraw) {
		// TO-DO: Check balance before withdraw

		Credentials credentials = Credentials.create(privateKeyOwner);
		
		DepositAccount depositAccountSmartContract = DepositAccount.load(
	          smartContractAddress,
	          getWeb3jInstance(),
	          credentials,
	          ManagedTransaction.GAS_PRICE,
	          DepositAccount.GAS_LIMIT
        );
		
		TransactionReceipt withdraw;
		try {
			withdraw = depositAccountSmartContract.withdraw(
					weiAmountToWithdraw,
			        ManagedTransaction.GAS_PRICE
			).send();
		} catch (Exception e) {
			return null;
		}
        
		return withdraw.getTransactionHash();
	}

	/*
	private Set<DepositAccountEntity> depositAccounts = new HashSet<DepositAccountEntity>();

	@Override
	public DepositAccountDto createDepositAccount(DepositAccountCreateDto createDto) {
		DepositAccountDto dto = null;
		try {
			dto = getDepositAccountByName(createDto.getDepositName());
			return null;
		}
		catch(DepositAccountNotFoundException danfe) {
			depositAccounts.add(getDepositAccountEntity(createDto));
			dto.setDepositName(createDto.getDepositName());
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

	private DepositAccountDto getDepositAccountDto(DepositAccountEntity entity) {
		if (entity == null)
			return null;
		
		return new DepositAccountDto(entity.getName(), null);
	}
	
	private DepositAccountEntity getDepositAccountEntity(DepositAccountCreateDto dto) {
		if (dto == null)
			return null;
		
		return new DepositAccountEntity(dto.getDepositName(), dto.getPrivateKeyOwner(), dto.getPinNumber());
	}
	*/

	
}
