import java.io.*;
import java.util.*;

// [백준] Gold5 뱀과 사다리 게임
// https://www.acmicpc.net/problem/16928

public class Main {

    final int INF = 987654321;

    int numOfLadder, numOfSnake;
    int[] board;
    int[] dp;
    boolean[] visited;

    int minNumOfRoll(int curPos) {
        if (curPos > 100) return INF;
        if (curPos == 100) return 0;

        while(curPos != board[curPos]) curPos = board[curPos];
        if (dp[curPos] != -1) return dp[curPos];

        if(visited[curPos]) return INF;
        visited[curPos] = true;

        int ret = INF;
        for (int i = 1; i <= 6; i++) {
            ret = Math.min(ret, minNumOfRoll(curPos + i) + 1);
        }
        return dp[curPos] = ret;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new int[101];
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }
        dp = new int[101];
        Arrays.fill(dp, -1);
        visited = new boolean[101];
        Arrays.fill(visited, false);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        numOfLadder = Integer.parseInt(st.nextToken());
        numOfSnake = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numOfLadder + numOfSnake; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            board[src] = dest;
        }

        bw.write(Integer.toString(minNumOfRoll(1)) + '\n');

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}