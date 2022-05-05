package BOJ;

// [백준] Silver3 바이러스
// https://www.acmicpc.net/problem/2606

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2606 {

    int numberOfComputers;
    List<List<Integer>> computerConnections;

    int virusCnt = 0;

    int[] visitedComputer;

    void dfs(int curComputer) {
        virusCnt++;
        List<Integer> curComputerConnections = computerConnections.get(curComputer);
        for (int connectedComputers : curComputerConnections) {
            // 이미 방문한 경우
            if(visitedComputer[connectedComputers] == 1) continue;

            visitedComputer[connectedComputers] = 1;
            dfs(connectedComputers);
        }
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numberOfComputers = Integer.parseInt(br.readLine());
        visitedComputer = new int[numberOfComputers];
        computerConnections = new ArrayList<>();
        for (int i = 0; i < numberOfComputers; i++) {
            computerConnections.add(new ArrayList<>());
        }

        int numberOfConnection = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfConnection; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int aComputer = Integer.parseInt(st.nextToken());
            int bComputer = Integer.parseInt(st.nextToken());
            aComputer--;
            bComputer--;

            computerConnections.get(aComputer).add(bComputer);
            computerConnections.get(bComputer).add(aComputer);
        }

        visitedComputer[0] = 1;
        dfs(0);

        System.out.println(virusCnt - 1); // 1번 컴퓨터는 제외
    }

    public static void main(String[] args) throws Exception {
        new BOJ_2606().solution();
    }
}
