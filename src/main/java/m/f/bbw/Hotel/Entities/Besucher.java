package m.f.bbw.Hotel.Entities;
import jakarta.persistence.*;


@Entity
@Table(name = "besucher")
public class Besucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "aufenthaltsdauer")
    private String aufenthaltsdauer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Besucher(Long id, String name, String aufenthaltsdauer, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.aufenthaltsdauer = aufenthaltsdauer;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAufenthaltsdauer() {
        return aufenthaltsdauer;
    }

    public void setAufenthaltsdauer(String aufenthaltsdauer) {
        this.aufenthaltsdauer = aufenthaltsdauer;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
