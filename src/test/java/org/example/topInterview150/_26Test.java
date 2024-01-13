package org.example.topInterview150;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class _26Test {

    class Solution {
        public int removeDuplicates(int[] nums) {

            boolean[] map = new boolean[201];
            int[] expecting = new int[nums.length];
            int lastIndex = 0;

            for (int n : nums) {
                if (isNew(n, map)) {
                    markItIsNotNew(n, map);
                    expecting[lastIndex] = n;
                    lastIndex++;
                }
            }

            if (lastIndex >= 0) {
                System.arraycopy(expecting, 0, nums, 0, lastIndex);
            }

            return lastIndex;
        }


        private boolean isNew(int num, boolean[] map) {
            return !map[convertToIndex(num)];
        }

        private void markItIsNotNew(int num, boolean[] map) {
            map[convertToIndex(num)] = true;
        }

        private int convertToIndex(int number) {
            return number + 100;
        }
    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int k) {
        Solution cut = new Solution();
        var x = cut.removeDuplicates(nums);
        assertThat(x).isEqualTo(k);
    }

    private static Stream<Object[]> tc() {
        return Stream.of(
            new Object[] {
                new int[] { 1, 1, 2 },
                2,
                },
            new Object[] {
                new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 },
                5,
                }
        );
    }
}