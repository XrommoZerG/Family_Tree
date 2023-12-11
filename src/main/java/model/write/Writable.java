package model.write;

import java.io.IOException;
import java.io.Serializable;

public interface Writable {
    boolean save(Serializable serializable,String filePath);
    Object read(String filePath) throws IOException;
}
