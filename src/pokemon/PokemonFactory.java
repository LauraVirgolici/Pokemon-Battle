package pokemon;

public class PokemonFactory {

    private static PokemonFactory pokemonFactory;
    PokemonFactory() {
    }

    public static PokemonFactory Instance() {
        if (pokemonFactory == null)
            pokemonFactory = new PokemonFactory();
        return pokemonFactory;
    }

    public enum PokemonType {
        Neutrel1 , Neutrel2, Pikachu,
        Bulbasaur, Charmander, Squirtle,
        Snorlax, Vulpix, Eevee,
        Jigglypuff, Meowth, Psyduck
    }


    public Pokemon createPokemon(PokemonType type){
        switch(type){
            case Neutrel1:{
                return new Pokemon.PokemonBuilder("Neutrel1")
                        .HP(10)
                        .attack(3, null)
                        .defense(1,1)
                        .build();
            }

            case Neutrel2:{
                return new Pokemon.PokemonBuilder("Neutrel2")
                        .HP(20)
                        .attack(4, null)
                        .defense(1,1)
                        .build();
            }

            case Pikachu:{
                return new Pokemon.PokemonBuilder("Pikachu")
                        .HP(35)
                        .attack(null, 4)
                        .defense(2,3)
                        .ability(new Ability(6, false, false,4 ),
                                new Ability(4, true, true, 5))
                        .build();
            }

            case Bulbasaur:{
                return new Pokemon.PokemonBuilder("Bulbasaur")
                        .HP(42)
                        .attack(null, 5)
                        .defense(3,1)
                        .ability(new Ability(6, false, false,4 ),
                                new Ability(5, false, false, 3))
                        .build();
            }

            case Charmander:{
                return new Pokemon.PokemonBuilder("Charmander")
                        .HP(50)
                        .attack(4, null)
                        .defense(3,2)
                        .ability(new Ability(4, true, false,4 ),
                                new Ability(7, false, false, 6))
                        .build();
            }

            case Squirtle:{
                return new Pokemon.PokemonBuilder("Squirtle")
                        .HP(60)
                        .attack(null, 3)
                        .defense(5,5)
                        .ability(new Ability(4, false, false,3 ),
                                new Ability(2, true, false, 2))
                        .build();
            }

            case Snorlax: {
                return new Pokemon.PokemonBuilder("Snorlax")
                        .HP(62)
                        .attack(3, null)
                        .defense(6, 4)
                        .ability(new Ability(4, true, false, 5),
                                new Ability(0, false, true, 5))
                        .build();
            }

            case Vulpix:{
                return new Pokemon.PokemonBuilder("Vulpix")
                        .HP(36)
                        .attack(5, null)
                        .defense(2, 4)
                        .ability(new Ability(8, true, false, 6),
                                new Ability(2, false, true, 7))
                        .build();
            }

            case Eevee:{
                return new Pokemon.PokemonBuilder("Eevee")
                        .HP(39)
                        .attack(null, 4)
                        .defense(3, 3)
                        .ability(new Ability(5, false, false, 3),
                                new Ability(3, true, false, 3))
                        .build();
            }

            case Jigglypuff:{
                return new Pokemon.PokemonBuilder("Jigglypuff")
                        .HP(34)
                        .attack(4, null)
                        .defense(2, 3)
                        .ability(new Ability(4, true, false, 4),
                                new Ability(3, true, false, 4))
                        .build();
            }

            case Meowth:{
                return new Pokemon.PokemonBuilder("Jigglypuff")
                        .HP(41)
                        .attack(3, null)
                        .defense(4, 2)
                        .ability(new Ability(5, false, true, 4),
                                new Ability(1, false, true, 3))
                        .build();
            }

            case Psyduck:{
                return new Pokemon.PokemonBuilder("Psyduck")
                        .HP(43)
                        .attack(3, null)
                        .defense(3, 3)
                        .ability(new Ability(2, false, false, 4),
                                new Ability(2, true, false, 5))
                        .build();
            }
        }
        throw new IllegalArgumentException("Pokemon " + type + " not found.");
    }



}
