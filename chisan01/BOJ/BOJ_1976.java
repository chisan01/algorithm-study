import java.io.*;
import java.util.Arrays;

class UnionFind {
    private int[] parentNodeOf;

    public UnionFind(int numOfNodes) {
        parentNodeOf = new int[numOfNodes];
        for (int i = 0; i < numOfNodes; i++) {
            parentNodeOf[i] = i;
        }
    }


    public int find(int node) {
        if (node == parentNodeOf[node]) return node;
        else return parentNodeOf[node] = find(parentNodeOf[node]);
    }


    public boolean isInTheSameGroup(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        return rootA == rootB;
    }


    public void union(int nodeA, int nodeB) {
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if(isInTheSameGroup(rootA, rootB)) return;

        parentNodeOf[rootA] = rootB;
    }
}


public class Main {

    int numOfCity, m;
    int[] travelPlan;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numOfCity = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        UnionFind unionFind = new UnionFind(numOfCity);

        for (int i = 0; i < numOfCity; i++) {
            int[] isConnectedWith = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < numOfCity; j++) {
                if (isConnectedWith[j] == 0) continue;

                unionFind.union(i, j);
            }
        }
        travelPlan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean isTravelPossible = true;
        int firstCity = travelPlan[0] - 1;
        for (int i = 1; i < travelPlan.length; i++) {
            int curCity = travelPlan[i] - 1;
            if(unionFind.isInTheSameGroup(firstCity, curCity)) continue;

            isTravelPossible = false;
            break;
        }

        if(isTravelPossible) bw.write("YES\n");
        else bw.write("NO\n");

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}