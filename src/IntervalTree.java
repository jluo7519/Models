public class IntervalTree {
    public boolean isOverlapped(ITNode root, Interval target) {
        // O(log n) if balanced ==> overall O(log n) but implementation of self-balancing is complicated
        // c.c.
        ITNode cur = root;
        while (cur != null) {
            if (isOverlap(cur.interval, target)) {
                return true;
            }
            if (cur.left != null && cur.left.max_end >= target.start) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }
    private boolean isOverlap(Interval cur, Interval target) {
        Interval leftInterval = cur.start < target.start ? cur : target;
        Interval rightInterval = leftInterval == cur ? target : cur;
        return leftInterval.end > rightInterval.start;
    }
}

class ITNode {
    Interval interval;
    ITNode left;
    ITNode right;
    int max_end;
    public ITNode(Interval interval) {
        this.interval = interval;
    }
}

class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
