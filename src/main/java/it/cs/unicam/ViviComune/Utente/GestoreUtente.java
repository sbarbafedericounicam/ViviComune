package it.cs.unicam.ViviComune.Utente;

import it.cs.unicam.ViviComune.Itinerario.GestoreItinerario;
import it.cs.unicam.ViviComune.Itinerario.Itinerario;
import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.POI.gestorePOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestoreUtente {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private gestorePOI gestorePOI;
    @Autowired
    private GestoreItinerario gestoreItinerario;

    public void creaUtente(String id, String nome, String cognome, String email, String username, RuoloUtente ruolo) {
        Utente utente = new Utente(id, nome, cognome, email, username, ruolo);
        utenteRepository.save(utente);
    }

    public void modificaUtente(String id, String nome, String cognome, String email, RuoloUtente ruolo) {
        Optional<Utente> optionalUtente = utenteRepository.findById(id);
        if (optionalUtente.isPresent()) {
            Utente utente = optionalUtente.get();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setRuolo(ruolo);
            utenteRepository.save(utente);
        }
    }

    public void eliminaUtente(String id) {
        utenteRepository.deleteById(id);
    }

    public Utente getUtente(String id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public List<Utente> getTuttiUtenti() {
        return utenteRepository.findAll();
    }

    public boolean esisteUtenteConEmail(String email) {
        return utenteRepository.findAll().stream().anyMatch(utente -> utente.getEmail().equals(email));
    }

    public boolean esisteUtenteConUsername(String username) {
        return utenteRepository.findAll().stream().anyMatch(utente -> utente.getUsername().equals(username));
    }

    public void aggiungiPOISalvato(String userId, String idPOI) {
        Optional<Utente> optionalUtente = utenteRepository.findById(userId);
        if (optionalUtente.isPresent()) {
            Utente utente = optionalUtente.get();
            POI poi = gestorePOI.getPOI(idPOI);
            if (poi != null) {
                utente.aggiungiPOISalvato(poi);
                utenteRepository.save(utente);
            }
        }
    }

    public void aggiungiItinerarioSalvato(String userId, String idItinerario) {
        Optional<Utente> optionalUtente = utenteRepository.findById(userId);
        if (optionalUtente.isPresent()) {
            Utente utente = optionalUtente.get();
            Itinerario itinerario = gestoreItinerario.getItinerario(idItinerario);
            if (itinerario != null) {
                utente.aggiungiItinerarioSalvato(itinerario);
                utenteRepository.save(utente);
            }
        }
    }

    public List<POI> getPOISalvati(String userId) {
        Utente utente = getUtente(userId);
        if (utente != null) {
            return utente.getPoiSalvati();
        }
        return null;
    }

    public List<Itinerario> getItinerariSalvati(String userId) {
        Utente utente = getUtente(userId);
        if (utente != null) {
            return utente.getItinerariSalvati();
        }
        return null;
    }

}

