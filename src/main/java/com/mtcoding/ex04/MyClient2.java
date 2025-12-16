package com.mtcoding.ex04;

import java.io.*;
import java.net.Socket;

public class MyClient2 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.39",20000); // 자기 자신 IP 주소 - 127.0.0.1 or localhost

            InputStream keyStream = System.in;
            InputStreamReader keyReader = new InputStreamReader(keyStream);
            BufferedReader keyBuf = new BufferedReader(keyReader);

            OutputStream out = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(ow);

            while (true) {
                String keyBoardDate = keyBuf.readLine();
                bw.write(keyBoardDate);
                bw.write("\n");
                bw.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
