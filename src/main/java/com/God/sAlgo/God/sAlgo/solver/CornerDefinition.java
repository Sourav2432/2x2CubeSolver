package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.Color;

import java.util.List;

public class CornerDefinition {

    public static final List<Color[]> SOLVED_CORNER = List.of(
        new Color[]{Color.W, Color.R, Color.G},
        new Color[]{Color.W, Color.G, Color.O},
        new Color[]{Color.W, Color.O, Color.B},
        new Color[]{Color.W, Color.B, Color.R},
        new Color[]{Color.Y, Color.G, Color.R},
        new Color[]{Color.Y, Color.O, Color.G},
        new Color[]{Color.Y, Color.B, Color.O},
        new Color[]{Color.Y, Color.R, Color.B}
    );
}
