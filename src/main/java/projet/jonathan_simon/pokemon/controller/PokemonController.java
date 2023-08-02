package projet.jonathan_simon.pokemon.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import projet.jonathan_simon.pokemon.entity.Pokemon;
import projet.jonathan_simon.pokemon.service.PokemonService;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("index", pokemonService.getPokemons());
        return "index";
    }

    @GetMapping(value = "/pokemons")
    public String pokemons(Model model) {
        model.addAttribute("pokemons", pokemonService.getPokemons());
        return "pokeList";
    }

    @GetMapping(value = "/pokeForm")
    public String createLink(Model model) {
        model.addAttribute("pokeForm", new Pokemon());
        return "pokeForm";
    }

    @PostMapping(value = "/pokeForm")
    public String pokeSubmit(@ModelAttribute Pokemon pokemon, Model model) {
        model.addAttribute("pokeForm", pokemon);
        return "pokeList";
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            pokemonService.deletePokemon(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> getAll() {
        try {
            List<Pokemon> pokemons = new ArrayList<Pokemon>();

            pokemonService.getPokemons().forEach(pokemons::add);

            if (pokemons.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(pokemons, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable("id") Long id) {
        Optional<Pokemon> existingItemOptional = pokemonService.getPokemonById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon pokemon) {
        try {
            Pokemon savedItem = pokemonService.savePokemon(pokemon);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable("id") Long id, @RequestBody Pokemon item) {
        Optional<Pokemon> existingItemOptional = pokemonService.getPokemonById(id);
        if (existingItemOptional.isPresent()) {
            Pokemon existingItem = existingItemOptional.get();
            System.out
                    .println("TODO for developer - update logic is unique to entity and must be implemented manually.");
            // existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(pokemonService.savePokemon(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
