import javax.swing.*;
import java.awt.*;

public class BoardUI extends JPanel {

    static int dimension = 30;
    private final Maze maze;

    public BoardUI(Maze maze){
        this.maze = maze;
        setSize(dimension * (maze.board[0].length) + 16, dimension * (maze.board.length) + 38);
    }

    public void paintComponent(Graphics g) {

        g.setColor(Color.BLUE);
        for (int i = 0; i < maze.board.length; i++)
            for (int j = 0; j < maze.board[0].length; j++)
                if (maze.board[i][j])
                    g.fillRect(j * dimension, i * dimension, dimension, dimension);

    }

}
