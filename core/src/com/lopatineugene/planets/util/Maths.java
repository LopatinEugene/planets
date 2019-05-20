package com.lopatineugene.planets.util;

import com.badlogic.gdx.math.MathUtils;

public class Maths {
    public static float dist(float x1, float y1, float x2, float y2) {
        float x = x2-x1;
        float y = y2-y1;
        return (float) Math.sqrt(x * x + y * y);
    }
}
