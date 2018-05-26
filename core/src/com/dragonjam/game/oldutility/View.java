package com.dragonjam.game.oldutility;

/**
 * An enum representing various constants used for viewport and camera
 * manipulation. Width is constant at 9 world units, but other values should
 * be overwritten by the resize method in Screen.
 *
 * @author Cinders-P
 **/

public enum View {
    HEIGHT (0),
    WIDTH (9),
    RATIO (0),
    DENSITY (72);

    private float val;

    View(float n) {
        val = n;
    }

    public float val() {
        return val;
    }

    public void setVal(float n) {
        val = n;
        HEIGHT.val = WIDTH.val * RATIO.val;
    }
}
