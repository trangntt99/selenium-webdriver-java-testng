package javaTester;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Topic_11_List {
    @Test
    public void testList() {
        // ArrayList - truy xuat du lieu (query)
        // LinkedList - them sua xoa
        List<String> studentName = new ArrayList<>();
        studentName.add("Nguyen Dinh Luong");
        studentName.add("Hoang Van Nam");
        studentName.add("Nguyen Anh Hong");

        // 3 element trong list
        System.out.println(studentName.size());
    }
}
