package model.service;

import model.family_tree.FamilyTree;
import model.HumanExcistsEception.HumanExcistsException;
import model.builder.HumanBuilder;
import model.human.Gender;
import model.human.Human;
import model.write.FileHandler;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Service {
    private FamilyTree<Human> tree;
    private HumanBuilder builder;
    private FileHandler fh;

    public Service() {
        tree = new FamilyTree<>();
        builder = new HumanBuilder();
        fh = new FileHandler();
    }

    public Human addNewToFamily(String name, String lastname, Gender gender) throws HumanExcistsException {
        Human human = builder.build(name, lastname, gender);
        tree.addMember(human);
        return human;
    }

    public String infoByID(int id) {
        return tree.infoById(id);
    }

    public boolean setBirthday(int id, int year, int month, int day) {
        Human human = tree.findInTree(id);
        if (human != null) {
            human.setBirthDate(checkDate(year, month, day));
        }
        return human.getBirthday() != null;
    }

    private LocalDate checkDate(int year, int month, int day) {
        LocalDate date = null;
        if (dateIsValid(year, month, day)) {
            date = LocalDate.of(year, month, day);
        }
        return date;
    }

    private boolean dateIsValid(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public String getInfoShort() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Human member : tree) {
            stringBuilder.append(member);
            if (member.getBirthday() == null) {
                stringBuilder.append(", возраст неизвестен\n");
            } else {
                stringBuilder.append(", возраст: ").append(member.age()).append("\n");
            }
        }
        return stringBuilder.toString();

    }


    public void sortByName() {
        tree.sortByName();
    }

    public void sortById() {
        tree.sorrtById();
    }
    @Override
    public String toString() {
        return getInfoShort();
    }

    public boolean setDeathdate(int id, int year, int month, int day) {
        Human human = tree.findInTree(id);
        if (human != null) {
            human.setDeathDate(checkDate(year, month, day));
        }
        return human.getDeathDate() != null;
    }
    public void addChild(int parentId, int childId) {
        Human parent = tree.findInTree(parentId);
        Human child = tree.findInTree(childId);
        parent.setChild(child);
    }
    public void load() throws IOException {
        tree = (FamilyTree) fh.read("output.data");
    }

    public boolean save() {
        return fh.save(tree, "output.data");
    }

    public boolean checkId(int id) {
        Human human = tree.findInTree(id);
        return human != null;
    }
}
