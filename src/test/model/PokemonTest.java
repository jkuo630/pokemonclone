package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// class that represents testing pokemon
public class PokemonTest {
    private int hitpoints;
    private int level;
    private ArrayList<String> attacks;
    private String name;
    private Pokemon pokemon;

    @BeforeEach
    public void runBefore(){
        pokemon = new Pokemon(15, 5, "Pikachu");
    }

    @Test
    public void testConstructor(){
        assertEquals(15, pokemon.getHitpoints());
        assertEquals(5, pokemon.getLevel());
        assertEquals("Pikachu", pokemon.getName());
        assertEquals(3, pokemon.getAttacks().size());
        assertEquals("Scratch", pokemon.getAttacks().get(0));
        assertEquals("Tackle", pokemon.getAttacks().get(1));
        assertEquals("Flail", pokemon.getAttacks().get(2));
    }

    @Test
    public void testPopulateWildPokemon(){
        ArrayList<Pokemon> wildPokemon = new ArrayList<>();
        Pokemon.populateWildPokemon(wildPokemon);
        assertEquals(11, wildPokemon.size());
        assertEquals("Pikachu", wildPokemon.get(0).getName());
        assertEquals("Charizard", wildPokemon.get(1).getName());
        assertEquals("Gengar", wildPokemon.get(2).getName());
        assertEquals("Umbreon", wildPokemon.get(3).getName());
        assertEquals("Lucario", wildPokemon.get(4).getName());
        assertEquals("Gardevoir", wildPokemon.get(5).getName());
        assertEquals("Dragonite", wildPokemon.get(6).getName());
        assertEquals("Typhlosion", wildPokemon.get(7).getName());
        assertEquals("Tyranitar", wildPokemon.get(8).getName());
        assertEquals("Infernape", wildPokemon.get(9).getName());
        assertEquals("Snorlax", wildPokemon.get(10).getName());
    }

    @Test
    public void testGenerateRandomInt(){
        assertTrue(Pokemon.generateRandomInt() >= 0);
        assertTrue(Pokemon.generateRandomInt() <= 10);
    }

    @Test
    public void testCheckIfCaught(){
        assertTrue(Pokemon.checkIfCaught(1));
        assertTrue(Pokemon.checkIfCaught(2));
        assertFalse(Pokemon.checkIfCaught(3));
        assertFalse(Pokemon.checkIfCaught(4));
        assertFalse(Pokemon.checkIfCaught(5));
        assertFalse(Pokemon.checkIfCaught(6));
        assertFalse(Pokemon.checkIfCaught(7));
        assertFalse(Pokemon.checkIfCaught(8));
        assertFalse(Pokemon.checkIfCaught(9));
        assertFalse(Pokemon.checkIfCaught(10));
    }
}
