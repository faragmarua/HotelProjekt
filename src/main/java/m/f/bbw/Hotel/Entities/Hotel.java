package m.f.bbw.Hotel.Entities;

import jakarta.persistence.*;

import java.util.List;


@Entity@Table(name = "hotel")
public class Hotel {
    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "adresse")
    private String adresse;
    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Besitzer besitzer;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Besucher> besucher;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Zimmer> zimmer;
// Konstruktor, Getter und Setter
//

    public Hotel(Long id, String name, String adresse, Besitzer besitzer, List<Besucher> besucher, List<Zimmer> zimmer) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.besitzer = besitzer;
        this.besucher = besucher;
        this.zimmer = zimmer;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Besitzer getBesitzer() {
        return besitzer;
    }

    public void setBesitzer(Besitzer besitzer) {
        this.besitzer = besitzer;
    }

    public List<Besucher> getBesucher() {
        return besucher;
    }

    public void setBesucher(List<Besucher> besucher) {
        this.besucher = besucher;
    }

    public List<Zimmer> getZimmer() {
        return zimmer;
    }

    public void setZimmer(List<Zimmer> zimmer) {
        this.zimmer = zimmer;
    }
}