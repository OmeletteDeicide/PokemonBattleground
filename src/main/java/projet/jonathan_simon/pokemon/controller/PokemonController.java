package projet.jonathan_simon.pokemon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projet.jonathan_simon.pokemon.entity.Pokemon;
import projet.jonathan_simon.pokemon.service.PokemonService;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("index", service.getPokemons());
        return "index";
    }

    @GetMapping(value = "/pokemons")
    public String pokemons(Model model) {
        model.addAttribute("pokemons", service.getPokemons());
        return "pokeList";
    }

    @GetMapping("/pokeForm")
    public String form(Model model) {
        model.addAttribute("pokemon", new Pokemon());
        return "pokeForm";
    }
    @PostMapping("/pokeForm")
    public String greetingSubmit(Pokemon pokemon) {
        service.savePokemon(pokemon);
        return "pokeSucces";
    }

    @GetMapping("/battle")
    public String formFight(Model model) {
            Pokemon pokemonId = new Pokemon();
            // Permet d'afficher dasn le select
            model.addAttribute("pokemons", service.getPokemons());
            model.addAttribute("pokemonId", pokemonId);
        return "battle";
    }

    @PostMapping("/battle")
    public String greetingSubmitFight(@ModelAttribute("pokemonId") Pokemon pokemon) {
        Optional<Pokemon> pokemonAttaquant = service.getPokemonById(pokemon.getId());
        if (pokemonAttaquant.isPresent())
        {
            pokemon = pokemonAttaquant.get();
            System.out.println(pokemon);
        }
        return "battle";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.deletePokemon(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> getAll() {
        try {
            List<Pokemon> pokemons = new ArrayList<Pokemon>();

            service.getPokemons().forEach(pokemons::add);

            if (pokemons.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(pokemons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable("id") Long id) {
        Optional<Pokemon> existingItemOptional = service.getPokemonById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
