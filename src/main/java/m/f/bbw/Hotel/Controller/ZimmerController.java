package m.f.bbw.Hotel.Controller;

import m.f.bbw.Hotel.Entities.Besitzer;
import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Entities.Zimmer;
import m.f.bbw.Hotel.Repository.BesitzerRepository;
import m.f.bbw.Hotel.Repository.HotelRepository;
import m.f.bbw.Hotel.Repository.ZimmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zimmer")
public class ZimmerController {

    @Autowired
    private ZimmerRepository zimmerRepository;

    @Autowired
    private HotelRepository hotelRepository;

    // Get all Zimmer
    @GetMapping
    public List<Zimmer> getAllZimmer() {
        return (List<Zimmer>) zimmerRepository.findAll();
    }

    // Get Zimmer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Zimmer> getZimmerById(@PathVariable(value = "id") Long zimmerId) {
        Optional<Zimmer> zimmer = zimmerRepository.findById(zimmerId);
        if (zimmer.isPresent()) {
            return ResponseEntity.ok().body(zimmer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new Zimmer
    @PostMapping
    public Zimmer createZimmer(@RequestBody Zimmer zimmer) {
        return zimmerRepository.save(zimmer);
    }

    // Update an existing Zimmer
    @PutMapping("/{id}")
    public ResponseEntity<Zimmer> updateZimmer(@PathVariable(value = "id") Long zimmerId,
                                               @RequestBody Zimmer zimmerDetails) {
        Optional<Zimmer> zimmer = zimmerRepository.findById(zimmerId);
        if (zimmer.isPresent()) {
            Zimmer updatedZimmer = zimmer.get();
            updatedZimmer.setNummer(zimmerDetails.getNummer());
            updatedZimmer.setStockwerk(zimmerDetails.getStockwerk());
            updatedZimmer.setHotel(zimmerDetails.getHotel());
            zimmerRepository.save(updatedZimmer);
            return ResponseEntity.ok(updatedZimmer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Zimmer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZimmer(@PathVariable(value = "id") Long zimmerId) {
        Optional<Zimmer> zimmer = zimmerRepository.findById(zimmerId);
        if (zimmer.isPresent()) {
            zimmerRepository.delete(zimmer.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all Zimmer for a specific Hotel
    @GetMapping("/hotel/{hotelId}")
    public List<Zimmer> getAllZimmerByHotel(@PathVariable(value = "hotelId") Long hotelId) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isPresent()) {
            return zimmerRepository.findByHotel(hotel.get());
        } else {
            return new ArrayList<Zimmer>();
        }
    }

    // Get a specific Zimmer for a specific Hotel
    @GetMapping("/hotel/{hotelId}/{zimmerNummer}")
    public ResponseEntity<Zimmer> getZimmerByHotelAndNummer(@PathVariable(value = "hotelId") Long hotelId,
                                                            @PathVariable(value = "zimmerNummer") Integer zimmerNummer) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if (hotel.isPresent()) {
            Optional<Zimmer> zimmer = zimmerRepository.findByHotelAndNummer(hotel.get(), zimmerNummer);
            if (zimmer.isPresent()) {
                return ResponseEntity.ok().body(zimmer.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

