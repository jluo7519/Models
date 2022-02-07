import java.util.Arrays;

public class MergeSort {
    public int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        int len = array.length;
        int[] result = new int[len];
        mergeSort(array, 0, len - 1, result);
        return result;
    }
    private void mergeSort(int[] array, int left, int right, int[] helper) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, helper);
        mergeSort(array, mid + 1, right, helper);
        merge(array, left, mid, right, helper);
    }
    private void merge(int[] array, int left, int mid, int right, int[] helper) {
        // copy contents to helper array, and we will merge from the helper array
        for (int i = left; i <= right; i++) {
            helper[i] = array[i];
        }
        int leftIdx = left;
        int rightIdx = mid + 1;
        int idx = left;
        while (leftIdx <= mid && rightIdx <= right) {
            if (helper[leftIdx] < helper[rightIdx]) {
                array[idx++] = helper[leftIdx++];
            } else {
                array[idx++] = helper[rightIdx++];
            }
        }
        // if there are elements in left side, we need to copy them
        while (leftIdx <= mid) {
            array[idx++] = helper[leftIdx++];
        }
        // if there are elements in the right side, we don't need to copy because they are already in their position
    }
    public static void main(String args[]) {
        MergeSort test = new MergeSort();
        int[] array1 = new int[]{3,5,10,2,13,5,1};
        test.mergeSort(array1);
        System.out.println(Arrays.toString(array1));
        int[] array2 = null;
        test.mergeSort(array2);
        System.out.println(Arrays.toString(array2));
        int[] array3 = new int[]{1};
        test.mergeSort(array3);
        System.out.println(Arrays.toString(array3));
        int[] array4 = new int[]{1,1,1,1,1,1,1,1,1,1};
        test.mergeSort(array4);
        System.out.println(Arrays.toString(array4));
        int[] array5 = new int[]{2,10};
        test.mergeSort(array5);
        System.out.println(Arrays.toString(array5));
        int[] array6 = new int[]{1,2,3,4,5,6,7};
        test.mergeSort(array6);
        System.out.println(Arrays.toString(array6));
        int[] array7 = new int[]{7,6,5,4,3,2,1};
        test.mergeSort(array7);
        System.out.println(Arrays.toString(array7));
    }
}
