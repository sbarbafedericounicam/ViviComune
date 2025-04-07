package it.cs.unicam.ViviComune.Itinerario;

import it.cs.unicam.ViviComune.POI.POI;
import it.cs.unicam.ViviComune.POI.POIRepository;
import it.cs.unicam.ViviComune.Utils.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestoreItinerario {

    @Autowired
    private ItinerarioRepository itinerarioRepository;
    @Autowired
    private POIRepository poiRepository;

    public Itinerario getItinerario(String id){
        return itinerarioRepository.findById(id).orElse(null);
    }

    public void creaItinerario(String id, String nome, String descrizione){
        Itinerario itinerario = new Itinerario(id, nome, descrizione);
        itinerarioRepository.save(itinerario);
    }

    public void modificaItinerario(String id, String nome, String descrizione){
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(id);
        if (optionalItinerario.isPresent()) {
            Itinerario itinerario = optionalItinerario.get();
            itinerario.setNome(nome);
            itinerario.setDescrizione(descrizione);
            itinerarioRepository.save(itinerario);
        }
    }

    public void aggiungiPOIAllItinerario(String itinerarioId, POI poi){
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(itinerarioId);
        if(optionalItinerario.isPresent()){
            Itinerario itinerario = optionalItinerario.get();
            itinerario.aggiungiPOI(poi);
            itinerarioRepository.save(itinerario);
        }
    }

    public void rimuoviPOIDaItinerario(String itinerarioId, POI poi){
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(itinerarioId);
        if(optionalItinerario.isPresent()){
            Itinerario itinerario = optionalItinerario.get();
            itinerario.rimuoviPOI(poi);
            itinerarioRepository.save(itinerario);
        }
    }

    public void rimuoviPOIdaTuttiItinerari(POI poi) {
        List<Itinerario> itinerari = itinerarioRepository.findAll();
        for (Itinerario itinerario : itinerari) {
            itinerario.rimuoviPOI(poi);
            itinerarioRepository.save(itinerario);
        }
    }

    public void eliminaItinerario(String id){
        itinerarioRepository.deleteById(id);
    }

    public List<Itinerario> getTuttiItinerari(){
        return itinerarioRepository.findAll();
    }

    public boolean esisteItinerarioConId(String id) {
        return itinerarioRepository.existsById(id);
    }

    public boolean esisteItinerarioConNome(String nome) {
        return itinerarioRepository.findAll().stream().anyMatch(itinerario -> itinerario.getNome().equals(nome));
    }

    public void approvaItinerario(String id){
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(id);
        if (optionalItinerario.isPresent()) {
            Itinerario itinerario = optionalItinerario.get();
            itinerario.setStatoItinerario(Stato.APPROVATO);
            itinerarioRepository.save(itinerario);
        }
    }

    public void disapprovaItinerario(String id){
        Optional<Itinerario> optionalItinerario = itinerarioRepository.findById(id);
        if (optionalItinerario.isPresent()) {
            Itinerario itinerario = optionalItinerario.get();
            itinerario.setStatoItinerario(Stato.DISAPPROVATO);
            itinerarioRepository.save(itinerario);
        }
    }
}
