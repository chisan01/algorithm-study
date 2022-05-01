package BOJ;

// [백준] Silver4 게임을 만든 동준이
// https://www.acmicpc.net/problem/2847

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2847 {
    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] levels = new int[N];
        for (int i = 0; i < N; i++) {
            levels[i] = Integer.parseInt(br.readLine());
        }
        int ret = 0;
        for (int i = levels.length - 2; i >= 0; i--) {
            if(levels[i] < levels[i+1]) continue;
            ret += levels[i] - (levels[i+1] - 1);
            levels[i] = levels[i+1] - 1;
        }
        System.out.println(ret);
    }

    public static void main(String[] args) throws Exception {
        new BOJ_2847().solution();
    }
}
