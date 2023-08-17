package persistence;

import model.Pokemon;
import model.Trainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// class and methods inspired by https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trainer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrainer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trainer from JSON object and returns it
    private Trainer parseTrainer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Trainer trainer = new Trainer(name);
        addOwnedPokemon(trainer, jsonObject);
        addPartyPokemon(trainer, jsonObject);
        return trainer;
    }

    // MODIFIES: trainer
    // EFFECTS: parses owned pokemons from JSON object and adds them to trainer
    private void addOwnedPokemon(Trainer trainer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of owned pokemon");
        for (Object json : jsonArray) {
            JSONObject nextOwnedPokemons = (JSONObject) json;
            addPokemon(trainer, nextOwnedPokemons);
        }
    }

    // MODIFIES: trainer
    // EFFECTS: parses party pokemons from JSON object and adds them to trainer
    private void addPartyPokemon(Trainer trainer, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of party pokemon");
        for (Object json : jsonArray) {
            JSONObject nextPartyPokemons = (JSONObject) json;
            addPPokemon(trainer, nextPartyPokemons);
        }
    }

    // MODIFIES: trainer
    // EFFECTS: parses collection pokemon from JSON object and adds it to trainer
    private void addPokemon(Trainer trainer, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int hitpoints = jsonObject.getInt("hitpoints");
        int level = jsonObject.getInt("level");
        Pokemon pokemon = new Pokemon(hitpoints, level, name);
        trainer.addPokemon(pokemon);
    }

    // MODIFIES: trainer
    // EFFECTS: parses party pokemon from JSON object and adds it to trainer
    private void addPPokemon(Trainer trainer, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int hitpoints = jsonObject.getInt("hitpoints");
        int level = jsonObject.getInt("level");
        Pokemon pokemon = new Pokemon(hitpoints, level, name);
        trainer.addPartyPokemon(pokemon);
    }
}
