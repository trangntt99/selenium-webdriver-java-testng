package javaTester;

import java.util.Random;

public class Topic_06_Random {
    // Java Builtin (Cung cấp sẵn - lấy ra sử dụng)
    // Java Libraries (Do 1 cá nhân/ tổ chức họ tự viết)
    // Java Faker

    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt(99999));
        System.out.println(rand.nextDouble());
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextBoolean());
    }
}
