import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public void quickSort(int[] nums) {
        //if (nums == null) throw new IllegalArgumentException();
        if (nums == null || nums.length <= 1) return;

        quickSort(nums, 0, nums.length - 1);
    }
    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partition(nums, start, end);
        quickSort(nums, start, pivot - 1);
        quickSort(nums, pivot + 1, end);
    }
    private int partition(int[] nums, int start, int end) {
        Random random = new Random();
        int pivotIdx = start + random.nextInt(end - start + 1);
        int pivot = nums[pivotIdx];
        swap(nums, pivotIdx, end);
        int left = start - 1; //[start, left] < pivot; [left + 1, i] >= pivot
        for (int i = start; i < end; i++) { //不能动end他是我们的pivot
            if (nums[i] < pivot) {
                swap(nums, ++left, i);
            }
        }
        swap(nums, ++left, end);
        return left;
    }
    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String args[]) {
        QuickSort test = new QuickSort();
        int[] array1 = new int[]{3,5,10,2,13,5,1};
        test.quickSort(array1);
        System.out.println(Arrays.toString(array1));
        int[] array2 = null;
        test.quickSort(array2);
        System.out.println(Arrays.toString(array2));
        int[] array3 = new int[]{1};
        test.quickSort(array3);
        System.out.println(Arrays.toString(array3));
        int[] array4 = new int[]{1,1,1,1,1,1,1,1,1,1};
        test.quickSort(array4);
        System.out.println(Arrays.toString(array4));
        int[] array5 = new int[]{2,10};
        test.quickSort(array5);
        System.out.println(Arrays.toString(array5));
        int[] array6 = new int[]{1,2,3,4,5,6,7};
        test.quickSort(array6);
        System.out.println(Arrays.toString(array6));
        int[] array7 = new int[]{7,6,5,4,3,2,1};
        test.quickSort(array7);
        System.out.println(Arrays.toString(array7));
    }
}
