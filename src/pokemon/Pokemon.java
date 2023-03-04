package pokemon;

public class Pokemon implements Cloneable{
    String name;
    int HP;
    Integer normalAttack;
    Integer specialAttack;
    int defense;
    int specialDefense;

    Ability ability1;
    Ability ability2;

    int canAttack;


    /*constructs object using builder*/
    private Pokemon(PokemonBuilder builder) {
        this.name = builder.name;
        this.HP = builder.HP;
        this.normalAttack = builder.normalAttack;
        this.specialAttack = builder.specialAttack;
        this.defense = builder.defense;
        this.specialDefense=builder.specialDefense;
        this.ability1=builder.ability1;
        this.ability2=builder.ability2;
        this.canAttack=1;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public Integer getNormalAttack() {
        return normalAttack;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public Pokemon clone() throws CloneNotSupportedException{
        return (Pokemon) super.clone();
    }


    public static class PokemonBuilder
    {
        String name;
        int HP;
        Integer normalAttack;
        Integer specialAttack;
        int defense;
        int specialDefense;

        Ability ability1;
        Ability ability2;

        public PokemonBuilder(String name) {
            this.name = name;
        }

        public PokemonBuilder HP(int HP){
            this.HP=HP;
            return this;
        }


        public PokemonBuilder attack(Integer normalAttack, Integer specialAttack) {
            this.normalAttack =normalAttack;
            this.specialAttack=specialAttack;
            return this;
        }

        public PokemonBuilder defense( int defense, int specialDefense) {
            this.defense =defense;
            this.specialDefense=specialDefense;
            return this;
        }

        public PokemonBuilder ability(Ability ability1, Ability ability2) {
            this.ability1 = ability1;
            this.ability2 = ability2;
            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);
        }
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setNormalAttack(Integer normalAttack) {
        this.normalAttack = normalAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }


    /*Power boosters*/
    public void addShield(){
       this.setDefense(this.getDefense()+2);
       this.setSpecialDefense(this.getSpecialDefense()+2);
    }

    public void addVest(){
        this.setHP(this.getHP()+10);
    }

    public void addSword(){
        if(this.getNormalAttack()!=null)
        this.setNormalAttack(this.getNormalAttack()+3);
    }

    public void addMagicWand(){
        if(this.getSpecialAttack()!=null)
        this.setSpecialAttack(this.getSpecialAttack()+3);
    }

    public void addVitamins(){
        this.setHP(this.getHP()+2);

        if(this.getNormalAttack()!=null)
        this.setNormalAttack(this.getNormalAttack()+2);

        if(this.getSpecialAttack()!=null)
        this.setSpecialAttack(this.getSpecialAttack()+2);
    }


    public void addChristmasTree(){
        if(this.getNormalAttack()!=null)
        this.setNormalAttack(this.getNormalAttack()+3);

        this.setDefense(this.getDefense()+1);
    }

    public void addCape(){
        if(this.getSpecialAttack()!=null)
        this.setSpecialDefense(this.getSpecialDefense()+3);
    }


    public void win(){
        this.setHP(this.getHP()+1);

        if(this.getSpecialAttack()!=null)
        this.setSpecialAttack(this.getSpecialAttack()+1);

        if(this.getNormalAttack()!=null)
        this.setNormalAttack(this.getNormalAttack()+1);

        this.setDefense(this.getDefense()+1);
        this.setSpecialDefense(this.getSpecialDefense()+1);

    }


    public String fulltoString() {
        String string= ( name  +"\n"+
                "HP=" + HP +
                ", normalAttack=" + normalAttack +
                ", specialAttack=" + specialAttack +
                ", defense=" + defense +
                ", specialDefense=" + specialDefense );

        if(this.ability1!=null)
            string+= "\nAbility1=" + ability1;

        if(this.ability2!=null)
            string+= "\nAbility2=" + ability2+"\n";


        return string;
    }

    public String toString() {
        return
                 name + '\'' +
                "HP= " + HP ;
    }




}
