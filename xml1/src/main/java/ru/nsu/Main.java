package ru.nsu;

import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
        try (InputStream stream = new FileInputStream("people.xml")) {
            HashMap<String, Person> data = new PeopleParser().parse(stream);
            Assertions assertions = new Assertions();
            System.out.println("ASSERTIONS");
            assertions.assertChildren(data);
            assertions.assertSiblings(data);
            System.out.println("COMPLETED");
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

}
