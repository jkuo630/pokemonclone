package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Trainer with two lists of pokemon, collection and party and a name as well
public class Trainer implements Writable {

    private ArrayList<Pokemon> listOfOwnedPokemon;
    private ArrayList<Pokemon> listOfPartyPokemon;
    private String name;

    public Trainer(String trainerName) {
        listOfOwnedPokemon = new ArrayList<Pokemon>();
        listOfPartyPokemon = new ArrayList<Pokemon>();
        name = trainerName;
    }

    // REQUIRES: pokemon object
    // MODIFIES: this
    // EFFECTS: adds passed in pokemon to the listOfOwnedPokemon arraylist
    public void addPokemon(Pokemon pokemon) {
        listOfOwnedPokemon.add(pokemon);
        EventLog.getInstance().logEvent(new Event(pokemon.getName() + " has been added to the collection!"));
    }

    public String getName() {
        return name;
    }

    // REQUIRES: pokemon object
    // MODIFIES: Arraylist of listOfPartyPokemon
    // EFFECTS: adds pokemon to party pokemon
    public void addPartyPokemon(Pokemon pokemon) {
        listOfPartyPokemon.add(pokemon);
        EventLog.getInstance().logEvent(new Event(pokemon.getName() + " has been added to the party!"));
    }

    // REQUIRES: pokemon object
    // MODIFIES: Arraylist of listOfOwnedPokemon
    // EFFECTS: removes pokemon from owned pokemon collection
    public void removePokemon(Pokemon pokemon) {
        listOfOwnedPokemon.remove(pokemon);
        EventLog.getInstance().logEvent(new Event(pokemon.getName() + " has been removed from the "
                + "collection."));
    }

    // REQUIRES: string, name of valid pokemon
    // MODIFIES: Arraylist of listOfOwnedPokemon
    // EFFECTS: removes pokemon to owned pokemon collection via name of pokemon (string)
    public void removePokemonString(String pokemon) {
        removePokemon(getPokemon(pokemon));
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns an arraylist of string, containing the all the owned pokemon the trainer has
    public ArrayList<String> viewPokemon() {
        ArrayList<String> pokemons = new ArrayList<String>();
        for (Pokemon pokemon : listOfOwnedPokemon) {
            String name = pokemon.getName();
            pokemons.add(name);
        }
        EventLog.getInstance().logEvent(new Event("Pokemon collection has been viewed."));
        return pokemons;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns an arraylist of string, containing the all the party pokemon the trainer has
    public ArrayList<String> viewPartyPokemon() {
        ArrayList<String> pokemons = new ArrayList<String>();
        for (Pokemon pokemon : listOfPartyPokemon) {
            String name = pokemon.getName();
            pokemons.add(name);
        }
        return pokemons;
    }

    // REQUIRES: string, name of a valid pokemon (preexisting)
    // MODIFIES:
    // EFFECTS: searches arraylist of owned pokemon and returns the single pokemon
    public Pokemon getPokemon(String name) {
        for (Pokemon pokemon : listOfOwnedPokemon) {
            if (Objects.equals(name, pokemon.getName())) {
                EventLog.getInstance().logEvent(new Event(name + " has been viewed."));
                return pokemon;
            }
        }
        return null;
    }

    // REQUIRES: string, name of valid pokemon (preexisting)
    // MODIFIES:
    // EFFECTS: searchs arraylist of owned pokemon and returns every pokemon with that name
    public ArrayList<Pokemon> getManyPokemon(String name) {
        ArrayList<Pokemon> manyPokemon = new ArrayList<Pokemon>();
        for (Pokemon pokemon : listOfOwnedPokemon) {
            if (Objects.equals(name, pokemon.getName())) {
                manyPokemon.add(pokemon);
            }
        }
        return manyPokemon;
    }

    // REQUIRES: string, name of valid pokemon (preexiting)
    // MODIFIES:
    // EFFECTS: searching arraylist of owned pokemon and returns the number of pokemon with that name
    public int countPokemon(String name) {
        int count = 0;
        for (Pokemon pokemon : listOfOwnedPokemon) {
            if (Objects.equals(name, pokemon.getName())) {
                count++;
            }
        }
        return count;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the amount of pokemon in owned pokemon
    public int countOwnedPokemon() {
        return listOfOwnedPokemon.size();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns the amount of pokemon in party pokemon
    public int countPartyPokemon() {
        return listOfPartyPokemon.size();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns party pokemon
    public ArrayList<Pokemon> getPartyPokemon() {
        EventLog.getInstance().logEvent(new Event("Pokemon party has been viewed."));
        return listOfPartyPokemon;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: returns collection pokemon
    public ArrayList<Pokemon> getOwnedPokemon() {
        EventLog.getInstance().logEvent(new Event("Pokemon collection has been viewed."));
        return listOfOwnedPokemon;
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS: creates a new json file with the state of each trainer
    // method inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("list of owned pokemon", ownedPokemonToJson());
        json.put("list of party pokemon", partyPokemonToJson());
        return json;
    }

    // method inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns pokemon in trainer's list of owned pokemon as a JSON array
    private JSONArray ownedPokemonToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Pokemon pokemon : listOfOwnedPokemon) {
            jsonArray.put(pokemon.toJson());
        }
        return jsonArray;
    }


    // method inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns pokemon in trainer's list of party pokemon as a JSON array
    private JSONArray partyPokemonToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Pokemon pokemon : listOfPartyPokemon) {
            jsonArray.put(pokemon.toJson());
        }
        return jsonArray;
    }
}
