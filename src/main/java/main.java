import model.write.FileHandler;
import view.ConsoleUI;

public class main {
    public static void main(String[] args) {


        ConsoleUI test = new ConsoleUI();
        test.setWriteble(new FileHandler());
        test.start();

    }



}


