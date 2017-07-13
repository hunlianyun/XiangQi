package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子 “将”
 */

class Jiang extends Chess {

    Jiang(Color color, int x, int y) {
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

        boolean b1 = Math.abs(fromX - toX) == 1 && fromY == toY;
        boolean b2 = Math.abs(fromY - toY) == 1 && fromX == toX;
        return (b1 || b2) && MoveUtils.move(fromX, fromY, toX, toY, chesses, indexArray);
    }

    @Override
    public int getType() {
        return ChessType.JIANG;
    }

}
