package ru.vsu.cs.kg2020.emb.main;

import ru.vsu.cs.kg2020.emb.main.line.GraphicsPixelDrawer;
import ru.vsu.cs.kg2020.emb.main.line.LineDrawer;
import ru.vsu.cs.kg2020.emb.main.pixellines.BresenhamLineDrawer;
import ru.vsu.cs.kg2020.emb.main.pixellines.DDALineDrawer;
import ru.vsu.cs.kg2020.emb.main.pixellines.WuLineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class MainPanel extends JPanel implements MouseMotionListener {

    /*
     * TODO
     *  - Сделать рисовальщики линий менее зависимыми от PixelLineDrawer (цвет)
     *  - Исправить скачки в рисовальщиках линий
     *  - Исправить отрисовку начальной точки
     */

    private Point2D position = new Point(0, 0);
    private Graphics2D gr;
    private LineDrawer ld;
    private BufferedImage bi;

    private final DDALineDrawer DDA;
    private final BresenhamLineDrawer BRES;
    private final WuLineDrawer WU;

    private int counter;

    {
        DDA = new DDALineDrawer(Color.BLACK);
        BRES = new BresenhamLineDrawer(Color.RED);
        WU = new WuLineDrawer(Color.BLUE);
    }

    public MainPanel(Dimension dimension) {
        setBounds(0, 0, dimension.width, dimension.height);
        addMouseMotionListener(this);
        addKeyListener(new Keys());
        setFocusable(true);
        init();
        setVisible(true);
    }

    public void init() {
        bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_BGR);
        gr = bi.createGraphics();
        GraphicsPixelDrawer pixelDrawer = new GraphicsPixelDrawer(gr);

        DDA.setPixelDrawer(pixelDrawer);
        BRES.setPixelDrawer(pixelDrawer);
        WU.setPixelDrawer(pixelDrawer);

        ld = DDA;
        gr.setColor(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());

        drawAll(ld);
        g.drawImage(bi, 0, 0, null);
        
        g.dispose();
    }

    private void drawAll(LineDrawer ld) {

        drawSnowflake(ld, 150, 16, 200, 300);
        drawSnowflake(ld, 100, 18, 400, 200);

        drawMouseLines(ld);
    }

    private void drawMouseLines(LineDrawer ld) {
        ld.drawLine(50, 50, (int) position.getX(), (int) position.getY());
        //ld.drawLine(0, 0, (int) position.getX(), (int) position.getY());
        //ld.drawLine(getWidth(), 0, (int) position.getX(), (int) position.getY());
        //ld.drawLine(0, getHeight(), (int) position.getX(), (int) position.getY());
        //ld.drawLine(getWidth(), getHeight(), (int) position.getX(), (int) position.getY());
    }

    private void drawSnowflake(LineDrawer ld, int r, int n, int x, int y) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double dx = r * Math.cos(a);
            double dy = r * Math.sin(a);
            ld.drawLine(x, y, x + (int) dx, y + (int) dy);
        }
    }

    private void changeLineDrawer() {
        if (counter < 2)  {
            counter++;
        } else {
            counter = 0;
        }
        System.out.println("counter: " + counter);
        System.out.println("Line drawer changed");

        if (counter == 0) {
            gr.setColor(Color.BLACK);
            ld = DDA;
        } else if (counter == 1) {
            gr.setColor(Color.RED);
            ld = BRES;
        } else {
            gr.setColor(Color.BLUE);
            ld = WU;
        }

        repaint();
    }

    class Keys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            final int KEY = e.getKeyCode();
            if (KEY == KeyEvent.VK_SPACE) {
                changeLineDrawer();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }
}
