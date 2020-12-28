package io.builders.poc.alvaromerinogarcia.depositsapp.domain.data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepositAccountCreateDto {

	@NotBlank
	private String depositName;

	private String contractAddress;
	
	@NotBlank
	private String privateKeyOwner;
	
	@NotNull
	@Min(1000)
	@Max(9999)
	private Integer pinNumber;

	public DepositAccountCreateDto() {
		
	}

	public DepositAccountCreateDto(@NotBlank String depositName, String contractAddress,
			@NotBlank String privateKeyOwner, @NotNull @Min(1000) @Max(9999) Integer pinNumber) {
		super();
		this.depositName = depositName;
		this.contractAddress = contractAddress;
		this.privateKeyOwner = privateKeyOwner;
		this.pinNumber = pinNumber;
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

	public String getPrivateKeyOwner() {
		return privateKeyOwner;
	}

	public void setPrivateKeyOwner(String privateKeyOwner) {
		this.privateKeyOwner = privateKeyOwner;
	}

	public Integer getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(Integer pinNumber) {
		this.pinNumber = pinNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractAddress == null) ? 0 : contractAddress.hashCode());
		result = prime * result + ((depositName == null) ? 0 : depositName.hashCode());
		result = prime * result + ((pinNumber == null) ? 0 : pinNumber.hashCode());
		result = prime * result + ((privateKeyOwner == null) ? 0 : privateKeyOwner.hashCode());
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
		DepositAccountCreateDto other = (DepositAccountCreateDto) obj;
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
		if (pinNumber == null) {
			if (other.pinNumber != null)
				return false;
		} else if (!pinNumber.equals(other.pinNumber))
			return false;
		if (privateKeyOwner == null) {
			if (other.privateKeyOwner != null)
				return false;
		} else if (!privateKeyOwner.equals(other.privateKeyOwner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DepositAccountCreateDto [depositName=" + depositName + ", contractAddress=" + contractAddress
				+ ", privateKeyOwner=" + privateKeyOwner + ", pinNumber=" + pinNumber + "]";
	}


	

}
