package pokemon;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Reader reader= new Reader();
        reader.readBattle("./in/battle1.txt", "out/output/Battle1");
        reader.readBattle("./in/battle2.txt", "out/output/Battle2");
        reader.readBattle("./in/battle3.txt", "out/output/Battle3");
        reader.readBattle("./in/battle4.txt", "out/output/Battle4");
        reader.readBattle("./in/battle5.txt", "out/output/Battle5");
        reader.readBattle("./in/battle6.txt", "out/output/Battle6");
        reader.readBattle("./in/battle7.txt", "out/output/Battle7");
        reader.readBattle("./in/battle8.txt", "out/output/Battle8");
        reader.readBattle("./in/battle9.txt", "out/output/Battle9");
        reader.readBattle("./in/battle10.txt", "out/output/Battle10");
    }

}
