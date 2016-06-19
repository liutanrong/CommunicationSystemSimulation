package com.tanrong.test;

import com.tanrong.channel.BSC;
import com.tanrong.channelCode.ChannelCodeUtil;
import com.tanrong.test.ChannelCodeUtilTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class BSCTest {
    @Test
    public void send() throws Exception {
        System.out.println("test1");
        int[] data=new int[]{1,0,1,1,1,0};
        int[] r= BSC.send(data,0.2);
        ChannelCodeUtilTest.println("send",r);
    }

    @Test
    public void test2() throws Exception {

        System.out.println("test2");
        int[] data=new int[]{1,0,1,1,1,0};
        int[] r= BSC.send2(data,0.2,0.1);
        ChannelCodeUtilTest.println("send",r);
    }
    @Test
    public void tes3() throws Exception {

        System.out.println("test3");
        int[] data=new int[]{1,0,1,1,1,0};
        int[] r= BSC.send2(data,0.2,0.3);
        ChannelCodeUtilTest.println("send",r);
    }

}