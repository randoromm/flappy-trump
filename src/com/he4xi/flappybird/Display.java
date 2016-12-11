package com.he4xi.flappybird;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to handle rendering.
 * Created by rando on 10.12.16.
 */
public class Display extends JPanel{
    private FlappyBird game;
    private Bird bird;
    private ArrayList<Rectangle> columns;
    private Font scoreFont, pauseFont;
    private Image background;

    public Display(FlappyBird game, Bird bird, ArrayList<Rectangle> columns) {
        this.game = game;
        this.bird = bird;
        this.columns = columns;

        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);

        try {
            background = ImageIO.read(new File("americanflag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);

        g.drawImage(background, 0,0, FlappyBird.WIDTH, FlappyBird.HEIGHT, null);

        bird.render(g);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setFont(scoreFont);
        g.setColor(new Color(255, 0, 29));
        g.drawString("Greatness of America: " + game.getScore(), 10, 20);

        if (game.paused) {
            g.setFont(pauseFont);
            g.setColor(new Color(71, 0, 66));
            g.drawString("Press Space", 80, (FlappyBird.HEIGHT / 2) + 100);
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(new Color(71, 0, 66));
        g.fillRect(column.x, column.y, column.width, column.height);
    }
}
