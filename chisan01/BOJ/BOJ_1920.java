package BOJ;

// [백준] Silver4 수 찾기
// https://www.acmicpc.net/problem/1920

import java.util.*;
import java.io.*;

public class BOJ_1920 {

    int N, M;
    int[] arr, searchNumbers;

    int search(int searchNumber, int startIndex, int endIndex) {
        if(startIndex > endIndex) return 0;

        int midIndex = (startIndex + endIndex) / 2;
        if(arr[midIndex] == searchNumber) return 1;
        else if(arr[midIndex] > searchNumber) return search(searchNumber, startIndex, midIndex - 1);
        else return search(searchNumber, midIndex + 1, endIndex);
    }

    void solution() throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        M = Integer.parseInt(br.readLine());
        searchNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int searchNumber : searchNumbers) {
            bw.write(Integer.toString(search(searchNumber, 0, arr.length - 1)) + "\n");
        }

        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1920().solution();
    }
}
