package model;

import java.util.ArrayList;
import java.util.Objects;
import model.Pokemon;
import model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test class that tests the trainer class
public class TrainerTest {
    private static ArrayList<Pokemon> listOfOwnedPokemon;
    private static ArrayList<Pokemon> listOfPartyPokemon;
    private static String name;
    private Trainer trainer;

    @BeforeEach
    public void runBefore(){
        listOfOwnedPokemon = new ArrayList<Pokemon>();
        listOfPartyPokemon = new ArrayList<Pokemon>();
        trainer = new Trainer("Jason");
    }

    @Test
    public void testConstructor(){
        assertEquals(0, listOfOwnedPokemon.size());
        assertEquals(0, listOfPartyPokemon.size());
        assertEquals("Jason", trainer.getName());
    }

    @Test
    public void testAddingPokemon(){
        //Party pokemon
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        Pokemon pokemon2 = new Pokemon(15, 5, "Charizard");
        Pokemon pokemon3 = new Pokemon(15, 5, "Charmander");
        trainer.addPartyPokemon(pokemon);
        assertEquals(1, trainer.viewPartyPokemon().size());
        assertEquals("Pikachu", trainer.viewPartyPokemon().get(0));
        trainer.addPartyPokemon(pokemon2);
        trainer.addPartyPokemon(pokemon3);
        assertEquals(3, trainer.viewPartyPokemon().size());
        assertEquals("Pikachu", trainer.viewPartyPokemon().get(0));
        assertEquals("Charizard", trainer.viewPartyPokemon().get(1));
        assertEquals("Charmander", trainer.viewPartyPokemon().get(2));
        //Collection Pokemon
        trainer.addPokemon(pokemon);
        assertEquals(1, trainer.viewPokemon().size());
        assertEquals("Pikachu", trainer.viewPokemon().get(0));
        trainer.addPokemon(pokemon2);
        trainer.addPokemon(pokemon3);
        assertEquals(3, trainer.viewPokemon().size());
        assertEquals("Pikachu", trainer.viewPokemon().get(0));
        assertEquals("Charizard", trainer.viewPokemon().get(1));
        assertEquals("Charmander", trainer.viewPokemon().get(2));
    }

    @Test
    public void testRemovingPokemon(){
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        Pokemon pokemon2 = new Pokemon(15, 5, "Charizard");
        Pokemon pokemon3 = new Pokemon(15, 5, "Charmander");
        trainer.addPokemon(pokemon);
        trainer.addPokemon(pokemon2);
        trainer.addPokemon(pokemon3);
        //Remove pokemon
        trainer.removePokemon(pokemon);
        assertEquals(2, trainer.viewPokemon().size());
        assertEquals("Charizard", trainer.viewPokemon().get(0));
        assertEquals("Charmander", trainer.viewPokemon().get(1));
        trainer.removePokemon(pokemon2);
        trainer.removePokemon(pokemon3);
        assertEquals(0, trainer.viewPokemon().size());
        //Readded
        trainer.addPokemon(pokemon);
        trainer.addPokemon(pokemon2);
        trainer.addPokemon(pokemon3);
        //Remove Pokemon via String name
        trainer.removePokemonString("Pikachu");
        assertEquals(2, trainer.viewPokemon().size());
        assertEquals("Charizard", trainer.viewPokemon().get(0));
        assertEquals("Charmander", trainer.viewPokemon().get(1));
        trainer.removePokemonString("Charizard");
        trainer.removePokemonString("Charmander");
        assertEquals(0, trainer.viewPokemon().size());
    }

    @Test
    public void testViewingPokemon(){
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        Pokemon pokemon2 = new Pokemon(15, 5, "Charizard");
        trainer.addPokemon(pokemon);
        trainer.addPokemon(pokemon2);
        assertEquals(2, trainer.viewPokemon().size());
        assertEquals("Pikachu", trainer.viewPokemon().get(0));
        assertEquals("Charizard", trainer.viewPokemon().get(1));
        trainer.addPartyPokemon(pokemon);
        trainer.addPartyPokemon(pokemon2);
        assertEquals(2, trainer.viewPartyPokemon().size());
        assertEquals("Pikachu", trainer.viewPartyPokemon().get(0));
        assertEquals("Charizard", trainer.viewPartyPokemon().get(1));

    }

    @Test
    public void testGetPokemon(){
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        trainer.addPokemon(pokemon);
        assertSame(pokemon, trainer.getPokemon("Pikachu"));
        assertNull(trainer.getPokemon("x"));
    }

    @Test
    public void testGetManyPokemon(){
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        trainer.addPokemon(pokemon);
        trainer.addPokemon(pokemon);
        assertEquals(2, trainer.getManyPokemon("Pikachu").size());
        assertEquals("Pikachu", trainer.getManyPokemon("Pikachu").get(0).getName());
        assertEquals("Pikachu", trainer.getManyPokemon("Pikachu").get(1).getName());
        assertEquals(0, trainer.getManyPokemon("x").size());
    }

    @Test
    public void testCounting(){
        Pokemon pokemon = new Pokemon(15, 5, "Pikachu");
        trainer.addPokemon(pokemon);
        trainer.addPokemon(pokemon);
        assertEquals(2, trainer.countPokemon("Pikachu"));
        assertEquals(0, trainer.countPokemon("x"));
    }
}
