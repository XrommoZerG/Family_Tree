package command;

import view.ConsoleUI;

public class Finish extends Command{

    public Finish(ConsoleUI consoleUI) {
        super((consoleUI));
        description = "Завершить работу";
    }

    public void execute(){
        consoleUI.finish();
    }
}
