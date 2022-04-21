package com;

import com.abstract1.Animal;
import com.abstract1.Cat;
import com.abstract1.Dog;
import com.interface1.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
            Reader reader = new Reader("Петров",1,"ИФ",
                    "21.10.1994","+79001234567");
            Reader reader1 = new Reader("Иванов",1,"ИБ",
                    "21.10.1956","+79001234567");
            Reader reader2 = new Reader("Сидоров",1,"ТБ",
                    "21.10.1562","+79001234567");
            Reader reader3 = new Reader("Ромашкин",1,"ЛОГ",
                    "21.10.1102","+79001234567");



            Reader[] readers = {reader,reader1,reader2,reader3};

            Book book = new Book("Пикник на обочине","Стругацкие");
            Book book1 = new Book("Война и мир","Толстой");
            Book book2 = new Book("История государства Российского"
                    ,"Карамзин");
            Book book3 = new Book("Идиот","Достоевский");

            Book[] books = {book,book1,book2,book3};

            printReaders(readers);
            printBooks(books);

            reader.takeBook(5);
            reader2.takeBook("алфавит","кирилл и мефодий");
            reader3.takeBook(book,book1,book2);

            reader.returnBook(1);
            reader2.returnBook("java","шилдт");
            reader3.returnBook(book3);




//        Printable printable = new Book("Java ","Shildt");
//       printable.print();
//       printable = new Journal("Журнал Караван историй");
//       printable.print();

//        ArrayList<Animal> house = new ArrayList<>();
//        house.add(new Cat());
//        house.add(new Dog());
//        house.add(new Dog());
//        for (Animal animal : house) {
//            animal.voice();
//        }


    }

    private static void printBooks(Book[] books) {
        System.out.println("Список книг");
        for (Book book:books) {
            System.out.println(book.getInfo());
        }
        System.out.println();
    }

    private static void printReaders(Reader[] readers) {
        System.out.println("Список читателей");
        for (Reader reader:readers) {
            System.out.println(reader.getInfo());
        }
        System.out.println();
    }
}