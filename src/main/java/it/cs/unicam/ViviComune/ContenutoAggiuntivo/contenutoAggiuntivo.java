package it.cs.unicam.ViviComune.ContenutoAggiuntivo;

import it.cs.unicam.ViviComune.Utente.Utente;
import it.cs.unicam.ViviComune.Utils.Stato;
import jakarta.persistence.*;

import java.io.File;

@Entity
public class contenutoAggiuntivo {
    @Id
    private String id;
    private String nome;
    private String descrizione;
    private File file;
    @Enumerated(EnumType.STRING)
    private Stato statoContenuto;
    @ManyToOne
    private Utente autore;

    public contenutoAggiuntivo(String id, String nome, String descrizione, File file, Utente autore){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.file = file;
        this.statoContenuto = Stato.ATTESA;
        this.autore = autore;
    }

    public contenutoAggiuntivo() {

    }

    public String getId(){return id;}
    public void setId(String id){this.id = id;}
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getDescrizione(){return descrizione;}

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Stato getStatoContenuto() {
        return statoContenuto;
    }
    public void setStatoContenuto(Stato statoContenuto) {
        this.statoContenuto = statoContenuto;
    }
    public Utente getAutore() {return autore;}
    public void setAutore(Utente autore) {this.autore = autore;}

    public void approvaContenuto(){
        this.setStatoContenuto(Stato.APPROVATO);
    }

    public void disapprovaContenuto(){
        this.setStatoContenuto(Stato.DISAPPROVATO);
    }

    public File getFile(){return file;}
    public void setFile(File file){this.file = file;}
}