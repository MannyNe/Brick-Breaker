/*

                       HiLCoE School of Computer Science and Technology
                                     Java Lab Project

                        Project name: Brick Breaker

                        Designed By: Amanuel Negussie
                        Code : HS8103
                        Submitted to: Mr. Elias
                        Project Due Date: Before August 22, 2018


*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener, KeyListener{
    private boolean play = false;
    private int score = 0;
    private int totalNumberOfBricks = 21;
    private Timer time;
    private int delay = 8;
    private int sliderPosition = 310;
    private int ballPositionX = 350;
    private int ballDirectionX = -1;
    private int ballDirectionY = -2;
    private int ballPositionY = 529;
    private MapGenerator map;

    public Gameplay()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
        map = new MapGenerator();
        map.MapGeneratorr(4,8);
    }

    public void paint(Graphics g)
    {
        //The Color for the background
        g.setColor(new Color(40,40,40));
        g.fillRect(1, 1, 692, 592);

        //To draw the bricks
        map.draw((Graphics2D)g); //explicit type to convert Graphics to Graphics2D

        //for the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("sans", Font.BOLD, 25));
        g.drawString(""+ score, 590, 30);

        //borders
        g.setColor(new Color(40,40,40));
        g.fillRect(0, 0 , 3, 592);
        g.fillRect(0, 0 , 692, 3);
        g.fillRect(691, 0 , 3, 592);

        //The Slider
        g.setColor(Color.BLUE);
        g.fillRect(sliderPosition, 550, 100, 8);

        //The ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);


        //if win
        if(map.didWin() == true)
        {
            play = false;
            ballDirectionX = 0;
            ballDirectionY = 0;
            g.setColor(Color.BLACK);
            g.fillRect(1,1,692,592);
            g.setColor(Color.WHITE);
            g.setFont(new Font("sans", Font.BOLD, 30));
            g.drawString("Congratulations, You won! ", 170, 250);

            //if a user wants to restart a game:
            g.setFont(new Font("sans", Font.BOLD, 20));
            g.drawString("Press ENTER to restart the game. ", 190, 290);
        }

        //if fail
        if(ballPositionY > 600)//570
        {
            play = false;
            ballDirectionX = 0;
            ballDirectionY = 0;
            g.setColor(Color.BLACK);
            g.fillRect(1,1,692,592);
            g.setColor(Color.WHITE);
            g.setFont(new Font("sans", Font.BOLD, 30));
            g.drawString("Game Over, Score: "+ score, 190, 250);

            //if a user wants to restart a game:
            g.setFont(new Font("sans", Font.BOLD, 20));
            g.drawString("Press ENTER to restart the game. ", 190, 290);
        }

        g.dispose();
    }

    public void actionPerformed(ActionEvent e)
    {
        time.start();
        if(play)
        {
            if(new Rectangle(ballPositionX, ballPositionY,20,20).intersects(new Rectangle(sliderPosition, 550, 100, 8))){ // used to bounce off the slider
                ballDirectionY = -ballDirectionY; // goes in thee inverse direction
            }

            A:  for(int i = 0; i < map.map.length; i++)// i used map.map because there's a MapGenerator variable and i had to access the array using that.
            {
                for(int j = 0; j < map.map[0].length; j++)
                {
                    if(map.map[i][j] > 0)
                    {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPositionX, ballPositionY, 20, 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect))
                        {
                            map.brickHit(i, j);
                            totalNumberOfBricks--;
                            score += 2;

                            if(ballPositionX + 19 <= brickRect.x || ballPositionX + 1 >= brickRect.x + brickRect.width)
                            {
                                ballDirectionX = -ballDirectionX;
                            }

                            else
                                ballDirectionY = -ballDirectionY;

                            break A;
                        }
                    }
                }
            }

            ballPositionX += ballDirectionX;
            ballPositionY += ballDirectionY;

            if(ballPositionX < 0)
            {
                ballDirectionX = -ballDirectionX;
            }

            if(ballPositionY < 0)
            {
                ballDirectionY = -ballDirectionY;
            }

            if(ballPositionX > 670)
            {
                ballDirectionX = -ballDirectionX;
            }
        }
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        //Not needed, just written because we have to override it inorder to use the keyListener.
    }

    public void keyReleased(KeyEvent k)
    {
        //Not needed, just written because we have to override it inorder to use the KeyListener.
    }

    public void keyPressed(KeyEvent k)
    {
        if(k.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if(sliderPosition >= 600) {
                sliderPosition = 600;
            }else
                moveRight();
        }
        if(k.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if(sliderPosition < 10) {
                sliderPosition = 10;
            }else
                moveLeft();
        }

        if(k.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                play = true;
                ballPositionX = 120;
                ballPositionY = 350;
                ballDirectionX = -1;
                ballDirectionY = -2;
                sliderPosition = 310;
                score = 0;
                totalNumberOfBricks = 21;
                map = new MapGenerator();
                map.MapGeneratorr(4,8);
                repaint();
            }
        }
    }

    public void moveRight()
    {
        play = true;
        sliderPosition += 20; //when +, it goes to the right.
    }

    public void moveLeft()
    {
        play = true;
        sliderPosition -= 20; //when -, it slides to the left.
    }
}

