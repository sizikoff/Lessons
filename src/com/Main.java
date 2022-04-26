package com;

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        //определяем путь для считывания файла
        try {
            FileInputStream fis = new FileInputStream("D:/ip.txt");
            int i ;
            String result = "";
            //считываем данные из файла FileInputStream
            while ((i=fis.read())!= -1) {
                //Пропускаем код 13(перенос каретки)
                if (i==13)continue;
                else if(i==10){
                    //["202.152.51.44","8080"]
                    String[] ipAndPort = result.split(":");
                    result = "";
                    String ip = ipAndPort[0];
                    int port = Integer.parseInt(ipAndPort[1]);
                    CheckProxyThread thread = new CheckProxyThread(ip,port);
                    thread.start();
                } else if (i==9) {
                    result += ":";
                }else{
                    result+=(char)i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CheckProxyThread extends Thread {

        String ip;
        int port;

        public CheckProxyThread(String ip,int port){
            this.port = port;
            this.ip = ip;
        }

        @Override
        public void run() {
            //формируем порт и айпи для постучаться
            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(ip,port));
            try {
                //адрес куда будем стучаться
                URL url = new URL("https://vozhzhaev.ru/test.php");
                // тук-тук
                URLConnection urlConnection = url.openConnection(proxy);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine())!= null)
                    System.out.println(inputLine + "- работает");
                FileOutputStream fos = new FileOutputStream("D:/good_ip.txt",true);
                byte[] buffer = (ip+ ":"+ port+"\n").getBytes();
                fos.write(buffer);
                fos.flush();
                fos.close();
            } catch (IOException e) {
            }
        }
    }
}

//        System.out.println("Главный поток стартовал");
//        JThread jThread = new JThread();
//        new Thread(jThread,"Поток ").start();
//        try {
//            Thread.sleep(2000);
//            jThread.disable();
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Главный поток финиш");

//        System.out.println("Main thread started...");
//        JThread jThread = new JThread("Поток");
//        jThread.start();
//        try {
//            jThread.join();
//        } catch (InterruptedException e) {
//            System.out.println(jThread.getName() +
//                    "has been interrupted");
//        }
//        System.out.println("Main thread finished");
//    }