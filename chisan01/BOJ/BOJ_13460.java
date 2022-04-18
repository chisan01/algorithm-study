package BOJ;

// 백준 골드1 구슬 탈출 2

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

        public boolean isOut() {
            return this.x == holeX && this.y == holeY;
        }

        // '.'이 아닌 블록을 만나기 전까지 dir 방향으로 이동
        public void go(DIR dir) {
            while (0 < this.y && this.y < board.length - 1 && 0 < this.x && this.x < board[0].length - 1) {
                int nextY = this.y + dy[dir.ordinal()];
                int nextX = this.x + dx[dir.ordinal()];
                char nextItem = board[nextY][nextX];

                // 구멍에 도착한 경우 구멍으로 이동
                if (nextItem == 'O') {
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

    class Balls {
        public Ball redBall;
        public Ball blueBall;
        public int time;

        public Balls(Ball redBall, Ball blueBall, int time) {
            this.redBall = new Ball(redBall);
            this.blueBall = new Ball(blueBall);
            this.time = time;
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

    // 이전에 움직였던 방향의 반대 방향으로 움직일 필요는 없다
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

    public int minMoveToOutOnlyRedBall() {
        Queue<Balls> Q = new LinkedList<>();
        Q.add(new Balls(initialRedBall, initialBlueBall, 0));
        while (!Q.isEmpty()) {
            int curTime = Q.peek().time;
            if (curTime > 10) break;
            redBall = Q.peek().redBall;
            blueBall = Q.peek().blueBall;
            Q.poll();

            if (blueBall.isOut()) continue;
            else if (redBall.isOut()) {
                return curTime;
            }

            Ball prevRedBall = new Ball(redBall);
            Ball prevBlueBall = new Ball(blueBall);

            for (DIR dir : DIR.values()) {
                tilt(dir);

//                System.out.println((curTime + 1) + "time " + dir.name());
//                printBoard();
//                System.out.println();

                Q.add(new Balls(redBall, blueBall, curTime + 1));
                redBall.restorePosition(prevRedBall);
                blueBall.restorePosition(prevBlueBall);
            }
        }
        return -1;
    }
}

public class BOJ_13460 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        String[] board = new String[N];
        for (int i = 0; i < N; i++) {
            board[i] = sc.next();
        }

        Board B = new Board(board);
        System.out.println(B.minMoveToOutOnlyRedBall());
    }
}
