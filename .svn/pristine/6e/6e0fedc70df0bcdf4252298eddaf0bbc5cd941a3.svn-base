package com.ccx.models.util;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @Description: opencsv 操作类
 * @author:lilong
 * @Date: 2017/11/21
 */
public class CSVUtil {

    /**
     * csv文件读取
     * @param filePath
     * @return
     */
    public static List<String[]> read(String filePath){
        File file = new File(filePath);
        FileReader fReader = null;
        CSVReader csvReader=null;
        try {
            fReader = new FileReader(file);
            csvReader = new CSVReader(fReader);
           return  csvReader.readAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(csvReader!=null) csvReader.close();
                if(fReader!=null) fReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        List<String[]> list=read("E:\\机器学习平台\\plotks.csv");
        System.out.println(list.size());
    }
}
