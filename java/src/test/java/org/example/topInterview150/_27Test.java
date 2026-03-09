package org.example.topInterview150;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class _27Test {

    class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums.length == 0) {
                return 0;
            }

            int[] expected = new int[nums.length];
            int total = 0;

            for (int currentNumber : nums) {
                if (currentNumber != val) {
                    expected[total] = currentNumber;
                    total++;
                }
            }
            for (int i = 0; i < total; i++) {
                nums[i] = expected[i];
            }

            return total;
        }

        // 다른 사람 풀이
        public int sample1(int[] nums, int val) {
            int numVal = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == val) {
                    numVal++;
                } else {
                    nums[i - numVal] = nums[i];
                }
            }
            return nums.length - numVal;
        }
    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int val, int[] expected, int k) {
        Solution cut = new Solution();
        var x = cut.removeElement(nums, val);
        assertThat(x).isEqualTo(k);
    }

    private static Stream<Object[]> tc() {
        return Stream.of(
            new Object[] {
                new int[] { 3, 2, 2, 3 },
                3,
                new int[] { 2, 2 },
                2
            },
            new Object[] {
                new int[] { 0, 1, 2, 2, 3, 0, 4, 2 },
                2,
                new int[] { 0, 1, 4, 0, 3 }
                , 5
            },

            new Object[] {
                new int[] {},
                2,
                new int[] {}
                , 0
            },

            new Object[] {
                new int[] { 2 },
                3,
                new int[] { 2 }
                , 1
            }
        );
    }
}