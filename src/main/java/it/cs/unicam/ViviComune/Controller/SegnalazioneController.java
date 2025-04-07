package it.cs.unicam.ViviComune.Controller;

import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.Segnalazione.GestoreSegnalazione;
import it.cs.unicam.ViviComune.Segnalazione.Segnalazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/segnalazione")
public class SegnalazioneController {

    private GestoreSegnalazione gestoreSegnalazione;

    @Autowired
    public SegnalazioneController(GestoreSegnalazione gestoreSegnalazione) {
        this.gestoreSegnalazione = gestoreSegnalazione;
    }

    @PostMapping("/nuova")
    public ResponseEntity<String> creaSegnalazione(@RequestBody Segnalazione nuovaSegnalazione) {
        gestoreSegnalazione.creaSegnalazione(nuovaSegnalazione.getId(), nuovaSegnalazione.getDescrizione(),
                nuovaSegnalazione.getTipoRiferimento(), nuovaSegnalazione.getRiferimentoId());
        return new ResponseEntity<>("Segnalazione creata con successo", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Segnalazione>> getAllSegnalazioni() {
        List<Segnalazione> segnalazioni = gestoreSegnalazione.getTutteSegnalazioni();
        return new ResponseEntity<>(segnalazioni, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Segnalazione> getSegnalazione(@PathVariable String id) {
        Segnalazione segnalazione = gestoreSegnalazione.getSegnalazione(id);
        if (segnalazione == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(segnalazione, HttpStatus.OK);
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<String> eliminaSegnalazione(@PathVariable String id) {
        gestoreSegnalazione.eliminaSegnalazione(id);
        return new ResponseEntity<>("Segnalazione eliminata con successo", HttpStatus.OK);
    }
}
