import java.util.Random;

public class QuickSelect {
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int pivotIdx = partition(nums, start, end);
        int rank = pivotIdx - start + 1;//current rank in subarray
        if (rank == k) {
            return nums[pivotIdx];
        } else if (rank < k) { //search right
            return quickSelect(nums, pivotIdx + 1, end, k - rank);
        } else { //search left
            return quickSelect(nums, start, pivotIdx - 1, k);
        }
    }
    private int partition(int[] nums, int start, int end) {
        Random random = new Random();
        int pivotIdx = start + random.nextInt(end - start + 1);
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        //[start, left]: < pivot
        //[left + 1, i - 1]: >= pivot
        //[i, end]: to be explored
        int left = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) { // if Kth largest, use >
                swap(nums, ++left, i);
            }
        }
        //swap back pivot to where it should be
        swap(nums, ++left, end);
        //left is pivot index
        return left;
    }
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String args[]) {
        QuickSelect test = new QuickSelect();
        System.out.println(test.quickSelect(new int[]{3,2,1,5,6,4}, 0, 5, 2));
        System.out.println(test.quickSelect(new int[]{3,2,3,1,2,4,5,5,6}, 0, 8, 4));
    }
}
