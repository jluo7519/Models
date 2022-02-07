import java.util.*;

public class Dijkstra {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        Queue<Integer> minHeap = new PriorityQueue<>(rows + cols, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                int x1 = i1 / cols;
                int y1 = i1 % cols;
                int x2 = i2 / cols;
                int y2 = i2 % cols;
                return matrix[x1][y1] - matrix[x2][y2];
            }
        });
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(0);
        while (k-- > 0) {
            Integer cur = minHeap.poll();
            if (cur == null) throw new IllegalArgumentException("matrix contains less than k elements");
            int x = cur / cols;
            int y = cur % cols;
            if (k == 0) {
                return matrix[x][y];
            }
            //generate neighbors
            if (x + 1 < rows) { // x + 1, y
                int down = (x + 1) * cols + y;
                if (!visited.contains(down)) {
                    minHeap.offer(down);
                    visited.add(down);
                }
            }
            if (y + 1 < cols) { // x, y + 1
                int right = x * cols + y + 1;
                if (!visited.contains(right)) {
                    minHeap.offer(right);
                    visited.add(right);
                }
            }
        }
        throw new RuntimeException("should never happen");
    }
}
