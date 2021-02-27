import javax.swing.*;

public class Main {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 600;

    public static void main (String [] args)
    {
        JFrame jf = new JFrame();
        Gameplay g1 = new Gameplay();
        jf.setBounds(10, 10, WIDTH, HEIGHT);
        jf.setTitle("Brick Breaker");
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);// sets the window to the center of the screen on the computer.
        jf.setResizable(false); //maybe if a person tries to adjust the window...
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.add(g1);
    }
}
