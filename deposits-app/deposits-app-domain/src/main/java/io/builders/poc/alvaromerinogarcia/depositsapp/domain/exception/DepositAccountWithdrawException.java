package io.builders.poc.alvaromerinogarcia.depositsapp.domain.exception;

public class DepositAccountWithdrawException extends RuntimeException {

    public DepositAccountWithdrawException(String msg) {
        super(String.format("Withdraw exception while tried to withdraw: %s", msg));
    }

}
