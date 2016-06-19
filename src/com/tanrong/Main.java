package com.tanrong;

import com.tanrong.channel.BSC;
import com.tanrong.channelCode.ChannelCodeUtil;
import com.tanrong.sourceCode.SourceCodeUtil;
import com.tanrong.sourceCode.huffman.HuffmanUtil;
import com.tanrong.sourceCode.shannonFano.ShannonFano;
import com.tanrong.sourceCode.shannonFano.Sign;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by tanrong.ltr on 16/6/19.
 *
 * 如果以jar方式启动,则需要在跟着七个参数,分别为
 * 离散信源分布概率
 * 离散信源长度
 * 信道编码器
 * 信源编码器
 * 传输是那种传输
 * 传输错误概率
 * 传输错误概率
 *
 */
public class Main {
    private static double originProbability;//离散信源分布概率
    private static int originLength;//离散信源长度

    private static int sourceCodeType;//信道编码器
    private static int channelCodeType;//信源编码器

    private static int errorType;//传输是那种传输
    private static double errorProbability1=0;//传输错误概率
    private static double errorProbability2=0;//传输错误概率

    public static void main(String[] args){
        if (args!=null&&args.length==7){
            originProbability= Double.parseDouble(args[0]);
            originLength= Integer.parseInt(args[1]);


            sourceCodeType= Integer.parseInt(args[2]);
            channelCodeType= Integer.parseInt(args[3]);


            errorType= Integer.parseInt(args[4]);
            errorProbability1= Double.parseDouble(args[5]);
            errorProbability2= Double.parseDouble(args[6]);

            if (sourceCodeType==1)
                System.out.println("信源编码器:无编码");
            else if (sourceCodeType==2)
                System.out.println("信源编码器:二进制香农-费诺编码");
            else if (sourceCodeType==3)
                System.out.println("信源编码器:二进制霍夫曼编码");


            if (channelCodeType==1)
                System.out.println("信道编码器:无编码");
            else if (channelCodeType==2)
                System.out.println("信道编码器:3次重复编码");
            else if (channelCodeType==3)
                System.out.println("信道编码器:Hamming（7，4）码");


            if (errorType==1)
                System.out.println("信道:理想信道");
            else if (errorType==2)
                System.out.println("信道:给定错误概率为"+errorProbability1+"的BSC信道");
            else if (errorType==3)
                System.out.println("信道:给定符号0，1各自错误概率"+errorProbability1+" "+errorProbability2);
        }else {
            input();
        }

        /****开始运算****/
        //生成原始序列
        Source source =new Source(originProbability);
        int[] original= source.generate(originLength);
        String originStr="";
        for (int i = 0; i < original.length; i++) {
            originStr=originStr+original[i];
        }


        Map<Character, Integer> statistics=null;//如果是Huffman信源编码,需要计算这个
        ArrayList<Sign> signs=null;//如果是ShannonFano信源编码,需要计算这个
        signs= ShannonFano.getSign(originStr);
        statistics=HuffmanUtil.statistics(originStr.toCharArray());



        int[] sourceEncodeResult= SourceCodeUtil.encode(sourceCodeType,original,statistics,signs);
        int[] channelEncodeResult= ChannelCodeUtil.encode(channelCodeType,sourceEncodeResult);

        int[] transmitResult;
        if (errorType==1||errorType==2){
            //如果是理想信道或者给定错误概率为p的BSC
            transmitResult=BSC.send(channelEncodeResult,errorProbability1);
        }else {
            //如果指定0、1各自错误率
            transmitResult=BSC.send2(channelEncodeResult,errorProbability1,errorProbability2);
        }

        int parityCount=0;//Hamming码校验位的长度
        if (channelCodeType==3){
            //如果是以海明码传输需要算出校验位的长度
            parityCount=channelEncodeResult.length-sourceEncodeResult.length;
        }
        int[] channelDecodeResult=ChannelCodeUtil.decode(channelCodeType,transmitResult,parityCount);

        int[] sourceDecodeResult=SourceCodeUtil.decode(sourceCodeType,channelDecodeResult,statistics,signs);

        println("原始序列",original);
        println("信源编码后序列",sourceEncodeResult);
        println("信道编码后序列",channelEncodeResult);
        println("信道传输后序列",transmitResult);
        println("信道解码后序列",channelDecodeResult);
        println("信源解码后序列",sourceDecodeResult);


        //BER 是在数据传输过程中比特被传错的概率
        //误码率=传输中的误码/所传输的总码数*100%
        //BLER 传输块经过CRC校验后的错误概率
        double codeEffectiveness=(double) original.length/channelEncodeResult.length;//信道编码后的传输效率

        double BER=(double)getErrorCount(sourceDecodeResult,original)/original.length;//整个过程中数据被传错的概率
        double channelEncodeError=(double)getErrorCount(channelDecodeResult,sourceEncodeResult)/sourceDecodeResult.length;//信道编译码过程中传错的概率
        System.out.printf("信道编码后的信息传输效率:%.2f%%\n",codeEffectiveness*100);
        System.out.printf("整个通信过程中的误比特率:%.2f%%\n",BER*100);
        System.out.printf("信道编译码过程中产生的误码率:%.2f%%\n",channelEncodeError*100);


    }

    private static int getErrorCount(int[] data1, int[] data2){
        if (data1.length!=data2.length) return 0;

        int errorCode=0;

        for (int i = 0; i < data1.length; i++) {
            if (data1[i]!=data2[i]){
                errorCode++;
            }
        }
        return errorCode;
    }
    /**
     * 输入值
     */
    private static void input(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入离散信源概率分布和二进制序列长度如 0.1  15");
        originProbability=scanner.nextDouble();
        originLength=scanner.nextInt();

        System.out.println("请选择信源编码器");
        System.out.println("1.无编码");
        System.out.println("2.二进制香农-费诺编码");
        System.out.println("3.二进制霍夫曼编码");
        sourceCodeType=scanner.nextInt();

        System.out.println("请选择信道编码器");
        System.out.println("1.无编码");
        System.out.println("2.3次重复编码");
        System.out.println("3.Hamming（7，4）码");
        channelCodeType=scanner.nextInt();


        System.out.println("请选择信道");
        System.out.println("1.理想信道");
        System.out.println("2.给定错误概率为p的BSC信道");
        System.out.println("3.给定符号0，1各自错误概率p,q");
        errorType=scanner.nextInt();
        if (errorType==1){
            errorProbability1=0;
        }else if (errorType==2){
            System.out.println("请输入信道错误率");
            errorProbability1=scanner.nextDouble();
        }else if (errorType==3){
            System.out.println("请输入0/1各自错误率如 0.2 0.4");
            errorProbability1=scanner.nextDouble();
            errorProbability1=scanner.nextDouble();
        }
    }


    /**
     * 打印字符
     * @param message
     * @param data
     */
    private static void println(String message,int[] data){
        System.out.println(message);
        for (int i:data){
            System.out.printf(i+" ");
        }
        System.out.println("\n====================");
    }
}
