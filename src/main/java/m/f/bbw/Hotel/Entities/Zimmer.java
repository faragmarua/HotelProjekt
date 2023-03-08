package m.f.bbw.Hotel.Entities;
import jakarta.persistence.*;

import java.util.List;


@Entity@Table(name = "zimmer")
public class Zimmer {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nummer")
    private int nummer;
    @Column(name = "stockwerk")
    private int stockwerk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
// Konstruktor, Getter und Setter


    public Zimmer(Long id, int nummer, int stockwerk, Hotel hotel) {
        this.id = id;
        this.nummer = nummer;
        this.stockwerk = stockwerk;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
