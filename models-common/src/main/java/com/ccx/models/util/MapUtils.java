package com.ccx.models.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:map操纵方法
 * @author:lilong
 * @Date: 2017/11/24
 */
public class MapUtils {
    /**
     * 将map中对应的值，转移到对应的实体类中，无法对应的不予处理
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T changeMapToBean(Map<String,Object> map,Class<T> clazz){
        T t=null;
        try {
            t = clazz.newInstance();
            Field[] fs=clazz.getDeclaredFields();
            changeMap(map);
            for(Field field:fs){
                Class type=field.getType();
                if(map.get(field.getName())!=null){
                    String methodName=getSetMethod(field.getName());
                    Method method = clazz.getDeclaredMethod(methodName,type);
                    method.invoke(t,changeType(map.get(field.getName()),type));
                    //field.set(t,changeType(map.get(field.getName()),type));
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }
    //通过私有属性命名过去set方法名称
    private static String getSetMethod(String pname){
        if(pname!=null&&!"".equals(pname.trim())){
            String st[] =pname.split("_");
            String name=st[0];
            StringBuilder bd=new StringBuilder("set");
            if((name.charAt(0)>'A'&&name.charAt(0)<'Z')||(name.length()>1&&name.charAt(1)>'A'&&name.charAt(1)<'Z')){
              bd.append(name);
            }else {
                bd.append(name.substring(0,1).toUpperCase()).append(name.substring(1));
            }
            for (int i = 1; i < st.length; i++) {
                if(!"".equals(st[i])){
                    bd.append(st[i].substring(0,1).toUpperCase()).append(st[i].substring(1));
                }
            }
            return bd.toString();
        }
        return null;
    }

    /**
     * 数据转换
     * @param o
     * @param clas
     * @param <D>
     * @return
     */
    private static <D> D changeType(Object o,Class<D> clas){
        if(o==null) return null;
        try{
            if(clas==BigDecimal.class){
                if(o.toString().indexOf("%")>0){
                    return (D)new BigDecimal(o.toString().substring(0,o.toString().length()-1)).divide(new BigDecimal("100"));
                }
                return (D)new BigDecimal(o.toString());
            }
            if(clas==Double.class){
                return (D)Double.valueOf(o.toString());
            }
            if(clas==Integer.class){
                return (D)Integer.valueOf(o.toString().indexOf(".")>0?o.toString().substring(0,o.toString().indexOf(".")):o.toString());
            }
            if(clas==Short.class){
                return (D)Short.valueOf(o.toString().indexOf(".")>0?o.toString().substring(0,o.toString().indexOf(".")):o.toString());
            }
            if(clas==String.class){
                return (D)String.valueOf(o.toString());
            }
            return (D)o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换带“_”的key
     * @param map
     */
    private static void changeMap(Map<String,Object> map){
        System.out.println(map.size());
        Map<String,Object> newmap = new HashMap<>();
        for(Map.Entry<String,Object> str:map.entrySet()){
            if(str.getKey().contains("_")){
                String[] st=str.getKey().split("_");
                StringBuilder builder=new StringBuilder(st[0]);
                for (int i = 1; i < st.length; i++) {
                    if(!"".equals(st[i])){
                        builder.append(st[i].substring(0,1).toUpperCase()).append(st[i].substring(1));
                    }
                }
                newmap.put(builder.toString(),str.getValue());
            }
        }
        if(newmap.size()>0) map.putAll(newmap);
    }
}
