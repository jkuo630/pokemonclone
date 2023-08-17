package persistence;

import model.Pokemon;
import model.Trainer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Trainer trainer = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTrainer.json");
        try {
            Trainer trainer = reader.read();
            assertEquals("Jason", trainer.getName());
            assertEquals(0, trainer.countOwnedPokemon());
            assertEquals(0, trainer.countPartyPokemon());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTrainer.json");
        try {
            Trainer trainer = reader.read();
            assertEquals("Jason", trainer.getName());
            List<Pokemon> partyPokemon = trainer.getPartyPokemon();
            List<Pokemon> collectionPokemon = trainer.getOwnedPokemon();
            assertEquals(3, partyPokemon.size());
            checkPokemon("Lucario", partyPokemon.get(0));
            checkPokemon("Tyranitar", partyPokemon.get(1));
            checkPokemon("Typhlosion", partyPokemon.get(2));
            assertEquals(2, collectionPokemon.size());
            checkPokemon("Bulbasaur", collectionPokemon.get(0));
            checkPokemon("Gengar", collectionPokemon.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
