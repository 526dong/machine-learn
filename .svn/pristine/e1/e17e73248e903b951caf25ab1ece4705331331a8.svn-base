package com.ccx.models.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/12/19
 */
public class NioReadUtil {

    public static void main(String[] args) throws Exception {
          read("E:\\apache-tomcat-7_2\\logs\\ccx-risk\\risk.log",0l,"UTF-8",(line)->{
              System.out.println(line);
           return null;
          });
//        List<String> list=read("E:\\apache-tomcat-7_2\\logs\\ccx-risk\\risk.log",0,"UTF-8");
//        System.out.println(list.size());
      //  list.stream().forEach(r-> System.out.println(r));
    }

    /**
     * nio按照位置读取读取文件
     * @param path
     * @param beginIndex
     * @return
     * @throws Exception
     */
    public static List<String> read(String path,Long  beginIndex,String encode) throws Exception {
        List<String> list = null;
        int bufSize = 100000;//一次读取的字节长度
        FileChannel fcin = new RandomAccessFile(path, "r").getChannel();
        fcin.position(beginIndex==null?0:beginIndex);
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        list= readFileByLine( encode,fcin, rBuffer);
        if(fcin.isOpen()){
            fcin.close();
        }
        return list;
    }

    public static List<String> readFileByLine( String encode,FileChannel fcin,
                                      ByteBuffer rBuffer) {
        List<String> dataList = new ArrayList<>();//存储读取的每行数据
        byte[] lineByte = new byte[0];
       // String encode = "GBK";
        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];
           // while (fcin.read(rBuffer) != -1||(sleep(1000)||fcin.read(rBuffer) != -1)) {//fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
            while (fcin.read(rBuffer) != -1) {//fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
               doit(encode,rBuffer,temp,lineByte,(t)->dataList.add(t));
            }
            if(temp != null && temp.length > 0){//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, encode);
                dataList.add(line);
             System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }
    /**
     * nio按照位置读取读取文件
     * @param path
     * @param beginIndex
     * @return
     * @throws Exception
     */
    public static Long read(String path,Long  beginIndex,String encode,Function<String,Object> fuc) throws Exception {
       Long size=0l;
        int bufSize = 100000;//一次读取的字节长度
        FileChannel fcin = new RandomAccessFile(path, "r").getChannel();
        fcin.position(beginIndex==null?(fcin.size()-1000<0?0:fcin.size()-1000):beginIndex);
        ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
        size=readFileByLine( fcin, rBuffer,encode,fuc);
        System.out.println("读完。。。。。");
        if(fcin.isOpen()){
            fcin.close();
        }
        return size;
    }

    public static Long readFileByLine( FileChannel fcin,
                                      ByteBuffer rBuffer,String encode,Function<String,Object> fuc) {
        byte[] lineByte = new byte[0];
        try {
            //temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
            //并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
            byte[] temp = new byte[0];
            while (fcin.read(rBuffer) != -1||(sleep(1000)&&fcin.read(rBuffer) != -1)) {//fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
               doit(encode,rBuffer,temp,lineByte,fuc);
            }
            if(temp != null && temp.length > 0){//兼容文件最后一行没有换行的情况
                String line = new String(temp, 0, temp.length, encode);
                fuc.apply(line);
            }
            return fcin.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return 0l;
    }

    //数据处理
    private static void doit(String encode, ByteBuffer rBuffer, byte[] temp, byte[] lineByte, Function<String,Object> f) throws UnsupportedEncodingException {
        int rSize = rBuffer.position();//读取结束后的位置，相当于读取的长度
        byte[] bs = new byte[rSize];//用来存放读取的内容的数组
        rBuffer.rewind();//将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
        rBuffer.get(bs);//相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
        rBuffer.clear();
//        String s=new String(bs,0,bs.length,encode);
//        f.apply(s);
        int startNum = 0;
        int LF = 10;//换行符
        int CR = 13;//回车符
        boolean hasLF = false;//是否有换行符
        for(int i = 0; i < rSize; i++){
            if(bs[i] == LF){
                hasLF = true;
                int tempNum = temp.length;
                int lineNum = i - startNum;
                lineByte = new byte[tempNum + lineNum];//数组大小已经去掉换行符

                System.arraycopy(temp, 0, lineByte, 0, tempNum);//填充了lineByte[0]~lineByte[tempNum-1]
                temp = new byte[0];
                System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);//填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

                String line = new String(lineByte, 0, lineByte.length, encode);//一行完整的字符串(过滤了换行和回车)
                f.apply(line);
                //过滤回车符和换行符
                if(i + 1 < rSize && bs[i + 1] == CR){
                    startNum = i + 2;
                }else{
                    startNum = i + 1;
                }
            }
        }
        if(hasLF){
            temp = new byte[bs.length - startNum];
            System.arraycopy(bs, startNum, temp, 0, temp.length);
        }else{//兼容单次读取的内容不足一行的情况
            byte[] toTemp = new byte[temp.length + bs.length];
            System.arraycopy(temp, 0, toTemp, 0, temp.length);
            System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
            temp = toTemp;
        }
    }
    /**
     * 睡眠
     * @param time
     * @return
     */
    public static boolean sleep(long time){
        try {
            //System.out.println("睡眠。。。");
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
