package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.CubeModel;
import com.God.sAlgo.God.sAlgo.model.Move;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class GodsTableGenerator {

    private final Map<Long, Integer> distance = new HashMap<>();

    public Map<Long, Integer> generate() {
        Queue<CubeModel> queue = new ArrayDeque<>();

        CubeModel solved = new CubeModel();
        long solvedKey = StateEncoder.encode(solved);

        distance.put(solvedKey, 0);
        queue.add(solved);

        int visited = 1;

        while(!queue.isEmpty()){
            CubeModel cur = queue.poll();
            int curDis = distance.get(StateEncoder.encode(cur));

            for (Move move : Move.values()) {
                CubeModel next = MoveApplier.applyMove(cur, move);
                long key = StateEncoder.encode(next);

                if(!distance.containsKey(key)){
                    distance.put(key, curDis + 1);
                    queue.add(next);
                    visited++;
                }
            }
        }
        System.out.println("BFS DONE. Total states = " + visited);
        GodsTableSerializer.save(distance);
        return distance;
    }

    public Map<Long, Integer>getDistance(){
        return distance;
    }

}
