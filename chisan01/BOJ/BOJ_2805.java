import java.io.*;
import java.util.*;

// [백준] Silver3 나무 자르기
// https://www.acmicpc.net/problem/2805

public class Main {

    int numOfTree, wantedLength;

    int[] lengthOfTrees;

    long sumOfLengthAfterCut(int H) {
        long sum = 0;
        for (int lengthOfTree : lengthOfTrees) {
            if (lengthOfTree <= H) continue;

            sum += lengthOfTree - H;
        }
        return sum;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        numOfTree = Integer.parseInt(st.nextToken());
        wantedLength = Integer.parseInt(st.nextToken());

        lengthOfTrees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int low = 0;
        int high = 1000000000;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (sumOfLengthAfterCut(mid) < wantedLength) high = mid - 1;
            else low = mid;
        }

        bw.write(Integer.toString(low) + '\n');

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}