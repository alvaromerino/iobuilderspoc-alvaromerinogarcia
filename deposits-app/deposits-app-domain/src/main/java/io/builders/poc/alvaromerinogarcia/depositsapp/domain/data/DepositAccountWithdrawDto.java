package io.builders.poc.alvaromerinogarcia.depositsapp.domain.data;

import java.math.BigInteger;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepositAccountWithdrawDto {

	@NotBlank
	private String depositName;

	@NotNull
	@Min(1000)
	@Max(9999)
	private Integer pinNumber;
	
	@NotNull
	private BigInteger weiAmount;

	public DepositAccountWithdrawDto() {
		super();
	}

	public DepositAccountWithdrawDto(@NotBlank String depositName, @NotNull @Min(1000) @Max(9999) Integer pinNumber,
			@NotNull BigInteger weiAmount) {
		super();
		this.depositName = depositName;
		this.pinNumber = pinNumber;
		this.weiAmount = weiAmount;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	public Integer getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(Integer pinNumber) {
		this.pinNumber = pinNumber;
	}

	public BigInteger getWeiAmount() {
		return weiAmount;
	}

	public void setWeiAmount(BigInteger weiAmount) {
		this.weiAmount = weiAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depositName == null) ? 0 : depositName.hashCode());
		result = prime * result + ((pinNumber == null) ? 0 : pinNumber.hashCode());
		result = prime * result + ((weiAmount == null) ? 0 : weiAmount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepositAccountWithdrawDto other = (DepositAccountWithdrawDto) obj;
		if (depositName == null) {
			if (other.depositName != null)
				return false;
		} else if (!depositName.equals(other.depositName))
			return false;
		if (pinNumber == null) {
			if (other.pinNumber != null)
				return false;
		} else if (!pinNumber.equals(other.pinNumber))
			return false;
		if (weiAmount == null) {
			if (other.weiAmount != null)
				return false;
		} else if (!weiAmount.equals(other.weiAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DepositAccountWithdrawDto [depositName=" + depositName + ", pinNumber=" + pinNumber + ", weiAmount="
				+ weiAmount + "]";
	}
	
}
