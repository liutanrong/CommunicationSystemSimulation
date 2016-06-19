package com.tanrong.channel;

/**
 * Created by tanrong.ltr on 16/6/18.
 *
 */

/**
 * BSC信道模拟器,当随机数小于错误发生概率时会发生传输错误
 */
public class BSC {
    /**
     * 经BSC传输信号,返回传输后的值
     * @param data
     * @param errorProbability  传输出错概率
     * @return
     */
    public static int[] send(int[] data,double errorProbability){
        boolean [] x=int2boolean(data);
        for(int i = 0; i<x.length;i++)
            if(Math.random()<errorProbability){
                x[i] = !x[i];
            }
        return boolean2int(x);
    }

    /**
     * 为0,1 设置各自的错误概率进行传输
     * @param data
     * @param error1 1的错误概率
     * @param error2 0的错误概率
     * @return
     */
    public static int[] send2(int[] data,double error1,double error2){
        boolean [] x=int2boolean(data);
        for(int i = 0; i<x.length;i++)
            if (x[i]){
                //如果是1的话
                if(Math.random()<error1){
                    x[i] = !x[i];
                }
            }else {
                //如果是1
                if(Math.random()<error2){
                    x[i] = !x[i];
                }
            }
        return boolean2int(x);
    }
    /**
     * 将Boolean型数组转为整形
     * @param data
     * @return
     */
    private static boolean[] int2boolean(int[] data){
        boolean [] x=new boolean[data.length];
        for (int i = 0; i < data.length; i++) {
            x[i] = data[i] != 0;
        }
        return x;
    }

    /**
     * 将int型数组转为boolean型
     * @param data
     * @return
     */
    private static int[] boolean2int(boolean[] data){
        int[] x=new int[data.length];
        for (int i = 0; i < data.length; i++) {
            x[i]=data[i]?1:0;
        }
        return x;
    }
}
