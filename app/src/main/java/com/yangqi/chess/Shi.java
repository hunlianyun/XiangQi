package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “士”
 */

class Shi extends Chess {

    Shi(Color color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray) {

        if (toX < 4 || toX > 6) {
            return false;
        }

        if (this.color == Color.BLACK) {
            if (toY > 3) {
                return false;
            }
        }

        if (this.color == Color.RED) {
            if (toY < 8) {
                return false;
            }
        }

        Point location = getLocation();
        int fromX = location.x;
        int fromY = location.y;

        boolean b1 = Math.abs(toX - fromX) == 1 && Math.abs(toY - fromY) == 1;
        return b1 && MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
    }

    @Override
    public int getType() {
        return ChessType.SHI;
    }

}
