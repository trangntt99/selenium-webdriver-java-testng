package javaTester;

public class Topic_09_Array {
    int[] studentAge = {15, 10, 20, 22};
    static String[] studentName = {"Nguyen Van An", "Le Van Hoa"};

    public static void main(String[] args) {
        String[] studentAddress = new String[5];
        studentAddress[0] = "Dang Ngoc Anh";
        studentAddress[1] = "Dao Duy Tu";
        studentAddress[2] = "Nguyen Trai";
        studentAddress[3] = "Nguyen Du";
        studentAddress[4] = "Le Loi";

        System.out.println(studentName[0]);

        for (int i = 0; i < studentAddress.length; i++) {
            System.out.println(studentAddress[i]);
        }
    }
}
