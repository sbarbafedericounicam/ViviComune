package it.cs.unicam.ViviComune.Segnalazione;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestoreSegnalazione {
    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    public void creaSegnalazione(String id, String descrizione, TipoRiferimento tipoRiferimento, String riferimentoId) {
        Segnalazione segnalazione = new Segnalazione(id, descrizione, tipoRiferimento, riferimentoId);
        segnalazioneRepository.save(segnalazione);
    }

    public void eliminaSegnalazione(String id) {
        segnalazioneRepository.deleteById(id);
    }

    public Segnalazione getSegnalazione(String id) {
        return segnalazioneRepository.findById(id).orElse(null);
    }

    public List<Segnalazione> getTutteSegnalazioni() {
        return segnalazioneRepository.findAll();
    }
}
