import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Maze {

    final boolean[][] board = {
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, true, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {false, false, false, true, false, true, false, false, false, false, false, false, false, true, false, true, false, false, false},
            {true, true, true, true, false, true, false, true, true, true, true, true, false, true, false, true, true, true, true},
            {true, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, true},
            {true, false, true, true, false, true, true, true, false, true, false, true, true, true, false, true, true, false, true},
            {true, false, false, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, true},
            {true, true, false, true, false, true, false, true, true, true, true, true, false, true, false, true, false, true, true},
            {true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, false, false, false, true},
            {true, false, true, true, true, true, true, true, false, true, false, true, true, true, true, true, true, false, true},
            {true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true},
            {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}
    };
    private final ArrayList<Point> teleport = new ArrayList<>(Arrays.asList(new Point(0, 9), new Point(18, 9)));
    private final int[][] translations = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };
    public Point end;
    public Point start;
    public ArrayList<MazeBlock> visited = new ArrayList<>();


    public Maze(String[] args) {

        if (args.length != 4)
            throw new RuntimeException();

        try {
            start = new Point(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            end = new Point(Integer.parseInt(args[2]), Integer.parseInt(args[3]));

            Rectangle rect = new Rectangle(board[0].length, board.length);

            if (!(rect.contains(start) && rect.contains(end)) || isOccupied(board, start) || isOccupied(board, end))
                throw new RuntimeException();

        } catch (Exception e) {
            throw new RuntimeException();
        }

        solveMaze();

    }

    private boolean[][] getCopy() {
        return Arrays.stream(board).map(boolean[]::clone).toArray(boolean[][]::new);
    }

    private boolean isOccupied(boolean[][] maze, Point point) {

        if (point.y < 0 || point.x < 0 || point.y >= maze.length || point.x >= maze[0].length)
            return true;

        return maze[point.y][point.x];
    }

    private boolean visitNeighbour(boolean[][] maze, ArrayList<MazeBlock> frontier) {

        for (int[] translation : translations) {

            Point p = new Point(frontier.get(0).currentPoint);
            p.translate(translation[0], translation[1]);

            if (!isOccupied(maze, p)) {
                MazeBlock mazeBlock = new MazeBlock(p, frontier.get(0));
                frontier.add(mazeBlock);
                visited.add(mazeBlock);
                maze[p.y][p.x] = true;

                if (p.equals(end))
                    return true;
            }
        }
        return false;
    }

    private boolean checkTeleport(boolean[][] maze, ArrayList<MazeBlock> frontier) {

        MazeBlock block = frontier.get(0);

        if (teleport.contains(block.currentPoint)) {
            MazeBlock teleportedBlock = new MazeBlock(teleport.get((teleport.indexOf(block.currentPoint) + 1) % 2), block);
            visited.add(teleportedBlock);
            maze[teleportedBlock.currentPoint.y][teleportedBlock.currentPoint.x] = true;
            frontier.add(teleportedBlock);
        }

        return frontier.get(frontier.size() - 1).currentPoint.equals(end);
    }

    public void solveMaze() {

        if (start.equals(end))
            return;

        boolean[][] maze_copy = getCopy();
        ArrayList<MazeBlock> frontier = new ArrayList<>();
        frontier.add(new MazeBlock(start, null));

        while (frontier.size() > 0) {

            if (visitNeighbour(maze_copy, frontier))
                return;

            if (checkTeleport(maze_copy, frontier))
                return;

            frontier.remove(0);

        }
    }
}

class MazeBlock {

    Point currentPoint;
    MazeBlock parent;

    public MazeBlock(Point current, MazeBlock parent) {
        currentPoint = current;
        this.parent = parent;

    }
}
