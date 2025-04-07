package it.cs.unicam.ViviComune.Controller;

import it.cs.unicam.ViviComune.Itinerario.Itinerario;
import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.Utente.GestoreUtente;
import it.cs.unicam.ViviComune.Utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    private GestoreUtente gestoreUtente;

    @Autowired
    public UtenteController(GestoreUtente gestoreUtente) {
        this.gestoreUtente = gestoreUtente;
    }

    @GetMapping("/")
    public ResponseEntity<List<Utente>> getAllUtenti() {
        List<Utente> poiList = gestoreUtente.getTuttiUtenti();
        return new ResponseEntity<>(poiList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtente(@PathVariable String id) {
        Utente utente = gestoreUtente.getUtente(id);
        if (utente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    @PostMapping("/nuovo")
    public ResponseEntity<String> creaUtente(@RequestBody Utente nuovoUtente) {
        if (gestoreUtente.esisteUtenteConEmail(nuovoUtente.getEmail())) {
            return new ResponseEntity<>("Un utente con la stessa email esiste già", HttpStatus.BAD_REQUEST);
        }
        if (gestoreUtente.esisteUtenteConUsername(nuovoUtente.getUsername())) {
            return new ResponseEntity<>("Un utente con lo stesso username esiste già", HttpStatus.BAD_REQUEST);
        }
        gestoreUtente.creaUtente(nuovoUtente.getId(), nuovoUtente.getNome(), nuovoUtente.getCognome(), nuovoUtente.getEmail(), nuovoUtente.getUsername(), nuovoUtente.getRuolo());
        return new ResponseEntity<>("Utente creato con successo", HttpStatus.CREATED);
    }

    @PutMapping("/modifica/{id}")
    public ResponseEntity<String> modificaUtente(@PathVariable String id, @RequestBody Utente utenteModificato) {
        Utente utenteEsistente = gestoreUtente.getUtente(id);
        if (utenteEsistente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        if (!utenteModificato.getEmail().equals(utenteEsistente.getEmail()) && gestoreUtente.esisteUtenteConEmail(utenteModificato.getEmail())) {
            return new ResponseEntity<>("Un utente con la stessa email esiste già", HttpStatus.BAD_REQUEST);
        }
        if (!utenteModificato.getUsername().equals(utenteEsistente.getUsername()) && gestoreUtente.esisteUtenteConUsername(utenteModificato.getUsername())) {
            return new ResponseEntity<>("Un utente con lo stesso username esiste già", HttpStatus.BAD_REQUEST);
        }
        gestoreUtente.modificaUtente(id, utenteModificato.getNome(), utenteModificato.getCognome(), utenteModificato.getEmail(), utenteModificato.getRuolo());
        return new ResponseEntity<>("Utente modificato con successo", HttpStatus.OK);
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<String> eliminaUtente(@PathVariable String id) {
        Utente utente = gestoreUtente.getUtente(id);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        gestoreUtente.eliminaUtente(id);
        return new ResponseEntity<>("Utente eliminato correttamente", HttpStatus.OK);
    }

    @PostMapping("/{userId}/salvaPOI/{idPOI}")
    public ResponseEntity<String> salvaPOI(@PathVariable String userId, @PathVariable String idPOI) {
        Utente utente = gestoreUtente.getUtente(userId);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        gestoreUtente.aggiungiPOISalvato(userId, idPOI);
        return new ResponseEntity<>("POI salvato con successo", HttpStatus.OK);
    }

    @PostMapping("/{userId}/salvaItinerario/{idItinerario}")
    public ResponseEntity<String> salvaItinerario(@PathVariable String userId, @PathVariable String idItinerario) {
        Utente utente = gestoreUtente.getUtente(userId);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }
        gestoreUtente.aggiungiItinerarioSalvato(userId, idItinerario);
        return new ResponseEntity<>("Itinerario salvato con successo", HttpStatus.OK);
    }


    @GetMapping("/{userId}/poiSalvati")
    public ResponseEntity<List<POI>> getPOISalvati(@PathVariable String userId) {
        List<POI> poiSalvati = gestoreUtente.getPOISalvati(userId);
        if (poiSalvati == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(poiSalvati, HttpStatus.OK);
    }

    @GetMapping("/{userId}/itinerariSalvati")
    public ResponseEntity<List<Itinerario>> getItinerariSalvati(@PathVariable String userId) {
        List<Itinerario> itinerariSalvati = gestoreUtente.getItinerariSalvati(userId);
        if (itinerariSalvati == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(itinerariSalvati, HttpStatus.OK);
    }
}
