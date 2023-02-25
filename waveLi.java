import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class waveLi {

    public static void main(String[] args) {
        int[][] labirint = getLabirint(10, 15, 40);
        printArr2D(labirint);
        System.out.println();
        int[] start = {6, 9};
        wave(labirint, start);
        printArr2D(labirint);
        int[] exit = {0, 5};
        String way = getExitWay(labirint, exit);
        System.out.println();
        System.out.println(way);
    }

    public static int[][] getLabirint(int x, int y, int wall) {
        int[][] lab = new int[x][y];
        int[][] wallArr = getWall(wall);
        for (int[] item : wallArr) {
            lab[item[0]][item[1]] = -1;
        }
        return lab;
    }

    private static int[][] getWall(int num) {
        int[][] wallArr = new int[num][2];
        Random rand = new Random();
        for (int i = 0; i < wallArr.length; i++) {
            wallArr[i][0] = rand.nextInt(10);
            wallArr[i][1] = rand.nextInt(15);
        }
        return wallArr;
    }

    public static void printArr2D(int[][] arr) {
        for (int[] item : arr) {
            for (int temp : item) {
                System.out.printf("%3d ", temp);
            }
            System.out.println();
        }
    }

    public static void wave(int[][] matrix, int[] start) {
        int count = 1;
        Queue<int[]> way = new LinkedList<>();
        way.add(start);
        matrix[start[0]][start[1]] = count;
        while (!way.isEmpty()) {
            int[]step = way.remove();
            
            count = matrix[step[0]][step[1]];
            if (step[0] != 0) {
                if (matrix[step[0] - 1][step[1]] == 0) {
                    int[] temp = {step[0] - 1, step[1]};
                    way.add(temp);
                    matrix[temp[0]][temp[1]] = count + 1;
                }
            }
            if (step[1] != matrix[0].length - 1) {
                if (matrix[step[0]][step[1] + 1] == 0) {
                    int[] temp = {step[0], step[1] + 1};
                    way.add(temp);
                    matrix[temp[0]][temp[1]] = count + 1;
                }
            }
            if (step[0] != matrix.length - 1) {
                if (matrix[step[0] + 1][step[1]] == 0) {
                    int[] temp = {step[0] + 1, step[1]};
                    way.add(temp);
                    matrix[temp[0]][temp[1]] = count + 1;
                }
            }
            if (step[1] != 0) {
                if (matrix[step[0]][step[1] - 1] == 0) {
                    int[] temp = {step[0], step[1] - 1};
                    way.add(temp);
                    matrix[temp[0]][temp[1]] = count + 1;
                }
            }
        }
    }

    public static String getExitWay(int[][] matrix, int[] exit) {
        String exitWay = new String();
        int count = matrix[exit[0]][exit[1]];
        if (count < 1) {
            System.out.println("Выхода нет!! Тефтелька умрет от голода((");
            return exitWay;
        } else {
            exitWay += Arrays.toString(exit);
            int[] step = new int[2];
                step[0] = exit[0];
                step[1] = exit[1];
            while (count > 1) {
                if (step[0] != 0 && matrix[step[0] - 1][step[1]] == count - 1) {
                    step[0] -= 1;
                    exitWay += Arrays.toString(step);
                    count --;
                } else if (step[1] != matrix[0].length - 1 && matrix[step[0]][step[1] + 1] == count - 1) {
                    step[1] += 1;
                    exitWay += Arrays.toString(step);
                    count--;
                } else if (step[0] != matrix.length - 1 && matrix[step[0] + 1][step[1]] == count - 1) {
                    step[0] += 1;
                    exitWay += Arrays.toString(step);
                    count--;
                }  else if (step[1] != 0 && matrix[step[0]][step[1] - 1] == count - 1) {
                    step[1] -= 1;
                    exitWay += Arrays.toString(step);
                    count--;
                }
            }
        }
        return exitWay;
    }
}
