package m.f.bbw.Hotel.Controller;


import m.f.bbw.Hotel.Entities.Besitzer;
import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Repository.BesitzerRepository;
import m.f.bbw.Hotel.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/besitzer")
public class BesitzerController {

    @Autowired
    private BesitzerRepository besitzerRepository;

    @Autowired
    private HotelRepository hotelRepository;

    // GET: alle Besitzer
    @GetMapping("/")
    public List<Besitzer> getAllBesitzer() {
        return (List<Besitzer>) besitzerRepository.findAll();
    }

    // GET: ein bestimmter Besitzer
    @GetMapping("/{id}")
    public Besitzer getBesitzerById(@PathVariable Long id) {
        return besitzerRepository.findById(id).orElseThrow();
    }

    // POST: neuer Besitzer
    @PostMapping("/")
    public Besitzer createBesitzer(@RequestBody Besitzer besitzer) {
        return besitzerRepository.save(besitzer);
    }

    // PUT: Besitzer aktualisieren
    @PutMapping("/{id}")
    public Besitzer updateBesitzer(@PathVariable Long id, @RequestBody Besitzer besitzerDetails) {
        Besitzer besitzer = besitzerRepository.findById(id).orElseThrow();
        besitzer.setName(besitzerDetails.getName());
        besitzer.setAlter(besitzerDetails.getAlter());
        return besitzerRepository.save(besitzer);
    }

    // DELETE: Besitzer löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBesitzer(@PathVariable Long id) {
        besitzerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // GET: alle Hotels eines Besitzers
    @GetMapping("/{besitzerId}/hotels")
    public List<Hotel> getHotelsByBesitzerId(@PathVariable Long besitzerId) {
        Besitzer besitzer = besitzerRepository.findById(besitzerId).orElseThrow();
        return hotelRepository.findByBesitzer(besitzer);
    }

}

