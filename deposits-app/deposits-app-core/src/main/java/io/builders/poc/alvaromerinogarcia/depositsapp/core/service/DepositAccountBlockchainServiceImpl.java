package io.builders.poc.alvaromerinogarcia.depositsapp.core.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.builders.poc.alvaromerinogarcia.depositsapp.domain.port.DepositAccountBlockchainPort;

@Service
public class DepositAccountBlockchainServiceImpl implements DepositAccountBlockchainService {

	@Autowired
    private DepositAccountBlockchainPort depositAccountBlockchainPort;
	
	public DepositAccountBlockchainServiceImpl() {
		super();
	}
	
	public DepositAccountBlockchainServiceImpl(DepositAccountBlockchainPort depositAccountBlockchainPort) {
		super();
		this.depositAccountBlockchainPort = depositAccountBlockchainPort;
	}

	@Override
	public String deployContract(String privateKeyOwner) {
		return this.depositAccountBlockchainPort.deployContract(privateKeyOwner);
	}

	@Override
	public BigInteger getBalance(String address) {
		return this.depositAccountBlockchainPort.getBalance(address);
	}

	@Override
	public String deposit(String privateKeySender, String fromAddress, String toAddress,
			BigInteger weiAmountToDeposit) {
		return this.depositAccountBlockchainPort.deposit(privateKeySender, fromAddress, toAddress, weiAmountToDeposit);
	}

	@Override
	public String withdraw(String smartContractAddress, String privateKeyOwner, BigInteger weiAmountToWithdraw) {
		return this.depositAccountBlockchainPort.withdraw(smartContractAddress, privateKeyOwner, weiAmountToWithdraw);
	}

}
