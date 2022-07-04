import java.io.*;
import java.util.*;

class Work implements Comparable<Work> {
    public int needTime, endTime;

    public Work(int needTime, int endTime) {
        this.needTime = needTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(Work another) {
        return another.endTime - this.endTime;
    }
}

class Solution {
    int N;
    PriorityQueue<Work> pq;

    int leastStartTime() {
        int prevWorkStartTime = 987654321;
        while(!pq.isEmpty()) {
            Work curWork = pq.poll();
            if(prevWorkStartTime >= curWork.endTime) {
                prevWorkStartTime = curWork.endTime - curWork.needTime;
            } else {
                prevWorkStartTime -= curWork.needTime;
            }
            if(prevWorkStartTime < 0) return -1;
        }
        return prevWorkStartTime;
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int needTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            pq.add(new Work(needTime, endTime));
        }

        System.out.println(leastStartTime());

        br.close();
        bw.close();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        new Solution().solution();
    }
}
