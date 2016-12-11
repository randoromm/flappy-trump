package com.he4xi.flappybird;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class for the flappy bird/trump.
 * Created by rando on 11.12.16.
 */
public class Bird {
    public float x, y, xVel, yVel;
    public static final int RAD = 25;
    private Image img;

    public Bird() {
        x = 100;
        y = FlappyBird.HEIGHT / 2;

        try {
            img = ImageIO.read(new File("trump2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePhysics() {
        x += xVel;
        y += yVel;
        yVel += 0.5f;

        if(yVel < -15) yVel *= 0.7;
    }

    public void jump() {
        yVel -= 14;
    }

    public void render(Graphics g) {
        g.setColor(new Color(146, 244, 66));
//        g.fillRect((int)x,  (int)y, 20, 20);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }

    public void reset() {
        x = 100;
        y = FlappyBird.HEIGHT / 2;
        xVel = yVel = 0;
    }
}
