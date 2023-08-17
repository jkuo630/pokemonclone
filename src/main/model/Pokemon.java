package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

// A pokemon has hitpoints, levels, attacks and name
public class Pokemon implements Writable {
    private int hitpoints;
    private int level;
    private ArrayList<String> attacks;
    private String name;

    // REQUIRES: an int > 0 (for hitpoints), another int > 0 && <= 100 (for level), string with string.length() > 0
    // MODIFIES:
    // EFFECTS: constructs a pokemon iwth the given hitpoints, level, name and attacks is set to default.
    public Pokemon(int hitpoints, int level, String name) {
        this.hitpoints = hitpoints;
        this.level = level;
        this.attacks = new ArrayList<String>();
        this.name = name;

        attacks.add("Scratch");
        attacks.add("Tackle");
        attacks.add("Flail");

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public int getHitpoints() {
        return hitpoints;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public int getLevel() {
        return level;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public ArrayList<String> getAttacks() {
        return attacks;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public String getName() {
        return name;
    }

    // REQUIRES: an arraylist of pokemon
    // MODIFIES: wildPokemon (the arraylist of pokemon)
    // EFFECTS: populates it with new pokemon
    public static void populateWildPokemon(ArrayList<Pokemon> wildPokemon) {
        Pokemon pikachu = new Pokemon(15, 5, "Pikachu");
        Pokemon charizard = new Pokemon(15, 5, "Charizard");
        Pokemon gengar = new Pokemon(15, 5, "Gengar");
        Pokemon umbreon = new Pokemon(15, 5, "Umbreon");
        Pokemon lucario = new Pokemon(15, 5, "Lucario");
        Pokemon gardevoir = new Pokemon(15, 5, "Gardevoir");
        Pokemon dragonite = new Pokemon(15, 5, "Dragonite");
        Pokemon typhlosion = new Pokemon(15, 5, "Typhlosion");
        Pokemon tyranitar = new Pokemon(15, 5, "Tyranitar");
        Pokemon infernape = new Pokemon(15, 5, "Infernape");
        Pokemon snorlax = new Pokemon(15, 5, "Snorlax");
        wildPokemon.add(pikachu);
        wildPokemon.add(charizard);
        wildPokemon.add(gengar);
        wildPokemon.add(umbreon);
        wildPokemon.add(lucario);
        wildPokemon.add(gardevoir);
        wildPokemon.add(dragonite);
        wildPokemon.add(typhlosion);
        wildPokemon.add(tyranitar);
        wildPokemon.add(infernape);
        wildPokemon.add(snorlax);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns a randomly generated int from 1-10
    public static int generateRandomInt() {
        int max = 10 - 1;
        int min = 0;
        int randomInt = (int)Math.floor(Math.random() * (max - min + 1) + min);
        return randomInt;
    }

    // REQUIRE: num from 1-10
    // MODIFIES:
    // EFFECTS: checks if num < 3, if so, return true, if not, return false
    public static boolean checkIfCaught(int num) {
        if (num < 3) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: creates a new json file with the state of each pokemon
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hitpoints", hitpoints);
        json.put("level", level);
        return json;
    }

}
