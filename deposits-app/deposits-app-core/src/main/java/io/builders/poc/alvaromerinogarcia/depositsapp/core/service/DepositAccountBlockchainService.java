package io.builders.poc.alvaromerinogarcia.depositsapp.core.service;

import java.math.BigInteger;

public interface DepositAccountBlockchainService {

	String deployContract(String privateKeyOwner);
	BigInteger getBalance(String address);
	String deposit(String privateKeySender, String fromAddress, String toAddress, BigInteger weiAmountToDeposit);
	String withdraw(String smartContractAddress, String privateKeyOwner, BigInteger weiAmountToWithdraw);
	
}
