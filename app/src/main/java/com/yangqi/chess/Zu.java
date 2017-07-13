package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “卒”
 */
class Zu extends Chess {

    Zu(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray) {

        Point location = getLocation();
        int fromX = location.x;
        int fromY = location.y;

        if (this.color == Color.BLACK && fromY < 6) {
            if (fromX == toX && toY - fromY == 1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (this.color == Color.BLACK && fromY >= 6) {
            if (fromX == toX && toY - fromY == 1 || fromY == toY && Math.abs(toX - fromX) == 1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (this.color == Color.RED && fromY >= 6) {
            if (fromX == toX && toY - fromY == -1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        if (this.color == Color.RED && fromY < 6) {
            if (fromX == toX && toY - fromY == -1 || fromY == toY && Math.abs(toX - fromX) == 1) {
                return MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
            }
        }
        return false;
    }

    @Override
    public int getType() {
        return ChessType.ZU;
    }
}
