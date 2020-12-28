package io.builders.poc.alvaromerinogarcia.depositsapp.domain.data;

import java.math.BigInteger;

public class DepositAccountDto {

	private String depositName;
	private String contractAddress;
	private BigInteger weiAmount;
	
	public DepositAccountDto() {
		super();
	}

	public DepositAccountDto(String depositName, String contractAddress, BigInteger weiAmount) {
		super();
		this.depositName = depositName;
		this.contractAddress = contractAddress;
		this.weiAmount = weiAmount;
	}

	public String getDepositName() {
		return depositName;
	}

	public void setDepositName(String depositName) {
		this.depositName = depositName;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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
		result = prime * result + ((contractAddress == null) ? 0 : contractAddress.hashCode());
		result = prime * result + ((depositName == null) ? 0 : depositName.hashCode());
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
		DepositAccountDto other = (DepositAccountDto) obj;
		if (contractAddress == null) {
			if (other.contractAddress != null)
				return false;
		} else if (!contractAddress.equals(other.contractAddress))
			return false;
		if (depositName == null) {
			if (other.depositName != null)
				return false;
		} else if (!depositName.equals(other.depositName))
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
		return "DepositAccountDto [depositName=" + depositName + ", contractAddress=" + contractAddress + ", weiAmount="
				+ weiAmount + "]";
	}

}
