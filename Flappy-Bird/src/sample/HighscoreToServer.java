package sample;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class HighscoreToServer {

    public static void write(String a, int i) throws IOException {
        FileOutputStream f = new FileOutputStream("username.xml", false);
        XMLEncoder encoder = new XMLEncoder(f);
        encoder.writeObject(a);
        encoder.writeObject(i);
        encoder.close();
    }

    public static void read() throws IOException {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("username.xml"))));
        String s = (String) decoder.readObject();
        int c = (int) decoder.readObject();
        System.out.println(s);
        System.out.println(c);
    }

    public static void main(String[] args) throws IOException {
         read();

    }
}
