package org.example.topInterview150;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;

class _80Test {
    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length < 2) {
                return 2;
            }

            int willBeInserted = 0;
            int[] expected = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {

                boolean needToInsert = i < 2 ||
                                       (nums[i - 1] != nums[i]) ||
                                       ((nums[i - 1] == nums[i]) && (nums[i - 2] != nums[i - 1]));

                if (needToInsert) {
                    expected[willBeInserted] = nums[i];
                    willBeInserted++;
                }
            }

            System.arraycopy(expected, 0, nums, 0, willBeInserted);

            return willBeInserted;
        }

        // 다른 사람 풀이
        public int removeDuplicates1(int[] nums) {
            if (nums.length < 2) {
                return nums.length;
            }

            int willBeInserted = 2;
            for (int i = willBeInserted; i < nums.length; i++) {

                boolean needToInsert = nums[willBeInserted - 2] != nums[i];

                if (needToInsert) {
                    nums[willBeInserted] = nums[i];
                    willBeInserted++;
                }
            }

            System.arraycopy(nums, 0, nums, 0, willBeInserted);

            return willBeInserted;
        }

    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int k) {
        Solution cut = new Solution();
        int x = cut.removeDuplicates(nums);
        assertThat(x).isEqualTo(k);
    }

    private static Stream<Object[]> tc() {
        return Stream.of(
            new Object[] {
                new int[] { 1, 1, 1, 2, 2, 3 },
                5,
                },
            new Object[] {
                new int[] { 0, 0, 1, 1, 1, 1, 2, 3, 3 },
                7,
                }
        );
    }
}