package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “象”
 */

class Xiang extends Chess {

    Xiang(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray) {

        if (this.color == Color.BLACK) {
            if (toY > 5) {
                return false;
            }
        }

        if (this.color == Color.RED) {
            if (toY < 6) {
                return false;
            }
        }

        Point location = getLocation();
        int fromX = location.x;
        int fromY = location.y;

        if (toX - fromX == 2 && toY - fromY == 2) {
            if (indexArray[fromX + 1][fromY + 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == 2 && toY - fromY == -2) {
            if (indexArray[fromX + 1][fromY - 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == -2 && toY - fromY == 2) {
            if (indexArray[fromX - 1][fromY + 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (toX - fromX == -2 && toY - fromY == -2) {
            if (indexArray[fromX - 1][fromY - 1] == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        return false;
    }

    @Override
    public int getType() {
        return ChessType.XIANG;
    }

}
