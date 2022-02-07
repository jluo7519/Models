public class BinarySearch {
    public int BinarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) { // search right
                left = mid + 1;
            } else { //search left
                right = mid - 1;
            }
        }
        // after search, right + 1 = left
        // right < target < left
        return left; // if look for largest smaller than target
        // return right; // if look for smallest larger than target
        // don't forget to check boundaries if both left and right could be our candidates
    }
    public int firstOccur(int[] array, int target) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }
        int left = 0;
        int right = array.length - 1;
        // we need to use left + 1 < right here to make sure no infinite loop
        // think about case when left == right - 1
        // then mid = left + (right - left) / 2 = left
        // it will become an infinite loop
        while (left + 1 < right) { // need two elements
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) { // go left
                right = mid;
            } else { //go right
                left = mid;
            }
        }
        // post process
        if (array[left] == target) {
            return left;
        } else if (array[right] == target){
            return right;
        }
        throw new RuntimeException("target not found");
    }

    public static void main(String args[]) {
        BinarySearch test = new BinarySearch();
        test.firstOccur(new int[]{1}, 1);
    }
}
