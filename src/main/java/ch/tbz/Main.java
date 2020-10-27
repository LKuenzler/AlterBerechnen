package ch.tbz;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class Main {
    public static void main(String[] args) {
        try {
            ArgsHandler argsHandler = new ArgsHandler();
            JCommander jCommander = JCommander.newBuilder()
                    .addObject(argsHandler)
                    .build();
            jCommander.parse(args);
            argsHandler.parse();
        } catch (ParameterException e) {
            printWrongInputText();
        }
    }

    public static void printWrongInputText() {
        System.out.println("Bitte geben sie ein datum folgender massen ein: \"-d dd.mm.yyyy\" oder  \" --date dd.mm.yyyy \"");
        System.exit(0);
    }
}
