package projet.jonathan_simon.pokemon.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.jonathan_simon.pokemon.entity.Pokemon;
import projet.jonathan_simon.pokemon.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemonById(final Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void deletePokemon(final Long id) {
        pokemonRepository.deleteById(id);
        return;
    }
}