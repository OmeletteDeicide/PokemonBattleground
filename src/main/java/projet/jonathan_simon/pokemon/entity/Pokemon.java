package projet.jonathan_simon.pokemon.entity;

import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pokemon")
public class Pokemon {

    @NotNull
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "type")
    List<typeEnum> type;

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
