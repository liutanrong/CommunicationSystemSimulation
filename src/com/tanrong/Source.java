package com.tanrong;

/**
 * Created by tanrong.ltr on 16/6/19.
 */
public class Source {
    double probability;

    public Source(double probability){
        this.probability=probability;
    }

    /**
     * 生成信源
     * @param length
     * @return
     */
    public int[] generate(int length) {
        if (length == 0) return new int[0];
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            if (Math.random() < probability) {
                result[i] = 1;
            }else {
                result[i]=0;
            }
        }
        return result;
    }
}
