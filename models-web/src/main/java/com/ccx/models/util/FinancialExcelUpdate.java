package com.ccx.models.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class FinancialExcelUpdate {
	
	private static Logger logger = LogManager.getLogger(FinancialExcelUpdate.class);
	 /**
	  * 
	  * @描述：更新Excel
	  * @创建时间：  2017年9月22日
	  * @作者：武向楠
	  * @参数： @param filePath:文件路径
	  * @参数： @param updateValue:要修改的数据
	  * @参数： @param updateSheetIndex:要修改第几个sheet页
	  * @参数： @param startCellIndex:开始更新的行数下标
	  * @参数： @param endCellIndex:结束更新的行数下标
	  * @参数： @param startRowIndex:开始更新的列数下标
	  * @参数： @param endRowIndex:结束更新的列数下标    
	  * @return void     
	  * @throws
	  */
    public static boolean updateExcel(String filePath,String updateValue,int updateSheetIndex,int startCellIndex,int endCellIndex,int startRowIndex,int endRowIndex){
    	boolean resultFlag = false;
    	try { 
        	File file = new File(filePath);
            //建立数据的输入管道
            FileInputStream fileInputStream = new FileInputStream(file);
            //初始化一个工作簿
            Workbook wb = new HSSFWorkbook(fileInputStream);
            //获得第二个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			Sheet sheet = wb.getSheetAt(updateSheetIndex-1);
			//循环行与列 从第startCellIndex行到第endCellIndex行
	    	for(int i=startCellIndex-1;i<endCellIndex;i++){
		    	Row row = sheet.getRow(i);//获取指定行
		    	if(UsedUtil.isNotNull(row)){
		    		//循环列，只需要赋值 几列 即可 从第startRowIndex列到第endRowIndex列
		    		for (int j = startRowIndex-1; j < endRowIndex; j++) {
		    			//需要赋值的列
		    			Cell cell1 = row.getCell(j);
		    			//更改excel单元格的值
		    			cell1.setCellValue(Double.parseDouble(updateValue));
		    			System.err.println("第"+(i+1)+"行第"+(j+1)+"列单元格值被更新为-----"+Double.parseDouble(updateValue));
		    		}
				}
	    	}
	    	//强制刷新sheet中的公式 如果没有这一步公式不会生效
	    	for (int i = 0; i < wb.getNumberOfSheets(); i++) {
	    		//获得工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
				Sheet sheett = wb.getSheetAt(i);
				sheett.setForceFormulaRecalculation(true);
			}
	    	fileInputStream.close();//关闭文件输入流
	        FileOutputStream fos=new FileOutputStream(file);
	        wb.write(fos);
	        fos.close();//关闭文件输出流
	        resultFlag = true;
    	}catch (Exception e) {
    		logger.info("更新财务模板失败，失败原因======》》》》》》"+e);
    		resultFlag = false;
    		return resultFlag;
		}
    	return resultFlag;
    }


    public static void main(String[] args) throws Exception{
    	//xls文件进行测试，2003格式的，非xlsx
        String path = "D://受评企业财务分析模板20170914.xls";
        String updateValue = "100";//要修改的数据
        int updateSheetIndex = 2;//要修改第几个sheet页
        int startCellIndex = 6;//开始更新的行数下标
        int endCellIndex = 6;//结束更新的行数下标
        int startRowIndex = 2;//开始更新的列数下标
        int endRowIndex = 2;//结束更新的列数下标
        //path:路径  
        //updateValue:要修改的数据
        //updateSheetIndex:要修改第几个sheet页
        //startCellIndex:开始更新的行数下标
        //endCellIndex:结束更新的行数下标
        //startRowIndex:开始更新的列数下标
        //endRowIndex:结束更新的列数下标
        updateExcel(path,updateValue,updateSheetIndex,startCellIndex,endCellIndex,startRowIndex,endRowIndex);
    }
	
}
