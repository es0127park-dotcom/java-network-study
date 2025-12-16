package com.mtcoding.ex05;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient3 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",20000); // 자기 자신 IP 주소 - 127.0.0.1 or localhost

            Scanner sc = new Scanner(System.in); // 키보드에 연결된 버퍼

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // 쓰기 버퍼 / true -> autoflush
            Scanner socketSc = new Scanner(socket.getInputStream());

            while (true) {
                String keyBoardDate = sc.nextLine();
                pw.println(keyBoardDate); // ln이 \n이 넣어주고, autoFlush가 된다.
                String recv = socketSc.nextLine();
                System.out.println("서버로부터 받은 메시지 : " + recv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
