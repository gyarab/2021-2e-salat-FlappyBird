package sample;

import java.io.*;
import java.net.Socket;

public class SendDataToServer {
    public static void send() throws IOException {
        Socket s = new Socket("localhost", 10000);
        PrintWriter print = new PrintWriter(s.getOutputStream());
        String data = String.format("{" +
                "\"username\": \"%s\"," +
                "\"score\": \"%s\" }", ReadWriteXML.readName(), ReadWriteXML.readHighscore());
        print.write(data);
        print.close();
    }
}
