package com.ccx.models.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 常用小工具
* @ClassName: UsedUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author wuXiangNan  
* @date 2017年3月22日 下午5:54:47 
*
 */
public class UsedUtil {
	
	private static DecimalFormat df = new DecimalFormat("#.00");

	// 判空
	public static boolean isNotNull(Object str) {
		return (null != str && !"".equals(str.toString().trim()));
	}

	// 格式化小数（保留两位小数）
	public static Double formatDecimal(Double formatValue) {
		if (isNotNull(formatValue))
			return Double.valueOf(df.format(formatValue));
		return 0.00;
	}

	// 转字符串
	public static String objct2str(Object value) {
		if (isNotNull(value))
			return value.toString().trim();
		return "";
	}

	/**
	 * 转码
	 * @param ori
	 * @return
	 */
	public static String convertUnicode(String ori) {
		char aChar;
		int len = ori.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = ori.charAt(x++);
			if (aChar == '\\') {
				aChar = ori.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = ori.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);

		}
		return outBuffer.toString();

	}
	
	 /**
	  * 汉字转码
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str){  
	        String result="";  
	        for (int i = 0; i < str.length(); i++){  
	            int chr1 = (char) str.charAt(i);  
	            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)  
	                result+="\\u" + Integer.toHexString(chr1);  
	            }else{  
	                result+=str.charAt(i);  
	            }  
	        }  
	        return result;  
	 }
	/**
	  * string类型转换为double类型
	 * @param str
	 * @return
	 */
	public static double setStringToDouble(String _str) {  
       String str=trim(_str);  
       double _long = 0.00;  
       if ("".equals(str) || "0".equals(str) || "0.0".equals(str) || "0.00".equals(str)) {  
           _long = 0;  
       } else {  
           double _d = Double.parseDouble(str);  
           _long = mul(_d);  
       }  
       //System.out.println(_long);  
       return _long;  
    }  
	 public static String trim(String str) {  
	        return ((str == null) ? "" : str.trim());  
	 } 
	
	 public static double mul(double v1) {  
       BigDecimal b1 = new BigDecimal(Double.toString(v1));  
      // BigDecimal b2 = new BigDecimal(Double.toString(v2));  
       return b1.doubleValue();  
	 }  
	 
	 /**
		 * 校验String参数
		 * 
		 * @param params
		 * @return
		 */
		public static boolean verifyParams(String... params) {
			List<String> list = Arrays.asList(params);
			for (String string : list) {
				if (string == null || string.isEmpty()) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * 获取随机数
		 * @param max	随机数最大值，如：999999
		 * @param min	随机数最小值，如：100000
		 * @return
		 */
		public static String calRandomScore(int max, int min) {
			Random random = new Random();
			Integer randomSum = random.nextInt(max) % (max - min + 1) + min;
			return randomSum+"";
		}
}
