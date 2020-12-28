package io.builders.poc.alvaromerinogarcia.depositsapp.domain.exception;

public class DepositAccountNotFoundException extends RuntimeException {

    public DepositAccountNotFoundException(String name) {
        super(String.format("Deposit with name: %s not found", name));
    }

}
