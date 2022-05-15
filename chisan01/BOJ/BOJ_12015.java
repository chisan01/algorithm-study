package BOJ;

// [백준] Gold2 가장 긴 증가하는 부분 수열 2
// https://www.acmicpc.net/problem/12015

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_12015 {

    int N;
    List<Integer> LIS;
    int[] arr;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LIS = new ArrayList<>();
        LIS.add(arr[0]);
        for (int curIndex = 1; curIndex < arr.length; curIndex++) {
            if (LIS.get(LIS.size() - 1) < arr[curIndex]) {
                LIS.add(arr[curIndex]);
            } else {
                int start = 0, end = LIS.size() - 1;
                while (start < end) {
                    int mid = (start + end) / 2;
                    if (arr[curIndex] > LIS.get(mid)) start = mid + 1;
                    else end = mid;
                }
                LIS.set(end, arr[curIndex]);
            }
        }

        bw.write(Integer.toString(LIS.size()) + '\n');

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new BOJ_12015().solution();
    }
}
