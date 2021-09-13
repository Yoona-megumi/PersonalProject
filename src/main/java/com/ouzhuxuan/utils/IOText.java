package com.ouzhuxuan.utils;

import java.io.*;
import java.math.BigDecimal;


public class IOText {
    /**
     * 读取目标文件的方法
     * 传入文件的绝对路径，将文件内容转化为String字符串输出
     *
     * @param absolutePath 文件绝对路径
     * @return
     */
    public static String read(String absolutePath) {
        // 1.创建字符输入流对象与结果字符串容器
        Reader reader = null;
        StringBuilder builder = null;
        try {
            reader = new FileReader(absolutePath);
            builder = new StringBuilder();
            // 2.定义字符数组
            char[] chs = new char[1024];
            // 定义一个变量，记录读到的有效字符数.
            int len;
            while((len = reader.read(chs)) != -1) {
                String s = new String(chs, 0, len);
                builder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            try {
                // 判断输出流是否为空
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 返回读取结果
        return builder.toString();
    }

    /**
     * 向文件写入内容的方法
     * 传入内容和文件的绝对路径
     * @param absolutePath 文件绝对路径
     * @param txt 内容
     */
    public static void write(String absolutePath,String txt) {

        Writer writer = null;
        try {
            //1.创建创建文件对象与字符输出流对象
            File file = new File(absolutePath);
            writer = new FileWriter(file);
            //2.写数据
            writer.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取文件名
     * @param absolutePath 文件绝对路径
     * @return 文件名
     */
    public static String getTxtName(String absolutePath) {
        int index = absolutePath.lastIndexOf("/");
        return absolutePath.substring(index + 1);
    }

    public static String printout(String originName, String copyName, double Similarity) {
        BigDecimal bigDecimal = new BigDecimal((Similarity * 100) + "");
        // 对查重率进行保留小数点后两位四舍五入处理
        BigDecimal res = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return "原文：" + originName + "\n" +
                "抄袭版论文：" + copyName + "\n" +
                "相似率为：" + res + "%";
    }
}
