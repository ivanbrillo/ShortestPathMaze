import javax.swing.*;
import java.awt.*;

public class BoardUI extends JPanel {

    static int dimension = 30;
    private final Maze maze;

    public BoardUI(Maze maze) {
        this.maze = maze;
        setSize(dimension * (maze.board[0].length) + 16, dimension * (maze.board.length) + 38);
    }

    public void paintComponent(Graphics g) {

        // print background
        g.setColor(Color.BLUE);
        for (int i = 0; i < maze.board.length; i++)
            for (int j = 0; j < maze.board[0].length; j++)
                if (maze.board[i][j])
                    g.fillRect(j * dimension, i * dimension, dimension, dimension);

        // print visited
        g.setColor(Color.GREEN);
        for (MazeBlock p : maze.visited)
            g.fillRect(p.currentPoint.x * dimension, p.currentPoint.y * dimension, dimension, dimension);

        // print the best path
        g.setColor(Color.BLACK);
        MazeBlock last = maze.visited.get(maze.visited.size() - 1);
        while (last.parent != null) {
            last = last.parent;
            g.fillRect(last.currentPoint.x * dimension + dimension / 4, last.currentPoint.y * dimension + dimension / 4, dimension / 2, dimension / 2);
        }

        // print start and end
        g.setColor(Color.RED);
        g.fillRect(maze.start.x * dimension, maze.start.y * dimension, dimension, dimension);
        g.fillRect(maze.end.x * dimension, maze.end.y * dimension, dimension, dimension);

    }

}
