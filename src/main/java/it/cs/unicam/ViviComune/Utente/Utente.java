package it.cs.unicam.ViviComune.Utente;

import it.cs.unicam.ViviComune.Itinerario.Itinerario;
import it.cs.unicam.ViviComune.POI.POI;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Utente {
    @Id
    String id;
    String nome;
    String cognome;
    String email;
    String username;
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruolo;
    @OneToMany
    private List<POI> poiSalvati;
    @OneToMany
    private List<Itinerario> itinerariSalvati;

    public Utente(String id, String nome, String cognome, String email, String username, RuoloUtente ruolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.ruolo=ruolo;
        this.poiSalvati = new ArrayList<>();
        this.itinerariSalvati = new ArrayList<>();
    }

    public Utente() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome(){
        return cognome;
    }

    public void setCognome(String cognome){
        this.cognome=cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RuoloUtente getRuolo() {
        return ruolo;
    }

    public void setRuolo(RuoloUtente ruolo) {
        this.ruolo = ruolo;
    }

    public List<POI> getPoiSalvati() {
        return poiSalvati;
    }

    public List<Itinerario> getItinerariSalvati() {
        return itinerariSalvati;
    }

    public void aggiungiPOISalvato(POI poi) {
        poiSalvati.add(poi);
    }

    public void aggiungiItinerarioSalvato(Itinerario itinerario) {
        itinerariSalvati.add(itinerario);
    }

}
