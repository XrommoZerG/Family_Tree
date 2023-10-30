import family_tree.FamilyTree;
import human.Gender;
import human.Human;
import write.FileHandler;

import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        //FamilyTree tree = creatTree();
        FamilyTree tree = load();

        System.out.println(tree);

        //save(tree);

    }

    private static FamilyTree load(){
        String filePath = "src/main/java/write/tree.txt";
        FileHandler fileHandler = new FileHandler();
        FamilyTree tree = (FamilyTree) fileHandler.read(filePath);
        return tree;
    }

    private static void save(FamilyTree tree){
        String filePath = "src/main/java/write/tree.txt";
        FileHandler fileHandler = new FileHandler();
        fileHandler.save(tree,filePath);

    }

    static FamilyTree creatTree(){
        FamilyTree tree = new FamilyTree();

        Human kola = new Human("Коля", LocalDate.of(1998,5,13), Gender.Male);

        Human yula = new Human("Юля", LocalDate.of(1993,8,22), Gender.Female);

        Human nikolay = new Human("Коля", LocalDate.of(1999,1,2), Gender.Male);


        tree.add(yula);
        tree.add(kola);
        tree.add(nikolay);

        return tree;



    }

}


