package BOJ;

// [백준] Silver2 연속합
// https://www.acmicpc.net/problem/1912

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912 {

    int[] dp;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ret = arr[0];
        int[] dp = new int[n];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            if (dp[i - 1] < 0) dp[i] = arr[i];
            else dp[i] = dp[i - 1] + arr[i];
            ret = Math.max(ret, dp[i]);
        }

        System.out.println(ret);
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1912().solution();
    }
}
