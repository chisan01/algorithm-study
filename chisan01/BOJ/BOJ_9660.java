package BOJ;

// [BOJ] GOld5 돌게임 6
// https://www.acmicpc.net/problem/9660

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9660 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long ret = N % 7;
        if(ret == 2 || ret == 0) {
            System.out.println("CY");
        }
        else {
            System.out.println("SK");
        }
    }
}
