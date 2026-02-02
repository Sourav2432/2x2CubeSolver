package com.God.sAlgo.God.sAlgo.service;

import com.God.sAlgo.God.sAlgo.solver.GodsTableGenerator;
import com.God.sAlgo.God.sAlgo.solver.GodsTableSerializer;

import java.util.Map;

public class GodsTableService {

    private static Map<Long, Integer> table;

    public static Map<Long, Integer> getTable() {
        if (table != null) return table;

        if (GodsTableSerializer.exists()) {
            table = GodsTableSerializer.load();
        } else {
            System.out.println("God's table not found. Generating...");
            GodsTableGenerator gen = new GodsTableGenerator();
            table = gen.generate();
        }
        return table;
    }

}
