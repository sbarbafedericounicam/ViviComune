package it.cs.unicam.ViviComune.Controller;

import it.cs.unicam.ViviComune.ContenutoAggiuntivo.contenutoAggiuntivo;
import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.POI.gestorePOI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/poi")
public class POIController {

    @Autowired
    private gestorePOI  gestorePoi;

    @Autowired
    public POIController(gestorePOI gestorePoi) {
        this.gestorePoi = gestorePoi;
    }


    @GetMapping("/")
    public ResponseEntity<List<POI>> getAllPOI() {
        List<POI> poiList = gestorePoi.getTuttiPOI();
        return new ResponseEntity<>(poiList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<POI> getPOI(@PathVariable String id) {
        POI poi = gestorePoi.getPOI(id);
        if (poi == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(poi, HttpStatus.OK);
    }


    @PostMapping("/nuovo")
    public ResponseEntity<String> creaPOI(@RequestBody POI nuovoPOI) {
        if (gestorePoi.esistePOIConId(nuovoPOI.getId())) {
            return new ResponseEntity<>("Un POI con lo stesso ID esiste già", HttpStatus.BAD_REQUEST);
        } else if (gestorePoi.esistePOIConNome(nuovoPOI.getNome())) {
            return new ResponseEntity<>("Un POI con lo stesso nome esiste già", HttpStatus.BAD_REQUEST);
        } else {
            gestorePoi.creaPOI(nuovoPOI.getId(), nuovoPOI.getNome(), nuovoPOI.getDescrizione(), nuovoPOI.getCoordinate());
            return new ResponseEntity<>("POI creato con successo", HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/elimina/{id}")
    public ResponseEntity<String> eliminaPOI(@PathVariable String id) {
        POI poi = gestorePoi.getPOI(id);
        if (poi != null) {
            gestorePoi.eliminaPOI(id);
            return new ResponseEntity<>("POI eliminato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifica/{id}")
    public ResponseEntity<String> modificaPOI(@PathVariable String id, @RequestBody POI poiModificato) {
        POI poi = gestorePoi.getPOI(id);
        if (poi != null) {
            gestorePoi.modificaPOI(id, poiModificato.getNome(), poiModificato.getDescrizione(), poiModificato.getCoordinate());
            return new ResponseEntity<>("POI modificato con successo", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/approva/{id}")
    public ResponseEntity<String> approvaPOI(@PathVariable String id) {
        POI poi = gestorePoi.getPOI(id);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.approvaPOI(id);
        return new ResponseEntity<>("POI approvato con successo", HttpStatus.OK);
    }

    @PutMapping("/disapprova/{id}")
    public ResponseEntity<String> disapprovaPOI(@PathVariable String id) {
        POI poi = gestorePoi.getPOI(id);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.disapprovaPOI(id);
        return new ResponseEntity<>("POI disapprovato con successo", HttpStatus.OK);
    }

    @PostMapping("/{id}/aggiungiContenuto")
    public ResponseEntity<String> aggiungiContenutoAggiuntivo(@PathVariable String id, @RequestBody contenutoAggiuntivo contenuto) {
        POI poi = gestorePoi.getPOI(id);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.aggiungiContenutoAggiuntivo(id, contenuto);
        return new ResponseEntity<>("Contenuto aggiuntivo aggiunto con successo", HttpStatus.OK);
    }

    @DeleteMapping("/{id}/rimuoviContenuto/{idContenuto}")
    public ResponseEntity<String> rimuoviContenutoAggiuntivo(@PathVariable String id, @PathVariable String idContenuto) {
        POI poi = gestorePoi.getPOI(id);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.rimuoviContenutoAggiuntivo(id, idContenuto);
        return new ResponseEntity<>("Contenuto aggiuntivo rimosso con successo", HttpStatus.OK);
    }

    @PutMapping("/{poiId}/approvaContenuto/{contenutoId}")
    public ResponseEntity<String> approvaContenutoAggiuntivo(@PathVariable String poiId, @PathVariable String contenutoId) {
        POI poi = gestorePoi.getPOI(poiId);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.approvaContenutoAggiuntivo(poiId, contenutoId);
        return new ResponseEntity<>("Contenuto approvato con successo", HttpStatus.OK);
    }

    @PutMapping("/{poiId}/disapprovaContenuto/{contenutoId}")
    public ResponseEntity<String> disapprovaContenutoAggiuntivo(@PathVariable String poiId, @PathVariable String contenutoId) {
        POI poi = gestorePoi.getPOI(poiId);
        if (poi == null) {
            return new ResponseEntity<>("POI non trovato", HttpStatus.NOT_FOUND);
        }
        gestorePoi.disapprovaContenutoAggiuntivo(poiId, contenutoId);
        return new ResponseEntity<>("Contenuto disapprovato con successo", HttpStatus.OK);
    }
}




