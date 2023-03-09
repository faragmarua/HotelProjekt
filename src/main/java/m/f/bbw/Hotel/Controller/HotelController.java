package m.f.bbw.Hotel.Controller;

import m.f.bbw.Hotel.Entities.Besitzer;
import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Entities.Zimmer;
import m.f.bbw.Hotel.Repository.BesitzerRepository;
import m.f.bbw.Hotel.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BesitzerRepository besitzerRepository;

    // Endpoint zum Erstellen eines neuen Hotels
    @PostMapping("")
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Endpoint zum Abrufen aller Hotels
    @GetMapping("")
    public Iterable<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Endpoint zum Abrufen eines einzelnen Hotels anhand seiner ID
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            return hotelOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel nicht gefunden");
        }
    }

    // Endpoint zum Aktualisieren eines Hotels anhand seiner ID
    @PutMapping("/{id}")
    public Hotel updateHotelById(@PathVariable Long id, @RequestBody Hotel updatedHotel) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            updatedHotel.setId(id);
            return hotelRepository.save(updatedHotel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel nicht gefunden");
        }
    }

    // Endpoint zum Löschen eines Hotels anhand seiner ID
    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable Long id) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.deleteById(id);
        }}





    // GET all visitors for a specific hotel
    @GetMapping("/{id}/visitors")
    public List<Besucher> getVisitorsByHotelId(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotelRepository.findBesucherByHotel(hotel.get());
        } else {
            return Collections.emptyList();
        }
    }

    // GET the owner of a specific hotel
    @GetMapping("/{id}/owner")
    public ResponseEntity<Besitzer> getOwnerByHotelId(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            Optional<Besitzer> owner = hotelRepository.findBesitzerByHotel(hotel.get());
            if (owner.isPresent()) {
                return ResponseEntity.ok(owner.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET all rooms for a specific hotel
    @GetMapping("/{id}/rooms")
    public List<Zimmer> getRoomsByHotelId(@PathVariable("id") Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isPresent()) {
            return hotelRepository.findZimmerByHotel(hotel.get());
        } else {
            return Collections.emptyList();
        }
    }

    // GET all hotels owned by a specific owner
    @GetMapping("/owned-by/{ownerId}")
    public List<Hotel> getHotelsByOwnerId(@PathVariable("ownerId") Long ownerId) {
        Optional<Besitzer> owner = besitzerRepository.findById(ownerId);
        if (owner.isPresent()) {
            return hotelRepository.findByBesitzer(owner.get());
        } else {
            return Collections.emptyList();
        }
    }
}

