package ru.levelp.java.PhoneBookGUI;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhoneBook {

    int size=0;
    MyArrayList phone_book = new MyArrayList();

    public void add(String name, String phone_number, String email) {
        Contact contact = new Contact(name, phone_number, email);
        phone_book.add(contact, size);
        size++;
        try{
            writeToLog("adding", contact);}
        catch (FileNotFoundException e){
            System.out.println("File not found");}
        try{
            writeToFile();}
        catch (FileNotFoundException e){
            System.out.println("File not found");}

    }

    public boolean delete(String name) {
        int position = -1;
        for (int i=0; i<size; i++){
            if (phone_book.get(i).getName().equals(name)){
                try{
                    writeToLog("delete", phone_book.get(i));}
                catch (FileNotFoundException e){
                    System.out.println("File not found");}
                phone_book.remove(i);
                size--;
                try{
                    writeToFile();}
                catch (FileNotFoundException e){
                    System.out.println("File not found");}
                return true;
            }
        }

        System.out.println("Can't find record");
        return false;
    }

    public void show(String show_what, String field) {
        Contact contact = findByName(show_what);
        try{
            writeToLog("showing", contact);}
        catch (FileNotFoundException e){
            System.out.println("File not found");}
        try {
            contact.show(field);
        }
        catch (NullPointerException e){
            System.out.println("Contact doesn't exist or contact list is empty");
        }
    }

    public void show(String show_what) {
        Contact contact = findByName(show_what);
        try{
            writeToLog("showing", contact);}
        catch (FileNotFoundException e){
            System.out.println("File not found");}
        try {
            contact.show();
        }
        catch (NullPointerException e){
            System.out.println("Contact doesn't exist or contact list is empty");
        }
    }

    public void show_all() {
        try{
            writeToLog();}
        catch (FileNotFoundException e){
            System.out.println("File not found");}
        for (int i = 0; i < size; i++) {
            phone_book.get(i).show();
        }
    }

    public boolean edit(String name, String field, String newValue) {
        for (int i = 0; i<size; i++){
            if (phone_book.get(i).getName().equals(name)){
                String oldValue = " editing field: "+ field + "; old value: " + getDifferentFieldValue(field, phone_book.get(i));
                phone_book.get(i).edit(field, newValue);
                try{
                    writeToLog(oldValue, newValue);}
                catch (FileNotFoundException e){
                    System.out.println("File not found");}
            }
            try{
                writeToFile();}
            catch (FileNotFoundException e){
                System.out.println("File not found");}
            return true;
        }
        System.out.println("Contact doesn't exist or contact list is empty");
        return false;
    }

    private Contact findByName(String name){
        for (int i=0;i<size;i++){
            if (phone_book.get(i).getName().equals(name))
                return phone_book.get(i);
        }
        return null;
    }

    public void writeToFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File("base.txt"));

        for (int i = 0; i < size; i++) {
            writer.println((phone_book.get(i).getName().equals("") ? "null" : phone_book.get(i).getName())
                    +"#"+(phone_book.get(i).getPhone_number().equals("") ? "null" : phone_book.get(i).getPhone_number())
                    +"#"+(phone_book.get(i).getEmail().equals("") ? "null" : phone_book.get(i).getEmail()));
        }

        writer.flush();
        writer.close();
    }

    public void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("base.txt"))
        );

        String s;
        String name;
        String phone_number;
        String email;
        int size=0;
        while ((s = reader.readLine()) != null) {
            String[] contactString = s.split("#");
            name = (contactString[0].equals("null") ? "" : contactString[0]);
            phone_number = (contactString[1].equals("null") ? "" : contactString[1]);
            email = (contactString[2].equals("null") ? "" : contactString[2]);
            Contact contact = new Contact(name, phone_number, email);
            size++;
            phone_book.add(contact);
        }
        this.size = size;

    }

    public void writeToLog(String actionType, Contact contact) throws FileNotFoundException {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        PrintWriter writer = new PrintWriter(new FileOutputStream("log.txt",true));
        writer.println(dateFormat.format(currentDate) + " " + actionType + " contact name:" + contact.getName() + ", phone number:" + contact.getPhone_number() + ", e-mail:" + contact.getEmail());
        writer.flush();
        writer.close();
    }

    public void writeToLog() throws FileNotFoundException {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        PrintWriter writer = new PrintWriter(new FileOutputStream("log.txt",true));
        writer.println(dateFormat.format(currentDate) + " showing all contacts");
        writer.flush();
        writer.close();
    }

    public void writeToLog(String oldValue, String newValue) throws FileNotFoundException {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        PrintWriter writer = new PrintWriter(new FileOutputStream("log.txt",true));
        writer.println(dateFormat.format(currentDate) + " " + oldValue + ", new value:" + newValue);
        writer.flush();
        writer.close();
    }


    public String getDifferentFieldValue(String field, Contact contact){
        if (field.equals("name"))
            return contact.getName();
        else if (field.equals("phone"))
            return contact.getPhone_number();
        else if (field.equals("email"))
            return contact.getEmail();
        return "";
    }

}