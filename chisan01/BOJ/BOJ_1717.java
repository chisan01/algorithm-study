import java.io.*;
import java.util.StringTokenizer;

class UnionFind {
    private final int[] parentOfNodes;

    public UnionFind(int numOfNode) {
        this.parentOfNodes = new int[numOfNode];
        for (int i = 0; i < parentOfNodes.length; i++) {
            parentOfNodes[i] = i;
        }
    }

    public int rootNodeOf(int startNode) {
        int rootNode;
        int curNode = startNode;
        int parentNode = parentOfNodes[curNode];
        while (true) {
            if (curNode == parentNode) {
                rootNode = curNode;
                break;
            }
            curNode = parentNode;
            parentNode = parentOfNodes[curNode];
        }
        parentOfNodes[startNode] = rootNode;
        return rootNode;
    }

    public boolean areSameGroup(int node1, int node2) {
        int group1RootNode = rootNodeOf(node1);
        int group2RootNode = rootNodeOf(node2);

        return group1RootNode == group2RootNode;
    }

    public void mergeTwoGroup(int node1, int node2) {
        int group1RootNode = rootNodeOf(node1);
        int group2RootNode = rootNodeOf(node2);

        // 이미 동일한 그룹에 속해있는 경우
        if (areSameGroup(group1RootNode, group2RootNode)) return;

        parentOfNodes[group1RootNode] = group2RootNode;
    }
}

public class Main {

    int n, m;
    UnionFind unionFind;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        unionFind = new UnionFind(n + 1);
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // a의 집합과 b 집합 merge
            if (operator == 0) {
                unionFind.mergeTwoGroup(a, b);
            }

            // a와 b가 같은 집합에 있는지 확인
            if (operator == 1) {
                if(unionFind.areSameGroup(a, b)) bw.write("YES\n");
                else bw.write("NO\n");
            }
        }

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}