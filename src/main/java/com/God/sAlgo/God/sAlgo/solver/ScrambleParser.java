package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.Move;

import java.util.ArrayList;
import java.util.List;

public class ScrambleParser {

    public static List<Move> parse(String s) {
        List<Move> moves = new ArrayList<>();
        String[] tokens = s.trim().split("\\s+");

        for (String t : tokens) {
            switch (t) {
                case "U" -> moves.add(Move.U);
                case "U'" -> moves.add(Move.U_PRIME);

                case "R" -> moves.add(Move.R);
                case "R'" -> moves.add(Move.R_PRIME);

                case "F" -> moves.add(Move.F);
                case "F'" -> moves.add(Move.F_PRIME);

                default -> throw new IllegalArgumentException("Invalid move: " + t);
            }
        }
        return moves;
    }

}
