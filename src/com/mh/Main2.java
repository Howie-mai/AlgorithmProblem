package com.mh;

import java.text.DecimalFormat;

/**
 * ClassName：
 * Time：20/7/20 下午1:43
 * Description：
 * Author： mh
 */
public class Main2 {

    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat("#.00");
//        double kmTotalD = 0.00d;
//        double calories = 0.00d;
//
//        //计算总公里
//        float kmTotal = getDistanceFromSteps(2922,1,170);
//        kmTotalD = Double.valueOf(df.format(kmTotal));
//        // 计算热量：1步约等于0.04千卡
//        calories = 2922 * 0.04;
//
//
//        System.out.println(Double.valueOf(df.format(calories)));
//        System.out.println(Double.valueOf(kmTotalD));

//        for (int i = 1;i <= 9;i++){
//            char x = (char) (i + '0');
//            System.out.println(x);
//        }

        int i = 0;
        String a = "    -123213";
        int i1 = Integer.parseInt(a);
        System.out.println(i1);
    }

    public static float getDistanceFromSteps(int steps, int gender, int height) {
        float genderParameter = gender == 2 ? 0.85F : 0.8F;
        float basicStrideLength = genderParameter * (float) height;
        float strideRate = 0.0F;
        if (steps >= 180) {
            strideRate = 0.5F;
        } else if (steps >= 162) {
            strideRate = 0.73F;
        } else if (steps >= 140) {
            strideRate = 0.68F;
        } else if (steps >= 120) {
            strideRate = 0.55F;
        } else if (steps >= 90) {
            strideRate = 0.5F;
        } else if (steps >= 80) {
            strideRate = 0.45F;
        } else {
            strideRate = 0.42F;
        }
        return (float) steps * strideRate * basicStrideLength / 100000.0F;
    }
}
