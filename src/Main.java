import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Maze maze = new Maze(args);
        BoardUI board = new BoardUI(maze);
        frame.setSize(board.getSize());
        frame.add(board);

        frame.setVisible(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

    }


}
