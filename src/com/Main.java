package com;

import java.io.*;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) {
        //checked  exception
//        File f = new File("C://note.txt");
//        FileReader fr = new FileReader(f);

        //unchecked
//        int []array = {2,3,4};
//        System.out.println(array[4]);

        //обработка исключений
        try {
            //потенциально взрывооопасный код
            int [] array = new int[2];
            System.out.println("Доступ к третьему элементу " + array[3]);
        }catch (ArrayIndexOutOfBoundsException e){
            //выброс ошибки
            e.printStackTrace();
        }finally {
            //данный блок выполняется ВСЕГДА!!!!!
            System.out.println("этот блок выполнится в любом случае");
        }
        System.out.println("Вне блока");

        //запись
//        String text = "Hello world";
//        try(FileOutputStream fos = new FileOutputStream("D://notes.txt")){
//            byte [] buffer = text.getBytes();
//            fos.write(buffer,0,buffer.length);
//            System.out.println("The file has been written");
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

        //читаем
//        try(FileInputStream fis = new FileInputStream("D://notes.txt")){
//            System.out.println(fis.available());
//            int i = fis.read();
//            while (i != -1) {
//                System.out.print((char)i);
//                i = fis.read();
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }

//        try(FileWriter writer = new FileWriter("D://notes.txt",false))
//                {
//          String str = "Here you can find activities to practise your reading skills." +
//                  "Reading will help you to improve your understanding of the";
//          writer.write(str);
//          writer.append('\n');
//          writer.flush();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try(FileReader reader = new FileReader("D://notes.txt"))
//        {
//            int c;
//            while ((c=reader.read())!=-1){
//                System.out.print((char)c);
//            }
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//        File dir = new File("D:/SDK");
//        if (dir.isDirectory()) {
//            for (File item: dir.listFiles()) {
//                if (item.isDirectory()) {
//                    System.out.println(item.getName() + "\t folder");
//                }else{
//                    System.out.println(item.getName() + "\t file");
//                }
//            }
//        }
    }
}
