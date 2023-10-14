package com.amazon.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextUtility {

    public static void writeToFile(String text){
        File file = new File("src/test/resources/files/productInfo.txt");
        try {
            FileWriter fw = new FileWriter(file,true);
            fw.write(text+"\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
