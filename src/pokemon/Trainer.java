package pokemon;

import java.util.ArrayList;
import java.util.Random;

public class Trainer {
    String name;
    int age;
    int nrWins;
    ArrayList<Pokemon> pokemons;
    ArrayList<String> attacks;


    public Trainer(String name, int age) {
        this.name = name;
        this.age = age;
        this.pokemons = new ArrayList<>();
        this.nrWins=0;
        this.attacks = new ArrayList<>();
        this.attacks.add("NormalAttack");
        this.attacks.add("Ability1");
        this.attacks.add("Ability2");

    }

    public int getNrWins() {
        return nrWins;
    }

    public String getName() {
        return name;
    }

    public void setNrWins(int nrWins) {
        this.nrWins = nrWins;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String randomAttack() {
        Random rand = new Random();
        return this.attacks.get(rand.nextInt(this.attacks.size()));
    }



}


