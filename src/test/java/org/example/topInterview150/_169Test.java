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


    }

    @MethodSource("tc")
    @ParameterizedTest
    void run(int[] nums, int k) {
        Solution cut = new Solution();
        int x = cut.majorityElement(nums);
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