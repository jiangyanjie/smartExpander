package util;

public class Progress {
    private static int num = 0;

    public static void print() {
        System.out.print((char)('!'+num));
        num++;
        if (num == 90) {
            System.out.println();
            num = 0;
        }
    }

    public static void main(String[] args) {
        print();
        print();
    }
}
