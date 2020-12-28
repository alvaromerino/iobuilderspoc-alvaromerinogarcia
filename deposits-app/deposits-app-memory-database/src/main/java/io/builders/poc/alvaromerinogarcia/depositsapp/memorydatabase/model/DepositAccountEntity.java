package io.builders.poc.alvaromerinogarcia.depositsapp.memorydatabase.model;

public class DepositAccountEntity {

	private String name;
	private String contractAddress;
	private String privateKeyOwner;
	private Integer pin;
	
	public DepositAccountEntity() {
		
	}

	public DepositAccountEntity(String name, String contractAddress, String privateKeyOwner, Integer pin) {
		super();
		this.name = name;
		this.contractAddress = contractAddress;
		this.privateKeyOwner = privateKeyOwner;
		this.pin = pin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractAddress == null) ? 0 : contractAddress.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
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
		DepositAccountEntity other = (DepositAccountEntity) obj;
		if (contractAddress == null) {
			if (other.contractAddress != null)
				return false;
		} else if (!contractAddress.equals(other.contractAddress))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
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
		return "DepositAccountEntity [name=" + name + ", contractAddress=" + contractAddress + ", privateKeyOwner="
				+ privateKeyOwner + ", pin=" + pin + "]";
	}


}
