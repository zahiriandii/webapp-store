package mk.ukim.finki.labprefixa.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Production
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String country;
    private String address;

    @OneToMany
    private List<Movie> movies;

    public Production() {
    }

    public Production(String name, String country, String address) {
        //this.id=(long)(Math.random()*1000);
        this.Name = name;
        this.country = country;
        this.address = address;
    }


}
