package it.cs.unicam.ViviComune.POI;

import it.cs.unicam.ViviComune.ContenutoAggiuntivo.contenutoAggiuntivo;
import it.cs.unicam.ViviComune.Utils.Coordinate;
import it.cs.unicam.ViviComune.Utils.Stato;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class POI {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private Stato statoPOI;
    @Embedded
    private Coordinate coordinate;
    @OneToMany(cascade = CascadeType.ALL)
    private List<contenutoAggiuntivo> contenutiAggiuntivi;

    public POI(String id, String nome, String descrizione, Coordinate coordinate) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.statoPOI = Stato.ATTESA;
        this.coordinate = coordinate;
        this.contenutiAggiuntivi=new ArrayList<>();
    }

    public POI() {}

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

    public Stato getStatoPOI() {
        return statoPOI;
    }

    public void setStatoPOI(Stato statoPOI) {
        this.statoPOI = statoPOI;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<contenutoAggiuntivo> getContenutiAggiuntivi() {
        return contenutiAggiuntivi;
    }

    public void aggiungiContenutoAggiuntivo(contenutoAggiuntivo contenuto) {
        contenutiAggiuntivi.add(contenuto);
    }

    public void rimuoviContenutoAggiuntivo(String idContenuto) {
        contenutiAggiuntivi.removeIf(contenuto -> contenuto.getId().equals(idContenuto));
    }
}