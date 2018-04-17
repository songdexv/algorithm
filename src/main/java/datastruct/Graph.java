package datastruct;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by songdexv on 2018/4/4.
 */
public class Graph {
    private static boolean[] visited;
    //节点
    private static String[] nodes = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    //邻接矩阵
    private static int[][] edges = {
            {0, 1, 0, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0}
    };

    /**
     * 深度优先遍历
     */
    public static void dfs() {
        int number = nodes.length;
        visited = new boolean[number];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < number; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                System.out.print(nodes[i] + " ");
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int k = stack.pop();
                for (int j = 0; j < number; j++) {
                    //节点相连
                    if (visited[j] == false && edges[k][j] == 1) {
                        visited[j] = true;
                        System.out.print(nodes[j] + " ");
                        stack.push(j);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public static void bfs() {
        int number = nodes.length;
        visited = new boolean[number];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < number; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                System.out.print(nodes[i] + " ");
                queue.add(i);
            }
            while (!queue.isEmpty()) {
                int k = queue.poll();
                for (int j = 0; j < number; j++) {
                    if (visited[j] == false && edges[k][j] == 1) {
                        visited[j] = true;
                        System.out.print(nodes[j] + " ");
                        queue.add(j);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        dfs();
        System.out.println();
        bfs();
    }
}
