package ru.vsu.cs.kg2020.emb.main.pixellines;

import ru.vsu.cs.kg2020.emb.main.line.LineDrawer;
import ru.vsu.cs.kg2020.emb.main.line.PixelDrawer;

import java.awt.*;


public class DDALineDrawer implements LineDrawer {
    private PixelDrawer pd;
    private Color color;

    public DDALineDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        final double K;

        if (Math.abs(dx) > Math.abs(dy)) {
            K = dy / dx;
            if (x1 > x2) {
                int tmp = x1;
                x1 = x2;
                x2 = tmp;
                y1 = y2;
            }
            for (int j = x1; j <= x2; j++) {
                double i = K * (j - x1) + y1;
                pd.drawPixel(j, (int) i, color);
            }
        } else {
            K = dx / dy;
            if (y1 > y2) {
                int tmp = y1;
                x1 = x2;
                y1 = y2;
                y2 = tmp;
            }
            for (int i = y1; i <= y2; i++) {
                double j = K * (i - y1) + x1;
                pd.drawPixel((int) j, i, color);
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
    public void setPixelDrawer(PixelDrawer pd) {
        this.pd = pd;
    }
}
