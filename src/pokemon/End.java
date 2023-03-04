package pokemon;

import java.util.ArrayList;
import java.util.List;


public class End {

    int State;
    private List<Printer> printers = new ArrayList<>();

    public void addObservers(Printer printer) {
        this.printers.add(printer);
    }

    public void removeObservers(Printer printer) {
        this.printers.remove(printer);
    }

    public void setState(int newState) {
        this.State = newState;

        for (Printer printer : this.printers) {
            printer.update(this.State);
        }
    }

    public void setPrinters(List<Printer> printers) {
        this.printers = printers;
    }


}
