package com.yangqi.chess;

/**
 * Created by Administrator on 2017/4/2.
 * 棋子工厂类
 */

public class ChessFactory {
    public static Chess newChess(Chess.Color color,int x, int y, int chess) {
        switch (chess) {
            case ChessType.JU:
                return new Ju(color, x, y);
            case ChessType.MA:
                return new Ma(color, x, y);
            case ChessType.XIANG:
                return new Xiang(color, x, y);
            case ChessType.SHI:
                return new Shi(color, x, y);
            case ChessType.JIANG:
                return new Jiang(color, x, y);
            case ChessType.PAO:
                return new Pao(color, x, y);
            case ChessType.ZU:
                return new Zu(color, x, y);
        }
        return null;
    }
}
