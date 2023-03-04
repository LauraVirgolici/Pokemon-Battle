package pokemon;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Battle implements Runnable {

    Pokemon pokemon1;
    Pokemon pokemon2;
    int index;
    String battle_type;
    Trainer trainer1;
    Trainer trainer2;
    Parser parser;
    String filePath;
    Printer printer;
    StringBuilder output=new StringBuilder();


    public Battle(Trainer trainer1, Trainer trainer2, int index, String type, String filePath, Printer printer) {
        this.pokemon1 = trainer1.getPokemons().get(index);
        this.pokemon2 = trainer2.getPokemons().get(index);
        this.index = index;
        this.battle_type = type;
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.parser = new Parser();
        this.filePath = filePath;
        this.printer=printer;
    }

    public void normalAttack(Pokemon attacked, Pokemon attacker) {

        if (attacker.normalAttack != null) {
            if (attacked.getDefense() < attacker.normalAttack)
                attacked.setHP(attacked.getHP() + attacked.getDefense() - attacker.normalAttack);
        }
            if (attacker.specialAttack != null) {
                if (attacked.getDefense() < attacker.specialAttack)
                    attacked.setHP(attacked.getHP() + attacked.getSpecialDefense() - attacker.specialAttack);
            }

        }


        public void abilityAttack (Ability ability, Pokemon attacked){

            if (attacked.getDefense() < ability.dmg)
                attacked.setHP(attacked.getHP() + attacked.getDefense() - ability.dmg);
        }

        public String getLegalAttack (Trainer trainer, Pokemon pokemon){

            String attack = trainer.randomAttack();    // trainers move

            while (true) {
                if (attack.equals("NormalAttack"))
                    break;

                if (attack.equals("Ability1") && pokemon.ability1.getCd() == pokemon.ability1.getCdReset())
                    break;

                if (attack.equals("Ability2") && pokemon.ability2.getCd() == pokemon.ability2.getCdReset())
                    break;

                attack = trainer.randomAttack();
            }
            return attack;
        }


        public void NormalAttack (Pokemon attacker, Pokemon attacked, StringBuilder output){
            normalAttack(attacked, attacker);
            output.append("\n" + attacker.getName() + " ataca " + attacked.getName() + " cu Attack" + "    ===>    Rezultat: " + attacked);

            if (attacker.ability1.activated == 1) {
                if (attacker.ability1.getCd() >= 0) {
                    output.append(", Ability 1, cooldown " + attacker.ability1.getCd());
                    attacker.ability1.countCd();
                } else {
                    attacker.ability1.resetCd();
                    attacker.ability1.activated = 0;
                }
            }

            if (attacker.ability2.activated == 1) {
                if (attacker.ability2.getCd() >= 0) {
                    output.append(", Ability 2, cooldown " + attacker.ability2.getCd());
                    attacker.ability2.countCd();
                } else {
                    attacker.ability2.resetCd();
                    attacker.ability2.activated = 0;
                }
            }

        }


        public void Ability1Attack (Pokemon attacker, Pokemon attacked, StringBuilder output){

            attacker.ability1.activated = 1;


            abilityAttack(attacker.ability1, attacked);

            output.append("\n" + attacker.getName() + " ataca " + attacked.getName() + " cu Ability 1" + "    ===>    Rezultat: " + attacked);
            output.append(", Ability 1, cooldown " + attacker.ability1.getCd());
            attacker.ability1.countCd();

            if (attacker.ability2.activated == 1) {
                if (attacker.ability2.getCd() >= 0) {
                    output.append(", Ability 2, cooldown " + attacker.ability2.getCd());
                    attacker.ability2.countCd();
                } else {
                    attacker.ability2.resetCd();
                    attacker.ability2.activated = 0;
                }
            }

        }


        public void Ability2Attack (Pokemon attacker, Pokemon attacked, StringBuilder output){

            attacker.ability2.activated = 1;
            abilityAttack(attacker.ability2, attacked);

            output.append("\n" + attacker.getName() + " ataca " + attacked.getName() + " cu Ability 2" + "    ===>    Rezultat: " + attacked);
            output.append(", Ability 2, cooldown " + attacker.ability2.getCd());

            attacker.ability2.countCd();

            if (attacker.ability1.activated == 1) {
                if (attacker.ability1.getCd() >= 0) {
                    output.append(", Ability 1, cooldown " + attacker.ability1.getCd());
                    attacker.ability1.countCd();
                } else {
                    attacker.ability1.resetCd();
                    attacker.ability1.activated = 0;
                }
            }
        }


        public void battle_neutrel (Trainer trainer, Pokemon pokemon, String battle, StringBuilder output){

            Pokemon neutrel;
            pokemon.ability1.resetCd();
            pokemon.ability2.resetCd();

            if (battle.equals("Neutrel1"))
                neutrel = parser.GetPokemon("Neutrel1");
            else
                neutrel = parser.GetPokemon("Neutrel2");

            output.append("\n-------DESCRIERE PLAYER2:-------\n" + neutrel.fulltoString() +
                    "\n\n------------------------------------------------------------------------------------");


            while (pokemon.getHP() > 0 && neutrel.getHP() > 0) {
                boolean isNeutrelStunned = neutrel.canAttack < 1;
                int dodge = 0;
                int stun = 0;

                String attack = getLegalAttack(trainer, pokemon);

                if (attack.equals("NormalAttack")) {
                    NormalAttack(pokemon, neutrel, output);

                }


                if (attack.equals("Ability1")) {

                    if (pokemon.ability1.isDodge())
                        dodge = 1;
                    if (pokemon.ability1.isStun())
                        stun = 1;

                    Ability1Attack(pokemon, neutrel, output);
                }


                if (attack.equals("Ability2")) {
                    if (pokemon.ability2.isDodge())
                        dodge = 1;
                    if (pokemon.ability2.isStun())
                        stun = 1;

                    Ability2Attack(pokemon, neutrel, output);
                }


                /*now neutrel1's attack*/
                if (neutrel.getHP() > 0 && dodge != 1 && !isNeutrelStunned) {
                    normalAttack(pokemon, neutrel);
                    output.append("\n" + neutrel.getName() + " ataca " + pokemon.getName() + " cu NormalAttack" + "   ===>    Rezultat: " + pokemon);
                }

                if (dodge == 1)
                    output.append("\n" + pokemon.getName() + " se fereste.");


                if (isNeutrelStunned) {
                    output.append("\n" + neutrel.getName() + " este inghetat.");

                }


                neutrel.canAttack++;
                if (neutrel.canAttack > 1) {
                    neutrel.canAttack = 1;
                }

                if (stun == 1)
                    neutrel.canAttack--;


                if (neutrel.getHP() <= 0) {
                    output.append("\n" + neutrel.getName() + " a murit.\n");
                    pokemon.ability1.resetCd();
                    pokemon.ability2.resetCd();
                }
            }


        }


        public void battle_pokemons (Trainer trainer1, Trainer trainer2, Pokemon pokemon1, Pokemon
        pokemon2, StringBuilder output){

            pokemon1.ability1.resetCd();
            pokemon1.ability2.resetCd();

            pokemon2.ability1.resetCd();
            pokemon2.ability2.resetCd();


            output.append("\n-------DESCRIERE PLAYER2:-------\n" + pokemon2.fulltoString() +
                    "\n\n------------------------------------------------------------------------------------");


            int stun_pokemon1;
            int stun_pokemon2;
            int dodge_pokemon2 = 0;
            int dodge_pokemon1;


            while (pokemon1.getHP() > 0 && pokemon2.getHP() > 0) {

                boolean isPokemon1Stunned = pokemon1.canAttack < 1;
                boolean isPokemon2Stunned = pokemon2.canAttack < 1;
                stun_pokemon1 = 0;
                dodge_pokemon1 = 0;


                if (pokemon1.getHP() > 0 && dodge_pokemon2 != 1 && !isPokemon1Stunned) {
                    String attack = getLegalAttack(trainer1, pokemon1);
                    if (attack.equals("NormalAttack")) {
                        NormalAttack(pokemon1, pokemon2, output);
                    }

                    if (attack.equals("Ability1")) {
                        if (pokemon1.ability1.isDodge())
                            dodge_pokemon1 = 1;
                        if (pokemon1.ability1.isStun())
                            stun_pokemon1 = 1;

                        Ability1Attack(pokemon1, pokemon2, output);
                    }

                    if (attack.equals("Ability2")) {
                        if (pokemon1.ability2.isDodge())
                            dodge_pokemon1 = 1;
                        if (pokemon1.ability2.isStun())
                            stun_pokemon1 = 1;

                        Ability2Attack(pokemon1, pokemon2, output);
                    }

                }

                stun_pokemon2 = 0;
                dodge_pokemon2 = 0;

                /*now playrers2 attack*/
                if (pokemon2.getHP() > 0 && dodge_pokemon1 != 1 && !isPokemon2Stunned) {
                    /*attack*/
                    String attack2 = getLegalAttack(trainer2, pokemon2);

                    if (attack2.equals("NormalAttack")) {
                        NormalAttack(pokemon2, pokemon1, output);
                    }

                    if (attack2.equals("Ability1")) {
                        if (pokemon2.ability1.isDodge())
                            dodge_pokemon2 = 1;
                        if (pokemon2.ability1.isStun())
                            stun_pokemon2 = 1;

                        Ability1Attack(pokemon2, pokemon1, output);
                    }

                    if (attack2.equals("Ability2")) {

                        if (pokemon2.ability2.isDodge())
                            dodge_pokemon2 = 1;
                        if (pokemon2.ability2.isStun())
                            stun_pokemon2 = 1;

                        Ability2Attack(pokemon2, pokemon1, output);
                    }
                }


                pokemon1.canAttack++;
                if (pokemon1.canAttack > 1) {
                    pokemon1.canAttack = 1;
                }

                pokemon2.canAttack++;
                if (pokemon2.canAttack > 1) {
                    pokemon2.canAttack = 1;
                }

                if (stun_pokemon2 == 1)
                    pokemon1.canAttack--;

                if (stun_pokemon1 == 1)
                    pokemon2.canAttack--;


                if (dodge_pokemon1 == 1) {
                    output.append("\n" + pokemon1.getName() + " se fereste.");
                }
                if (dodge_pokemon2 == 1) {
                    output.append("\n" + pokemon2.getName() + " se fereste.");
                }


                if (isPokemon1Stunned) {
                    output.append("\n" + pokemon1.getName() + " este inghetat.");
                }

                if (isPokemon2Stunned) {
                    output.append("\n" + pokemon2.getName() + " este inghetat.");
                }

                if (pokemon1.getHP() <= 0) {
                    output.append("\n" + pokemon1.getName() + " a murit.\n");

                    pokemon1.ability1.resetCd();
                    pokemon1.ability2.resetCd();
                    pokemon2.ability1.resetCd();
                    pokemon2.ability2.resetCd();
                }


                if (pokemon2.getHP() <= 0) {
                    output.append("\n" + pokemon2.getName() + " a murit.\n");

                    pokemon1.ability1.resetCd();
                    pokemon1.ability2.resetCd();
                    pokemon2.ability1.resetCd();
                    pokemon2.ability2.resetCd();
                }

            }


        }


        public void resetAbilities (Pokemon pokemon1, Pokemon pokemon2){
            pokemon1.ability1.activated = 0;
            pokemon1.ability2.activated = 0;

            pokemon2.ability1.activated = 0;
            pokemon2.ability2.activated = 0;
        }


        @Override
        public void run () {
            output.append("############## ARENA A ALES BATALIA: " + battle_type + " ##############\n");

            Pokemon pokemonClone1 = null;
            try {
                pokemonClone1 = trainer1.getPokemons().get(this.index).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            Pokemon pokemonClone2 = null;
            try {
                pokemonClone2 = trainer2.getPokemons().get(this.index).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


            if (pokemonClone1 != null && pokemonClone2 != null) {
                resetAbilities(pokemonClone1, pokemonClone2);


                if (this.battle_type.equals("Neutrel1") || this.battle_type.equals("Neutrel2")) {

                    output.append("\n=========================[Batalia " + battle_type + " vs " + pokemon1.getName() + "]=========================");
                    output.append("\n-------DESCRIERE PLAYER1:--------\n" + pokemon1.fulltoString());

                    if (this.battle_type.equals("Neutrel1") || this.battle_type.equals("Neutrel2"))
                        battle_neutrel(trainer1, pokemonClone1, battle_type, output);

                    trainer1.getPokemons().get(index).win();
                    output.append("CASTIGATOR: " + "====> \n" + pokemon1.fulltoString() + "\n");
                    resetAbilities(pokemonClone1, pokemonClone2);


                    output.append("\n=========================[Batalia " + battle_type + " vs " + pokemon2.getName() + "]=========================");
                    output.append("\n-------DESCRIERE PLAYER1:--------\n" + pokemon2.fulltoString());

                    if (this.battle_type.equals("Neutrel1") || this.battle_type.equals("Neutrel2"))
                        battle_neutrel(trainer2, pokemonClone2, battle_type, output);

                    trainer2.getPokemons().get(index).win();
                    output.append("CASTIGATOR: " + "====>  \n" + pokemon2.fulltoString() + "\n");

                    resetAbilities(pokemonClone1, pokemonClone2);
                    printer.append(output);


                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    output.append("\n=========================[Batalia " + pokemon1.getName() + " vs " + pokemon2.getName() + "]=========================");
                    output.append("\n-------DESCRIERE PLAYER1:--------\n" + pokemon1.fulltoString() + "\n");

                    battle_pokemons(trainer1, trainer2, pokemonClone1, pokemonClone2, output);

                    if (pokemonClone1.getHP() <= 0)
                        trainer1.setNrWins(trainer1.getNrWins() + 1);
                    else
                        trainer2.setNrWins(trainer2.getNrWins() + 1);


                    resetAbilities(pokemonClone1, pokemonClone2);

                    if (pokemonClone1.getHP() <= 0) {
                        trainer2.getPokemons().get(index).win();
                        output.append("CASTIGATOR: " + "====>  \n" + pokemon2.fulltoString() + "\n");
                    } else {
                        trainer1.getPokemons().get(index).win();
                        output.append("CASTIGATOR: " + "====>  \n" + pokemon1.fulltoString() + "\n");
                    }

                    printer.append(output);

                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }


