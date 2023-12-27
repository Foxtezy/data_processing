package ru.nsu;

import java.util.Map;

public class Assertions {

    public void assertSiblings(Map<String, Person> id_peoples) {
        for (var p : id_peoples.values()) {
            if (p.siblingsCount != null && p.siblingsId.size() != p.siblingsCount) {
                System.out.println("SIBLINGS ASSERT: " + p);
            }
        }
    }

    public void assertChildren(Map<String, Person> id_peoples) {
        for (var p : id_peoples.values()) {
            if (p.childrenCount != null && p.childrenId.size() != p.childrenCount) {
                System.out.println("CHILDREN ASSERT: " + p);
            }
        }
    }
}
