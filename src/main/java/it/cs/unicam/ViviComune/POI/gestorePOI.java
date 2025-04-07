package it.cs.unicam.ViviComune.POI;

import it.cs.unicam.ViviComune.Itinerario.GestoreItinerario;
import it.cs.unicam.ViviComune.Utils.Coordinate;
import it.cs.unicam.ViviComune.Utils.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.cs.unicam.ViviComune.ContenutoAggiuntivo.contenutoAggiuntivo;

import java.util.List;
import java.util.Optional;

@Service
public class gestorePOI {

    @Autowired
    private POIRepository poiRepository;
    @Autowired
    private GestoreItinerario gestoreItinerario;

    public void creaPOI(String id, String nome, String descrizione, Coordinate coordinate) {
        POI poi = new POI(id, nome, descrizione, coordinate);
        poiRepository.save(poi);
    }

    public void modificaPOI(String id, String nome, String descrizione, Coordinate coordinate) {
        Optional<POI> optionalPOI = poiRepository.findById(id);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            poi.setNome(nome);
            poi.setDescrizione(descrizione);
            poi.setCoordinate(coordinate);
            poiRepository.save(poi);
        }
    }

    public void eliminaPOI(String id) {
        POI poi = getPOI(id);
        gestoreItinerario.rimuoviPOIdaTuttiItinerari(poi);
        poiRepository.deleteById(id);
    }

    public POI getPOI(String id) {
        return poiRepository.findById(id).orElse(null);
    }

    public List<POI> getTuttiPOI() {
        return poiRepository.findAll();
    }

    public boolean esistePOIConId(String id) {
        return poiRepository.existsById(id);
    }

    public boolean esistePOIConNome(String nome) {
        return poiRepository.findAll().stream().anyMatch(poi -> poi.getNome().equals(nome));
    }

    public void approvaPOI(String id) {
        Optional<POI> optionalPOI = poiRepository.findById(id);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            poi.setStatoPOI(Stato.APPROVATO);
            poiRepository.save(poi);
        }
    }

    public void disapprovaPOI(String id) {
        Optional<POI> optionalPOI = poiRepository.findById(id);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            poi.setStatoPOI(Stato.DISAPPROVATO);
            poiRepository.save(poi);
        }
    }

    public void aggiungiContenutoAggiuntivo(String id, contenutoAggiuntivo contenuto) {
        Optional<POI> optionalPOI = poiRepository.findById(id);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            poi.aggiungiContenutoAggiuntivo(contenuto);
            poiRepository.save(poi);
        }
    }

    public void rimuoviContenutoAggiuntivo(String idPOI, String idContenuto) {
        Optional<POI> optionalPOI = poiRepository.findById(idPOI);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            poi.rimuoviContenutoAggiuntivo(idContenuto);
            poiRepository.save(poi);
        }
    }

    public void approvaContenutoAggiuntivo(String poiId, String contenutoId) {
        Optional<POI> optionalPOI = poiRepository.findById(poiId);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            for (contenutoAggiuntivo contenuto : poi.getContenutiAggiuntivi()) {
                if (contenuto.getId().equals(contenutoId)) {
                    contenuto.approvaContenuto();
                    break;
                }
            }
            poiRepository.save(poi);
        }
    }

    public void disapprovaContenutoAggiuntivo(String poiId, String contenutoId) {
        Optional<POI> optionalPOI = poiRepository.findById(poiId);
        if (optionalPOI.isPresent()) {
            POI poi = optionalPOI.get();
            for (contenutoAggiuntivo contenuto : poi.getContenutiAggiuntivi()) {
                if (contenuto.getId().equals(contenutoId)) {
                    contenuto.disapprovaContenuto();
                    break;
                }
            }
            poiRepository.save(poi);
        }
    }
}
