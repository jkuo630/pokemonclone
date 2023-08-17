package persistence;

import model.Pokemon;
import model.Trainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPokemon(String name, Pokemon pokemon) {
        assertEquals(name, pokemon.getName());
    }
}
