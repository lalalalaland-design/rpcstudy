package com.luban.framework.register;

import com.luban.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void regist(String interfaceName,URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {


        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\Person\\Documents\\temp.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, List<URL>> registerMap = null;
        try {
            registerMap = (Map) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<URL> list = registerMap.get(interfaceName);
            return list;

    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Person\\Documents\\temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
