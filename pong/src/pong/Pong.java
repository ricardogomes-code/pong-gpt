package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    
    private int ballX = 150; // x-coordinate of the ball
    private int ballY = 150; // y-coordinate of the ball
    private int ballXdir = 1; // direction of movement of the ball in the x-axis
    private int ballYdir = 1; // direction of movement of the ball in the y-axis
    private int paddle1Y = 150; // y-coordinate of the left paddle
    private int paddle2Y = 150; // y-coordinate of the right paddle


    public Pong() {
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        // Set the background color to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Set the color of the ball to white
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 20, 20);
        // Set the color of the left paddle to red
        g.setColor(Color.RED);
        g.fillRect(10, paddle1Y, 10, 50);
        // Set the color of the right paddle to blue
        g.setColor(Color.BLUE);
        g.fillRect(getWidth() - 20, paddle2Y, 10, 50);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Pong");
        Pong pong = new Pong();
        frame.add(pong);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            pong.move();
            pong.repaint();
            Thread.sleep(10);
        }
    }

    public void move() {
        // Check for collision with left wall and paddle
        if (ballX + ballXdir < 20) {
            if (ballY >= paddle1Y && ballY <= paddle1Y + 50) {
                // Change direction of ball on collision with paddle
                ballXdir = 1;
            } else {
                // Reset ball position on collision with wall
                ballX = 150;
                ballY = 150;
                ballXdir = 1;
                ballYdir = 1;
            }
        }
        // Check for collision with right wall and paddle
        if (ballX + ballXdir > getWidth() - 40) {
            if (ballY >= paddle2Y && ballY <= paddle2Y + 50) {
                // Change direction of ball on collision with paddle
                ballXdir = -1;
            } else {
                // Reset ball position on collision with wall
                ballX = 150;
                ballY = 150;
                ballXdir = -1;
                ballYdir = 1;
            }
        }
        // Check for collision with top wall
        if (ballY + ballYdir < 0)
            ballYdir = 1;
        // Check for collision with bottom wall
        if (ballY + ballYdir > getHeight() - 20)
            ballYdir = -1;
        // Move the ball
        ballX += ballXdir;
        ballY += ballYdir;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            if (paddle1Y - 10 > 0) {
                // Move the left paddle up
                paddle1Y -= 10;
            }
        }
        if (key == KeyEvent.VK_S) {
            if (paddle1Y + 60 < getHeight()) {
                // Move the left paddle down
                paddle1Y += 10;
            }
        }
        if (key == KeyEvent.VK_UP) {
            if (paddle2Y - 10 > 0) {
                // Move the right paddle up
                paddle2Y -= 10;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            if (paddle2Y + 60 < getHeight()) {
                // Move the right paddle down
                paddle2Y += 10;
            }
        }
    }


    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    
    }
}
