package BOJ;

// [백준] Silver3 스위치 켜고 끄기
// https://www.acmicpc.net/problem/1244

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1244 {

    int[] switches;

    void changeSwitch(int index) {
        switches[index] = (switches[index] + 1) % 2;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfSwitch = Integer.parseInt(br.readLine());
        switches = new int[numberOfSwitch];
        switches = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int numberOfStudent = Integer.parseInt(br.readLine());
        for (int i = 0; i < numberOfStudent; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int sex = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            // 남학생
            if (sex == 1) {
                for (int j = num - 1; j < numberOfSwitch; j += num) {
                    changeSwitch(j);
                }
            }
            // 여학생
            else if (sex == 2) {
                num--;
                changeSwitch(num);
                for (int j = 1; num + j < numberOfSwitch && num - j >= 0; j++) {
                    if (switches[num - j] != switches[num + j]) break;
                    changeSwitch(num - j);
                    changeSwitch(num + j);
                }
            }
        }
        for (int i = 0; i < numberOfSwitch; i++) {
            System.out.print(switches[i]);
            if ((i + 1) % 20 == 0) System.out.print("\n");
            else System.out.print(" ");
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1244().solution();
    }
}
