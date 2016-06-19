package com.tanrong.test;

import com.tanrong.channelCode.ChannelCodeUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class ChannelCodeUtilTest {
    @Test
    public void encode() throws Exception {
        System.out.println("test1");
        int type=1;
        int[] data=new int[]{1,0,1,0,1};
        int[] encode=ChannelCodeUtil.encode(type,data);
        println("encode",encode);

        int[] decode=ChannelCodeUtil.decode(type,encode,encode.length-data.length);
        println("decode",decode);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("test2");
        int type=2;
        int[] data=new int[]{1,0,1,0,1};
        int[] encode=ChannelCodeUtil.encode(type,data);
        println("encode",encode);

        int[] decode=ChannelCodeUtil.decode(type,encode,encode.length-data.length);
        println("decode",decode);
    }
    @Test
    public void test3() throws Exception {
        int type=2;
        System.out.println("test3");
        int[] data=new int[]{1,0,1,0,1};
        int[] encode=ChannelCodeUtil.encode(type,data);
        println("encode",encode);

        int[] decode=ChannelCodeUtil.decode(type,encode,encode.length-data.length);
        println("decode",decode);
    }

    public static void println(String message,int[] data){
        System.out.println(message);
        for (int i:data){
            System.out.printf(i+" ");
        }
        System.out.println("\n====================");
    }
}