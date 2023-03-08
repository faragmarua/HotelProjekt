package m.f.bbw.Hotel.Repository;

import m.f.bbw.Hotel.Entities.Besucher;
import m.f.bbw.Hotel.Entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {
    List<Besucher> findBesucherByHotel(Hotel hotel);
}
