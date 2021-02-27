import java.awt.*;

public class MapGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public void MapGeneratorr(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int r = (int)(Math.random() * 3 + 1); // so that it will generate 4 types of blocks based on strength.
                map[i][j] = r; // this will detect that the ball didn't intersect with the brick.
            }
        }

        brickWidth = 540 / col;
        brickHeight = 150 / row;

    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    if(map[i][j] == 1)
                    {
                        g.setColor(new Color(200,200,200));
                    }
                    if(map[i][j] == 2)
                    {
                        g.setColor(new Color(150,150,150));
                    }
                    if(map[i][j] == 3)
                    {
                        g.setColor(new Color(100,100,100));
                    }
                    if(map[i][j] == 4)
                    {
                        g.setColor(new Color(50,50,50));
                    }

                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    //Below this, they're used to add the bricks.
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {

        map[row][col] = value;
    }

    public void brickHit(int row, int col)
    {
        map[row][col] -= 1;
        if(map[row][col] < 0)
        {
            map[row][col] = 0;
        }
    }

    public boolean didWin() {
        //play = false;
        boolean didWin = false;
        int bricksRemaining = 0;

        for (int row = 0; row < map.length; row++)
        {
            for (int col = 0; col < map[row].length; col++)
            {
                bricksRemaining += map[row][col];
            }
        }

        if(bricksRemaining == 0){
            didWin = true;
        }

        return didWin;

    }

    public int[][] getMapArray() {
        return map;
    }

}
