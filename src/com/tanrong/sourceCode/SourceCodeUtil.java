package com.tanrong.sourceCode;

import com.tanrong.sourceCode.huffman.HuffmanUtil;
import com.tanrong.sourceCode.shannonFano.ShannonFano;
import com.tanrong.sourceCode.shannonFano.Sign;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class SourceCodeUtil {
    public static int[] encode(int type, int[] data, Map<Character, Integer> statistics, ArrayList<Sign> signs){
        int[] result=new int[0];
        String ori="";
        for (int aData : data) {
            ori = ori + aData;
        }

        String strResult="";
        switch (type){
            case 1:
                //无编码
                result=data;
                break;
            case 2:
                //二进制香农-费诺编码
                strResult= ShannonFano.encodeString(ori,signs);
                break;
            case 3:
                //二进制霍夫曼编码
                strResult= HuffmanUtil.encode(ori,statistics);
                break;
            default:
                System.out.println("请选择正确的信源编码器");
                break;
        }
        //如果是ShannonFano或者Huffman编码将其转化为数组
        if (strResult!=null&&strResult.length()!=0){
            char[] charResult=strResult.toCharArray();
            result=new int[charResult.length];
            for (int i = 0; i < charResult.length; i++) {
                if (charResult[i]=='1')
                    result[i]=1;
                else
                    result[i]=0;
            }
        }
        return result;
    }
    public static int[] decode(int type,int[] data,Map<Character, Integer> statistics,ArrayList<Sign> signs){
        int[] result = new int[0];
        String ori="";
        for (int i = 0; i < data.length; i++) {
            ori=ori+data[i];
        }
        String strResult="";

        switch (type){
            case 1:
                //无编码
                result=data;
                break;
            case 2:
                //二进制香农-费诺编码
                strResult= ShannonFano.decodeString(ori,signs);
                break;
            case 3:
                //二进制霍夫曼编码
                strResult= HuffmanUtil.decode(ori,statistics);
                break;
            default:
                System.out.println("请选择正确的信源编码器");
                break;
        }
        //如果是ShannonFano或者Huffman编码将其转化为数组
        if (strResult!=null&&strResult.length()!=0){
            char[] charResult=strResult.toCharArray();
            result=new int[charResult.length];
            for (int i = 0; i < charResult.length; i++) {
                if (charResult[i]=='1')
                    result[i]=1;
                else
                    result[i]=0;
            }
        }

        return result;
    }

}
