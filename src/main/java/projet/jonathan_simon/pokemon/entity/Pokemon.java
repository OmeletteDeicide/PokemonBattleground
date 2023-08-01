package projet.jonathan_simon.pokemon.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "pokemon")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    Set<typeEnum> type;

    @Column(name = "pc")
    Integer pc;

    @Column(name = "pv")
    Integer pv;

    @Column(name = "attack")
    Integer attack;

    @Column(name = "defense")
    Integer defense;

    public Pokemon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
