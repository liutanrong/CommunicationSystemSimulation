package com.tanrong.sourceCode.shannonFano;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class ShannonFano {

    public static ArrayList<Sign> getSign(String source){
        ArrayList<Sign> signs=new ArrayList<Sign>(); //用来存放各个字符的信息。
        int total=source.length();//字符的总个数

        //统计各个字符出现的次数
        char content[]=source.toCharArray();
        ArrayList<String> list=new ArrayList<String>();
        for(int i=0;i<content.length;i++){
            list.add(String.valueOf(content[i]));
        }
        Collections.sort(list);
        int begin=0,end=0,times=0;
        String character="";
        while(end<total-1){
            character=list.get(begin);
            end=list.lastIndexOf(character);
            times=end-begin+1;
            Sign stmp=new Sign(character,times);
            signs.add(stmp);
            begin=end+1;
        }
        //按照字符出现的次数进行排序
        Collections.sort(signs);
        //对字符进行编码
        signs=encode(0,signs.size(),total,signs);
        return signs;
    }

    /**
     * 根据字符编码对文件进行压缩
     * @param source
     * @param signs
     * @return
     */
    public static String encodeString(String source, ArrayList<Sign> signs){

        //根据字符编码，对源文件进行压缩
        String compression="";
        for(int i=0;i<source.length();i++){
            char temp=source.charAt(i);
            for(int j=0;j<signs.size();j++){
                if(temp==signs.get(j).GetSign().charAt(0)){
                    compression+=signs.get(j).GetValue();
                    break;
                }
            }
        }
        return compression;
    }

    //对字符进行编码，用了递归算法
    private static ArrayList<Sign> encode(int begin,int end,int total_times,ArrayList<Sign> signs){
        if(begin<end-1){
            int middle=0,fwd_times=0;
            int i;
            for(i=begin;i<end;i++){
                if(2*fwd_times<total_times){
                    fwd_times+=signs.get(i).GetTimes();
                }else{
                    break;
                }
            }
            for(int j=begin;j<i;j++){
                signs.get(j).SetValue(signs.get(j).GetValue()+"0");
            }
            for(int j=i;j<end;j++){
                signs.get(j).SetValue(signs.get(j).GetValue()+"1");
            }
            //递归
            signs=encode(begin,i,fwd_times,signs);
            signs=encode(i,end,(total_times-fwd_times),signs);
        }
        return signs;
    }

    /**
     * 解压缩字符串
     * @param source
     * @param signs
     * @return
     */
    public static String decodeString(String source, ArrayList<Sign> signs){
        String compression = source; //源字符串
        //进行解压缩
        String uncompression="";
        while(compression.length()>0){
            for(int i=0;i<signs.size();i++){
                String temp=signs.get(i).GetValue();
                if(compression.startsWith(temp)){
                    uncompression=uncompression+signs.get(i).GetSign();
                    compression=compression.substring(temp.length());
                    break;
                }
            }
        }
        return uncompression;
    }

    /**
     * 输出编码的熵，平均码长和编码效率
     * @param signs
     * @param sourceLength
     */
    public static void getInfo(ArrayList<Sign> signs,int sourceLength) {
        Map<String,String> result=new HashMap<>();
        double entropy=0,length=0;
        //计算熵
        for(int i=0;i<signs.size();i++){
            int temp=signs.get(i).GetTimes();
            entropy+=temp*Math.log10(temp);
        }
        entropy=(entropy-Math.log10(sourceLength))/(23*Math.log10(2));
        //计算平均码长
        for(int i=0;i<signs.size();i++){
            length+=signs.get(i).GetTimes()*signs.get(i).GetValue().length();
        }
        length=length/23;

        result.put("entropy",entropy+"");
        result.put("length",length+"");
        result.put("xiaolv",entropy/length+"");
    }
}