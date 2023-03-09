package m.f.bbw.Hotel.Repository;

import m.f.bbw.Hotel.Entities.Besitzer;
import m.f.bbw.Hotel.Entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BesitzerRepository extends CrudRepository<Besitzer, Long> {

    //Hotel eines bestimmten Besitzers
    Optional<Hotel> findHotelByBesitzer(Besitzer besitzer);
}
