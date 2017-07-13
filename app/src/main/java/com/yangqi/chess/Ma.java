package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “马”
 */

class Ma extends Chess {

    Ma(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray) {

        Point location = getLocation();
        int fromX = location.x;
        int fromY = location.y;

        if (toX - fromX == 1 && toY - fromY == 2 || toX - fromX == -1 && toY - fromY == 2) {
            if (indexArray[fromX][fromY + 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == -1 && toY - fromY == -2 || toX - fromX == 1 && toY - fromY == -2) {
            if (indexArray[fromX][fromY - 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == 2 && toY - fromY == 1 || toX - fromX == 2 && toY - fromY == -1) {
            if (indexArray[fromX + 1][fromY] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == -2 && toY - fromY == -1 || toX - fromX == -2 && toY - fromY == 1) {
            if (indexArray[fromX - 1][fromY] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        return false;
    }

    @Override
    public int getType() {
        return ChessType.MA;
    }

}
