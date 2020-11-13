package lab5;

import java.io.Closeable;
import java.io.IOException;

public class MyCloseable implements Closeable {

    public MyCloseable(){
        System.out.println("Create");
    }

    @Override
    public void close() throws IOException {
        System.out.println("Close");
    }
}
