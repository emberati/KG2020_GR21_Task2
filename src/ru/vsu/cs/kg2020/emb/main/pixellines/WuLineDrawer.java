package ru.vsu.cs.kg2020.emb.main.pixellines;

import ru.vsu.cs.kg2020.emb.main.line.LineDrawer;
import ru.vsu.cs.kg2020.emb.main.line.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;
    private Color color;

    public WuLineDrawer(Color color) {
        this.color = color;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        Color color = this.color;
        int dx = x2 - x1;
        int dy = y2 - y1;
        double gradient = 0;
        if (Math.abs(dx) > Math.abs(dy)) {
            if (x2 < x1) {
                x1 += x2;
                x2 = x1 - x2;
                x1 -= x2;
                y1 += y2;
                y2 = y1 - y2;
                y1 -= y2;
            }
            if (dx == 0) {
                for (int i = x1; i < x2; i++) {
                    pd.drawPixel(i, y1, color);
                }
            }
            gradient = (double) dy / dx;
            double intery = y1 + gradient;
            pd.drawPixel(x1, y1, color);
            for (int x = x1; x < x2; ++x) {
                pd.drawPixel(
                        x, (int) intery,
                        new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - fractionalPart(intery) * 255))
                );
                pd.drawPixel(x, (int) intery + 1,
                        new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (fractionalPart(intery) * 255))
                );
                intery += gradient;
            }
        } else {
            if (y2 < y1) {
                x1 += x2;
                x2 = x1 - x2;
                x1 -= x2;
                y1 += y2;
                y2 = y1 - y2;
                y1 -= y2;
            }
            if (dy == 0) {
                for (int i = y1; i < y2; i++) {
                    pd.drawPixel(x1, i, color);
                }
            }
            gradient = (double) dx / dy;
            double interx = x1 + gradient;
            pd.drawPixel(x1, y1, color);
            for (int y = y1; y < y2; ++y) {
                pd.drawPixel(
                        (int) interx, y,
                        new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (255 - fractionalPart(interx) * 255))
                );
                pd.drawPixel((int) interx + 1, y,
                        new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (fractionalPart(interx) * 255))
                );
                interx += gradient;
            }

        }
        pd.drawPixel(x2, y2, color);

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        pd = pixelDrawer;
    }

    private double fractionalPart(double x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }
}