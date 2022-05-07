package BOJ;

// [백준] Silver3 01타일
// https://www.acmicpc.net/problem/1904

import java.io.*;
import java.util.Arrays;

public class BOJ_1904 {

    final int MOD = 15746;
    int N;

    int[] dp;

    int calc(int size) {
        if(size < 0) return 0;
        if(size == 0) return 1;

        if(dp[size] != -1) return dp[size];

        int ret = calc(size - 2) + calc(size - 1);
        ret %= MOD;
        return dp[size] = ret;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        System.out.println(calc(N));
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1904().solution();
    }
}
