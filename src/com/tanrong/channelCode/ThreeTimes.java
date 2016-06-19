package com.tanrong.channelCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class ThreeTimes {

    /**
     * 生成三次重复码
     * @param data
     * @return
     */
    public static int[] encode(int[] data){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
            list.add(data[i]);
            list.add(data[i]);
        }

        int[] r=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            r[i]=list.get(i);
        }
        return r;
    }

    /**
     * 解码三次重复码
     * @param data
     * @return
     */
    public static int[] decode(int[] data){
        if (data.length%3!=0) return new int[0];

        int[] result=new int[data.length/3];
        for (int i = 0,m=0; i < data.length; i=i+3,m++) {
            int i1=data[i];
            int i2=data[i+1];
            int i3=data[i+2];
             if (i1==i2||i2==i3){
                result[m]=i2;
            }else if (i1==i3){
                result[m]=i1;
            }else {
                 //如果输入是标准0/1的话永远执行不到这句话
                 result[m]=i1;
             }
        }
        return result;
    }
}
