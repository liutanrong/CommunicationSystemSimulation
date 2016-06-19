package com.tanrong.channelCode;

import java.util.ArrayList;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class Hamming {

    /**
     * 生成hamming 码
     * @param a
     * @return
     */
    static public int[] generateHamming(int[] a) {
        int b[];
        int i=0, parity_count=0 ,j=0, k=0;
        while(i < a.length) {

            if(Math.pow(2,parity_count) == i+parity_count + 1) {
                parity_count++;
            }
            else {
                i++;
            }
        }

        b = new int[a.length + parity_count];


        for(i=1 ; i <= b.length ; i++) {
            if(Math.pow(2, j) == i) {

                b[i-1] = 2;
                j++;
            }
            else {
                b[k+j] = a[k++];
            }
        }
        for(i=0 ; i < parity_count ; i++) {

            b[((int) Math.pow(2, i))-1] = getParity(b, i);
        }
        return b;

    }
    static int getParity(int b[], int power) {
        int parity = 0;
        for(int i=0 ; i < b.length ; i++) {
            if(b[i] != 2) {

                int k = i+1;
                String s = Integer.toBinaryString(k);

                int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(x == 1) {
                    if(b[i] == 1) {
                        parity = (parity+1)%2;
                    }
                }
            }
        }
        return parity;
    }

    /**
     * 将收到的hamming码解码出来
     * @param a 收到的hamming码数组 校验码个数(生成的hamming码长度-原始长度)
     * @param parity_count
     * @return
     */
    public static int[] decode(int a[], int parity_count) {

        int power;

        int parity[] = new int[parity_count];

        String syndrome = new String();
        for(power=0 ; power < parity_count ; power++) {

            for(int i=0 ; i < a.length ; i++) {

                int k = i+1;
                String s = Integer.toBinaryString(k);
                int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(bit == 1) {
                    if(a[i] == 1) {
                        parity[power] = (parity[power]+1)%2;
                    }
                }
            }
            syndrome = parity[power] + syndrome;
        }

        int error_location = Integer.parseInt(syndrome, 2);
        if(error_location != 0) {
            a[error_location-1] = (a[error_location-1]+1)%2;

        }

        power = parity_count-1;
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=a.length ; i > 0 ; i--) {
            if(Math.pow(2, power) != i) {
                list.add(a[i-1]);
            }
            else {
                power--;
            }
        }
        int[] result=new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i]=list.get(list.size()-1-i);
        }
        return result;
    }

}