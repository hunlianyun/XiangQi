package com.yangqi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yangqi.chess.Chess;
import com.yangqi.chess.ChessFactory;
import com.yangqi.chess.ChessType;
import com.yangqi.xiangqi.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/4/1.
 * 棋盘 View
 */
public class ChessBoardView extends View {

    private static final String TAG = "ChessBoardView";

    private Paint mChessBoardPaint;
    private Paint mBoardSidePaint_1;
    private Paint mBoardSidePaint_2;
    private Paint mChessPaint;
    private Paint mClickedPoint;
    private int mClickedChessIndex;
    private int avg;
    private Path path;
    private List<Chess> chesses;
    private int[][] mIndexArray;
    private boolean isFirstClicked = true;
    private Chess.Color currentPlayer = Chess.Color.RED;

    public ChessBoardView(Context context) {
        this(context, null);
    }

    public ChessBoardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChessBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        chesses = new ArrayList<>(32);
        mIndexArray = new int[10][11];
        path = new Path();
        initPaint();
        resetList();
    }

    private void resetList() {
        chesses.clear();
        for (int i = 0; i < mIndexArray.length; i++) {
            for (int j = 0; j < mIndexArray[i].length; j++) {
                mIndexArray[i][j] = -1;
            }
        }
        // 黑区
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 1, 1, ChessType.JU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 2, 1, ChessType.MA));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 3, 1, ChessType.XIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 4, 1, ChessType.SHI));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 5, 1, ChessType.JIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 6, 1, ChessType.SHI));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 7, 1, ChessType.XIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 8, 1, ChessType.MA));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 9, 1, ChessType.JU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 2, 3, ChessType.PAO));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 8, 3, ChessType.PAO));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 1, 4, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 3, 4, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 5, 4, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 7, 4, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.BLACK, 9, 4, ChessType.ZU));


        // 红区
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 1, 10, ChessType.JU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 2, 10, ChessType.MA));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 3, 10, ChessType.XIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 4, 10, ChessType.SHI));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 5, 10, ChessType.JIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 6, 10, ChessType.SHI));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 7, 10, ChessType.XIANG));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 8, 10, ChessType.MA));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 9, 10, ChessType.JU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 2, 8, ChessType.PAO));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 8, 8, ChessType.PAO));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 1, 7, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 3, 7, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 5, 7, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 7, 7, ChessType.ZU));
        chesses.add(ChessFactory.newChess(Chess.Color.RED, 9, 7, ChessType.ZU));

        for (int i = 0; i < chesses.size(); i++) {
            int x = chesses.get(i).getLocation().x;
            int y = chesses.get(i).getLocation().y;
            mIndexArray[x][y] = i;
        }
    }

    /**
     * 初始化各个画笔
     */
    private void initPaint() {

        mChessBoardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mChessBoardPaint.setColor(Color.BLACK);
        mChessBoardPaint.setStrokeWidth(2.0f);
        mChessBoardPaint.setStyle(Paint.Style.STROKE);

        mClickedPoint = new Paint(mChessBoardPaint);
        mClickedPoint.setColor(Color.BLUE);

        mChessPaint = new Paint(mChessBoardPaint);

        mBoardSidePaint_1 = new Paint(mChessBoardPaint);
        mBoardSidePaint_1.setStrokeWidth(3.0f);

        mBoardSidePaint_2 = new Paint(mChessBoardPaint);
        mBoardSidePaint_2.setStrokeWidth(4.0f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                RectF rect = getLittleRect(x, y, 0);
                if (isFirstClicked) {
                    int index = getContainChessIndex(rect);
                    if (index != -1) {
                        if (chesses.get(index).color == currentPlayer) {
                            mClickedChessIndex = index;
                            isFirstClicked = false;
                            invalidate();
                        }
                    }
                } else {
                    Point point = getContainPoint(rect);
                    if (point != null) {
                        Log.i(TAG, "onTouchEvent: 进入判断方法");
                        if (chesses.get(mClickedChessIndex).tryMove(point.x, point.y, chesses, mIndexArray)) {
                            currentPlayer = currentPlayer == Chess.Color.RED ? Chess.Color.BLACK : Chess.Color.RED;
                        }
                    }
                    isFirstClicked = true;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private Point getContainPoint(RectF rect) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                if (rect.contains(avg * i, avg * j)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * 获取包含在 rect 中的棋子在集合中的索引
     *
     * @param rect 矩形
     * @return 返回包含的棋子索引，若没有包含返回 -1
     */
    private int getContainChessIndex(RectF rect) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                if (rect.contains(avg * i, avg * j)) {
                    for (Chess chess : chesses) {
                        if (chess.getLocation().equals(i, j)) {
                            return chesses.indexOf(chess);
                        }
                    }
                }
            }
        }
        return -1;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = widthSize / 10 * 10;
        avg = width / 10;
        int height = avg * 11;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        long start = System.currentTimeMillis();

        drawSide(canvas);
        drawCenterBoard(canvas);
        drawOtherLine(canvas);
        drawChess(canvas);

        if (!isFirstClicked) {
            drawClickedPoint(canvas);
        }

        long end = System.currentTimeMillis();
        Log.i(TAG, "onDraw: 耗时 ： " + (end - start) + " ms");
    }

    private void drawClickedPoint(Canvas canvas) {
        path.reset();
        int x = chesses.get(mClickedChessIndex).getLocation().x;
        int y = chesses.get(mClickedChessIndex).getLocation().y;
        float length = avg / 6f;

        path.moveTo(x * avg - avg / 2, y * avg - avg / 2 + length);
        path.lineTo(x * avg - avg / 2, y * avg - avg / 2);
        path.lineTo(x * avg - avg / 2 + length, y * avg - avg / 2);

        path.moveTo(x * avg + avg / 2, y * avg - avg / 2 + length);
        path.lineTo(x * avg + avg / 2, y * avg - avg / 2);
        path.lineTo(x * avg + avg / 2 - length, y * avg - avg / 2);

        path.moveTo(x * avg - avg / 2, y * avg + avg / 2 - length);
        path.lineTo(x * avg - avg / 2, y * avg + avg / 2);
        path.lineTo(x * avg - avg / 2 + length, y * avg + avg / 2);

        path.moveTo(x * avg + avg / 2, y * avg + avg / 2 - length);
        path.lineTo(x * avg + avg / 2, y * avg + avg / 2);
        path.lineTo(x * avg + avg / 2 - length, y * avg + avg / 2);

        canvas.drawPath(path, mClickedPoint);

    }

    /**
     * 画两个边框
     *
     * @param canvas 画布
     */
    private void drawSide(Canvas canvas) {
        // 画内边框
        canvas.drawRect(avg, avg, getWidth() - avg, getHeight() - avg, mBoardSidePaint_1);

        // 画外边框
        int offset = 7;
        canvas.drawRect(avg - offset, avg - offset, getWidth() - avg + offset, getHeight() - avg + offset, mBoardSidePaint_2);
    }

    /**
     * 中间部分的所有线条
     *
     * @param canvas 画布
     */
    private void drawCenterBoard(Canvas canvas) {
        // 绘制棋盘基础格子线条
        for (int i = 2; i <= 9; i++) {
            // 只绘制中间 8 条横线，即 i 从 2 到 9
            canvas.drawLine(avg, i * avg, getMeasuredWidth() - avg, i * avg, mChessBoardPaint);

            // 只绘制中间 7 条竖线，即 i 从 2 到 8
            if (i < 9) {
                // 黑区竖线
                canvas.drawLine(i * avg, avg, i * avg, 5 * avg, mChessBoardPaint);
                // 红区竖线
                canvas.drawLine(i * avg, 6 * avg, i * avg, getMeasuredHeight() - avg, mChessBoardPaint);
            }
        }
    }

    /**
     * “将区”斜线和“炮”“卒”拐角
     *
     * @param canvas 画布
     */
    private void drawOtherLine(Canvas canvas) {
        // 绘制“将区”斜线
        canvas.drawLine(4 * avg, avg, 6 * avg, 3 * avg, mChessBoardPaint);
        canvas.drawLine(6 * avg, avg, 4 * avg, 3 * avg, mChessBoardPaint);
        canvas.drawLine(4 * avg, 8 * avg, 6 * avg, 10 * avg, mChessBoardPaint);
        canvas.drawLine(6 * avg, 8 * avg, 4 * avg, 10 * avg, mChessBoardPaint);

        // 绘制“十卒”区域标志
        int i = 1;
        while (i <= 9) {
            drawSymbol(i, 4, canvas);
            drawSymbol(i, 7, canvas);
            i += 2;
        }
        // 绘制“四炮”区域标志
        drawSymbol(2, 3, canvas);
        drawSymbol(8, 3, canvas);
        drawSymbol(2, 8, canvas);
        drawSymbol(8, 8, canvas);

    }

    /**
     * 以指定点为中心画拐角
     *
     * @param x      指定点 x 坐标
     * @param y      指定点 y 坐标
     * @param canvas 画布
     */
    private void drawSymbol(int x, int y, Canvas canvas) {
        path.reset();
        float offset = avg / 12f;
        float length = offset * 2f;
        if (x != 1) {
            // 左上角拐角
            path.moveTo(x * avg - offset - length, y * avg - offset);
            path.lineTo(x * avg - offset, y * avg - offset);
            path.lineTo(x * avg - offset, y * avg - offset - length);

            // 左下角拐角
            path.moveTo(x * avg - offset - length, y * avg + offset);
            path.lineTo(x * avg - offset, y * avg + offset);
            path.lineTo(x * avg - offset, y * avg + offset + length);
        }

        if (x != 9) {
            // 右上角拐角
            path.moveTo(x * avg + offset + length, y * avg - offset);
            path.lineTo(x * avg + offset, y * avg - offset);
            path.lineTo(x * avg + offset, y * avg - offset - length);

            // 右下角拐角
            path.moveTo(x * avg + offset + length, y * avg + offset);
            path.lineTo(x * avg + offset, y * avg + offset);
            path.lineTo(x * avg + offset, y * avg + offset + length);
        }

        canvas.drawPath(path, mChessBoardPaint);
    }

    /**
     * 画棋子
     *
     * @param canvas 画布
     */
    private void drawChess(Canvas canvas) {
        Bitmap bitmap;
        RectF dst;
        for (Chess chess : chesses) {
            dst = getLittleRect(chess.getLocation().x * avg, chess.getLocation().y * avg, 3);
            switch (chess.getType()) {
                case ChessType.JU:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_che);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_che);
                    }
                    break;
                case ChessType.MA:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_ma);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_ma);
                    }
                    break;
                case ChessType.XIANG:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_xiang);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_xiang);
                    }
                    break;
                case ChessType.SHI:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_shi);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_shi);
                    }
                    break;
                case ChessType.JIANG:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_jiang);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_jiang);
                    }
                    break;
                case ChessType.PAO:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_pao);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_pao);
                    }
                    break;
                case ChessType.ZU:
                    if (chess.color == Chess.Color.RED) {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_zu);
                    } else {
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.black_zu);
                    }
                    break;
                default:
                    bitmap = null;
                    break;
            }
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, null, dst, mChessPaint);
            }
        }
    }

    /**
     * 获取以指定点为中心，边长为 avg 的小矩形
     * @param x x 坐标
     * @param y y 坐标
     * @param offset 边长偏移量
     * @return 得到的矩形
     */
    private RectF getLittleRect(float x, float y, int offset) {
        float left = x - avg / 2 + offset;
        float top = y - avg / 2 + offset;
        float right = x + avg / 2 - offset;
        float bottom = y + avg / 2 - offset;
        return new RectF(left, top, right, bottom);
    }
}
