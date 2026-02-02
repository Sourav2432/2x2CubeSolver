package com.God.sAlgo.God.sAlgo.controller.dto;

import java.util.List;
import java.util.Map;

public class CubeColorRequest {

    private Map<String, List<String>> faces;

    public Map<String, List<String>> getFaces() {
        return faces;
    }

    public void setFaces(Map<String, List<String>> faces){
        this.faces = faces;
    }

}
