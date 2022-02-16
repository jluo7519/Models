import java.util.*;

public class TopologicalSort {
    // DFS solution
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // c.c.
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        int[] topoOrder = new int[numCourses];
        int[] idx = new int[]{numCourses - 1};
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (topoDFS(graph, i, status, topoOrder, idx)) {
                return new int[]{};
            }
        }
        return topoOrder;
    }
    private boolean topoDFS(Map<Integer, List<Integer>> graph, int cur, int[] status, int[] topoOrder, int[] idx) {
        if (status[cur] == 1) { // visiting ==> cycle
            return true;
        }
        if (status[cur] == 2) { // visited ==> acyclic
            return false;
        }
        status[cur] = 1; // set to visiting
        List<Integer> nexts = graph.get(cur);
        if (nexts != null) {
            for (int next : nexts) {
                if (topoDFS(graph, next, status, topoOrder, idx)) {
                    return true;
                }
            }
        }
        status[cur] = 2; // set to visited
        topoOrder[idx[0]--] = cur;
        return false;
    }
    // BFS solution
    public int[] topoBFS(int numCourses, int[][] prerequisites) {
        // c.c.
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites);
        int[] topoOrder = new int[numCourses];
        int[] incomingEdges = new int[numCourses];
        // count incoming edges for each vertex
        for (int i = 0; i < numCourses; i++) {
            List<Integer> nexts = graph.get(i);
            if (nexts != null) {
                for (int next : nexts) {
                    incomingEdges[next]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        // start from vertices of no incoming edges
        for (int i = 0; i < numCourses; i++) {
            if (incomingEdges[i] == 0) {
                queue.offer(i);
            }
        }

        int numExpanded = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            topoOrder[numExpanded++] = cur;
            List<Integer> nexts = graph.get(cur);
            if (nexts != null) {
                for (int next : nexts) {
                    if (--incomingEdges[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }
        return numExpanded == numCourses ? topoOrder : new int[]{};
    }
    private Map<Integer, List<Integer>> buildGraph(int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] p : prerequisites) {
            int first = p[1]; // take first, i.e. prerequisite
            int next = p[0]; // in order to take next
            List<Integer> nexts = graph.getOrDefault(first, new ArrayList<>());
            nexts.add(next);
            graph.put(first, nexts);
        }
        return graph;
    }

    public static void main(String args[]) {
        TopologicalSort test = new TopologicalSort();
        int[][] pre = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(test.findOrder(4, pre)));
        System.out.println(Arrays.toString(test.topoBFS(4, pre)));
    }
}
