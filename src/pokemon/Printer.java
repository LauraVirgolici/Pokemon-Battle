package pokemon;
import java.io.*;

public class Printer implements IPrinter{

    int State;
    File OutputFile;
    StringBuilder finalText=new StringBuilder();

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public void update(int newState){
        this.setState(newState);
    }

    public void append(StringBuilder output){
        this.finalText.append(output);
    }

    public void printToFile(String outputFilePath) throws FileNotFoundException {

        if(this.State==1) {
            this.OutputFile = new File(outputFilePath);
            PrintStream printStreamToFile = new PrintStream(outputFilePath);
            System.setOut(printStreamToFile);
            System.out.println(finalText);
        }
    }


}
