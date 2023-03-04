package pokemon;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;


public class Arena {
    ArrayList<String> battles;

    public Arena() {
        /*Initialize possible types of battles*/
        this.battles = new ArrayList<>();
        this.battles.add("Battle_neutrel1");
        this.battles.add("Battle_neutrel2");
        this.battles.add("Final_battle");
    }

    public String getRandomElement(ArrayList<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public void adventure(Trainer trainer1, Trainer trainer2,  String outputFilePath) throws InterruptedException, FileNotFoundException {

        int index = 0;
        Thread currentThread = null;

        Printer printer=new Printer(); //observer
        End observeEnd=new End();  // subject
        observeEnd.addObservers(printer);
        observeEnd.setState(0);

        while (index < 3) {
        String battle_type;
            do {
                if (currentThread != null) {
                    currentThread.join();
                }
                battle_type = getRandomElement(this.battles);

                if (battle_type.equals("Battle_neutrel1")) {
                    Battle battle= new Battle(trainer1, trainer2, index, "Neutrel1",outputFilePath,printer);
                    currentThread = new Thread(battle);
                    currentThread.start();

                }

                if (battle_type.equals("Battle_neutrel2")) {

                    Battle battle=new Battle(trainer1, trainer2, index, "Neutrel2",outputFilePath,printer);
                    currentThread = new Thread(battle);
                    currentThread.start();
                }

                if (battle_type.equals("Final_battle")) {
                    Battle battle=new Battle(trainer1, trainer2, index, "Final_battle",outputFilePath,printer);
                    currentThread = new Thread(battle);
                    currentThread.start();
                }

            } while (!battle_type.equals("Final_battle"));
            currentThread.join();
            index++;
        }



        printer.append(new StringBuilder("\nCONCLUZIE:\n" +trainer1.getName() +" a castigat "+trainer1.getNrWins()+" de batalii"));
        printer.append(new StringBuilder("\n"+trainer2.getName() +" a castigat "+trainer2.getNrWins()+" de batalii\n"));


        if( trainer1.getNrWins() > trainer2.getNrWins())
            printer.append(new StringBuilder("\n------------------------"+ trainer1.getName()+" A CASTIGAT------------------------"));
        else
            printer.append(new StringBuilder("\n------------------------"+trainer2.getName() + " A CASTIGAT------------------------"));


        observeEnd.setState(1);  // batalia s-a terminat, pot printa
        printer.printToFile(outputFilePath);


    }



}