package com.tanrong.sourceCode.huffman;

import java.nio.charset.Charset;
import java.util.*;
/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class Main {

    public static void main(String[] args) {
        String oriStr = "00011";
        Map<Character, Integer> statistics = HuffmanUtil.statistics(oriStr.toCharArray());
        String encodedBinariStr = HuffmanUtil.encode(oriStr, statistics);
        String decodedStr = HuffmanUtil.decode(encodedBinariStr, statistics);

        System.out.println("Original sstring: " + oriStr);
        System.out.println("Huffman encoed binary string: " + encodedBinariStr);
        System.out.println("decoded string from binariy string: " + decodedStr);

        System.out.println("binary string of UTF-8: "
                + HuffmanUtil.getStringOfByte(oriStr, Charset.forName("UTF-8")));
        System.out.println("binary string of UTF-16: "
                + HuffmanUtil.getStringOfByte(oriStr, Charset.forName("UTF-16")));
        System.out.println("binary string of US-ASCII: "
                + HuffmanUtil.getStringOfByte(oriStr, Charset.forName("US-ASCII")));
        System.out.println("binary string of GB2312: "
                + HuffmanUtil.getStringOfByte(oriStr, Charset.forName("GB2312")));
    }

}
