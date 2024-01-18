package org.example.topInterview150;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class _169Test {
    static class Solution {
        public int majorityElement(int[] nums) {

            Map<Integer, Integer> counter = new HashMap<>();

            for (int n : nums) {
                if (counter.containsKey(n)) {
                    counter.put(n, counter.get(n) + 1);
                } else {
                    counter.put(n, 1);
                }
            }

            return counter.entrySet()
                          .stream()
                          .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                          .map(Entry::getKey)
                          .findFirst()
                          .get();
        }
        // 놓친 힌트: n//2 이상을 차진한다.
        // 굳이 정렬시킬 필요 없었고, value가 n/2보다만 크면 됐다.

        public int mooreVoting(int[] nums) {
            int count = 0;
            int candidate = 0;

            for (int n : nums) {
                if (count == 0) {
                    candidate = n;
                }
                if (candidate == n) {
                    count++;
                } else {
                    count--;
                }
            }
            return candidate;
        }
    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int k) {
        Solution cut = new Solution();
        int x = cut.mooreVoting(nums);
        assertThat(x).isEqualTo(k);
    }

    private static Stream<Object[]> tc() {
        return Stream.of(
            new Object[] {
                new int[] { 3, 2, 3 },
                3,
                },
            new Object[] {
                new int[] { 2, 2, 1, 1, 1, 2, 2 },
                2,
                }
        );
    }
}