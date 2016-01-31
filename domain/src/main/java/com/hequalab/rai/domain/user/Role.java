package com.hequalab.rai.domain.user;

public enum Role {

    Admin(true), // Admin 
   Consorzio(true), // Consorzio : Oversinerco, MS Servizi, Euro Global Service
   Manager(true), // Manager di almeno una divisione 
   SOperatore(false), // Super-Operatore, ovvero l'operatore che puÃ  auto-approvarsi le richieste
   Operatore(false); // Operatore normale => Divisione

	private final boolean privileged;
	Role(boolean privileged) {
		this.privileged = privileged;
	}
	
	public boolean isPrivileged() {
		return privileged;
	}
}