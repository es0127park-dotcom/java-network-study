package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer4 {
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
                Gson gson = new Gson();
                Person p = gson.fromJson(line, Person.class);

                System.out.println(p.getNo());
                System.out.println(p.getName());
                System.out.println(p.getAge());
                System.out.println(p.getHobby().get(0));
                System.out.println(p.getHobby().get(1));

                if (p.getAge() > 18) {
                    pw.println("성인");
                } else {
                    pw.println("미성년자");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
