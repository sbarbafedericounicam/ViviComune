package it.cs.unicam.ViviComune.Contest;

import it.cs.unicam.ViviComune.POI.POI;

import java.util.Date;

public class ContestDirector {
    private ContestBuilder builder;

    public ContestDirector(ContestBuilder builder) {
        this.builder = builder;
    }

    public void construct(String nome, String descrizione, Date dataInizio, Date dataFine, POI poi, boolean isPubblico) {
        builder.reset();
        builder.setNome(nome);
        builder.setDescrizione(descrizione);
        builder.setDataInizio(dataInizio);
        builder.setDataFine(dataFine);
        builder.setPOI(poi);
        builder.setIsPubblico(isPubblico);
    }
}
