package org.maximum0.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [2744] 대소문자 바꾸기
 *
 * 영어 소문자와 대문자로 이루어진 단어를 입력받은 뒤, 대문자는 소문자로, 소문자는 대문자로 바꾸어 출력하는 프로그램을 작성하시오.
 *
 */
public class Question01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        char[] str = input.toCharArray();
        for(int i = 0; i < str.length; i++) {
            char ch = str[i];
            if (ch >= 'A' && ch <= 'Z')
                str[i] = (char)(ch + 'a' - 'A');
            else
                str[i] = (char)(ch + 'A' - 'a');
        }
        System.out.println(String.valueOf(str));
    }
}
