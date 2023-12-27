package ru.nsu;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.InputStream;
import java.util.*;

public class PeopleParser {

    public HashMap<String, Person> parse(InputStream stream) throws XMLStreamException {
        ArrayList<Person> data = new ArrayList<>();

        XMLInputFactory streamFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = streamFactory.createXMLStreamReader(stream);

        int peopleCount = 0;
        Person person = null;

        while (reader.hasNext()) {
            reader.next();
            String[] temp;

            int event_type = reader.getEventType();
            switch (event_type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    switch (reader.getLocalName()) {
                        case "people" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("count")) {
                                    peopleCount = Integer.parseInt(reader.getAttributeValue(i));
                                } else {
                                    System.out.println("Unknown attribute in <people>");
                                }
                            }
                        }
                        case "person" -> {
                            person = new Person();
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                switch (reader.getAttributeLocalName(i)) {
                                    case "name" -> {
                                        String[] full = reader.getAttributeValue(i).trim().split("\\s+");
                                        person.firstName = full[0];
                                        person.lastName = full[1];
                                    }
                                    case "id" -> {
                                        person.id = reader.getAttributeValue(i).trim();
                                    }
                                    default -> System.out.println("Unknown attribute in <person>");
                                }
                            }
                        }
                        case "id" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    assert person != null;
                                    person.id = reader.getAttributeValue(i).trim();
                                } else {
                                    System.out.println("Unknown attribute in <id>");
                                }
                            }
                        }
                        case "firstname" -> {
                            if (reader.getAttributeCount() > 0) {
                                for (int i = 0; i < reader.getAttributeCount(); i++) {
                                    if (reader.getAttributeLocalName(i).equals("value")) {
                                        assert person != null;
                                        person.firstName = reader.getAttributeValue(i).trim();
                                    } else {
                                        System.out.println("Unknown attribute in <firstname>");
                                    }
                                }
                            } else {
                                reader.next();
                                assert person != null;
                                person.firstName = reader.getText().trim();
                            }
                        }
                        case "surname" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    assert person != null;
                                    person.lastName = reader.getAttributeValue(i).trim();
                                } else {
                                    System.out.println("Unknown attribute in <surname>");
                                }
                            }
                        }
                        case "fullname", "children" -> {
                        }
                        case "first" -> {
                            reader.next();
                            assert person != null;
                            person.firstName = reader.getText().trim();
                        }
                        case "family", "family-name" -> {
                            reader.next();
                            assert person != null;
                            person.lastName = reader.getText().trim();
                        }
                        case "gender" -> {
                            if (reader.getAttributeCount() > 0) {
                                for (int i = 0; i < reader.getAttributeCount(); i++) {
                                    if (reader.getAttributeLocalName(i).equals("value")) {
                                        assert person != null;
                                        person.gender = reader.getAttributeValue(i).trim().toUpperCase().substring(0, 1);
                                    } else {
                                        System.out.println("Unknown attribute in <gender>");
                                    }
                                }
                            } else {
                                reader.next();
                                assert person != null;
                                person.gender = reader.getText().trim().toUpperCase().substring(0, 1);
                            }
                        }
                        case "spouce" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    if (!reader.getAttributeValue(i).trim().equals("NONE")) {
                                        assert person != null;
                                        person.spouseName = reader.getAttributeValue(i);
                                    }
                                } else {
                                    System.out.println("Unknown attribute in <spouce>");
                                }
                            }
                            if (reader.hasText()) {
                                if (!reader.getText().trim().equals("NONE")) {
                                    assert person != null;
                                    person.spouseName = reader.getText();
                                }
                            }
                        }
                        case "husband" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    assert person != null;
                                    person.husbandId = reader.getAttributeValue(i).trim();
                                } else {
                                    System.out.println("Unknown attribute in <husband>");
                                }
                            }
                        }
                        case "wife" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if ("value".equals(reader.getAttributeLocalName(i))) {
                                    assert person != null;
                                    person.wifeId = reader.getAttributeValue(i).trim();
                                } else {
                                    System.out.println("Unknown attribute in <wife>");
                                }
                            }
                        }
                        case "siblings" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("val")) {
                                    List<String> siblings = Arrays.asList(reader.getAttributeValue(i).trim().split("\\s+"));
                                    assert person != null;
                                    person.siblingsId.addAll(siblings);
                                } else {
                                    System.out.println("Unknown attribute in <siblings>");
                                }
                            }
                        }
                        case "brother" -> {
                            reader.next();
                            temp = reader.getText().trim().split("\\s+");
                            assert person != null;
                            person.brothersName.add(temp[0] + " " + temp[1]);
                        }
                        case "sister" -> {
                            reader.next();
                            temp = reader.getText().trim().split("\\s+");
                            assert person != null;
                            person.sistersName.add(temp[0] + " " + temp[1]);
                        }
                        case "siblings-number" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    assert person != null;
                                    person.siblingsCount = Integer.parseInt(reader.getAttributeValue(i).trim());
                                } else {
                                    System.out.println("Sibling number has unknown attribute: " + reader.getAttributeLocalName(i));
                                }
                            }
                        }
                        case "child" -> {
                            reader.next();
                            temp = reader.getText().trim().split("\\s+");
                            assert person != null;
                            person.childrenName.add(temp[0] + " " + temp[1]);
                        }
                        case "son" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("id")) {
                                    assert person != null;
                                    person.sonsId.add(reader.getAttributeValue(i).trim());
                                } else {
                                    System.out.println("Unknown attribute in <son>");
                                }
                            }
                        }
                        case "daughter" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("id")) {
                                    assert person != null;
                                    person.daughtersId.add(reader.getAttributeValue(i).trim());
                                } else {
                                    System.out.println("Unknown attribute in <daughter>");
                                }
                            }
                        }
                        case "parent" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    if (!reader.getAttributeValue(i).trim().equals("UNKNOWN")) {
                                        assert person != null;
                                        person.parentsId.add(reader.getAttributeValue(i).trim());
                                    }
                                } else {
                                    System.out.println("Unknown attribute in <parent>");
                                }
                            }
                        }
                        case "father" -> {
                            reader.next();
                            temp = reader.getText().trim().split("\\s+");
                            assert person != null;
                            person.fatherName = temp[0] + " " + temp[1];
                        }
                        case "mother" -> {
                            reader.next();
                            temp = reader.getText().trim().split("\\s+");
                            assert person != null;
                            person.motherName = temp[0] + " " + temp[1];
                        }
                        case "children-number" -> {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                if (reader.getAttributeLocalName(i).equals("value")) {
                                    assert person != null;
                                    person.childrenCount = Integer.parseInt(reader.getAttributeValue(i).trim());
                                } else {
                                    System.out.println("Unknown attribute in <children-number>");
                                }
                            }
                        }
                        default -> System.out.println("Uncnown attribute: " + reader.getLocalName());
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    if (reader.getLocalName().equals("person")) {
                        data.add(person);
                        person = null;
                    }
                }
            }
        }

        reader.close();
        return normalize(data, peopleCount);
    }

    private HashMap<String, Person> normalize(ArrayList<Person> data, Integer peopleCount) {
        HashMap<String, Person> id_peoples = new HashMap<>();
        ArrayList<Person> remaining_data = new ArrayList<>();

        fillUpKnownIdPeoples(data, id_peoples, remaining_data, peopleCount);

        data = remaining_data;
        remaining_data = new ArrayList<>();
        mergeNullIdPeopleWithoutNamesakes(data, id_peoples, remaining_data);

        data = remaining_data;
        remaining_data = new ArrayList<>();
        mergeSiblingsOfNamesakes(data, id_peoples, remaining_data);

        data = remaining_data;
        remaining_data = new ArrayList<>();
        mergeChildrenOfNamesakes(data, id_peoples, remaining_data);

        System.out.println("PREPARING");

        childrenPrepare(id_peoples);
        siblingsPrepare(id_peoples);
        genderPrepare(id_peoples);

        return id_peoples;
    }

    private void mergeChildrenOfNamesakes(ArrayList<Person> data, HashMap<String, Person> id_peoples, ArrayList<Person> remaining_data) {
        for (Person person : data) {
            if (!person.childrenId.isEmpty()) {
                List<Person> found_namesakes = id_peoples.values().parallelStream().filter(x -> x.firstName.equals(person.firstName) && x.lastName.equals(person.lastName)).toList();
                List<Person> found = found_namesakes.stream().filter(f -> f.childrenCount != null && f.childrenCount > f.childrenId.size() && !f.childrenId.containsAll(person.childrenId)).toList();
                if (found.size() == 1) {
                    Person foundPerson = found.get(0);
                    foundPerson.merge(person);
                } else {
                    remaining_data.add(person);
                }
            } else {
                remaining_data.add(person);
            }
        }
    }

    private void mergeSiblingsOfNamesakes(ArrayList<Person> data, HashMap<String, Person> id_peoples, ArrayList<Person> remaining_data) {
        for (Person person : data) {
            if (!person.siblingsId.isEmpty()) {
                List<Person> found_namesakes = id_peoples.values().parallelStream().filter(x -> x.firstName.equals(person.firstName) && x.lastName.equals(person.lastName)).toList();
                List<Person> found = found_namesakes.stream().filter(f -> f.siblingsCount != null && f.siblingsCount > f.siblingsId.size() && !f.siblingsId.containsAll(person.siblingsId)).toList();
                if (found.size() == 1) {
                    Person foundPerson = found.get(0);
                    foundPerson.merge(person);
                } else {
                    remaining_data.add(person);
                }
            } else {
                remaining_data.add(person);
            }
        }
    }

    private void mergeNullIdPeopleWithoutNamesakes(ArrayList<Person> data, HashMap<String, Person> id_peoples, ArrayList<Person> remaining_data) {
        for (Person p : data) {
            List<Person> found = id_peoples.values().parallelStream().filter(x -> x.firstName.equals(p.firstName) && x.lastName.equals(p.lastName)).toList();
            if (found.size() == 1) {
                Person foundPerson = found.get(0);
                foundPerson.merge(p);
            } else {
                remaining_data.add(p);
            }
        }
    }

    private static void fillUpKnownIdPeoples(ArrayList<Person> data, HashMap<String, Person> id_peoples, ArrayList<Person> remaining_peoples, Integer peopleCount) {
        for (Person p : data) {
            if (p.id != null) {
                if (id_peoples.containsKey(p.id)) {
                    id_peoples.get(p.id).merge(p);
                } else {
                    id_peoples.put(p.id, p);
                }
            } else {
                remaining_peoples.add(p);
            }
        }
        assert id_peoples.size() == peopleCount;
    }

    private void childrenPrepare(HashMap<String, Person> id_peoples) {
        for (String key : id_peoples.keySet()) {
            Person p = id_peoples.get(key);
            p.childrenId.addAll(p.sonsId);
            p.childrenId.addAll(p.daughtersId);
            for (String s : p.daughtersName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person daughter = f.get(0);
                    if (daughter != null) {
                        p.childrenId.add(daughter.id);
                    }
                }
            }
            for (String s : p.sonsName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person son = f.get(0);
                    if (son != null) {
                        p.childrenId.add(son.id);
                    }
                }
            }
            for (String s : p.childrenName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person child = f.get(0);
                    if (child != null) {
                        p.childrenId.add(child.id);
                    }
                }
            }
        }
    }

    private void siblingsPrepare(HashMap<String, Person> id_peoples) {
        for (String key : id_peoples.keySet()) {
            Person p = id_peoples.get(key);
            p.siblingsId.addAll(p.brothersId);
            p.siblingsId.addAll(p.sistersId);
            for (String s : p.sistersName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person sister = f.get(0);
                    if (sister != null) {
                        p.siblingsId.add(sister.id);
                    }
                }
            }
            for (String s : p.brothersName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person brother = f.get(0);
                    if (brother != null) {
                        p.siblingsId.add(brother.id);
                    }
                }
            }
            for (String s : p.siblingsName) {
                List<Person> f = id_peoples.values().parallelStream().filter(x -> s.equals(x.firstName + " " + x.lastName)).toList();
                if (!f.isEmpty()) {
                    Person sibling = f.get(0);
                    if (sibling != null) {
                        p.siblingsId.add(sibling.id);
                    }
                }
            }
        }
    }

    private void genderPrepare(HashMap<String, Person> id_peoples) {
        for (var p : id_peoples.values()) {
            if (p.gender == null) {
                if (p.wifeId != null || p.wifeName != null) {
                    p.gender = "M";
                } else if (p.husbandId != null || p.husbandName != null) {
                    p.gender = "F";
                }
            }
        }
        for (var p : id_peoples.values()) {
            if (p.gender == null) {
                if (p.spouseId != null) {
                    Person pp = id_peoples.get(p.spouseId);
                    if (pp.gender != null) {
                        if (pp.gender.equals("M")) {
                            p.gender = "F";
                        }
                        if (pp.gender.equals("F")) {
                            p.gender = "M";
                        }
                    } else if (pp.husbandName != null || pp.husbandId != null) {
                        p.gender = "M";
                    } else if (pp.wifeName != null || pp.wifeId != null) {
                        p.gender = "F";
                    }
                }
            }
        }
    }
}
