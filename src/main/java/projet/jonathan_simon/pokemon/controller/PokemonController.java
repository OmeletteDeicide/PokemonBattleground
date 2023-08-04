package projet.jonathan_simon.pokemon.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

    // Page d'accueil de notre site
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("index", service.getPokemons());
        return "index";
    }

    // Page de présentation de tous les pokémons en BDD
    @GetMapping(value = "/pokemons")
    public String pokemons(Model model) {
        model.addAttribute("pokemons", service.getPokemons());
        return "pokeList";
    }

    // Page de présentation de la création de pokémons en BDD
    @GetMapping("/pokeForm")
    public String form(Model model) {
        Pokemon pokemon = new Pokemon();
        model.addAttribute("pokemon", pokemon);
        return "pokeForm";
    }

    // Page de création de pokémons en BDD
    @PostMapping("/pokeForm")
    public String greetingSubmit(Pokemon pokemon) {
        int pvRan = (int) (Math.abs(Math.random() * ((200 - 20) + 20)));
        int attackRan = (int) (Math.abs(Math.random() * ((60 - 30) + 30)));
        int defenseRan = (int) (Math.abs(Math.random() * ((50 - 25) + 25)));
        int pcCalc = pvRan * ((attackRan + defenseRan) / 2);
        pokemon.setAttack(attackRan);
        pokemon.setDefense(defenseRan);
        pokemon.setPv(pvRan);
        pokemon.setPc(pcCalc);
        service.savePokemon(pokemon);
        return "pokeSucces";
    }

    // Page de combat de pokémons
    @GetMapping("/battle")
    public String formFight(Model model) {
        Pokemon pokemonId = new Pokemon();
        // Permet d'afficher dasn le select
        model.addAttribute("pokemons", service.getPokemons());
        model.addAttribute("pokemonId", pokemonId);
        return "battle";
    }

    // Page de combat de pokémons
    @PostMapping("/battle")
    public String greetingSubmitFight(@ModelAttribute("pokemonId") Pokemon pokemonA, Model model) {
        Optional<Pokemon> pokemonAttaquant = service.getPokemonById(pokemonA.getId());
        Random r = new Random();
        List<Pokemon> pokemonsList = service.getPokemons();
        int defenserPosition = pokemonsList.size();
        int position = r.nextInt(defenserPosition);
        Long defenserId = pokemonsList.get(position).getId();
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

    // Page de combat de pokémons
    @GetMapping("/battle2")
    public String formFight2(@RequestParam("pokemonAttaquant") Pokemon pokemonA,
            @RequestParam("pokemonDefenseur") Pokemon pokemonD, Model model) {

        model.addAttribute("pokemonAttaquant", pokemonA);
        model.addAttribute("pokemonDefenseur", pokemonD);
        return "battle2";
    }

    // Page de combat de pokémons
    @PostMapping("/battle2")
    public String greetingSubmitFight2(@RequestParam("pokemonAttaquant") Pokemon pokemonA,
            @RequestParam("pokemonDefenseur") Pokemon pokemonD, Model model) {

        String resultatCombat = pokemonA.Fight(pokemonA, pokemonD);

        model.addAttribute("resultatCombat", resultatCombat);
        model.addAttribute("pokemonAttaquant", pokemonA);
        model.addAttribute("pokemonDefenseur", pokemonD);

        return "battle3";
    }

    // Page de combat de pokémons
    @GetMapping("/battle3")
    public String formFight3(@RequestParam("pokemonAttaquant") Long pokemonAttaquantId,
            @RequestParam("pokemonDefenseur") Long pokemonDefenseurId,
            @RequestParam("resultCombat") String resultCombat, Model model) {
        Optional<Pokemon> pokemonAttaquant = service.getPokemonById(pokemonAttaquantId);
        Optional<Pokemon> pokemonDefenseur = service.getPokemonById(pokemonDefenseurId);
        model.addAttribute("resultatCombat", resultCombat);

        if (pokemonAttaquant.isPresent() && pokemonDefenseur.isPresent()) {
            Pokemon pokemonA = pokemonAttaquant.get();
            Pokemon pokemonD = pokemonDefenseur.get();

            model.addAttribute("pokemonAttaquant", pokemonA);
            model.addAttribute("pokemonDefenseur", pokemonD);

            return "battle3";
        } else {
            // Gérer les cas où les Pokémon ne peuvent pas être trouvés
            return "redirect:/erreur"; // rediriger vers une page d'erreur par exemple
        }
    }

    // Page de présentation de la suppression de pokémons en BDD
    @GetMapping("/pokeDelete")
    public String formDelete(Model model) {
        Pokemon pokemonId = new Pokemon();
        // Permet d'afficher dasn le select
        model.addAttribute("pokemons", service.getPokemons());
        model.addAttribute("pokemonId", pokemonId);
        return "pokeDelete";
    }

    // Page de suppression de pokémons en BDD
    @PostMapping("/pokeDelete")
    public String greetingSubmitDelete(@ModelAttribute("pokemonId") Pokemon pokemon) {
        service.deletePokemon(pokemon.getId());
        return "pokeDeleteSuccess";
    }

    // Page d'affichage d'un pokémon en BDD
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        Optional<Pokemon> existingItemOptional = service.getPokemonById(id);
        Pokemon pokemon = new Pokemon();
        if (existingItemOptional.isPresent()) {
            pokemon = existingItemOptional.get();
        }
        model.addAttribute("pokemon", pokemon);
        return "pokeDetail";
    }

    // Page de suppresion d'un pokémon en BDD depuis l'affichage unique du pokémon
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        try {
            service.deletePokemon(id);
            return "pokeDeleteSuccess";
        } catch (Exception e) {
            return "Error";
        }
    }
}
