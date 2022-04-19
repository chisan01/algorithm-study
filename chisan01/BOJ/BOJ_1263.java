package BOJ;

// [백준] Silver1 시간관리
// https://www.acmicpc.net/problem/1263

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1263 {
     static class Work {
        public int spendTime;
        public int finishTime;

        public Work(int spendTime, int finishTime) {
            this.spendTime = spendTime;
            this.finishTime = finishTime;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Work> W = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int spendTime = Integer.parseInt(st.nextToken());
            int finishTime = Integer.parseInt(st.nextToken());
            W.add(new Work(spendTime, finishTime));
        }

        // 제일 늦은 시간부터 차례대로 계산
        W.sort((Work a, Work b) -> {
            return b.finishTime - a.finishTime;
        });
//        W.forEach(a -> System.out.println(a.finishTime));

        int latestStartTime = 1000000;
        for (Work curWork : W) {
            if (curWork.finishTime < latestStartTime) {
                latestStartTime = curWork.finishTime - curWork.spendTime;
            } else {
                latestStartTime -= curWork.spendTime;
            }
//            System.out.println(latestStartTime);
        }

        if(latestStartTime >= 0) System.out.println(latestStartTime);
        else System.out.println("-1");
    }
}
