package javaTester;

import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {

    public static void main(String[] args) {
        List<String> students = new ArrayList<String>();
        students.add("John");
        students.add("Mary");
        students.add("Peter");
        students.add("Nam");

        List<Object> addresses = new ArrayList<>();
        addresses.add("123 Main St.");
        addresses.add(15);
        addresses.add(true);
        addresses.add(15.56);
    }
}
