package m.f.bbw.Hotel.Controller;

import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

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
        }}}

