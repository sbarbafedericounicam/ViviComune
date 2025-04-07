package it.cs.unicam.ViviComune.Controller;

import it.cs.unicam.ViviComune.Contest.Contest;
import it.cs.unicam.ViviComune.Contest.GestoreContest;
import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.Utente.GestoreUtente;
import it.cs.unicam.ViviComune.Utente.Utente;
import it.cs.unicam.ViviComune.ContenutoAggiuntivo.contenutoAggiuntivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contest")
public class ContestController {

    @Autowired
    private GestoreContest gestoreContest;
    @Autowired
    private GestoreUtente gestoreUtente;

    @PostMapping("/nuovo")
    public ResponseEntity<String> creaContest(@RequestBody Contest nuovoContest) {
        if (gestoreContest.getContest(nuovoContest.getId()) != null) {
            return new ResponseEntity<>("Un Contest con lo stesso ID esiste già", HttpStatus.BAD_REQUEST);
        }
        gestoreContest.creaContest(nuovoContest.getId(), nuovoContest.getNome(), nuovoContest.getDescrizione(), nuovoContest.getDataInizio(), nuovoContest.getDataFine(), nuovoContest.getPoi().getId(), nuovoContest.isPubblico());
        return new ResponseEntity<>("Contest creato con successo", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Contest>> getAllContests() {
        List<Contest> contestList = gestoreContest.getTuttiContests();
        return new ResponseEntity<>(contestList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contest> getContest(@PathVariable String id) {
        Contest contest = gestoreContest.getContest(id);
        if (contest == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contest, HttpStatus.OK);
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<String> eliminaContest(@PathVariable String id) {
        Contest contest = gestoreContest.getContest(id);
        if (contest == null) {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }
        gestoreContest.eliminaContest(id);
        return new ResponseEntity<>("Contest eliminato con successo", HttpStatus.OK);
    }

    @PostMapping("/{contestId}/aggiungiPartecipante/{userId}")
    public ResponseEntity<String> aggiungiPartecipante(@PathVariable String contestId, @PathVariable String userId) {
        Contest contest = gestoreContest.getContest(contestId);
        if (contest == null) {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }

        Utente utente = gestoreUtente.getUtente(userId);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        gestoreContest.aggiungiPartecipante(contestId, utente);
        return new ResponseEntity<>("Partecipante aggiunto con successo", HttpStatus.OK);
    }

    @PostMapping("/{contestId}/invitaUtente/{userId}")
    public ResponseEntity<String> invitaUtente(@PathVariable String contestId, @PathVariable String userId) {
        Contest contest = gestoreContest.getContest(contestId);
        if (contest == null) {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }
        if (contest.isPubblico()) {
            return new ResponseEntity<>("Impossibile invitare utenti in un contest pubblico", HttpStatus.BAD_REQUEST);
        }

        Utente utente = gestoreUtente.getUtente(userId);
        if (utente == null) {
            return new ResponseEntity<>("Utente non trovato", HttpStatus.NOT_FOUND);
        }

        gestoreContest.invitaUtente(contestId, utente);
        return new ResponseEntity<>("Utente invitato con successo", HttpStatus.OK);
    }

    @PostMapping("/{contestId}/aggiungiContenuto")
    public ResponseEntity<String> aggiungiContenutoAggiuntivo(@PathVariable String contestId, @RequestBody contenutoAggiuntivo contenuto) {
        Contest contest = gestoreContest.getContest(contestId);
        if (contest == null) {
            return new ResponseEntity<>("Contest non trovato", HttpStatus.NOT_FOUND);
        }
        gestoreContest.aggiungiContenuto(contestId, contenuto);
        return new ResponseEntity<>("Contenuto aggiunto con successo al contest", HttpStatus.OK);
    }

    @PostMapping("/{contestId}/vincitore/{contenutoId}")
    public ResponseEntity<String> dichiaraVincitore(@PathVariable String contestId, @PathVariable String contenutoId) {
        gestoreContest.dichiaraVincitore(contestId, contenutoId);
        return new ResponseEntity<>("Vincitore del contest dichiarato correttamente", HttpStatus.OK);
    }

}