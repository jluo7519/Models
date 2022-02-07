public class BinarySearch {
    private int BinarySearch(int[] nums, int target) {
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
    }
}
