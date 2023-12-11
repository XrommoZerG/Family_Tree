package model.write;

import java.io.*;

public class FileHandler implements Writable {

    @Override
    public boolean save(Serializable serializable, String filePath){

        boolean key = false;

        File file = new File(filePath);
        ObjectOutputStream objectOutputStream = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            if (fileOutputStream != null) {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(serializable);
                key = true;
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return key;

    }

    @Override
    public Object read(String filePath){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))){
            return objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
