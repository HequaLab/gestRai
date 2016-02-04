package com.hequalab.rai.domain.richiestanuovoservizio.events;

import org.joda.time.LocalDate;

/*
 * Class generated by AppWizard
 */

import com.hequalab.rai.dddd.DefaultEvent;
import com.hequalab.rai.domain.produzioni.ProduzioniId;
import com.hequalab.rai.domain.richiestanuovoservizio.RichiestaNuovoServizioId;
import com.hequalab.rai.domain.servizi.ServiziId;

public class RichiestaNuovoServizioUpdated
	extends DefaultEvent<RichiestaNuovoServizioId> {

    private static final long serialVersionUID = 1L;

    private LocalDate data;
    private LocalDate dataFine;
    private String divisione;
    private String fornitore;
    private String nome;
    private String note;
    private String ora;
    private Integer ore;
    private String uorg;
    private String lotto;
    private String operatore;
    private String tipologia;
    private Integer matricola;
    private String produzione;
    private String luogo;
    private String utenteApprovante;
    private Double importo;
    private Double costoTotale;
    private String statoEsportazione;
    private String voce;
    private String luogoId;
    private ProduzioniId idProduzione;
    private ServiziId idServizio;

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

    @SuppressWarnings("unused")
    private RichiestaNuovoServizioUpdated() {
    }

    public RichiestaNuovoServizioUpdated(RichiestaNuovoServizioId id,
	    LocalDate data, LocalDate dataFine, String divisione,
	    String fornitore, String nome, String note, String ora, Integer ore,
	    String uorg,  String lotto, String operatore,
	    String tipologia, Integer matricola, String produzione,
	    String luogo,String utenteApprovante,Double importo,Double costoTotale, 
	    String statoEsportazione,String voce, String luogoId, ProduzioniId idProduzione, ServiziId idServizio) {
	super(id);
	this.setData(data);
	this.setDataFine(dataFine);
	this.setDivisione(divisione);
	this.setFornitore(fornitore);
	this.setNome(nome);
	this.setNote(note);
	this.setOra(ora);
	this.setOre(ore);
	this.setUorg(uorg);
	this.setLotto(lotto);
	this.setOperatore(operatore);
	this.setTipologia(tipologia);
	this.setMatricola(matricola);
	this.setProduzione(produzione);
	this.setLuogo(luogo);
	this.setUtenteApprovante(utenteApprovante);
	this.setImporto(importo);
	this.setCostoTotale(costoTotale);
	this.setStatoEsportazione(statoEsportazione);
	this.setVoce(voce);
	this.setLuogoId(luogoId);
	this.setIdProduzione(idProduzione);
	this.setIdServizio(idServizio);
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

}