package com.tanrong.channelCode;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class ChannelCodeUtil {

    /**
     * 将传输来的数据进行信道编码
     *
     * @param type
     * @param data
     * @return
     */
    public static int[] encode(int type,int[] data){
        int[] result;
        switch (type){
            case 1:
                //无编码
                result=data;
                break;
            case 2:
                //3次重复编码
                result= ThreeTimes.encode(data);
                break;
            case 3:
                //Hamming(7,4)编码

                result= Hamming.generateHamming(data);
                break;
            default:
                System.out.println("请选择正确的信道编码器");
                result=new int[0];
                System.exit(0);
                break;
        }
        return result;
    }

    /**
     * 将传输来的数据进行信道译码
     * @param type
     * @param data
     * @param length
     * @return
     */
    public static int[] decode(int type,int[] data,int length){
        int[] result;
        switch (type){
            case 1:
                //无编码
                result=data;
                break;
            case 2:
                //3次重复编码
                result= ThreeTimes.decode(data);
                break;
            case 3:
                //Hamming(7,4)编码
                result=Hamming.decode(data,length);
                break;
            default:
                System.out.println("请选择正确的信道编码器");
                result=new int[0];
                System.exit(0);
                break;
        }
        return result;
    }
}
