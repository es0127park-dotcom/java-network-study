package com.mtcoding.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf03 {
    public static void main(String[] args) {
        // 기능 확장 -> 데코레이터(장식) 패턴

        // 1. 바이트 스트림 연결
        InputStream in = System.in; // 스트림 연결됨

        // 2. 숫자를 문자로 변환해주는 것을 설정
        InputStreamReader ir = new InputStreamReader(in);

        // 3. 직적 배열을 다는 게 아니라, 가변 배열을 달아줌
        BufferedReader br = new BufferedReader(ir); // 상자를 직접 만들어 주겠다!

        try {
            String line = br.readLine(); // 엔터키까지 읽는 함수 (버퍼에 있는 것들을)
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
