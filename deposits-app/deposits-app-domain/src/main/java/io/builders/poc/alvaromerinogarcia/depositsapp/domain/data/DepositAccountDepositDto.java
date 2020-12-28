package io.builders.poc.alvaromerinogarcia.depositsapp.domain.data;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepositAccountDepositDto {

	@NotBlank
	private String depositName;

	@NotBlank
	private String privateKeySender;
	
	@NotNull
	private BigInteger weiAmount;

	public DepositAccountDepositDto() {
		super();
	}

	public DepositAccountDepositDto(@NotBlank String depositName, @NotBlank String privateKeySender,
			@NotNull BigInteger weiAmount) {
		super();
		this.depositName = depositName;
		this.privateKeySender = privateKeySender;
		this.weiAmount = weiAmount;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	public String getPrivateKeySender() {
		return privateKeySender;
	}

	public void setPrivateKeySender(String privateKeySender) {
		this.privateKeySender = privateKeySender;
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
		result = prime * result + ((privateKeySender == null) ? 0 : privateKeySender.hashCode());
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
		DepositAccountDepositDto other = (DepositAccountDepositDto) obj;
		if (depositName == null) {
			if (other.depositName != null)
				return false;
		} else if (!depositName.equals(other.depositName))
			return false;
		if (privateKeySender == null) {
			if (other.privateKeySender != null)
				return false;
		} else if (!privateKeySender.equals(other.privateKeySender))
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
		return "DepositAccountDepositDto [depositName=" + depositName + ", privateKeySender=" + privateKeySender
				+ ", weiAmount=" + weiAmount + "]";
	}
	
	
}
