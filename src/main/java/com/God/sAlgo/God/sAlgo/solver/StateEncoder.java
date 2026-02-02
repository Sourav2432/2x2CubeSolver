package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.CubeModel;

public class StateEncoder {

    public static long encode(CubeModel c){
        long orientCode = encodeOrientation(c);
        long permCode = encodePermutation(c);
        return permCode * 2187L + orientCode;
    }

    private static int encodeOrientation(CubeModel c){
        int code=0;
        for(int i=0;i<7;i++){
            code = code * 3 + c.orient[i];
        }
        return code;
    }

    private static int encodePermutation(CubeModel c){
        int code=0;
        int factor = 1;

        for(int i = 7; i >= 0; i--){
            int count = 0;
            for(int j=i - 1; j>=0; j--){
                if(c.perm[j] > c.perm[i]){
                    count++;
                }
            }
            code += count * factor;
            factor *= (i+1);
        }
        return code;
    }


}
