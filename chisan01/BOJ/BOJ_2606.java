package BOJ;

// [백준] Silver3 바이러스
// https://www.acmicpc.net/problem/2606

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2606 {

    int numberOfComputers;
    List<List<Integer>> computerConnections;

    int[] visitedComputer;

    int dfs(int startComputer) {
        int virusCnt = 0;
        Stack<Integer> S = new Stack<>();
        S.push(startComputer);
        while (!S.isEmpty()) {
            int curComputer = S.pop();
            List<Integer> curComputerConnections = computerConnections.get(curComputer);
            for (int connectedComputers : curComputerConnections) {
                // 이미 방문한 경우
                if(visitedComputer[connectedComputers] == 1) continue;

                virusCnt++;
                visitedComputer[connectedComputers] = 1;
                S.push(curComputer);
                S.push(connectedComputers);
                break;
            }
        }
        return virusCnt;
    }

    int bfs(int startComputer) {
        int virusCnt = 0;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(startComputer);
        while (!Q.isEmpty()) {
            int curComputer = Q.poll();
            List<Integer> curComputerConnections = computerConnections.get(curComputer);
            for (int connectedComputers : curComputerConnections) {
                // 이미 방문한 경우
                if(visitedComputer[connectedComputers] == 1) continue;

                virusCnt++;
                visitedComputer[connectedComputers] = 1;
                Q.add(connectedComputers);
            }
        }
        return virusCnt; // 1번 컴퓨터는 제외
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        numberOfComputers = Integer.parseInt(br.readLine());
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

        visitedComputer = new int[numberOfComputers];
        visitedComputer[0] = 1;
        System.out.println(bfs(0));
    }

    public static void main(String[] args) throws Exception {
        new BOJ_2606().solution();
    }
}
