package sample;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class ReadWriteXML {

    public static void write(String a, int i) throws IOException {
        FileOutputStream f = new FileOutputStream("username.xml", false);
        XMLEncoder encoder = new XMLEncoder(f);
        encoder.writeObject(a);
        encoder.writeObject(i);
        encoder.close();
    }

    public static String readName() throws IOException {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("username.xml"))));
        String c = (String) decoder.readObject();
        decoder.readObject();
        decoder.close();
        return c;
    }

    public static int readInt() throws IOException {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("username.xml"))));
        decoder.readObject();
        int c = (int) decoder.readObject();
        decoder.close();
        return c;
    }

    public static int readHighscore() throws IOException {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("high_score.xml"))));
        int c = (int) decoder.readObject();
        decoder.close();
        return c;
    }

    public static void writeHighscore(int i) throws IOException {
        FileOutputStream f = new FileOutputStream("high_score.xml", false);
        XMLEncoder encoder = new XMLEncoder(f);
        encoder.writeObject(i);
        encoder.close();
    }
}
