package m.f.bbw.Hotel.Repository;

import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BesucherRepository extends CrudRepository<Besucher, Long> {
    //alle Besucher eines bestimmten Hotels erhalten
    List<Besucher> findByHotel(Hotel hotel);

   // bestimmten Besucher für ein bestimmtes Hotel erhalten
    Optional<Besucher> findByHotelAndId(Hotel hotel, Long id);

}