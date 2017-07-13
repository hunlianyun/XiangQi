package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 * 抽象棋子类
 */

public abstract class Chess {
    public enum Color {RED, BLACK}

    private Point location;

    public Color color;

    Chess(Color color, int x, int y) {
        this.color = color;
        location = new Point(x, y);
    }

    /**
     * 尝试移动棋子到指定点
     * @param toX 目标位置 x 坐标
     * @param toY 目标位置 y 坐标
     * @param chesses 棋子集合
     * @return 能够移动返回 true， 无法移动返回 false
     */
    public abstract boolean tryMove(int toX, int toY, List<Chess> chesses, int[][] indexArray);

    public abstract int getType();

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
