package BOJ;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

class Board {
    private final int INF = 987654321;
    // left, up, right, down
    private final int[] dx = {-1, 0, 1, 0};
    private final int[] dy = {0, -1, 0, 1};

    private enum DIR {LEFT, UP, RIGHT, DOWN}

    private final char[][] board;
    private int holeX, holeY;
    private Ball initialRedBall, initialBlueBall;
    private Ball redBall, blueBall;
    private int ret;

    // 공
    class Ball {
        public int x, y;

        public Ball(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Ball(Ball copy) {
            this.x = copy.x;
            this.y = copy.y;
        }

        public boolean equals(Ball comp) {
            return this.x == comp.x && this.y == comp.y;
        }

        public boolean isOut() {
            return this.x == holeX && this.y == holeY;
        }

        // '.'이 아닌 블록을 만나기 전까지 dir 방향으로 이동
        public void go(DIR dir) {
            while (0 < this.y && this.y < board.length - 1 && 0 < this.x && this.x < board[0].length - 1) {
                int nextY = this.y + dy[dir.ordinal()];
                int nextX = this.x + dx[dir.ordinal()];
                char nextItem = board[nextY][nextX];

                if (nextItem == 'O') { // 구멍에 도착한 경우 구멍으로 이동
                    this.x = nextX;
                    this.y = nextY;
                    break;
                }

                // 다른 공이나 벽이랑 부딪치는 경우 바로 앞 칸에서 stop
                if (this == redBall) {
                    if (nextY == blueBall.y && nextX == blueBall.x) break;
                }
                if (this == blueBall) {
                    if (nextY == redBall.y && nextX == redBall.x) break;
                }
                if (nextItem == '#') break;

                // 일반적으로 직진하는 경우
                this.x = nextX;
                this.y = nextY;
            }
        }

        public void restorePosition(Ball origin) {
            this.x = origin.x;
            this.y = origin.y;
        }
    }

    public Board(String[] board) {
        this.board = new char[board.length][board[0].length()];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length(); x++) {
                this.board[y][x] = board[y].charAt(x);
                switch (this.board[y][x]) {
                    case 'B':
                        initialBlueBall = new Ball(x, y);
                        this.board[y][x] = '.';
                        break;
                    case 'R':
                        initialRedBall = new Ball(x, y);
                        this.board[y][x] = '.';
                        break;
                    case 'O':
                        this.holeY = y;
                        this.holeX = x;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // 빨간 공이랑 파란 공이랑 같은 줄에 있는 경우, 더 옆에 있는 공부터 먼저 움직여야 한다.
    public void tilt(DIR dir) {
        if ((dir == DIR.LEFT && redBall.y == blueBall.y && redBall.x < blueBall.x) ||
                (dir == DIR.UP && redBall.x == blueBall.x && redBall.y < blueBall.y) ||
                (dir == DIR.RIGHT && redBall.y == blueBall.y && redBall.x > blueBall.x) ||
                (dir == DIR.DOWN && redBall.x == blueBall.x && redBall.y > blueBall.y)
        ) {
            redBall.go(dir);
            blueBall.go(dir);
        } else {
            blueBall.go(dir);
            redBall.go(dir);
        }
    }

    // 디버깅을 위해 전체 board 출력
    public void printBoard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (redBall.x == x && redBall.y == y) System.out.print('R');
                else if (blueBall.x == x && blueBall.y == y) System.out.print('B');
                else System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    public void play(int time, DIR prevDir) {
        if (time > 10) {
            return;
        }

        if (blueBall.isOut()) {
            return;
        } else if (redBall.isOut()) {
            ret = Math.min(ret, time);
            return;
        }

        Ball prevRedBall = new Ball(redBall);
        Ball prevBlueBall = new Ball(blueBall);

        for (DIR dir : DIR.values()) {
            // 이전에 움직였던 방향의 반대 방향으로 움직일 필요는 없다
            if ((prevDir == DIR.DOWN || prevDir == DIR.UP) && (dir == DIR.DOWN || dir == DIR.UP)) continue;
            if ((prevDir == DIR.RIGHT || prevDir == DIR.LEFT) && (dir == DIR.RIGHT || dir == DIR.LEFT)) continue;
            tilt(dir);

//            System.out.println((time + 1) + "time " + dir.name());
//            printBoard();
//            System.out.println();

            play(time + 1, dir);
            redBall.restorePosition(prevRedBall);
            blueBall.restorePosition(prevBlueBall);
        }
    }

    public int minMoveToOutOnlyRedBall() {
        redBall = new Ball(initialRedBall);
        blueBall = new Ball(initialBlueBall);
        ret = INF;

        play(0, null);
        return ret == INF ? -1 : ret;
    }
}

public class BOJ_13460 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] board = new String[N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        Board B = new Board(board);
        System.out.println(B.minMoveToOutOnlyRedBall());
    }
}
