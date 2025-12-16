package com.mtcoding.ex01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class buf04 {
    public static void main(String[] args) {
        // 1. 바이트 스트림 연결
        OutputStream out = System.out; // 모니터 연결
        OutputStreamWriter ow = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(ow);
        try {
            bw.write("ABC"); //
            bw.flush(); // 버퍼를 비워라! (꽉 찼을 때는 자동으로 비워짐)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
