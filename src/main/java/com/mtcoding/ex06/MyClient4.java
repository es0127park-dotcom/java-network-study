package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class MyClient4 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 20000); // 자기 자신 IP 주소 - 127.0.0.1 or localhost

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true); // 쓰기 버퍼 / true -> autoflush
            Scanner socketSc = new Scanner(socket.getInputStream());

            Person person = new Person(1, "홍길동", 20, Arrays.asList("축구", "농구"));
            Gson gson = new Gson();
            String json = gson.toJson(person);

            pw.println(json); // ln이 \n이 넣어주고, autoFlush가 된다.
            String recv = socketSc.nextLine();
            System.out.println("서버로부터 받은 메시지 : " + recv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
