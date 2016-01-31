package com.hequalab.rai.api.read.views.fornitori;

/*
 * Class generated by AppWizard
 */

import java.util.Set;
import com.google.common.collect.Sets;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import com.hequalab.rai.api.read.views.BaseEntity;
import javax.persistence.JoinTable;
import com.hequalab.rai.domain.fornitori.FornitoriId;



@SuppressWarnings("unused")
@Entity
@Table(name = "fornitori_view")
public class FornitoriView extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Type(type = "FornitoriIdType")
	private FornitoriId fornitoriId;
	
	private String note;
	
	private String ragioneSociale;
	
	
	public FornitoriId getFornitoriId() {
		return fornitoriId;
	}

	public void setFornitoriId(FornitoriId fornitoriId) {
		this.fornitoriId = fornitoriId;
	}

	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

}