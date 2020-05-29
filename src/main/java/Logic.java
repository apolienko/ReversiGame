import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Logic {

    final private static int[][] board = new int[8][8];

    private static boolean moveFlag;

    private static Set<Pair<Integer, Integer>> placeablePositions = new HashSet<>();

    private static List<Pair<Integer, Integer>> repaintCell = new ArrayList<>();

    // Начало игры
    public void initialize() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (i == 3 && j == 3)
                    board[i][j] = 1;
                else if (i == 4 && j == 4)
                    board[i][j] = 1;
                else if (i == 3 && j == 4)
                    board[i][j] = 2;
                else if (i == 4 && j == 3)
                    board[i][j] = 2;
                else board[i][j] = 0;
            }

        moveFlag = true;

    }

    public boolean findCorrectLine(int i, int j, boolean move) {
        repaintCell.clear();

        if (move) repaintCell.add(new Pair<>(i, j));

        byte currentPlayer = moveFlag ? (byte) 1 : 2;

        // Справа от (i, j)
        if (j != 7) {
            int newJ = j;
            int count = 1;
            while (newJ < 7) {
                newJ++;
                if (board[i][newJ] == 0) break;
                if (board[i][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move)
                            for (int m = j; m < newJ; m++)
                                board[i][m] = currentPlayer;

                        for (int m = j + 1; m < newJ; m++)
                            repaintCell.add(new Pair<>(i, m));
                    }

                    break;
                }
                count++;
            }
        }

        // Слева от (i, j)
        if (j != 0) {
            int newJ = j;
            int count = 1;
            while (newJ > 0) {
                newJ--;
                if (board[i][newJ] == 0) break;
                if (board[i][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move)
                            for (int m = j; m > newJ; m--)
                                board[i][m] = currentPlayer;

                        for (int m = j - 1; m > newJ; m--)
                            repaintCell.add(new Pair<>(i, m));
                    }

                    break;
                }
                count++;
            }
        }

        // Снизу от (i, j)
        if (i != 7) {
            int newI = i;
            int count = 1;
            while (newI < 7) {
                newI++;
                if (board[newI][j] == 0) break;
                if (board[newI][j] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move)
                            for (int k = i; k < newI; k++)
                                board[k][j] = currentPlayer;

                        for (int k = i + 1; k < newI; k++)
                            repaintCell.add(new Pair<>(k, j));
                    }

                    break;
                }
                count++;
            }
        }

        // Сверху от (i, j)
        if (i != 0) {
            int newI = i;
            int count = 1;
            while (newI > 0) {
                newI--;
                if (board[newI][j] == 0) break;
                if (board[newI][j] == currentPlayer){
                    if (count != 1) {
                        if (!move) return true;

                        if (move)
                            for (int k = i; k > newI; k--)
                                board[k][j] = currentPlayer;

                        for (int k = i - 1; k > newI; k--)
                            repaintCell.add(new Pair<>(k, j));
                    }

                    break;
                }
                count++;
            }
        }

        // Нижняя правая диагональ от (i, j)
        if (i != 7 && j != 7) {
            int newI = i;
            int newJ = j;
            int count = 1;
            while (newI < 7 && newJ < 7) {
                newI++;
                newJ++;
                if (board[newI][newJ] == 0) break;
                if (board[newI][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move) {
                            int k = i;
                            int m = j;
                            while(k < newI && m < newJ) {
                                board[k][m] = currentPlayer;
                                k++;
                                m++;
                            }
                        }

                        int k = i + 1;
                        int m = j + 1;
                        while(k < newI && m < newJ) {
                            repaintCell.add(new Pair<>(k, m));
                            k++;
                            m++;
                        }
                    }

                    break;
                }
                count++;
            }
        }

        // Нижняя левая диагональ от (i, j)
        if (i != 7 && j != 0) {
            int newI = i;
            int newJ = j;
            int count = 1;
            while (newI < 7 && newJ > 0) {
                newI++;
                newJ--;
                if (board[newI][newJ] == 0) break;
                if (board[newI][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move) {
                            int k = i;
                            int m = j;
                            while(k < newI && m > newJ) {
                                board[k][m] = currentPlayer;
                                k++;
                                m--;
                            }
                        }

                        int k = i + 1;
                        int m = j - 1;
                        while(k < newI && m > newJ) {
                            repaintCell.add(new Pair<>(k, m));
                            k++;
                            m--;
                        }
                    }

                    break;
                }
                count++;
            }
        }

        // Левая верхняя диагональ от (i, j)
        if (i != 0 && j != 0) {
            int newI = i;
            int newJ = j;
            int count = 1;
            while (newI > 0 && newJ > 0) {
                newI--;
                newJ--;
                if (board[newI][newJ] == 0) break;
                if (board[newI][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;

                        if (move) {
                            int k = i;
                            int m = j;
                            while(k > newI && m > newJ) {
                                board[k][m] = currentPlayer;
                                k--;
                                m--;
                            }
                        }

                        int k = i - 1;
                        int m = j - 1;
                        while(k > newI && m > newJ) {
                            repaintCell.add(new Pair<>(k, m));
                            k--;
                            m--;
                        }
                    }

                    break;
                }
                count++;
            }
        }

        // Правая верхняя диагональ от (i, j)
        if (i != 0 && j != 7) {
            int newI = i;
            int newJ = j;
            int count = 1;
            while (newI > 0 && newJ < 7) {
                newI--;
                newJ++;
                if (board[newI][newJ] == 0) break;
                if (board[newI][newJ] == currentPlayer) {
                    if (count != 1) {
                        if (!move) return true;


                        if (move) {
                            int k = i;
                            int m = j;
                            while(k > newI && m < newJ) {
                                board[k][m] = currentPlayer;
                                k--;
                                m++;
                            }
                        }

                        int k = i - 1;
                        int m = j + 1;
                        while(k > newI && m < newJ) {
                            repaintCell.add(new Pair<>(k, m));
                            k--;
                            m++;
                        }
                    }

                    break;
                }
                count++;
            }
        }

        if (move) {
            moveFlag = !moveFlag;
        }

        return false;
    }

}
