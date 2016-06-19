package com.tanrong.test;

import com.tanrong.channelCode.ChannelCodeUtil;
import com.tanrong.sourceCode.SourceCodeUtil;
import com.tanrong.sourceCode.huffman.HuffmanUtil;
import com.tanrong.sourceCode.shannonFano.ShannonFano;
import com.tanrong.sourceCode.shannonFano.Sign;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class SourceCodeUtilTest {
    @Test
    public void encode() throws Exception {
        int[] original=new int[]{1,0,1,0,1,1,0,0};
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }

        int sourceCodeType=1;
        Map<Character, Integer> statistics=null;//如果是Huffman信源编码,需要计算这个
        ArrayList<Sign> signs=null;//如果是ShannonFano信源编码,需要计算这个
        if (sourceCodeType==2){
            signs= ShannonFano.getSign(originStr);
        }else if (sourceCodeType==3){
            statistics= HuffmanUtil.statistics(originStr.toCharArray());
        }

        int[] sourceEncodeResult= SourceCodeUtil.encode(sourceCodeType,original,statistics,signs);
        ChannelCodeUtilTest.println("sourceEncodeResult",sourceEncodeResult);

        int[] sourceDecodeResult=SourceCodeUtil.decode(sourceCodeType,sourceEncodeResult,statistics,signs);
        ChannelCodeUtilTest.println("sourceDecodeResult",sourceDecodeResult);
        ChannelCodeUtilTest.println("original",original);
    }
    @Test
    public void test2() throws Exception {
        int[] original=new int[]{1,0,1,0,1,1,0,0};
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }

        int sourceCodeType=2;
        Map<Character, Integer> statistics=null;//如果是Huffman信源编码,需要计算这个
        ArrayList<Sign> signs=null;//如果是ShannonFano信源编码,需要计算这个
        if (sourceCodeType==2){
            signs= ShannonFano.getSign(originStr);
        }else if (sourceCodeType==3){
            statistics= HuffmanUtil.statistics(originStr.toCharArray());
        }

        int[] sourceEncodeResult= SourceCodeUtil.encode(sourceCodeType,original,statistics,signs);
        ChannelCodeUtilTest.println("sourceEncodeResult",sourceEncodeResult);

        int[] sourceDecodeResult=SourceCodeUtil.decode(sourceCodeType,sourceEncodeResult,statistics,signs);
        ChannelCodeUtilTest.println("sourceDecodeResult",sourceDecodeResult);
        ChannelCodeUtilTest.println("original",original);
    }
    @Test
    public void test3() throws Exception {
        int[] original=new int[]{1,0,1,0,1,1,0,0};
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }

        int sourceCodeType=3;
        Map<Character, Integer> statistics=null;//如果是Huffman信源编码,需要计算这个
        ArrayList<Sign> signs=null;//如果是ShannonFano信源编码,需要计算这个
        if (sourceCodeType==2){
            signs= ShannonFano.getSign(originStr);
        }else if (sourceCodeType==3){
            statistics= HuffmanUtil.statistics(originStr.toCharArray());
        }

        int[] sourceEncodeResult= SourceCodeUtil.encode(sourceCodeType,original,statistics,signs);
        ChannelCodeUtilTest.println("sourceEncodeResult",sourceEncodeResult);

        int[] sourceDecodeResult=SourceCodeUtil.decode(sourceCodeType,sourceEncodeResult,statistics,signs);
        ChannelCodeUtilTest.println("sourceDecodeResult",sourceDecodeResult);
        ChannelCodeUtilTest.println("original",original);
    }
}