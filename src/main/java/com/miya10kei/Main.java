package com.miya10kei;

import com.miya10kei.model.ClassFile;
import java.io.DataInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try (var raw = new DataInputStream(Files.newInputStream(Paths.get("./src/main/resources/HelloWorld.class")))) {
            var classFile = new ClassFile(raw);
            System.out.println(classFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
