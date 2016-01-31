package com.hequalab.rai.dddd;

import java.util.UUID;

public abstract class UUIDIdentity implements Identity {

	private static final long serialVersionUID = 1L;
	
	private final UUID uuid;
	
	public UUIDIdentity() {
		this.uuid = UUID.randomUUID();
	}

	public UUIDIdentity(String uuid) {
		this.uuid = UUID.fromString(uuid);
	}

	public UUIDIdentity(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return uuid.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		UUIDIdentity other = (UUIDIdentity) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}