package com.java4all.formula;

import java.math.BigDecimal;

/**
 * Author: momo
 * Date: 2018/3/27
 * Description:常用的计算公式/方法
 * 计算同比 （a-b）/b
 * a / b   计算百分比
 * a / b
 */
public class FormulaUtil {


    /**
     * 计算同比增长   公式：（今年值-去年同期值）/去年同期值
     * @param nowSum    今年
     * @param lastSum   去年
     * @return  前台直接显示即可
     */
    public static String getTongbiPercent(BigDecimal nowSum, BigDecimal lastSum){

        String sumYoYStr = "";
        try {

            //分母为0 或者为 负数
            if (null == lastSum || null == nowSum
                    || lastSum.compareTo(BigDecimal.ZERO) == 0 || lastSum.compareTo(BigDecimal.ZERO) == -1) {
                sumYoYStr = "-";
            } else {
                //计算同比   （今年值-去年同期值）/去年同期值
                BigDecimal big = ((nowSum.subtract(lastSum)).divide(lastSum,4,BigDecimal.ROUND_HALF_UP));

                //  处理如0.00003之类的特殊情况
                BigDecimal zh = new BigDecimal(0.0001);
                BigDecimal fu = new BigDecimal(-0.0001);
                BigDecimal ze = new BigDecimal(0.0000);

                if (big.compareTo(BigDecimal.ZERO) == 1 && big.compareTo(zh) == -1) {//        0.0000 --0.0001之间
                    //sumYoYStr = "0.0001";
                    sumYoYStr = "0.01%";
                } else if (big.compareTo(fu) == 1 && big.compareTo(BigDecimal.ZERO) == -1) {// -0.0001 --0.0000之间
                    //sumYoYStr = "-0.0001";
                    sumYoYStr = "0.01%";
                } else {
                    //sumYoYStr = big.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue() + "";
                    BigDecimal v = big.setScale(4, BigDecimal.ROUND_HALF_UP);
                    sumYoYStr = v.multiply(new BigDecimal(100)).setScale(2) + "%";
                }
            }
            return sumYoYStr;
        }catch (Exception ex){
            throw new RuntimeException("====>计算同比出错",ex);
        }
    }
    /**
     * a / b   计算百分比
     * @param a
     * @param b
     * @return eg:65.32%
     */
    public static String ADivideBPercent(BigDecimal a,BigDecimal b){
        String percent =
                b == null ? "-" :
                        b.compareTo(BigDecimal.ZERO) == 0 ? "-" :
                                a == null ? "0.00%" :
                                        a.multiply(new BigDecimal(100)).divide(b,4).setScale(2,BigDecimal.ROUND_HALF_UP) + "%";
        return percent;
    }

    /**
     * a / b
     * @param a
     * @param b
     * @return eg:65.32
     */
    public static String ADivideB(BigDecimal a,BigDecimal b){
        String result =
                b == null ? "-" :
                        b.compareTo(BigDecimal.ZERO) == 0 ? "-":
                                a == null ? "0.00" :
                                        a.divide(b,4).setScale(2,BigDecimal.ROUND_HALF_UP) + "";
        return result;
    }

    public static void main(String[] args){
        System.out.println(ADivideB(new BigDecimal(2),new BigDecimal(2)));
    }
}
