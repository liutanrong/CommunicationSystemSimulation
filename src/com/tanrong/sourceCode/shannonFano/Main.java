package com.tanrong.sourceCode.shannonFano;

import java.util.ArrayList;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class Main {
    public static void main(String[] args){
        String source="12345678abcdefg";
        System.out.println(source);
        ArrayList<Sign> signs= ShannonFano.getSign(source);
        String s1=ShannonFano.encodeString(source,signs);
        System.out.println(s1);

        String o=ShannonFano.decodeString(s1,signs);
        System.out.println(o);
    }
}
