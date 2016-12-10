package com.he4xi.flappybird;

import javax.swing.*;
import java.awt.*;

/**
 * Class to handle rendering.
 * Created by rando on 10.12.16.
 */
public class Display extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FlappyBird.game.repaint(g);
    }
}
