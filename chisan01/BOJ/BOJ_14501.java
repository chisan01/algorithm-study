package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {

    class Counsel {
        int time;
        int profit;

        public Counsel(int time, int profit) {
            this.time = time;
            this.profit = profit;
        }
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Counsel[] counsels = new Counsel[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(time, profit);
        }

        int[] dp = new int[N+1];
        for(int i=0; i<N; i++) {
            int finishDate = i + counsels[i].time;
            // 일하지 않고 지나가는 경우
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            // 일하는 경우
            if(finishDate > N) continue; // 퇴사한 경우
            dp[finishDate] = Math.max(dp[finishDate], dp[i] + counsels[i].profit);
        }

        System.out.println(dp[N]);
    }

    public static void main(String[] args) throws Exception {
        new BOJ_14501().solution();
    }
}