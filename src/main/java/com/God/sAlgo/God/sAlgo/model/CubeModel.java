package com.God.sAlgo.God.sAlgo.model;

import java.util.Arrays;

public class CubeModel {

    public int[] perm = new int[8];
    public int[] orient = new int[8];

    public CubeModel(){
        for(int i=0;i<8;i++){
            perm[i] = i;
            orient[i] = 0;
        }
    }

    public CubeModel(CubeModel other){
        this.perm = Arrays.copyOf(other.perm, 8);
        this.orient = Arrays.copyOf(other.orient, 8);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CubeModel)) return false;
        CubeModel o = (CubeModel) obj;
        return Arrays.equals(perm, o.perm) && Arrays.equals(orient, o.orient);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(perm) * 31 + Arrays.hashCode(orient);
    }

    @Override
    public String toString() {
        return "perm=" + Arrays.toString(perm) +
                " orient=" + Arrays.toString(orient);
    }

}
