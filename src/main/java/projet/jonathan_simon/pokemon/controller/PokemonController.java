package projet.jonathan_simon.pokemon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        Pokemon pokemon = new Pokemon();
        model.addAttribute("pokemon", pokemon);
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
    public String greetingSubmitFight(@ModelAttribute("pokemonId") Pokemon pokemonA, Model model) {
        Optional<Pokemon> pokemonAttaquant = service.getPokemonById(pokemonA.getId());
        Random r = new Random();
        Long defenserId = r.nextLong(service.countAll());
        Optional<Pokemon> pokemonDefenseur = service.getPokemonById(defenserId);
        Pokemon pokemonD = new Pokemon();
        if (pokemonAttaquant.isPresent()) {
            pokemonA = pokemonAttaquant.get();
        }
        if (pokemonDefenseur.isPresent()) {
            pokemonD = pokemonDefenseur.get();
        }
        model.addAttribute("pokemonAttaquant", pokemonA);
        model.addAttribute("pokemonDefenseur", pokemonD);

        return "battle2";
    }

    @GetMapping("/battle2")
    public String formFight2(@RequestParam("pokemonAttaquant") Pokemon pokemonA,
            @RequestParam("pokemonDefenseur") Pokemon pokemonD, Model model) {

        model.addAttribute("pokemonAttaquant", pokemonA);
        model.addAttribute("pokemonDefenseur", pokemonD);
        return "battle2";
    }

    @PostMapping("/battle2")
    public String greetingSubmitFight2(@RequestParam("pokemonAttaquant") Pokemon pokemonA,
            @RequestParam("pokemonDefenseur") Pokemon pokemonD, Model model) {
        model.addAttribute("pokemonAttaquant", pokemonA);
        model.addAttribute("pokemonDefenseur", pokemonD);

        return "battle3";
    }

    @GetMapping("/battle3")
    public String formFight3(@RequestParam("pokemonAttaquant") Long pokemonAttaquantId,
            @RequestParam("pokemonDefenseur") Long pokemonDefenseurId, Model model) {

        Optional<Pokemon> pokemonAttaquant = service.getPokemonById(pokemonAttaquantId);
        Optional<Pokemon> pokemonDefenseur = service.getPokemonById(pokemonDefenseurId);

        if (pokemonAttaquant.isPresent() && pokemonDefenseur.isPresent()) {
            model.addAttribute("pokemonAttaquant", pokemonAttaquant.get());
            model.addAttribute("pokemonDefenseur", pokemonDefenseur.get());
            return "battle3";
        } else {
            // Gérer les cas où les Pokémon ne peuvent pas être trouvés
            return "redirect:/erreur"; // rediriger vers une page d'erreur par exemple
        }
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
