import java.io.*;
import java.util.*;

public class Main {

    final int ROOT_NODE = 0;

    int numOfNode;
    List<List<Integer>> adjList;
    boolean[] visited;

    int sumOfDepthOfLeafNodes = 0;

    void dfs(int depth, int curNode) {
        List<Integer> curAdjList = adjList.get(curNode);

        boolean isLeafNode = true;

        for (int nextNode : curAdjList) {
            if(visited[nextNode]) continue;

            isLeafNode = false;
            visited[nextNode] = true;
            dfs(depth + 1, nextNode);
        }

        if(isLeafNode) {
            sumOfDepthOfLeafNodes += depth;
        }
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numOfNode = Integer.parseInt(br.readLine());
        adjList = new ArrayList<>();
        for (int i = 0; i < numOfNode; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[numOfNode];
        Arrays.fill(visited, false);
        visited[ROOT_NODE] = true;

        for (int i = 0; i < numOfNode - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            a--;
            b--;
            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        dfs(0, ROOT_NODE);

        if (sumOfDepthOfLeafNodes % 2 == 0) bw.write("No\n");
        else bw.write("Yes\n");

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}