package ru.vsu.cs.kg2020.emb.main.line;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer {

    private final Graphics g;

    public GraphicsPixelDrawer(Graphics g) {
        this.g = g;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        g.setColor(c);
        g.fillRect(x, y, 1, 1);
    }
}
