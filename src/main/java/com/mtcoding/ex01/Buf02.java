package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf02 {
    public static void main(String[] args) {
        // 1. 바이트 스트림 연결
        InputStream in = System.in;

        // 불편한 점 : 글자 하나씩만 받을 수 있음(-> 버퍼 필요), 숫자를 문자로 캐스팅 안 해줌(-> char로 캐스팅)
        // 2. 배열을 가질 수 있고, 문자로 캐스팅 해줌
        InputStreamReader ir = new InputStreamReader(in);
        char[] buf = new char[3]; // 버퍼 -> 글자를 여러 개 받을 수 있음(단점 : 크기 고정됨)

        try {
            ir.read(buf); // 키보드 입력 대기 (\n 까지)

            for (char c : buf) {
                System.out.print(c + ", ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
