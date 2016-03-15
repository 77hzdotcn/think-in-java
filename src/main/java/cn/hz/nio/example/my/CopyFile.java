package cn.hz.nio.example.my;

import java.io.IOException;
import java.util.Calendar;

public class CopyFile {

    private static String infile = "nio.txt";
    private static String outfile = "out.txt";

    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DAY_OF_MONTH, 35);
        System.out.println(cal.getTime());
    }

}
