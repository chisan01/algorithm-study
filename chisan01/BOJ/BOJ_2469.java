package BOJ;

// [백준] Gold5 사다리 타기
// https://www.acmicpc.net/problem/2469

import java.io.*;

public class BOJ_2469 {

    int numOfParticipant, numOfRow;
    String finalParticipantOrders;
    int hiddenRowIndex;

    int[] startOrders, ordersBeforeHiddenRow, ordersAfterHiddenRow, finalOrders;

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        numOfParticipant = Integer.parseInt(br.readLine());
        numOfRow = Integer.parseInt(br.readLine());
        finalParticipantOrders = br.readLine();

        startOrders = new int[numOfParticipant];
        finalOrders = new int[numOfParticipant];
        for (int i = 0; i < numOfParticipant; i++) {
            startOrders[i] = i;
            finalOrders[i] = finalParticipantOrders.charAt(i) - 'A';
        }

        String[] rows = new String[numOfRow];
        for (int i = 0; i < numOfRow; i++) {
            rows[i] = br.readLine();
            if (rows[i].charAt(0) == '?') hiddenRowIndex = i;
        }

        ordersBeforeHiddenRow = new int[numOfParticipant];
        for (int startPosition = 0; startPosition < numOfParticipant; startPosition++) {
            int curPosition = startPosition;
            for (int row = 0; row < hiddenRowIndex; row++) {
                if(curPosition > 0 && rows[row].charAt(curPosition - 1) == '-') curPosition--;
                else if(curPosition < numOfParticipant - 1 && rows[row].charAt(curPosition) == '-') curPosition++;
            }
            ordersBeforeHiddenRow[curPosition] = startOrders[startPosition];;
        }

        ordersAfterHiddenRow = new int[numOfParticipant];
        for (int startPosition = 0; startPosition < numOfParticipant; startPosition++) {
            int curPosition = startPosition;
            for (int row = numOfRow - 1; row > hiddenRowIndex; row--) {
                if(curPosition > 0 && rows[row].charAt(curPosition - 1) == '-') curPosition--;
                else if(curPosition < numOfParticipant - 1 && rows[row].charAt(curPosition) == '-') curPosition++;
            }
            ordersAfterHiddenRow[curPosition] = finalOrders[startPosition];;
        }

        String ret = "";
        boolean isPossible = true;
        for (int position = 0; position < numOfParticipant; position++) {
            if (ordersBeforeHiddenRow[position] == ordersAfterHiddenRow[position]) {
                // 마지막 수인 경우 예외처리
                if(position == numOfParticipant - 1) continue;

                ret += "*";
            }
            else {
                if(position < numOfParticipant - 1
                        && ordersBeforeHiddenRow[position] == ordersAfterHiddenRow[position+1]
                        && ordersBeforeHiddenRow[position+1] == ordersAfterHiddenRow[position] ) {
                    ret += "-";
                    position++;

                    // 마지막 수인 경우 예외처리
                    if(position == numOfParticipant - 1) continue;

                    ret += "*";
                } else {
                    isPossible = false;
                    break;
                }
            }
        }

        if(isPossible) bw.write(ret + "\n");
        else {
            for (int i = 0; i < numOfParticipant - 1; i++) {
                bw.write("x");
            }
            bw.write("\n");
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new BOJ_2469().solution();
    }
}
