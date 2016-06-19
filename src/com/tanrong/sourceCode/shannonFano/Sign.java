package com.tanrong.sourceCode.shannonFano;

/**
 * Created by tanrong.ltr on 16/6/19.
 */

public class Sign implements Comparable{

    /** Creates a new instance of Sign */
    private String sign;
    private int times;
    private String value;

    public Sign() {
        sign="";
        times=0;
        value="";
    }
    public Sign(String sign,int times){
        this.sign=sign;
        this.times=times;
        this.value="";
    }
    public String GetSign(){
        return sign;
    }
    public int GetTimes(){
        return times;
    }
    public String GetValue(){
        return value;
    }
    public void SetValue(String value){
        this.value=value;
    }

    public int compareTo(Object o){
        if (o instanceof Sign) {
            return (((Sign)o).GetTimes()-this.times);
        }else {
            throw new ClassCastException("Can't compare");
        }
    }
}