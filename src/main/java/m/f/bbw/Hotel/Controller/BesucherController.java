package m.f.bbw.Hotel.Controller;

import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Repository.BesucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/besucher")
public class BesucherController {

    @Autowired
    private BesucherRepository besucherRepository;

    // Endpoint zum Erstellen eines neuen Besuchers
    @PostMapping("")
    public Besucher createBesucher(@RequestBody Besucher besucher) {
        return besucherRepository.save(besucher);
    }

    // Endpoint zum Abrufen aller Besucher
    @GetMapping("")
    public Iterable<Besucher> getAllBesucher() {
        return besucherRepository.findAll();
    }

    // Endpoint zum Abrufen eines einzelnen Besuchers anhand seiner ID
    @GetMapping("/{id}")
    public Besucher getBesucherById(@PathVariable Long id) {
        Optional<Besucher> besucherOptional = besucherRepository.findById(id);
        if (besucherOptional.isPresent()) {
            return besucherOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Besucher nicht gefunden");
        }
    }

    // Endpoint zum Aktualisieren eines Besuchers anhand seiner ID
    @PutMapping("/{id}")
    public Besucher updateBesucherById(@PathVariable Long id, @RequestBody Besucher updatedBesucher) {
        Optional<Besucher> besucherOptional = besucherRepository.findById(id);
        if (besucherOptional.isPresent()) {
            updatedBesucher.setId(id);
            return besucherRepository.save(updatedBesucher);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Besucher nicht gefunden");
        }
    }

    // Endpoint zum Löschen eines Besuchers anhand seiner ID
    @DeleteMapping("/{id}")
    public void deleteBesucherById(@PathVariable Long id) {
        Optional<Besucher> besucherOptional = besucherRepository.findById(id);
        if (besucherOptional.isPresent()) {
            besucherRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Besucher nicht gefunden");
        }
    }
}

