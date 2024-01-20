package org.example.topInterview150;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class _189Test {
    static class Solution {
        public void rotate(int[] nums, int k) {
            int length = nums.length;
            int[] expected = new int[nums.length];

            for (int i = 0; i < length; i++) {
                expected[getNextIndex(i, k, length)] = nums[i];
            }
            System.arraycopy(expected, 0, nums, 0, length);
        }

        private int getNextIndex(int i, int k, int totalLength) {
            int nextI = i + k;
            if (nextI >= totalLength) {
                return nextI % totalLength;
            }
            return nextI;
        }
    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int k, int[] expected) {
        Solution cut = new Solution();
        cut.rotate(nums, k);
        for (int i = 0; i < nums.length; i++) {
            assertThat(nums).containsExactly(expected);
        }
    }

    private static Stream<Object[]> tc() {
        return Stream.of(
            new Object[] {
                new int[] { 1, 2, 3, 4, 5, 6, 7 },
                3,
                new int[] { 5, 6, 7, 1, 2, 3, 4 },
                },
            new Object[] {
                new int[] { -1, -100, 3, 99 },
                2,
                new int[] { 3, 99, -1, -100 },
                }
        );
    }
}