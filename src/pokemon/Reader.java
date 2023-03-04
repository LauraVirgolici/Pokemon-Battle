package pokemon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    Parser parser;
    public Reader() {
        this.parser = new Parser();
    }

    public void readPokemons(BufferedReader reader, Trainer trainer) throws IOException {

        int numberPokemons = 3;
        while (numberPokemons != 0) {
            String line = reader.readLine();

            String[] pokemonInfo = line.split("\\s");
            Pokemon pokemon = parser.GetPokemon(pokemonInfo[0]);
            parser.boostPokemon(pokemonInfo[1], pokemon);

            trainer.getPokemons().add(pokemon);
            numberPokemons--;

        }

    }


    public void readBattle(String filePath, String outputFilePath)  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            Trainer trainer1 = parser.GetTrainer(line);
            this.readPokemons(reader, trainer1);

            reader.readLine();   //line for separation

            line= reader.readLine();
            Trainer trainer2 = parser.GetTrainer(line);
            this.readPokemons(reader, trainer2);


            /*SEND TO BATTLE*/
            Arena arena= new Arena();
            arena.adventure(trainer1, trainer2, outputFilePath);
            reader.close();


        } catch (IOException | InterruptedException e) {
           e.printStackTrace();
        }

    }
}