package BOJ;

// [백준] Gold5 합분해
// https://www.acmicpc.net/problem/2225

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2225 {
    static int N, K;

    static long[][] dp;

    static long func(int sum, int cnt) {
        // base case
        if(cnt > K || sum > N) return 0;
        if (sum == N) return 1;
        if(dp[sum][cnt] != -1) return dp[sum][cnt];

        long ret = 0;
        for (int i = 0; i <= N; i++) {
            ret += func(sum + i, cnt + 1);
        }
        return dp[sum][cnt] = ret % 1000000000;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new long[N+1][K+1];
        for(long[] d : dp) Arrays.fill(d, -1);

        System.out.println(func(0, 0));
    }
}
