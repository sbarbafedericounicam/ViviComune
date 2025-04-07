package it.cs.unicam.ViviComune.Itinerario;

import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.Utils.Stato;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Itinerario {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    @OneToMany
    private List<POI> listaPOI;
    @Enumerated(EnumType.STRING)
    private Stato statoItinerario;

    public Itinerario(String id, String nome, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.listaPOI = new ArrayList<>();
        this.statoItinerario = Stato.ATTESA;
    }

    public Itinerario() {

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

    public List<POI> getListaPOI() {
        return listaPOI;
    }

    public void setListaPOI(List<POI> listaPOI) {
        this.listaPOI = listaPOI;
    }

    public void aggiungiPOI(POI poi) {
        listaPOI.add(poi);
    }

    public void rimuoviPOI(POI poi) {
        listaPOI.remove(poi);
    }

    public Stato getStatoItinerario() {
        return statoItinerario;
    }

    public void setStatoItinerario(Stato statoItinerario) {
        this.statoItinerario = statoItinerario;
    }
}