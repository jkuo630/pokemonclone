package persistence;

import org.junit.jupiter.api.Test;
import model.Pokemon;
import model.Trainer;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Trainer trainer = new Trainer("Jason");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTrainer() {
        try {
            Trainer trainer = new Trainer("Jason");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTrainer.json");
            writer.open();
            writer.write(trainer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTrainer.json");
            trainer = reader.read();
            assertEquals("Jason", trainer.getName());
            assertEquals(0, trainer.countOwnedPokemon());
            assertEquals(0, trainer.countPartyPokemon());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTrainer() {
        try {
            Trainer trainer = new Trainer("Jason");
            trainer.addPokemon(new Pokemon(15, 5, "Pikachu"));
            trainer.addPartyPokemon(new Pokemon(15,  5, "Bulbasaur"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTrainer.json");
            writer.open();
            writer.write(trainer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTrainer.json");
            trainer = reader.read();
            assertEquals("Jason", trainer.getName());
            List<Pokemon> partyPokemon = trainer.getPartyPokemon();
            List<Pokemon> collectionPokemon = trainer.getOwnedPokemon();
            assertEquals(1, partyPokemon.size());
            checkPokemon("Bulbasaur", partyPokemon.get(0));
            assertEquals(1, collectionPokemon.size());
            checkPokemon("Pikachu", collectionPokemon.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
