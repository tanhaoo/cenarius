package com.th.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: Aaron
 * @Date: 2022/9/16
 */
public class LeetCode {

    @Test
    public void testTwoSum() {
        assertArrayEquals(new int[]{1, 0}, twoSum2());
    }

    public int[] twoSum() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int expect = target - nums[i];
            if (map.get(expect) != null && map.get(expect) != i) {
                return new int[]{i, map.get(expect)};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum2() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int expect = target - nums[i];
            if (map.get(expect) != null) {
                return new int[]{i, map.get(expect)};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    @Test
    public void testMaxProfit() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        assertEquals(5, maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int left, right, max;
        max = 0;
        left = 0;
        right = 1;
        while (right < prices.length) {
            if (prices[right] > prices[left]) {
                int current = prices[right] - prices[left];
                max = current > max ? current : max;
            } else {
                left = right;
            }
            right++;
        }

        return max;
    }

    @Test
    public void testContainsDuplicate() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4, 7};
        assertEquals(true, containsDuplicate(prices));
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }
        return false;
    }

    @Test
    public void testProductExceptSelf() {
        int[] prices = new int[]{7, 1, 5, 3, 6};
        assertArrayEquals(new int[]{90, 630, 126, 210, 105}, productExceptSelf(prices));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int prefix = 1, postfix = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= postfix;
            postfix = nums[i];
        }
        return result;
    }

    @Test
    public void testMaxSubArray() {
        int[] prices = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        assertEquals(6, maxSubArray(prices));
    }

    public int maxSubArray(int[] nums) {
        int max, current;
        current = 0;
        max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            current += nums[i];
            max = max > current ? max : current;
            if (current < 0) {
                current = 0;
            }
        }
        return max;
    }

    @Test
    public void testMaxProduct() {
        int[] prices = new int[]{2, 3, -2, 4};
        assertEquals(6, maxProduct(prices));
    }

    public int maxProduct(int[] nums) {
        int result = Arrays.stream(nums).max().getAsInt();
        int cur, curMin = 1, curMax = 1;
        for (int i = 0; i < nums.length; i++) {
            cur = nums[i];

            if (cur == 0) {
                curMax = 1;
                curMin = 1;
                continue;
            }

            int temp = curMax * cur;
            curMax = Arrays.stream(new int[]{temp, curMin * cur, cur}).max().getAsInt();
            curMin = Arrays.stream(new int[]{temp, curMin * cur, cur}).min().getAsInt();

            result = Arrays.stream(new int[]{curMax, result}).max().getAsInt();
        }
        return result;
    }

    @Test
    public void testFindMin() {
        int[] prices = new int[]{7, 8, 9, 0, 1, 2, 3, 4, 5};
        assertEquals(0, findMin(prices));
    }

    public int findMin(int[] nums) {
        int left, right, mid, min;
        left = 0;
        right = nums.length - 1;
        min = nums[left];
        while (left <= right) {

            if (nums[right] > nums[left]) {
                min = min < nums[left] ? min : nums[left];
                break;
            }

            mid = (left + right) / 2;
            min = min < nums[mid] ? min : nums[mid];

            if (nums[mid] >= nums[left])
                left = mid + 1;
            else
                right = mid - 1;
        }
        return min;
    }

    @Test
    public void testSearch() {
        int[] prices = new int[]{4, 5, 6, 7, 0, 1, 2};
        assertEquals(4, search(prices, 0));
    }

    public int search(int[] nums, int target) {
        int left, right, mid;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] >= nums[left]) {
                if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void testThreeSum() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] failExample = {3, 0, -2, -1, 1, 2};

        List<List<Integer>> collect =
                Arrays.stream(new int[][]{{-1, -1, 2}, {-1, 0, 1}})
                        .map(item -> Arrays.stream(item).boxed().collect(Collectors.toList()))
                        .collect(Collectors.toList());

        assertEquals(false, collect.retainAll(threeSum(nums)));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        int first;
        int left, right;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            first = nums[i];

            left = i + 1;
            right = nums.length - 1;

            while (left < right) {
                int sum = first + nums[left] + nums[right];

                if (sum > 0)
                    right--;
                else if (sum < 0)
                    left++;
                else {
                    result.add(Arrays.asList(first, nums[left], nums[right]));
                    left++;
                    while (nums[left] == nums[left - 1] && left < right) {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void testMaxArea() {
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        assertEquals(49, maxArea(nums));
    }

    public int maxArea(int[] height) {
        int left, right, maxArea;
        left = maxArea = 0;
        right = height.length - 1;

        while (left < right) {
            int area = (right - left) * Arrays.stream(new int[]{height[left], height[right]}).min().getAsInt();
            maxArea = maxArea > area ? maxArea : area;
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    @Test
    public void testHammingWeight() {

        int n = 00000000000000000000000000011011;

        assertEquals(4, hammingWeight(n));
        System.out.println(Integer.valueOf(Integer.toBinaryString(2)) & 2);
        System.out.println(Integer.toBinaryString(00000000000000000000000000001011 >>> 1));
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    @Test
    public void testCountBits() {
        /**
         * 0       0
         * 1       1    1
         * 2      10    1
         * 3      11    2
         * 4     100    1
         * 5     101    2
         * 6     110    2
         * 7     111    3
         * 8    1000    1
         * 9    1001    2
         */
        assertArrayEquals(new int[]{0, 1, 1}, countBits(2));
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int count, cur;
        res[0] = 0;
        for (int i = 1; i < res.length; i++) {
            count = 0;
            cur = i;
            while (cur != 0) {
                if (cur % 2 == 1)
                    count++;
                cur >>>= 1;
//                cur /= 2;
            }
            res[i] = count;
        }
        return res;
    }

    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        int offset = 1;
        res[0] = 0;

        for (int i = 1; i < res.length; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            res[i] = 1 + res[offset - i];
        }

        return res;
    }

    @Test
    public void testMissingNumber() {
        assertEquals(2, missingNumber1(new int[]{3, 0, 1}));
    }

    public int missingNumber(int[] nums) {
        int size = nums.length + 1;
        int temp1, temp2;
        temp1 = temp2 = 0;
        /**
         * 0    1    2    3
         *
         *      0   01   11
         *      1   10   11
         * ^=   1   11   00  expect
         *               10  actual
         *  总数亦或的结果 去 亦或 实际数亦或的结果 得到的结果 就是少的那个数
         *  比如说[0,1,2,3]异或结果为00，实际数为[0,1,3]异或结果为10，少的那个数就是2
         */
        for (int i = 1; i < size; i++) {
            temp1 ^= i;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == 0)
                temp2 = nums[i];
            else
                temp2 ^= nums[i];
        }
        return temp1 ^ temp2;
    }

    public int missingNumber1(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res += (i - nums[i]);
        }
        return res;
    }

    @Test
    public void testReverseBits() {
        assertEquals(964176192, reverseBits1(43261596));
    }

    public int reverseBits(int n) {
        int res = 0, bit = 0;

        for (int i = 0; i < 32; i++) {
            bit = (n >>> i) & 1;
            res = (res << 1) | bit;
        }

        return res;
    }

    public int reverseBits1(int n) {
        int res = 0, bit = 0;

        for (int i = 0; i < 32; i++) {
            bit = (n >> i) & 1;
            res = (bit << (31 - i)) | res;
        }

        return res;
    }

    @Test
    public void testClimbStairs() {

        assertEquals(3, climbStairs2(3));
    }

    public int climbStairs(int n) {
        if (n == 0 || n == 1)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs1(int n) {
        int one, two;
        one = two = 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                one += two;
            else
                two += one;
        }
        return n % 2 == 0 ? one : two;
    }

    public int climbStairs2(int n) {
        int one, two, temp;
        one = two = 1;
        for (int i = 0; i < n - 1; i++) {
            temp = one;
            one += two;
            two = temp;
        }
        return one;
    }

    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];//存储每一个台阶的方法数
        dp[0] = 1;//0号位是多余的，故数组要分配n+1项
        dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    @Test
    public void testCoinChange() {
        /**
         * Amount      Coins
         *   0          0
         *   1        1+DP[0]
         *   2        1+DP[2-coin]
         *   3        1+DP[3-coin]
         *   4
         *   5
         *   6
         *   7
         */
        assertEquals(3, coinChangeWithResult(new int[]{1, 2, 5}, 11));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0)
                    // + 1 代表的是从coins里选出了一枚硬币，然后加上差值所对应的硬币数量
                    dp[i] = Arrays.stream(new int[]{dp[i], 1 + dp[i - coins[j]]}).min().getAsInt();
            }
        }
        if (dp[amount] != amount + 1)
            return dp[amount];
        else
            return -1;
    }

    public int coinChangeWithResult(int[] coins, int amount) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < amount + 1; i++) {
            result.add(i, new ArrayList<>());
        }

        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    // + 1 代表的是从coins里选出了一枚硬币，然后加上差值所对应的硬币数量
                    if (dp[i] > (1 + dp[i - coins[j]])) {
                        dp[i] = 1 + dp[i - coins[j]];
                        List<Integer> temp = new ArrayList<>(result.get(i - coins[j]));
                        temp.add(coins[j]);
                        result.set(i, temp);
                    }
                }
            }
        }

        result.forEach(System.out::println);

        if (dp[amount] != amount + 1)
            return dp[amount];
        else
            return -1;
    }

    @Test
    public void testLengthOfLIS() {

        //10,9,2,5,3,7,101,18
        assertEquals(4, lengthOfLISWithResult(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public int lengthOfLIS(int[] nums) {
        //[0,1,0,3,2,3]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j])
                    dp[i] = dp[i] > (1 + dp[j]) ? dp[i] : (1 + dp[j]);
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    // 输出所有结果
    public int lengthOfLISWithResult(int[] nums) {
        //[0,1,0,3,2,3]
        int[] dp = new int[nums.length];
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            result.add(i, new ArrayList<>(Arrays.asList(new Integer[]{nums[i]})));
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    if (dp[i] < (1 + dp[j])) {
                        dp[i] = 1 + dp[j];
                        List<Integer> temp = new ArrayList<>(result.get(j));
                        temp.add(0, nums[i]);
                        result.set(i, temp);
                    }
                }
            }
        }
        result.forEach(System.out::println);
        return Arrays.stream(dp).max().getAsInt();
    }

    @Test
    public void testLongestCommonSubsequence() {
        /**
         *      a    b   c   d    e
         *  a   2   2    2   1    1      0
         *  c   2   2   [2]  1    1      0
         *  e   1   1    1   1   [1]     0
         *      0   0    0   0    0      0
         *
         *  从底部开始找，像C是第两个的时候，其实就代表 [c,e] [c,d,e]
         */
        String text1 = "abcde";
        String text2 = "ace";
        assertEquals(3, longestCommonSubsequence(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();
        int[][] dp = new int[char1.length + 1][char2.length + 1];

        for (int i = char1.length - 1; i >= 0; i--) {
            for (int j = char2.length - 1; j >= 0; j--) {
                if (char1[i] == char2[j])
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else
                    dp[i][j] = Arrays.stream(new int[]{dp[i + 1][j], dp[i][j + 1]}).max().getAsInt();
            }
        }
        return dp[0][0];
    }

    @Test
    public void testWordBreak() {

        String test = "abcd";

        assertEquals(true, wordBreak(test, new ArrayList<>(Arrays.asList("a", "abc", "b", "cd"))));

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[dp.length - 1] = true;
        for (int i = s.length() - 1; i >= 0; i--) {
            String tempStr = s.substring(i);
            for (int j = 0; j < wordDict.size(); j++) {
                String curStr = wordDict.get(j);
                if (tempStr.length() >= curStr.length() && tempStr.startsWith(curStr))
                    dp[i] = dp[i + curStr.length()];
                if (dp[i])
                    break;
            }
        }
        return dp[0];
    }

    @Test
    public void testCombinationSum() {

        List<List<Integer>> result = Arrays.asList(Arrays.asList(new Integer[]{2, 2, 3}), Arrays.asList(new Integer[]{7}));
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        assertEquals(result, combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, new ArrayList<>(), 0, candidates, target, result);
        return result;
    }

    public void dfs(Integer index, List<Integer> cur, Integer total, int[] candidates, int target, List<List<Integer>> result) {
        if (total == target) {
            result.add(new ArrayList<>(cur));
            return;
        }
        if (index >= candidates.length || total > target)
            return;

        cur.add(candidates[index]);
        dfs(index, cur, total + candidates[index], candidates, target, result);
        cur.remove(cur.size() - 1);
        dfs(index + 1, cur, total, candidates, target, result);
    }

    @Test
    public void testRob() {
        assertEquals(12, rob2(new int[]{2, 7, 9, 3, 1}));
    }

    public int rob(int[] nums) {
        /**
         *
         * 0    1   2   3   4
         * 2    7   9   3   1
         * 2    7   11  11  12
         */
        if (nums.length < 2)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1] > nums[0] ? nums[1] : nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] + dp[i - 2] > dp[i - 1])
                dp[i] = nums[i] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public int rob1(int[] nums) {
        /**
         *
         * 0    1   2   3   4
         * 2    7   9   3   1
         * 2    7   11  11  12
         */
        if (nums.length < 2)
            return nums[0];
        int first, second, temp;
        first = nums[0];
        second = nums[1] > nums[0] ? nums[1] : nums[0];
        for (int i = 2; i < nums.length; i++) {
            temp = second;
            if (nums[i] + first > second)
                second = nums[i] + first;
            first = temp;
        }
        return first > second ? first : second;
    }

    public int rob2(int[] nums) {
        /**
         *
         * 0    1   2   3   4
         * 2    7   9   3   1
         * 2    7   11  11  12
         */
        int rob1, rob2, temp;
        rob1 = rob2 = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = rob1 + nums[i] > rob2 ? rob1 + nums[i] : rob2;
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    @Test
    public void testSecondRob() {
        /**
         * All houses at this place are arranged in a circle.
         * That means the first house is the neighbor of the last one.
         * Meanwhile, adjacent houses have a security system connected,
         * and it will automatically contact the police if two adjacent houses were broken into on the same night.
         */

        assertEquals(3, secondRob(new int[]{1}));
    }

    public int secondRob(int[] nums) {
        if (nums.length < 2)
            return nums[0];
        //2 7 9 3 1 5 6
        int[] start = new int[nums.length - 1], end = new int[nums.length - 1];
        for (int i = 0; i < start.length; i++) {
            start[i] = nums[i];
            end[i] = nums[i + 1];
        }
        int i = rob2(start);
        int j = rob2(end);
        return i > j ? i : j;
    }

    public int secondRob1(int[] nums) {
        int[] start = new int[nums.length - 1], end = new int[nums.length - 1];
        for (int i = 0; i < start.length; i++) {
            start[i] = nums[i];
            end[i] = nums[i + 1];
        }
        return Arrays.stream(new int[]{nums[0], rob2(start), rob2(end)}).max().getAsInt();
    }

    @Test
    public void testNumDecodings() {

        assertEquals(2, numDecodings2("1231516226"));
    }

    // reverse order
    public int numDecodings(String s) {
        /**
         * 1        2                   2                                   4                                       1
         * 1    [1,2],[12]    [1,2,2],[12,2],[1,22]     [1,2,2,4],[1,22,4],[1,2,24],[12,2,4],[12,24]        [1,2,2,4,1],[1,22,4,1],          [1,2,24,1]           ,[12,2,4,1],[12,24,1]
         *                                                                                                  [1,2,2,1,1],[1,22,1,1],[1,22,11],[1,2,22,1],[1,2,2,21],[12,2,2,1],[12,22,1],[12,2,21]
         * 1        2                   3                                   5
         */
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length + 1];
        Arrays.fill(dp, 1);

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0')
                dp[i] = 0;
            else
                dp[i] = dp[i + 1];

            if (i + 1 < chars.length && (chars[i] == '1' || (chars[i] == '2' && Integer.parseInt(String.valueOf(chars[i + 1])) < 7)))
                dp[i] += dp[i + 2];
        }

        return dp[0];
    }

    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int[] data = new int[chars.length];
        int[] dp = new int[chars.length + 1];
        Arrays.fill(dp, 1);
        for (int i = 0; i < chars.length; i++) {
            data[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        for (int i = 1; i < dp.length; i++) {

            if (data[i - 1] == 0)
                dp[i] = 0;
            else
                dp[i] = dp[i - 1];

            if (i - 2 >= 0 && (data[i - 2] == 1 || (data[i - 2] == 2 && data[i - 1] < 7)))
                dp[i] += dp[i - 2];
        }

        return dp[dp.length - 1];
    }

    @Test
    public void testUniquePaths() {

        /**
         * m = 3, n = 2
         *
         *         0    1      2
         *    0    3    2      1       0
         *    1    1    1    target    0
         *         0    0      0
         *
         *    左上走到右下，边界都为0，target最终路径数为1，
         */
        assertEquals(28, uniquePaths(3, 7));
    }

    public int uniquePaths(int m, int n) {
        int[][] data = new int[m + 1][n + 1];
        for (int i = data.length - 2; i >= 0; i--) {
            for (int j = data[i].length - 2; j >= 0; j--) {
                if (i == data.length - 2 && j == data[i].length - 2)
                    data[i][j] = 1;
                else {
                    data[i][j] = data[i + 1][j] + data[i][j + 1];
                }
            }
        }
        return data[0][0];
    }

    public int uniquePaths1(int m, int n) {
        // 底部一行一定都是0
        int[][] data = new int[m + 1][n + 1];
        Arrays.fill(data[m - 1], 1);
        for (int i = data.length - 3; i >= 0; i--) {
            for (int j = data[i].length - 2; j >= 0; j--) {
                data[i][j] = data[i + 1][j] + data[i][j + 1];
            }
        }
        return data[0][0];
    }

    @Test
    public void testCanJump() {

        assertEquals(true, canJump2(new int[]{2, 3, 1, 1, 4}));

    }

    public boolean canJump1(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j <= i + nums[i]; j++) {
                if (dp[j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public boolean canJump2(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal)
                goal = i;
        }
        return goal == 0 ? true : false;
    }


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    @Test
    public void testCloneGraph() {
        /**
         * 1    2
         * 4    3
         */

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));
        Node node = cloneGraph(node1);
    }

    public Node cloneGraph(Node node) {
        HashMap map = new HashMap<Integer, Node>();
        return node == null ? null : dfsGraph(node, map);
    }

    public Node dfsGraph(Node node, HashMap<Integer, Node> map) {
        int curVal = node.val;
        if (map.get(curVal) != null) {
            return map.get(curVal);
        }

        Node copyNode = new Node(curVal);
        map.put(curVal, copyNode);

        for (Node neighbor : node.neighbors) {
            copyNode.neighbors.add(dfsGraph(neighbor, map));
        }

        return copyNode;
    }

    @Test
    public void testCanFinish() {
        assertEquals(true, canFinish(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {3, 4}}));
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 先建一个课程条件表，key是当前课程，value，就是前置条件课程集合
        HashMap<Integer, List<Integer>> dataMap = new HashMap<>();
        // 建一个课程记录表，这样可以发现相互依赖的课程
        HashSet<Integer> visitSet = new HashSet<>();

        for (int i = 0; i < prerequisites.length; i++) {
            Integer course = prerequisites[i][0];
            if (dataMap.get(course) == null)
                dataMap.put(course, new ArrayList<>());
            dataMap.get(course).add(prerequisites[i][1]);
        }

        for (Integer integer : dataMap.keySet()) {
            // 课程可能不是一张图，是分割的，所以每个都要试
            if (!dfsCanFinish(integer, dataMap, visitSet))
                return false;
        }
        System.out.println(dataMap);
        return true;
    }

    public boolean dfsCanFinish(Integer curVal, HashMap<Integer, List<Integer>> dataMap, HashSet<Integer> visitSet) {
        /**
         * 0 - 1 - 3
         * |    \  |
         * 2       4
         *
         */
        // 这边很关键，如果没有当前课程，就应该直接返回，不能先执行下面的判断，如果先添加进visit记录中，会对接下来流程有影响
        if (dataMap.get(curVal) == null || dataMap.get(curVal).size() == 0) {
            return true;
        }

        if (!visitSet.add(curVal)) {
            return false;
        }

        for (Integer integer : dataMap.get(curVal)) {
            if (!dfsCanFinish(integer, dataMap, visitSet))
                return false;
        }
        visitSet.remove(curVal);
        dataMap.put(curVal, new ArrayList<>());
        return true;
    }

    @Test
    public void testPacificAtlantic() {
        /**
         * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
         */
        ArrayList<List<Integer>> lists = new ArrayList<>(
                Arrays.asList(
                        Arrays.asList(0, 4),
                        Arrays.asList(1, 3),
                        Arrays.asList(1, 4),
                        Arrays.asList(2, 2),
                        Arrays.asList(3, 0),
                        Arrays.asList(3, 1),
                        Arrays.asList(4, 0)));
        System.out.println(lists);

        assertEquals(false, lists.retainAll(pacificAtlantic(new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}})));

    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        /**
         * 1    2   3
         * 8    9   4
         * 7    6   5
         */
        int columns = heights[0].length;
        int rows = heights.length;
        HashSet<List<Integer>> pacificVisit = new HashSet<>();
        HashSet<List<Integer>> atlanticVisit = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            dfsPacificAtlantic(heights, i, 0, pacificVisit, heights[i][0]);
            dfsPacificAtlantic(heights, i, columns - 1, atlanticVisit, heights[i][columns - 1]);
        }
        for (int i = 0; i < columns; i++) {
            dfsPacificAtlantic(heights, 0, i, pacificVisit, heights[0][i]);
            dfsPacificAtlantic(heights, rows - 1, i, atlanticVisit, heights[rows - 1][i]);
        }

        return pacificVisit.stream().filter(item -> atlanticVisit.contains(item)).collect(Collectors.toList());
    }

    public void dfsPacificAtlantic(int[][] height, int row, int column, HashSet<List<Integer>> visit, int previous) {

        if (row < 0 || row >= height.length || column < 0 || column >= height[0].length
                || height[row][column] < previous
                || visit.contains(Arrays.asList(row, column)))
            return;
        visit.add(Arrays.asList(row, column));

        dfsPacificAtlantic(height, row + 1, column, visit, height[row][column]);
        dfsPacificAtlantic(height, row - 1, column, visit, height[row][column]);
        dfsPacificAtlantic(height, row, column + 1, visit, height[row][column]);
        dfsPacificAtlantic(height, row, column - 1, visit, height[row][column]);
    }

    @Test
    public void testNumIslands() {
        assertEquals(1, numIslands(
                new char[][]{
                        {'1', '1', '1', '1', '0'},
                        {'1', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}}));

    }

    public int numIslands(char[][] grid) {
        int island = 0;
        int rows = grid.length, cols = grid[0].length;
        // 不能用<Integer[]> 数组比较地址，不比较具体值
        HashSet<List<Integer>> visit = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visit.contains(Arrays.asList(i, j)) && grid[i][j] == '1') {
                    bfsNumIslands(grid, i, j, visit);
                    island++;
                }
            }
        }
        return island;
    }

    private void bfsNumIslands(char[][] grid, int row, int col, HashSet<List<Integer>> visit) {
        Queue<List<Integer>> curVisit = new LinkedList<>();
        curVisit.add(Arrays.asList(row, col));
        visit.add(Arrays.asList(row, col));
        while (!curVisit.isEmpty()) {
            List<Integer> poll = curVisit.poll();
            Integer[][] directions = new Integer[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < directions.length; i++) {
                row = poll.get(0) + directions[i][0];
                col = poll.get(1) + directions[i][1];
                if (row >= 0 &&
                        col >= 0 &&
                        row < grid.length &&
                        col < grid[0].length &&
                        !visit.contains(Arrays.asList(row, col)) &&
                        !curVisit.contains(Arrays.asList(row, col)) &&
                        grid[row][col] == '1') {
                    visit.add(Arrays.asList(row, col));
                    curVisit.add(Arrays.asList(row, col));
                }
            }
        }
    }

    @Test
    public void testLongestConsecutive() {
        assertEquals(9, longestConsecutive1(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    public int longestConsecutive(int[] nums) {
        // Time Limit Exceeded
        int result = 0;
        HashSet<Integer> data = new HashSet<>();
        Arrays.stream(nums).forEach(data::add);

        Iterator<Integer> iterator = data.iterator();
        while (data.iterator().hasNext()) {
            int tempResult = recursiveLongest(data, data.iterator().next(), iterator) - 1;
            result = result > tempResult ? result : tempResult;
        }
        return result;
    }

    public int recursiveLongest(HashSet<Integer> data, Integer value, Iterator<Integer> iterator) {
        if (!data.contains(value))
            return 1;
        data.remove(value);
        return recursiveLongest(data, value - 1, iterator) + recursiveLongest(data, value + 1, iterator);
    }

    public int longestConsecutive1(int[] nums) {
        int result = 0;
        HashSet<Integer> data = new HashSet<>();
        Arrays.stream(nums).forEach(data::add);

        Iterator<Integer> iterator = data.iterator();
        while (iterator.hasNext()) {
            Integer cur = iterator.next();
            Integer length = 0;
            if (data.contains(cur - 1))
                continue;

            while (data.contains(cur + length)) {
                length += 1;
            }
            result = result > length ? result : length;
        }

        return result;
    }

    @Test
    public void testInsert() {
        assertEquals(new int[][]{{1, 5}, {6, 9}}, insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int[][] resultArray;
        for (int i = 0; i < intervals.length; i++) {
            /**
             *          1   2   3   4   5   6   7   8   9
             *          ---------
             *                              -------------
             * newInterval  -------------
             * result   -----------------   -------------
             */
            // CASE1：如果当前遍历值首位已经比插入值的末位大了，那后面的肯定也都是大的，因为升序排列，所以可以直接结束了
            if (intervals[i][0] > newInterval[1]) {
                result.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                resultArray = new int[result.size()][];
                return result.toArray(resultArray);
            }
            // 如果当前小，那就先把这个小的放进结果集，然后遍历下一个
            if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
                continue;
            }
            // 当前值与目标值重合了，取最大范围(首位取最小，末位取最大)
            newInterval = new int[]{Arrays.stream(new int[]{intervals[i][0], newInterval[0]}).min().getAsInt(),
                    Arrays.stream(new int[]{intervals[i][1], newInterval[1]}).max().getAsInt()};
        }
        // CASE2：没有命中CASE1则代表newInterval就是集合中的最大值，加进来，返回
        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void testMerge() {
        assertEquals(new int[][]{{1, 6}, {8, 10}, {15, 18}}, merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    }

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        // Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 很关键，如果不排序很难搞
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= result.get(result.size() - 1)[1]) {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }

        result.stream().map(Arrays::toString).forEach(System.out::println);
        result.stream().forEach(item -> System.out.println(Arrays.toString(item)));
        return result.toArray(new int[result.size()][]);
    }

    @Test
    public void testEraseOverlapIntervals() {
//        {1, 2}, {2, 3}, {3, 4}, {1, 3}
        assertEquals(2, eraseOverlapIntervals(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}}));

    }

    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] temp = new int[2];
        int count = 0;
        temp = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            /**
             *  1   2   3   4   5   6   7   8   9
             *  -----
             *  ---------
             *      -----
             *          -----
             */
            // 相交不算覆盖，所以temp[1]>就行，不用>=,如果两段覆盖在一起，删末位更大的值，因为他更有可能与下一点重合
            if (temp[0] <= intervals[i][0] && temp[1] > intervals[i][0]) {
                count++;
                if (intervals[i][1] < temp[1])
                    temp = intervals[i];
            } else {
                temp = intervals[i];
            }
        }
        return count;
    }

    public int eraseOverlapIntervals1(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int preEnd;
        int count = 0;
        preEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= preEnd) {
                preEnd = intervals[i][1];
            } else {
                count++;
                preEnd = Math.min(preEnd, intervals[i][1]);
            }
        }
        return count;
    }

    public class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    @Test
    public void testCanAttendMeetings() {
        assertEquals(false, canAttendMeetings(Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))));
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        if (intervals.size() == 0) return true;
        int preEnd = intervals.get(0).end;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start < preEnd)
                return false;
            else
                preEnd = intervals.get(i).end;
        }
        return true;
    }

    @Test
    public void testMinMeetingRooms() {
//        assertEquals(2, minMeetingRooms(Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))));
        assertEquals(7, minMeetingRooms1(Arrays.asList(new Interval(65, 424), new Interval(351, 507),
                new Interval(314, 807), new Interval(387, 722), new Interval(19, 797), new Interval(259, 722),
                new Interval(165, 221), new Interval(136, 897))));
    }

    public int minMeetingRooms1(List<Interval> intervals) {
        int count = 0, endIndex = 0, max = 0;
        // 把所有开始时间和结束时间分别升序排列
        List<Integer> start = intervals.stream().map(item -> item.start).sorted((a, b) -> a - b).collect(Collectors.toList());
        List<Integer> end = intervals.stream().map(item -> item.end).sorted((a, b) -> a - b).collect(Collectors.toList());
        /** 依次拿开始时间和结束时间做比较，比结束时间小，就代表重合，需要一个room，即++
         *  如果开始时间比结束时间大了，代表这两个会议时间不会重合，则代表这个结束时间的room可以被释放，即--，
         *  同时保留当前这个开始时间，他需要在和下一个结束时间做比较
         *  取期间最大的room数则代表这些会议期间，最少需要用到的room
         */
        for (int i = 0; i < start.size(); i++) {
            if (start.get(i) < end.get(endIndex)) {
                count++;
                max = max > count ? max : count;
            } else {
                // 保留当前开始时间，重新和下一个结束时间比较
                i--;
                endIndex++;
                count--;
            }
        }
        return max;
    }

    @Test
    public void alienOrder() {
        assertEquals("wertf", alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
    }


    public String alienOrder(String[] words) {
        /**
         *  w r t
         *  w r f
         *  e r
         *  e t t
         *  r f t t
         *
         *  t -> f
         *  w -> e
         *  r -> t
         *  e -> r
         */
        // 太难了，放弃!!！ 2022.11.08
        HashMap<Character, HashSet<Character>> adjacent = new LinkedHashMap<>();
        HashMap<Character, Boolean> visit = new HashMap<>();
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                // w r t f e
                adjacent.put(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String pre = words[i];
            String cur = words[i + 1];

            int min = Math.min(pre.length(), cur.length());
            if (pre.substring(min).equals(cur.substring(min)) && pre.length() > cur.length())
                return "";
            for (int j = 0; j < min; j++) {
                if (pre.charAt(j) != cur.charAt(j)) {
                    adjacent.get(pre.charAt(j)).add(cur.charAt(j));
                    break;
                }
            }
        }
        System.out.println(adjacent);

        Iterator<Character> iterator = adjacent.keySet().iterator();
        while (iterator.hasNext()) {
            if (dfsAlienOrder(iterator.next(), visit, adjacent, result))
                return "";
        }
        return result.reverse().toString();
    }

    public boolean dfsAlienOrder(Character c, HashMap<Character, Boolean> visit, HashMap<Character, HashSet<Character>> adjacent, StringBuilder res) {
        if (visit.get(c) != null)
            return visit.get(c);
        visit.put(c, true);
        for (Character curChar : adjacent.get(c)) {
            if (dfsAlienOrder(curChar, visit, adjacent, res))
                return true;
        }
        visit.put(c, false);
        res.append(c);
        return false;
    }

    @EqualsAndHashCode
    @ToString
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


    }

    @Test
    public void testReverseList() {
        reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
    }

    public ListNode reverseList(ListNode head) {
        ListNode result = new ListNode();
        if (head == null)
            return null;
//        System.out.println(dfsReverseList(head, result));
        System.out.println(dfsReverseList1(head));
        System.out.println(result);
        return result;
    }

    public ListNode dfsReverseList(ListNode node, ListNode result) {
        if (node.next == null) {
            result.val = node.val;
            return result;
        }
        ListNode newNode = dfsReverseList(node.next, result);
        newNode.next = new ListNode(node.val);
        return newNode.next;
    }


    public ListNode dfsReverseList1(ListNode node) {
        if (node == null)
            return null;
        ListNode newHead = node;
        if (node.next != null) {
            newHead = dfsReverseList1(node.next);
            node.next.next = node;
        }
        node.next = null;
        return newHead;
    }


    public ListNode reverseList1(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        System.out.println(pre);
        return pre;
    }

    @Test
    public void testHasCycle() {
        List<ListNode> data = new ArrayList<>();
        data.add(new ListNode(3));
        data.add(new ListNode(2));
        data.add(new ListNode(0));
        data.add(new ListNode(4));
        data.get(0).next = data.get(1);
        data.get(1).next = data.get(2);
        data.get(2).next = data.get(3);
        data.get(3).next = data.get(1);
        assertEquals(true, hasCycle1(data.get(0)));
    }

    public boolean hasCycle(ListNode head) {
        Set<ListNode> data = new HashSet<>();
        while (head != null) {
            if (!data.add(head))
                return true;
            head = head.next;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    @Test
    public void testMergeTwoLists() {
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        assertEquals(
                new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4)))))),
                mergeTwoLists(node1, node2));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode result = new ListNode();
        ListNode head = result;

        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                head.next = new ListNode(list2.val, new ListNode());
                list2 = list2.next;
            } else {
                head.next = new ListNode(list1.val, new ListNode());
                list1 = list1.next;
            }
            head = head.next;
        }
        if (list1 == null) {
            head.next = list2;
        } else {
            head.next = list1;
        }
        return result.next;
    }

    @Test
    public void testMergeKLists() {
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node3 = new ListNode(5, new ListNode(6, new ListNode(7)));
        assertEquals(new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7))))))))),
                mergeKLists1(new ListNode[]{node1, node2, node3}));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode curResult = lists[0];
        for (int i = 1; i < lists.length; i++) {
            curResult = mergeTwoLists(curResult, lists[i]);
        }
        return curResult;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode[] result = lists;
        while (result.length > 1) {
            List<ListNode> temp = new ArrayList<>();
            for (int i = 0; i < result.length; i += 2) {
                ListNode left = result[i];
                ListNode right = i + 1 >= result.length ? null : result[i + 1];
                temp.add(mergeTwoLists(left, right));
            }
            result = temp.toArray(new ListNode[temp.size()]);
        }
        return result[0];
    }

    @Test
    public void testRemoveNthFromEnd() {
        assertEquals(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5)))),
                removeNthFromEnd2(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        List<ListNode> lists = new ArrayList<>();
        while (cur != null) {
            lists.add(cur);
            cur = cur.next;
        }
        if (lists.size() == 0)
            return head;
        if ((lists.size() - n - 1) < 0) {
            return head.next;
        }
        ListNode pre = lists.get(lists.size() - n - 1);
        pre.next = pre.next.next;
        return head;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        if (right == null)
            return head.next;
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode left = dummy;
        ListNode right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    @Test
    public void testReorderList() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reorderList1(head);

        assertEquals(new ListNode(1, new ListNode(5, new ListNode(2, new ListNode(4, new ListNode(3))))), head);
    }

    public void reorderList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode dummy = head;
        while (dummy != null) {
            nodes.add(dummy);
            dummy = dummy.next;
        }
        int left, right, count;
        left = 1;
        count = 1;
        right = nodes.size() - 1;
        dummy = head;
        while (left <= right) {
            if (count % 2 == 0) {
                dummy.next = nodes.get(left);
                dummy = dummy.next;
                left++;
            } else {
                dummy.next = nodes.get(right);
                dummy = dummy.next;
                right--;
            }
            dummy.next = null;
            count++;
        }
    }

    public void reorderList1(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        /**
         *  先分成两段
         *  1   2   3   4   5
         *  slow    fast
         *   2       3
         *   3       5
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 拿到第二段的开头
        ListNode second = slow.next;
        ListNode pre;
        // 这里很关键，要把slow.next的地址第一段也有，这边要赋null，不然就下面就要自己指向自己了
        // 现在就分成了 【1，2，3】【4，5】两段了
        // 倒叙排列
        pre = slow.next = null;
        while (second != null) {
            ListNode temp = second.next;
            second.next = pre;
            pre = second;
            second = temp;
        }

        /**
         *  1 —> 2 -> 3   5 -> 4
         *  1 -> 5 -> 2
         *  2 -> 4 -> 3
         *  1 -> 5 -> 2 -> 4 -> 3
         */
        ListNode temp1, temp2, first = head;
        while (pre != null) {
            temp1 = first.next;
            temp2 = pre.next;
            first.next = pre;
            pre.next = temp1;
            pre = temp2;
            first = temp1;
        }
    }

}

