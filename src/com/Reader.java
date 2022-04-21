package com;

public class Reader {
    private String fio;
    private int number;
    private String facility;
    private String dob;
    private String phone;

    public Reader(String fio, int number, String facility, String dob, String phone) {
        this.fio = fio;
        this.number = number;
        this.facility = facility;
        this.dob = dob;
        this.phone = phone;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void takeBook(int number){
        System.out.println(this.fio + " взял " + number +
                "книги");
    }

    public void takeBook(String... books){
        System.out. println(this.fio +"взял следующие книги ");
        for (String book : books){
            System.out.println(book);
        }
        System.out.println();
    }

    public void takeBook(Book...books){
        System.out. println(this.fio +"взял следующие книги ");
        for (Book book : books){
            System.out.println(book.getName()+ ",автор- "+ book.getAuthor());
        }
        System.out.println();
    }

    public void returnBook(int number){
        System.out.println(this.fio + " вернул " + number +
                "книги");
    }

    public void returnBook(String...books){
        System.out.println(this.fio + " вернул следующие книги:");
        for (String book : books) {
            System.out.println(book);
        }
        System.out.println();
    }

    public void returnBook(Book...books){
        System.out. println(this.fio +"взял следующие книги ");
        for (Book book : books){
            System.out.println(book.getName()+ ",автор- "+ book.getAuthor());
        }
        System.out.println();
    }

    public String getInfo(){
        return "{"+ "fio" +fio +'\''+
                ", number=" +number+
                ", facility=" +facility+'\''+
                ", dob="+dob + '\''+
                ", phone="+phone+'\''+'}';
    }
}
