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
                if(this == redBall) {
                    if (nextY == blueBall.y && nextX == blueBall.x) break;
                }
                if(this == blueBall) {
                    if (nextY == redBall.y && nextX == redBall.x) break;
                }
                if(nextItem == '#') break;

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
                    case 'R' :
                        initialRedBall = new Ball(x, y);
                        this.board[y][x] = '.';
                        break;
                    case 'O' :
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
        switch(dir.ordinal()) {
            case 0: // LEFT, x값 감소
                if (redBall.y == blueBall.y && redBall.x < blueBall.x) {
                    redBall.go(DIR.LEFT);
                    blueBall.go(DIR.LEFT);
                } else {
                    blueBall.go(DIR.LEFT);
                    redBall.go(DIR.LEFT);
                }
                break;
            case 1: // UP, y값 감소
                if (redBall.x == blueBall.x && redBall.y < blueBall.y) {
                    redBall.go(DIR.UP);
                    blueBall.go(DIR.UP);
                } else {
                    blueBall.go(DIR.UP);
                    redBall.go(DIR.UP);
                }
                break;
            case 2: // RIGHT, x값 증가
                if (redBall.y == blueBall.y && redBall.x > blueBall.x) {
                    redBall.go(DIR.RIGHT);
                    blueBall.go(DIR.RIGHT);
                } else {
                    blueBall.go(DIR.RIGHT);
                    redBall.go(DIR.RIGHT);
                }
                break;
            case 3: // DOWN, y값 증가
                if (redBall.x == blueBall.x && redBall.y > blueBall.y) {
                    redBall.go(DIR.DOWN);
                    blueBall.go(DIR.DOWN);
                } else {
                    blueBall.go(DIR.DOWN);
                    redBall.go(DIR.DOWN);
                }
                break;
            default:
                break;
        }
    }

    // 디버깅을 위해 전체 board 출력
    public void printBoard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if(redBall.x == x && redBall.y == y) System.out.print('R');
                else if(blueBall.x == x && blueBall.y == y) System.out.print('B');
                else System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    public void play(int time) {
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

        for(DIR dir : DIR.values()) {
            tilt(dir);

//            System.out.println((time + 1) + "time " + dir.name());
//            printBoard();
//            System.out.println();

            play(time + 1);
            redBall.restorePosition(prevRedBall);
            blueBall.restorePosition(prevBlueBall);
        }
    }

    public int minMoveToOutOnlyRedBall() {
        redBall = new Ball(initialRedBall);
        blueBall = new Ball(initialBlueBall);
        ret = INF;

        play(0);
        return ret == INF ? -1 : ret;
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
