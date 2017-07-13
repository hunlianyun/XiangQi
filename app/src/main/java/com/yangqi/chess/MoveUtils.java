package com.yangqi.chess;

import android.graphics.Point;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 * 移动棋子工具类
 */

class MoveUtils {
    static boolean move(int fromX, int fromY, int toX, int toY, List<Chess> chesses, int[][] indexArray) {
        Chess chess = chesses.get(indexArray[fromX][fromY]);
        if (indexArray[toX][toY] == -1) {
            chess.setLocation(new Point(toX, toY));
            indexArray[toX][toY] = indexArray[fromX][fromY];
            indexArray[fromX][fromY] = -1;
            return true;
        }
        if (chesses.get(indexArray[toX][toY]).color != chess.color) {
            chess.setLocation(new Point(toX, toY));
            chesses.remove(indexArray[toX][toY]);
            indexArray[fromX][fromY] = -1;
            for (int i = 0; i < chesses.size(); i++) {
                int x = chesses.get(i).getLocation().x;
                int y = chesses.get(i).getLocation().y;
                indexArray[x][y] = i;
            }
            return true;
        }
        return false;
    }
}
