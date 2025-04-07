package it.cs.unicam.ViviComune.Contest;

import it.cs.unicam.ViviComune.POI.POI;

import java.util.Date;

public interface ContestBuilder {
    void reset();
    void setNome(String nome);
    void setDescrizione(String descrizione);
    void setDataInizio(Date dataInizio);
    void setDataFine(Date dataFine);
    void setPOI(POI poi);
    void setIsPubblico(boolean isPubblico);
    Contest getResult();
}