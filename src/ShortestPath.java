import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ShortestPath extends JPanel {


    static boolean m[][] = {
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

    static int dimensione = 30;
    boolean back[][] = Arrays.stream(m).map(boolean[]::clone).toArray(boolean[][]::new);

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
        f.setSize(dimensione * (m[0].length) + 16, dimensione * (m.length) + 38);
        f.setVisible(true);
        f.setResizable(true);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        path = percorso(teleport);

    }

    public void paintComponent(Graphics g) {

//  print visitati
        g.setColor(Color.GREEN);
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                if (m[i][j])
                    g.fillRect(j * dimensione, i * dimensione, dimensione, dimensione);

// print background
        g.setColor(Color.BLUE);
        for (int i = 0; i < back.length; i++)
            for (int j = 0; j < back[0].length; j++)
                if (back[i][j])
                    g.fillRect(j * dimensione, i * dimensione, dimensione, dimensione);

//        path scelta
        g.setColor(Color.BLACK);
        for (int i = 0; i < path.size(); i++) {
            g.fillRect(path.get(i)[1] * dimensione + dimensione / 4, path.get(i)[0] * dimensione + dimensione / 4, dimensione / 2, dimensione / 2);
        }

//    print inizio e fine
        g.setColor(Color.RED);
        g.fillRect(start[1] * dimensione, start[0] * dimensione, dimensione, dimensione);
        g.fillRect(end[1] * dimensione, end[0] * dimensione, dimensione, dimensione);
    }


    public static ArrayList<int[]> percorso(int[][]... teletrasporto) {

        int index = 0;
        ArrayList<ArrayList<int[]>> visitate = new ArrayList<>();
        visitate.add(new ArrayList<int[]>());
        visitate.get(0).add(start);
        m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

        while (true) {

//              sopra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1 >= 0 && !m[visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1, visitate.get(index).get(visitate.get(index).size() - 1)[1]});
                m[visitate.get(index).get(visitate.get(index).size() - 1)[0] - 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

            }

//          sotto
            if (visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1 < m.length && !m[visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1, visitate.get(index).get(visitate.get(index).size() - 1)[1]});
                m[visitate.get(index).get(visitate.get(index).size() - 1)[0] + 1][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;

            }

//          destra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1 < m[0].length && !m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0], visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1});
                m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] + 1] = true;

            }

//          sinistra
            if (visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1 >= 0 && !m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1]) {
                visitate.add(new ArrayList<>(visitate.get(index)));
                visitate.get(visitate.size() - 1).add(new int[]{visitate.get(index).get(visitate.get(index).size() - 1)[0], visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1});
                m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1] - 1] = true;

            }

//            codice per teletrasporti nella matrice
            for (int i = 0; i < teletrasporto.length; i++) {
                if (teletrasporto[i][0][0] == visitate.get(index).get(visitate.get(index).size() - 1)[0] && teletrasporto[i][0][1] == visitate.get(index).get(visitate.get(index).size() - 1)[1]) {
                    visitate.add(new ArrayList<>(visitate.get(index)));
                    visitate.get(visitate.size() - 1).add(new int[]{teletrasporto[i][1][0], teletrasporto[i][1][1]});
                    m[visitate.get(index).get(visitate.get(index).size() - 1)[0]][visitate.get(index).get(visitate.get(index).size() - 1)[1]] = true;
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
