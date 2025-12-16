package com.mtcoding.ex05;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer3 {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(20000);
            Socket socket = ss.accept();

            // 읽기 버퍼
            Scanner sc = new Scanner(socket.getInputStream());

            // 쓰기 버퍼
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = sc.nextLine(); // 엔터키(\n)까지 읽기 / 엔터키까지 안읽는 다른 함수도 있음
                System.out.println("[Server] " + line);
                pw.println("ok");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
