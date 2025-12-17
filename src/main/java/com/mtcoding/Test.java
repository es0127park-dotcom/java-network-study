package com.mtcoding;

public class Test {
    public static void main(String[] args) {
        String answer = "";
        String my_string = "bread";
        char c;

        for (int i = 0; i < my_string.length(); i++) {
            c = my_string.charAt(i);
            answer = c + answer;
        }

        System.out.println(answer);
    }
}
