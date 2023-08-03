package projet.jonathan_simon.pokemon.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pokemon")
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private typeEnum type;

    @Column(name = "pc")
    private Integer pc;

    @Column(name = "pv")
    private Integer pv;

    @Column(name = "attack")
    private Integer attack;

    @Column(name = "defense")
    private Integer defense;

    public Pokemon() {
        super();
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

    public typeEnum getType() {
        return type;
    }

    public void setType(typeEnum type) {
        this.type = type;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer Hit(Pokemon pokemon1, Pokemon pokemon2) {
        double multiply = 1;
        int damage = 0;
        // Pokemon de type : FEU, EAU, PLANTE
        switch (pokemon1.getType()) {
            case FEU:
                switch (pokemon2.getType()) {
                    case EAU:
                        multiply = 0.5;
                        break;
                    case FEU:
                        multiply = 1;
                        break;
                    case PLANTE:
                        multiply = 2;
                        break;
                }
                break;
            case EAU:
                switch (pokemon2.getType()) {
                    case EAU:
                        multiply = 1;
                        break;
                    case FEU:
                        multiply = 2;
                        break;
                    case PLANTE:
                        multiply = 0.5;
                        break;
                }
                break;
            case PLANTE:
                switch (pokemon2.getType()) {
                    case EAU:
                        multiply = 2;
                        break;
                    case FEU:
                        multiply = 0.5;
                        break;
                    case PLANTE:
                        multiply = 1;
                        break;
                }
                break;
        }
        damage = (int) (multiply * pokemon1.getAttack() - pokemon2.getDefense());
        return damage;
    }

    public String Fight(Pokemon pokemon1, Pokemon pokemon2) {
        String action = "";
        float degats = 0;
        int pv = 0;
        while (pokemon1.getPv() > 0 || pokemon2.getPv() > 0) {
            if (pokemon1.getPv() > 0) {
                degats = pokemon1.Hit(pokemon1, pokemon2);
                pokemon2.setPv((int) (pokemon2.pv - degats));
                action += "Le pokemon " + pokemon1.getName() + " à fait " + degats + " au pokemon "
                        + pokemon2.getName();
            } else {
                action += "Le pokemon " + pokemon1.getName() + " à mis K.O " + pokemon2.getName();
            }
            if (pokemon2.getPv() > 0) {
                degats = pokemon2.Hit(pokemon2, pokemon1);
                pokemon1.setPv((int) (pokemon1.pv - degats));
                action += "Le pokemon " + pokemon2.getName() + " à fait " + degats + " au pokemon "
                        + pokemon1.getName();
            } else {
                action += "Le pokemon " + pokemon2.getName() + " à mis K.O " + pokemon1.getName();
            }
        }
        return action;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", pc=" + pc +
                ", pv=" + pv +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
