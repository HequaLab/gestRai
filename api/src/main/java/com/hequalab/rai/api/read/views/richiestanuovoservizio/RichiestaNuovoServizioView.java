package com.hequalab.rai.api.read.views.richiestanuovoservizio;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.hequalab.rai.api.read.views.BaseEntity;
import com.hequalab.rai.domain.produzioni.ProduzioniId;
import com.hequalab.rai.domain.richiestanuovoservizio.RichiestaNuovoServizioId;
import com.hequalab.rai.domain.servizi.ServiziId;

@Entity
@Table(name = "richiestanuovoservizio_view")
public class RichiestaNuovoServizioView extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Type(type = "RichiestaNuovoServizioIdType")
    private RichiestaNuovoServizioId richiestanuovoservizioId;

    private LocalDate data;
    private LocalDate dataFine;
    private String divisione;
    private String fornitore;
    private String nome;
    private String note;
    private String ora;
    private Integer ore;
    private String uorg;
    private String stato;
    private String lotto;
    private String operatore;
    private String tipologia;
    private Integer matricola;
    private String produzione;
    private String luogo;
    private LocalDateTime timeStamp;
    private String utenteApprovante;
    private Double importo;
    private Double costoTotale;
    private String statoEsportazione;
    private String voce;
    private String luogoId;

    @Type(type = "ProduzioniIdType")
    private ProduzioniId idProduzione;

    @Type(type = "ServiziIdType")
    private ServiziId idServizio;

    public RichiestaNuovoServizioId getRichiestaNuovoServizioId() {
	return richiestanuovoservizioId;
    }

    public void setRichiestaNuovoServizioId(
	    RichiestaNuovoServizioId richiestaNuovoServizioId) {
	this.richiestanuovoservizioId = richiestaNuovoServizioId;
    }

    public LocalDate getData() {
	return data;
    }

    public void setData(LocalDate data) {
	this.data = data;
    }

    public LocalDate getDataFine() {
	return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
	this.dataFine = dataFine;
    }

    public String getDivisione() {
	return divisione;
    }

    public void setDivisione(String divisione) {
	this.divisione = divisione;
    }

    public String getFornitore() {
	return fornitore;
    }

    public void setFornitore(String fornitore) {
	this.fornitore = fornitore;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNote() {
	return note;
    }

    public void setNote(String note) {
	this.note = note;
    }

    public String getOra() {
	return ora;
    }

    public void setOra(String ora) {
	this.ora = ora;
    }

    public Integer getOre() {
	return ore;
    }

    public void setOre(Integer ore) {
	this.ore = ore;
    }

    public String getStato() {
	return stato;
    }

    public void setStato(String stato) {
	this.stato = stato;
    }

    public String getLotto() {
	return lotto;
    }

    public void setLotto(String lotto) {
	this.lotto = lotto;
    }

    public String getOperatore() {
	return operatore;
    }

    public void setOperatore(String operatore) {
	this.operatore = operatore;
    }

    public String getTipologia() {
	return tipologia;
    }

    public void setTipologia(String tipologia) {
	this.tipologia = tipologia;
    }

    public Integer getMatricola() {
	return matricola;
    }

    public void setMatricola(Integer matricola) {
	this.matricola = matricola;
    }

    public String getProduzione() {
	return produzione;
    }

    public void setProduzione(String produzione) {
	this.produzione = produzione;
    }

    public String getLuogo() {
	return luogo;
    }

    public void setLuogo(String luogo) {
	this.luogo = luogo;
    }

    public LocalDateTime getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
	this.timeStamp = timeStamp;
    }

    public String getUtenteApprovante() {
	return utenteApprovante;
    }

    public void setUtenteApprovante(String utenteApprovante) {
	this.utenteApprovante = utenteApprovante;
    }

    public Double getImporto() {
	return importo;
    }

    public void setImporto(Double importo) {
	this.importo = importo;
    }

    public Double getCostoTotale() {
	return costoTotale;
    }

    public void setCostoTotale(Double costoTotale) {
	this.costoTotale = costoTotale;
    }

    public String getStatoEsportazione() {
	return statoEsportazione;
    }

    public void setStatoEsportazione(String statoEsportazione) {
	this.statoEsportazione = statoEsportazione;
    }

    public String getUorg() {
	return uorg;
    }

    public void setUorg(String uorg) {
	this.uorg = uorg;
    }

    public String getVoce() {
	return voce;
    }

    public void setVoce(String voce) {
	this.voce = voce;
    }

    public String getLuogoId() {
	return luogoId;
    }

    public void setLuogoId(String luogoId) {
	this.luogoId = luogoId;
    }

    public ProduzioniId getIdProduzione() {
	return idProduzione;
    }

    public void setIdProduzione(ProduzioniId idProduzione) {
	this.idProduzione = idProduzione;
    }

    public ServiziId getIdServizio() {
	return idServizio;
    }

    public void setIdServizio(ServiziId idServizio) {
	this.idServizio = idServizio;
    }

}