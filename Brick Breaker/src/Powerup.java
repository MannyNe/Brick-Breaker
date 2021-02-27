import java.awt.*;

public class Powerup{
    private int x, y, dy, type, width, height; //Getter and setter methods needed!
    private boolean isOnScreen;
    private Color color;
    public static final int WIDE_PADDLE = 4;
    public static final int  FAST_BALL = 5;
    public static final Color WIDE_COLOR = Color.GREEN;
    public static final Color FAST_COLOR = Color.RED;

    public Powerup(int xStart, int yStart, int theType, int theWidth, int theHeight)
    {
        x = xStart;
        y = yStart;
        type = theType;
        width = theWidth;
        height = theHeight;

        if(type < 4){                    /* the types are there to show the powerup type */
            type = 4;
        }

        if(type > 5){
            type = 5;
        }

        if(type == WIDE_PADDLE){
            color = WIDE_COLOR;
        }

        if(type == FAST_BALL){
            color = FAST_COLOR;
        }

        dy = (int)(Math.random() * 6 + 1);


    }

    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void update()
    {
        y += dy;
        if(y > Main.HEIGHT){// if powerup falls out of the screen, it dissappears.
            isOnScreen = false;
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public int getDy(){
        return dy;
    }

    public int getType(){
        return type;
    }

    public void setIsOnScreen(boolean isOnScreen){
        this.isOnScreen = isOnScreen;
    }

    public boolean getIsOnScreen(){
        return isOnScreen;
    }

    public Rectangle getRect(){ // checks if the ball or powerup touches the paddle.
        return new Rectangle(x, y, width, height);
    }
}
