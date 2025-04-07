package it.cs.unicam.ViviComune.Contest;

import it.cs.unicam.ViviComune.Utente.Utente;
import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.ContenutoAggiuntivo.contenutoAggiuntivo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Contest {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    private Date dataInizio;
    private Date dataFine;
    @OneToOne
    private POI poi;
    private boolean isPubblico;
    @OneToMany
    private List<Utente> partecipanti;
    @OneToMany
    private List<Utente> invitati;
    @OneToMany
    private List<contenutoAggiuntivo> contenuti;
    @OneToOne
    private Utente vincitore;
    @OneToOne
    private contenutoAggiuntivo contenutoVincitore;

    public Contest(String id, String nome, String descrizione, Date dataInizio, Date dataFine, POI poi, boolean isPubblico) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.poi = poi;
        this.isPubblico = isPubblico;
        this.partecipanti = new ArrayList<>();
        this.invitati = new ArrayList<>();
        this.contenuti = new ArrayList<>();
        this.vincitore = null;
    }

    public Contest() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public POI getPoi() {
        return poi;
    }

    public void setPoi(POI poi) {
        this.poi = poi;
    }

    public boolean isPubblico() {
        return isPubblico;
    }

    public void setPubblico(boolean pubblico) {
        isPubblico = pubblico;
    }

    public void setContenutoVincitore(contenutoAggiuntivo contenuto) {
        this.contenutoVincitore=contenuto;
    }

    public void setVincitore(Utente autore) {
        this.vincitore=autore;
    }

    public List<Utente> getPartecipanti() {
        return partecipanti;
    }

    public List<Utente> getInvitati() {
        return invitati;
    }

    public List<contenutoAggiuntivo> getContenuti() {
        return contenuti;
    }

    public void aggiungiPartecipante(Utente utente) {
        if (isPubblico || invitati.contains(utente)) {
            partecipanti.add(utente);
        }
    }

    public void invitaUtente(Utente utente) {
        if (!isPubblico) {
            invitati.add(utente);
        }
    }

    public void aggiungiContenuto(contenutoAggiuntivo contenuto) {
        contenuti.add(contenuto);
    }

    public Utente getVincitore() {
        return vincitore;
    }

    public contenutoAggiuntivo getContenuto(String contenutoId) {
        for (contenutoAggiuntivo contenuto : contenuti) {
            if (contenuto.getId().equals(contenutoId)) {
                return contenuto;
            }
        }
        return null;
    }
}