package com.hequalab.rai.domain.filiali.events;

/*
 * Class generated by AppWizard
 */

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.filiali.FilialiId;

public class FilialiCreated extends DefaultEvent<FilialiId> {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@SuppressWarnings("unused")
	private FilialiCreated() {
	}

	public FilialiCreated(FilialiId id ,String nome) {
		super(id);
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
