package m.f.bbw.Hotel.Repository;

import m.f.bbw.Hotel.Entities.Hotel;
import m.f.bbw.Hotel.Entities.Zimmer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZimmerRepository extends CrudRepository<Zimmer, Long> {
    //alle Besucher eines bestimmten Hotels erhalten
    List<Zimmer> findByHotel(Hotel hotel);

    //bestimmtes Zimmer für ein bestimmtes Hotel
    Optional<Zimmer> findByHotelAndNummer(Hotel hotel, Integer nummer);

}

