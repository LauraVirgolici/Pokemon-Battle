package pokemon;

public class Parser {

    PokemonFactory pokemonFactory= new PokemonFactory();

    public Trainer GetTrainer(String line){
        String[] trainerInfo = line.split("\\s");
        return  new Trainer(trainerInfo[0], Integer.parseInt(trainerInfo[1]));
    }

    public Pokemon GetPokemon(String line) {

        Pokemon pokemon=null;
        if(line.equals("Neutrel1"))
        pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Neutrel1);

        if(line.equals("Neutrel2"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Neutrel2);

        if(line.equals("Pikachu"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Pikachu);

        if(line.equals("Bulbasaur"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Bulbasaur);

        if(line.equals("Charmander"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Charmander);

        if(line.equals("Squirtle"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Squirtle);

        if(line.equals("Snorlax"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Snorlax);

        if(line.equals("Vulpix"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Vulpix);

        if(line.equals("Eevee"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Eevee);

        if(line.equals("Jigglypuff"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Jigglypuff);

        if(line.equals("Meowth"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Meowth);

        if(line.equals("Psyduck"))
            pokemon=this.pokemonFactory.createPokemon(PokemonFactory.PokemonType.Psyduck);

        return pokemon;
    }


    public void boostPokemon(String line, Pokemon pokemon){

        line=line.replace("(", "");
        line=line.replace(")", "");

        String[] itemInfo = line.split(",");

        for(String item: itemInfo) {

            if(item.equals("Shield"))
                pokemon.addShield();

            if(item.equals("Vest"))
                pokemon.addVest();

            if(item.equals("Sword"))
                pokemon.addSword();

            if(item.equals("Magic Wand"))
                pokemon.addMagicWand();

            if(item.equals("Vitamins"))
                pokemon.addVitamins();

            if(item.equals("Christmas Tree"))
                pokemon.addChristmasTree();

            if(item.equals("Cape"))
                pokemon.addCape();
        }

    }


}
