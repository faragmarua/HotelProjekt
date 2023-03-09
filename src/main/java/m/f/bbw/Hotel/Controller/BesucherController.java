package m.f.bbw.Hotel.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Repository.BesucherRepository;
import m.f.bbw.Hotel.Repository.HotelRepository;

@RestController
@RequestMapping("/hotels/{hotelId}/besucher")
public class BesucherController {

    // Autowire the required repositories
    @Autowired
    private BesucherRepository besucherRepository;
    @Autowired
    private HotelRepository hotelRepository;

    // Endpoint to get all visitors of a specific hotel
    @GetMapping("")
    public List<Besucher> getAllVisitorsByHotel(@PathVariable Long hotelId) {
        // Retrieve the specified hotel
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();

        // Retrieve all visitors of the hotel
        List<Besucher> besucher = besucherRepository.findByHotel(hotel);

        return besucher;
    }

    // Endpoint to get a specific visitor of a specific hotel
    @GetMapping("/{besucherId}")
    public Besucher getVisitorByHotelAndId(@PathVariable Long hotelId, @PathVariable Long besucherId) {
        // Retrieve the specified hotel
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();

        // Retrieve the specified visitor of the hotel
        Besucher besucher = besucherRepository.findByHotelAndId(hotel, besucherId).orElseThrow();

        return besucher;
    }

    // Endpoint to add a new visitor to a specific hotel
    @PostMapping("")
    public Besucher addVisitor(@PathVariable Long hotelId, @RequestBody Besucher besucher) {
        // Retrieve the specified hotel
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();

        // Set the hotel of the visitor to the specified hotel
        besucher.setHotel(hotel);

        // Save the visitor to the database
        Besucher savedBesucher = besucherRepository.save(besucher);

        return savedBesucher;
    }

    // Endpoint to update a specific visitor of a specific hotel
    @PutMapping("/{besucherId}")
    public Besucher updateVisitor(@PathVariable Long hotelId, @PathVariable Long besucherId, @RequestBody Besucher besucherRequest) {
        // Retrieve the specified hotel
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();

        // Retrieve the specified visitor of the hotel
        Besucher besucher = besucherRepository.findByHotelAndId(hotel, besucherId).orElseThrow();

        // Update the visitor's properties with the request body
        besucher.setName(besucherRequest.getName());
        besucher.setAufenthaltsdauer(besucherRequest.getAufenthaltsdauer());

        // Save the updated visitor to the database
        Besucher updatedBesucher = besucherRepository.save(besucher);

        return updatedBesucher;
    }

    // Endpoint to delete a specific visitor of a specific hotel
    @DeleteMapping("/{besucherId}")
    public ResponseEntity<?> deleteVisitor(@PathVariable Long hotelId, @PathVariable Long besucherId) {
        // Retrieve the specified hotel
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();

        // Retrieve the specified visitor of the hotel
        Besucher besucher = besucherRepository.findByHotelAndId(hotel, besucherId).orElseThrow();

        // Delete the visitor from the database
        besucherRepository.delete(besucher);

        return ResponseEntity.ok().build();
    }
}


