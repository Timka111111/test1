package kz.timka.today.example2.exception;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReportMaker {

    public void createReport(String filename, String data) throws IOException{

            if(filename.length() < 5) {
                throw new FileNotFoundException("file not found");
            }
            FileOutputStream out = new FileOutputStream(filename);
            out.write(data.getBytes());
            out.close();


    }
}
