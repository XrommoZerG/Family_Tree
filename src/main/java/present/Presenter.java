package present;

import model.HumanExcistsEception.HumanExcistsException;
import model.human.Gender;
import model.service.Service;
import model.write.Writable;
import view.View;

import java.io.IOException;
import java.time.LocalDate;

public class Presenter {
    private View view;
    private Service service;

    public Presenter(View view) {
        this.view = view;
        service = new Service();
    }

    public void allTree() {
        String tree = service.getInfoShort();
        view.printAnswer(tree);
    }

    public void addBody(String name, String lastname, Gender gender) throws HumanExcistsException {
        view.printAnswer(service.addNewToFamily(name, lastname, gender).toString());
    }

    public boolean setBirthday(int id, int year, int month, int day) {
        if (service.setBirthday(id, year, month, day)) {
            view.printAnswer(service.infoByID(id));
            return true;
        }
        return false;
    }

    public void getBodyInfoById(int id) {
        view.printAnswer(service.infoByID(id));
    }

    public void sortById() {
        service.sortById();
        view.printAnswer(service.getInfoShort());
    }

    public boolean setDeathdate(int id, int year, int month, int day) {
        if (service.setDeathdate(id, year, month, day)) {
            view.printAnswer(service.infoByID(id));
            return true;
        }
        return false;
    }

    public void addChild(int parentId, int childId) {
        service.addChild(parentId, childId);
        view.printAnswer(service.infoByID(parentId));
    }
    public void load() throws IOException {
        service.load();
    }

    public boolean save() {
        return service.save();
    }

    public void setWriteble(Writable writable){
        service.setWritable(writable);
    }

    public boolean checkId(int id) {
        return service.checkId(id);
    }
}
