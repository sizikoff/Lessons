package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWindow extends JFrame {
    //адрес сервера
    private static final String SERVER_HOST = "localhost";
    //порт
    private static final int SERVER_PORT = 3443;
    //клиентский сокет
    private Socket clientSocket;
    //входящее сообщение
    private Scanner inMessage;
    //исходящее сообщение
    private PrintWriter outMessage;
    //следующие 3 поля отвечают за элементы формы
    private JTextField jtfMessage;
    private JTextField jtfName;
    private JTextArea jTextAreaMessage;
    //имя клиента
    private String clientName = "";
    //получаем имя клиента
    public String getClientName(){
        return this.clientName;
    }
    //конструктор
    public ClientWindow(){
        //подключаемся к серверу
        try {
            clientSocket = new Socket(SERVER_HOST,SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //задаем настройки элементов на форме
        setBounds(600,300,600,500);
        setTitle("Впдоъезде");
        //строчка отвечает за нажатие на крестик
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jTextAreaMessage = new JTextArea();
        jTextAreaMessage.setEditable(false);
        jTextAreaMessage.setLineWrap(true);
        //scroll
        JScrollPane jsp = new JScrollPane(jTextAreaMessage);
        add(jsp, BorderLayout.CENTER);
        //label который будет отображать количество клиентов в чате
        JLabel jlNumberOfClients = new JLabel("Количество участников в чате");
        add(jlNumberOfClients,BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        add(bottomPanel,BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("Отправить ");
        bottomPanel.add(jbSendMessage,BorderLayout.EAST);
        jtfMessage = new JTextField("Введите ваше сообщение " );
        bottomPanel.add(jtfMessage,BorderLayout.CENTER);
        jtfName = new JTextField("Введите ваше имя");
        bottomPanel.add(jtfName,BorderLayout.WEST);
        //ОБРАБОТЧИК НАЖАТИЯ(слушатель)
        jbSendMessage.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //если имя клиента и сообщения непустые то отправляем сообщение
                if (!jtfMessage.getText().trim().isEmpty() && !jtfName.getText().trim().isEmpty()){
                clientName = jtfName.getText();
                sendMsg();
                //меняем фокус на поле с сообщением
                    jtfMessage.grabFocus();
                }
            }
        });
        //при фокусе поле очищается
        jtfMessage.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMessage.setText("");
            }
        });
        //при фокусе поле ия чистится
        jtfName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfName.setText("");
            }
        });
        //в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                //бесконечный цикл
                while(true){
                    //есть ли входящие сообщения
                    if (inMessage.hasNext()) {
                        //считываем его
                        String inMes = inMessage.nextLine();
                        String clientInChat = "Клиентов в чате = ";
                        if (inMes.indexOf(clientInChat)==0) {
                            jlNumberOfClients.setText(inMes);
                        }else{
                            //выводим сообщение
                            jTextAreaMessage.append(inMes);
                            //добавляем строку перехода
                            jTextAreaMessage.append("\n");
                        }
                    }
                }
            }
        }).start();
        //добавялем обработчик события закрытия клиентского окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    if (!clientName.isEmpty() && clientName!= "Введите ваше имя: ") {
                        outMessage.println(clientName+ " вышел из окна");
                    }else {
                        outMessage.println("Участник вышел из чата так и не представившись");
                    }
                    //служебные сообщения сигнал что клиент вышел из чата
                    outMessage.println("##session##end##");
                    outMessage.flush();
                    outMessage.close();
                    inMessage.close();
                    clientSocket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //отображаем форму
        setVisible(true);
    }

    //отправка сообщения
    private void sendMsg() {
        //формируем сообщение
        String messageStr = jtfName.getText() + ": " + jtfMessage.getText();
        //отправляем
        outMessage.println(messageStr);
        outMessage.flush();
        jtfMessage.setText("");
    }
}
