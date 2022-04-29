package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    //порт,который будет слушать сервер
    static final int PORT = 3443;
    //Список клиентов,которые будут подключаться к серверу
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server(){
        /*сокет клиента-это некий поток который будет подключаться к серверу
         по адресу и порту
            */
        Socket clientSocket = null;
        //Серверный сокет
        ServerSocket serverSocket = null;
        try {
            //создаем серверный сокет на определенном порте
            serverSocket = new ServerSocket(PORT);
            System.out.println("СТАРТУЕМ");
            //запускаем бесконечный цикл
            while (true){
                //ждем подключений от сервера
                clientSocket = serverSocket.accept();
                //создаем обработчик который подключается к нашему серверу
                // this - это наш сервер
                ClientHandler client = new ClientHandler(clientSocket,this);
                clients.add(client);
                //каждое подключение в новом потоке
                new Thread(client).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //отправлять сообщения всем клиентам
    public void sendMessageToAllClients(String msg){
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
    //удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client){
        clients.remove(client);
    }
}
