package com.mtcoding.ex08;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServerDuplex {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(20000);
            System.out.println("[server] 대기중...");

            Socket socket = ss.accept();
            System.out.println("[server] 연결됨: " + socket.getInetAddress());

            Scanner socketSc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            Scanner keyboardSc = new Scanner(System.in);

            // 1) 수신 스레드: 클라이언트 -> 서버
            Thread receiver = new Thread(() -> {
                try {
                    while (socketSc.hasNextLine()) {
                        String line = socketSc.nextLine();
                        System.out.println("[server][recv] " + line);

                        // 자동 응답도 가능
                        // pw.println("ok: " + line);

                        if ("quit".equalsIgnoreCase(line)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[server] receiver 종료: " + e.getMessage());
                }
            });

            // 2) 송신 스레드: 서버 -> 클라이언트 (서버 키보드 입력)
            Thread sender = new Thread(() -> {
                try {
                    while (true) {
                        String msg = keyboardSc.nextLine();
                        pw.println(msg);

                        if ("quit".equalsIgnoreCase(msg)) {
                            try { socket.close(); } catch (Exception ignored) {}
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("[server] sender 종료: " + e.getMessage());
                }
            });

            receiver.start();
            sender.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}