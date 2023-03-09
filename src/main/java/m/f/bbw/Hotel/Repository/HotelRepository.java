package m.f.bbw.Hotel.Repository;

import m.f.bbw.Hotel.Entities.Besitzer;
import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Entities.Zimmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    //alle Besucher für ein bestimmtes Hotel erhalten

    List<Besucher> findBesucherByHotel(Hotel hotel);

    //Besitzer eines bestimmten Hotels
    Optional<Besitzer> findBesitzerByHotel(Hotel hotel);

    //alle Zimmer für ein bestimmtes Hotel
    List<Zimmer> findZimmerByHotel(Hotel hotel);


    List<Hotel> findByBesitzer(Besitzer besitzer);
}
