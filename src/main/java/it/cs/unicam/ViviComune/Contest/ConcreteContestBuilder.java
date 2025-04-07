package it.cs.unicam.ViviComune.Contest;

import it.cs.unicam.ViviComune.POI.POI;

import java.util.Date;

public class ConcreteContestBuilder implements ContestBuilder {
    private Contest contest;

    @Override
    public void reset() {
        contest = new Contest();
    }

    @Override
    public void setNome(String nome) {
        contest.setNome(nome);
    }

    @Override
    public void setDescrizione(String descrizione) {
        contest.setDescrizione(descrizione);
    }

    @Override
    public void setDataInizio(Date dataInizio) {
        contest.setDataInizio(dataInizio);
    }


    @Override
    public void setDataFine(Date dataFine) {
        contest.setDataFine(dataFine);
    }

    @Override
    public void setPOI(POI poi) {
        contest.setPoi(poi);
    }

    @Override
    public void setIsPubblico(boolean isPubblico) {
        contest.setPubblico(isPubblico);
    }

    @Override
    public Contest getResult() {
        return contest;
    }
}