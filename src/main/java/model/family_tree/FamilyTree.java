package model.family_tree;

import model.HumanExcistsEception.HumanExcistsException;
import model.human.comparators.HumanComparatorById;
import model.human.comparators.HumanComparatorByName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FamilyTree<E extends ItemTree> implements Serializable,Iterable<E> {
    public long idhuman;
    private List<E> humans;

    public FamilyTree() {
        humans = new ArrayList<>();
    }


    public FamilyTree(List<E> humans ){
        this.humans = humans;
    }

    public void tryAdd(E human) throws HumanExcistsException {
        if (humans.contains(human)) {
            throw new HumanExcistsException("Такая запись уже есть!");
        }
    }

    public void addMember(E human) throws HumanExcistsException {
        human.setId(idhuman++);
        tryAdd(human);
        if (!humans.contains(human)) {
            humans.add(human);
        }
    }


    public E getbyId(long id){
        if (!checkId(id)) {
            return null;
        }
        for(E d: humans){
            if (d.getId() == id){
                return d;
            }
        }
        return null;
    }
    public E findInTree(int id) {
        for (E member : humans) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }
    public String infoById(int id) {
        StringBuilder sb = new StringBuilder("Id - ");
        sb.append(id).append("\n").append("\n");
        E human = findInTree(id);
        if (human != null) {
            sb.append("Имя: ").append(human.getName() + "\n");
            sb.append("Пол: ").append(human.getGender() + "\n");
            if (human.getBirthday() != null) {
                sb.append("Дата рождения: ").append(human.getBirthday() + "\n");
                sb.append("Возраст: ").append(human.getAge() + "\n");
            }
            if (human.getChildren() != null) {
                sb.append(human.getChildren() + "\n");
            }
            return sb.toString();
        }
        return "Данных нет.";
    }

    private boolean checkId(long id){
        return id < idhuman && id <= 0;
    }

    public String getInfo(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("В дереве ");
        stringBuilder.append(humans.size());
        stringBuilder.append(" персон: \n");
        for (E human : humans){
            stringBuilder.append(human);
            stringBuilder.append("\n ");
        }
        return stringBuilder.toString();
    }

    public String getInfoShort(){
        StringBuilder stringBuilder = new StringBuilder();
        for(E member : humans){
            stringBuilder.append(member);
            if (member.getBirthday() == null) {
                stringBuilder.append(", возраст не известен/n");
            }
            else {
                stringBuilder.append(", возраст:").append(member.age()).append("/n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString(){
        return getInfo();
    }

    public void sortByName(){
        humans.sort(new HumanComparatorByName<>());
    }

    public void sorrtById(){
        humans.sort(new HumanComparatorById<>());
    }

    @Override
    public Iterator<E> iterator() {
        return new HumanIterator<>(humans);
    }
}
