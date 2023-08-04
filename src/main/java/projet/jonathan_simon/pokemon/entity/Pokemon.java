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
        int attack = 0;
        int defense = 0;
        int pv = 0;
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
        int attack = 0;
        int health = 0;
        float degats = 0;
        int pv = 0;
        Pokemon pokemonAttaquant = pokemon1;
        Pokemon pokemonDefenseur = pokemon2;
        while (pokemonAttaquant.pv > 0 || pokemonDefenseur.pv > 0) {
            if (pokemonAttaquant.pv > 0) {
                degats = pokemonAttaquant.Hit(pokemonAttaquant, pokemonDefenseur);
                pokemonDefenseur.pv = ((int) (pokemonDefenseur.pv - degats));
                action += "Le pokemon " + pokemonAttaquant.getName() + " à fait " + degats + " au pokemon "
                        + pokemonDefenseur.getName() + "\n";
            } else {
                action += "Le pokemon " + pokemonAttaquant.getName() + " à mis K.O " + pokemonDefenseur.getName()
                        + "\n";
                return action;
            }
            if (pokemonDefenseur.pv > 0) {
                degats = pokemonDefenseur.Hit(pokemonDefenseur, pokemonAttaquant);
                pokemonAttaquant.pv = ((int) (pokemonAttaquant.pv - degats));
                action += "Le pokemon " + pokemonDefenseur.getName() + " à fait " + degats + " au pokemon "
                        + pokemonAttaquant.getName() + "\n";
            } else {
                action += "Le pokemon " + pokemonDefenseur.getName() + " à mis K.O " + pokemonAttaquant.getName()
                        + "\n";
                return action;
            }
        }
        return action;
    }
}
