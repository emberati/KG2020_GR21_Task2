package ru.vsu.cs.kg2020.emb.main.pixellines;

import ru.vsu.cs.kg2020.emb.main.line.LineDrawer;
import ru.vsu.cs.kg2020.emb.main.line.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pd;
    private Color color;

    /* TODO: Принудительный сбор мусора Java */

    public BresenhamLineDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;

        int err = dx - dy;
        int e2;

        while (true) {
            pd.drawPixel(x1, y1, color);

            if (x1 == x2 && y1 == y2)
                break;

            e2 = 2 * err;
            if (e2 > -dy) {
                err = err - dy;
                x1 += sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y1 += sy;
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setPixelDrawer(PixelDrawer ld) {
        this.pd = ld;
    }
}
