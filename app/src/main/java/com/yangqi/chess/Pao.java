package com.yangqi.chess;

import android.graphics.Point;
import android.util.Log;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “炮”
 */

class Pao extends Chess {

    Pao(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray) {

        Point location = getLocation();
        int fromX = location.x;
        int fromY = location.y;

        if (fromX == toX && fromY == toY) {
            Log.i(TAG, "tryMove: 起点终点位置一样");
            return false;
        }

        if (fromX != toX && fromY != toY) {
            Log.i(TAG, "tryMove: 不能斜着走");
            return false;
        }

        int count = 0;

        if (fromX == toX) {
            if (fromY > toY) {
                for (int i = toY; i < fromY; i++) {
                    if (indexArray[fromX][i] != -1) {
                        count++;
                    }
                }
            } else if (fromY < toY) {
                for (int i = fromY + 1; i <= toY; i++) {
                    if (indexArray[fromX][i] != -1) {
                        count++;
                    }
                }
            }
        }

        if (fromY == toY) {
            if (fromX > toX) {
                for (int i = toX; i < fromX; i++) {
                    if (indexArray[i][fromY] != -1) {
                        count++;
                        Log.i(TAG, "tryMove: (x, y) = " + "(" + i + ", " + fromY + ")");
                    }
                }
            } else if (fromX < toX) {
                for (int i = fromX + 1; i <= toX; i++) {
                    if (indexArray[i][fromY] != -1) {
                        count++;
                        Log.i(TAG, "tryMove: (x, y) = " + "(" + i + ", " + fromY + ")");
                    }
                }
            }
        }

        Log.i(TAG, "tryMove: count = " + count);

        if (count == 0) {
            setLocation(new Point(toX, toY));
            indexArray[toX][toY] = indexArray[fromX][fromY];
            indexArray[fromX][fromY] = -1;
            return true;
        }

        if (count == 2) {
            if (indexArray[toX][toY] == -1) {
                return false;
            }
            if (chesses.get(indexArray[toX][toY]).color != this.color) {
                setLocation(new Point(toX, toY));
                chesses.remove(indexArray[toX][toY]);
                indexArray[fromX][fromY] = -1;
                for (int i = 0; i < chesses.size(); i++) {
                    int x = chesses.get(i).getLocation().x;
                    int y = chesses.get(i).getLocation().y;
                    indexArray[x][y] = i;
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public int getType() {
        return ChessType.PAO;
    }

}
