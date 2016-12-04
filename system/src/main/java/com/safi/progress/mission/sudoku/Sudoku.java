package com.safi.progress.mission.sudoku;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by safi on 16/12/3.
 *
 * @version 1.0
 */
public class Sudoku {
    private static final int ROWS = 9, COLS = 9;

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("system/src/main/resources/sudoku.txt");
        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        List<String> dataList = lines.collect(Collectors.toList());

        //初始化数据
        int[][] sudoku = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            String tempData = dataList.get(i);
            for (int j = 0; j < COLS; j++) {
                sudoku[i][j] = tempData.charAt(j) - 48;
            }
        }

        //递归遍历每一种可能的数字
        Sudoku service = new Sudoku();
        service.printAll(sudoku);
        //先找到入口
        Map enter = service.getEnter(sudoku);
        int row = (int) enter.get("row"), col = (int) enter.get("col");
        //从入口开始填值
        List<Integer> avails = service.getAvailableList(sudoku, row, col);
        avails.stream().forEach(avail -> service.setNum(service.cloneSudoku(sudoku), row, col, avail));
    }

    public void setNum(int[][] sudoku, int row, int col, int num) {
        int[][] data = cloneSudoku(sudoku);
        data[row][col] = num;

        //找到下一个入口
        Map enter = getEnter(data);
        int rowT = (int) enter.get("row"), colT = (int) enter.get("col");
        if (rowT == 0 && colT == 0) {//全部填完
            printAll(data);
            System.exit(1);
        }
        List<Integer> avails = getAvailableList(data, rowT, colT);
        avails.stream().forEach(avail -> setNum(cloneSudoku(data), rowT, colT, avail));
    }

    public int[][] cloneSudoku(int[][] data) {
        int[][] sudoku = new int[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) sudoku[i] = data[i].clone();
        return sudoku;
    }

    public void printAll(int[][] data) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    public Map getEnter(int[][] data) {
        int row = 0, col = 0;
        if (data[0][0] != 0) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (data[i][j] == 0) {
                        row = i;
                        col = j;
                        break;
                    }
                }
                if (row != 0 || col != 0) break;
            }
        }

        Map result = new HashMap();
        result.put("row", row);
        result.put("col", col);
        return result;
    }

    public List<Integer> getAvailableList(int[][] data, int row, int col) {// 获取所有可用的数字
        List<Integer> nums = new ArrayList<>();
        // 横/纵排已经有的数字
        for (int i = 0; i < COLS; i++) {
            int tempRow = data[row][i];
            if (tempRow != 0 && !nums.contains(tempRow)) nums.add(tempRow);
            int tempCol = data[i][col];
            if (tempCol != 0 && !nums.contains(tempCol)) nums.add(tempCol);
        }
        //九宫格中已经有的数字
        row = row / 3 * 3;
        col = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = data[row + i][col + j];
                if (temp != 0 && !nums.contains(temp))
                    nums.add(temp);
            }
        }

        //筛选出未出现的数字
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!nums.contains(i)) result.add(i);
        }
        return result;
    }
}
