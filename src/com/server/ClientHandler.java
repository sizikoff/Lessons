package com.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//реализуем интерфейс Runnable котороый позволяет работать с потоками
public class ClientHandler implements Runnable{
    //создаем экземпляр нашего сервера
    private Server server;
    //исходящеес сообщение
    private PrintWriter outMessage;
    //входящее сообщение
    private Scanner inMessage;

    private static final String HOST= "localhost";
    private static final int PORT= 3443;
    //создаем клиентский сокет
    private Socket clientsSocket = null;
    //количество клиентов в чате(статичное поле)
    private static int clients_count = 0;
    //конструктор который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket,Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientsSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //переопределяем метод run() который вызывается когда мы вызываем new Thread
    @Override
    public void run() {
                    try {
                            //сервер отправляяет сообщение
                            server.sendMessageToAllClients("Новый участник вошел в чат");
                            server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                        while (true) {
                            //если от клиента пришло сообщение
                            if (inMessage.hasNext()) {
                                String clientMessage = inMessage.nextLine();
                                //если клиент отправляет данное сообщение то цикл прерывается и клиент выходит из чата
                                if (clientMessage.equalsIgnoreCase("##session##end##")) {
                                    break;
                                }
                                //выводим в консоль сообщение
                                System.out.println(clientMessage);
                                //отправляем данное сообщение всем клиентам
                                server.sendMessageToAllClients(clientMessage);
                            }
                            //останавливаем поток на 100мс
                            Thread.sleep(100);
                        }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        this.close();
                    }
    }
    //отправляем сообщение
    public void sendMsg(String msg){
        outMessage.println(msg);
        outMessage.flush();
    }
    public void close(){
        //удаляем клиент из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }
}


