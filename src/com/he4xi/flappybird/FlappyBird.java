package com.he4xi.flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Main game class.
 * Created by rando on 10.12.16.
 */
public class FlappyBird implements ActionListener, KeyListener {

    public static final int WIDTH = 600, HEIGHT = 600;
    public static final int P_WIDTH = 100;
    public static final int FPS = 60;

    private JFrame frame;
    private JPanel display;
    private Bird bird;
    private ArrayList<Rectangle> columns;
    private Timer timer;
    private AudioPlayer bgMusic;
    private int scroll, time;

    boolean paused;

    public FlappyBird() {
        frame = new JFrame("Lehviv Trump v1");
        bird = new Bird();
        columns = new ArrayList<>();
        display = new Display(this, bird, columns);
        bgMusic = new AudioPlayer("airhorn.mp3");

        frame.add(display);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);
        frame.setVisible(true);

        paused = true;

        timer = new Timer(1000 / FPS, this);
        timer.start();
    }

    /**
     * Method to deal with updating. Set to 60 times a second.
     * @param actionEvent Action event for action listener.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        display.repaint();

        if (!paused) {
            bird.updatePhysics();
            if (scroll % 120 == 0) {
                Rectangle c1 = new Rectangle(WIDTH, 0, P_WIDTH, (int) ((Math.random() * HEIGHT) / 5f + (0.21f) * HEIGHT));
                int h2 = (int) ((Math.random() * HEIGHT) / 5f + (0.21f) * HEIGHT);
                Rectangle c2 = new Rectangle(WIDTH, HEIGHT - h2, P_WIDTH, h2);
                columns.add(c1);
                columns.add(c2);
            }

            boolean game = true;

            ArrayList<Rectangle> toRemove = new ArrayList<>();
            for (Rectangle column : columns) {
                column.x -= 3;
                if (column.x + column.width < 0) {
                    toRemove.add(column);
                }

                if ((column.contains(bird.x - 20, bird.y - 25)) || (column.contains(bird.x + 20, bird.y + 20))) {
                    game = false;
                }
            }

            columns.removeAll(toRemove);
            scroll++;
            time++;

            if (bird.y < 0 || bird.y + 20 > HEIGHT) {
                game = false;
            }

            if (!game) {
                JOptionPane.showMessageDialog(frame, "You failed to make America great again!\n" +
                        "Your score was: " + time + ".");
                paused = true;
                columns.clear();
                bird.reset();
                time = 0;
                scroll = 0;
            }
        } else {

        }
    }

    public int getScore() {
        return time;
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_SPACE) {
            bird.jump();
            if (paused) {
                paused = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyReleased(KeyEvent k) {

    }
    public static void main(String[] args) {
        new FlappyBird();
    }
}
