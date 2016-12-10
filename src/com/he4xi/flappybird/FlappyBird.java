package com.he4xi.flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main game class.
 * Created by rando on 10.12.16.
 */
public class FlappyBird implements ActionListener {

    public static FlappyBird game;
    public static final int WIDTH = 500, HEIGHT = 600;

    public int ticks, yMotion;
    public Display display;
    public Random rndm;
    public Rectangle bird;
    public ArrayList<Rectangle> columns;

    public FlappyBird() {
        JFrame frame = new JFrame();
        Timer timer = new Timer(20, this);

        display = new Display();
        rndm = new Random();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Flappy Bird");
        frame.setLocationRelativeTo(null);
        frame.add(display);
        frame.setVisible(true);

        bird = new Rectangle(100, (HEIGHT / 2) - 10, 20, 20);
        columns = new ArrayList<>();

        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + rndm.nextInt(250);

        if (start)
        {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 110, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 110, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }


    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(new Color(244, 0, 126));
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    /**
     * Method to deal with rendering.
     * @param g Graphics component (JPanel).
     */
    public void repaint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.pink);
        g.fillRect(0, HEIGHT - 110, WIDTH, 110);

        g.setColor(new Color(203, 66, 244));
        g.fillRect(0, HEIGHT - 110, WIDTH, 20);

        g.setColor(new Color(146, 244, 66));
        g.fillRect(bird.x,  bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }
    }

    /**
     * Method to deal with updating.
     * @param actionEvent Action event for action listener.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ticks++;
        if (ticks > 666) ticks = 0;
        int speed = 4;

        for (int i = 0; i < columns.size(); i++) {
           Rectangle column = columns.get(i);
           column.x -= speed;
        }

        if (ticks % 2 == 0 && yMotion < 15) {
            yMotion += 2;
        }

        for (int i = 0; i < columns.size(); i++) {
            Rectangle column = columns.get(i);

            if (column.x + column.width < 0) {
                columns.remove(i);
            }

            if (column.y == 0) {
                addColumn(false);
            }
        }

        bird.y += yMotion;

        display.repaint();
    }

    public static void main(String[] args) {
        game = new FlappyBird();
    }
}
