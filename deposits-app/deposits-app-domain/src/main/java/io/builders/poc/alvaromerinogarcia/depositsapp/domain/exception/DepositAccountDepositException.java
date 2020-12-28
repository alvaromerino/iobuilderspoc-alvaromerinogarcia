package io.builders.poc.alvaromerinogarcia.depositsapp.domain.exception;

public class DepositAccountDepositException extends RuntimeException {

    public DepositAccountDepositException(String msg) {
        super(String.format("Deposit exception while tried to deposit: %s", msg));
    }
	
}
