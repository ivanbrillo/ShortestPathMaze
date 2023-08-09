import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShortestPath extends JPanel {


    static boolean[][] maze = {
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

    static int dimension = 30;
    boolean[][] back = Arrays.stream(maze).map(boolean[]::clone).toArray(boolean[][]::new);

    static int[] end = new int[]{3, 3};
    static int[] start = new int[]{9, 16};
    static int[][][] teleport = {
        {
                {9, 0},        
                {9, 18}        
        },
        {
                {9, 18},       
                {9, 0}          
        },
};

    static ArrayList<int[]> path;

    public static void main(String[] args) {

        JFrame f = new JFrame();
        ShortestPath b = new ShortestPath();

        f.add(b);
        f.setSize(dimension * (maze[0].length) + 16, dimension * (maze.length) + 38);
        f.setVisible(true);
        f.setResizable(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        path = percorso(teleport);

    }

    public void paintComponent(Graphics g) {

//  print visitati
        g.setColor(Color.GREEN);
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++)
                if (maze[i][j])
                    g.fillRect(j * dimension, i * dimension, dimension, dimension);

// print background
        g.setColor(Color.BLUE);
        for (int i = 0; i < back.length; i++)
            for (int j = 0; j < back[0].length; j++)
                if (back[i][j])
                    g.fillRect(j * dimension, i * dimension, dimension, dimension);

//        path scelta
        g.setColor(Color.BLACK);
        for (int i = 0; i < path.size(); i++) {
            g.fillRect(path.get(i)[1] * dimension + dimension / 4, path.get(i)[0] * dimension + dimension / 4, dimension / 2, dimension / 2);
        }

//    print inizio e fine
        g.setColor(Color.RED);
        g.fillRect(start[1] * dimension, start[0] * dimension, dimension, dimension);
        g.fillRect(end[1] * dimension, end[0] * dimension, dimension, dimension);
    }


    public static ArrayList<int[]> percorso(int[][]... teletrasporto) {

        int index = 0;
        ArrayList<ArrayList<int[]>> visitate = new ArrayList<>();
        visitate.add(new ArrayList<int[]>());
        visitate.get(0).add(start);
        maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

        while (true) {

//              sopra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1 >= 0 && !maze[visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1, visitate.get(index).get(visitate.get(index).size() - 1)[1]});
                maze[visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

            }

//          sotto
            if (visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1 < maze.length && !maze[visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1, visitate.get(index).get(visitate.get(index).size() - 1)[1]});
                maze[visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

            }

//          destra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1 < maze[0].length && !maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0], visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1});
                maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1] = true;

            }

//          sinistra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1 >= 0 && !maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0], visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1});
                maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1] = true;

            }

//            codice per teletrasporti nella matrice
            for (int i = 0; i < teletrasporto.length; i++) {
                if (teletrasporto[i][0][0] == visitate.get(index).get(visitate.get(index).size() - 1)[0] && teletrasporto[i][0][1] == visitate.get(index).get(visitate.get(index).size() - 1)[1]) {
                    visitate.add(new ArrayList<>(visitate.get(index)));
                    visitate.get(visitate.size() - 1).add(new int[]{teletrasporto[i][1][0], teletrasporto[i][1][1]});
                    maze[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;
                }
            }

            index++;

// guarda se hai trovato la fine

            for (int i = index; i < visitate.size(); i++)
                if (visitate.get(i).get(visitate.get(index).size() - 1)[0] == end[0] && visitate.get(i).get(visitate.get(index).size() - 1)[1] == end[1]) {

                    return visitate.get(i);
                }
        }
    }

}
