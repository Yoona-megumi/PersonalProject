package com.ouzhuxuan.utils;

import junit.framework.TestCase;

public class IOTextTest extends TestCase {

    public void testRead() {
        // 路径存在，正常读取
        String str = IOText.read("E:/testText/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public void testWrite() {
        // 路径存在，正常写入
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            String s = Double.toString(elem[i]);
            IOText.write( "E:/testText/test.txt",s);
        }
    }

    public void testreadTxtFail() {
        // 路径不存在，读取失败
        String str = IOText.read("E:/testText/test.txt");
    }


    public void testwriteTxtFail() {
        // 路径错误，写入失败
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            String s = Double.toString(elem[i]);
            IOText.write( "E:/testText/test.txt",s);
        }
    }


    public void testGetTxtName() {
        String str = IOText.getTxtName("E:/testText/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public void testPrintout() {
        String originTxtName = IOText.getTxtName("E:/testText/orig.txt");
        String copyTxtName = IOText.getTxtName("E:/testText/orig_0.8_add.txt");
        String str0 = IOText.read("E:/testText/orig.txt");
        String str1 = IOText.read("E:/testText/orig_0.8_add.txt");

        // 由字符串得出对应的 simHash值
        String simHash0 = SimHash.getSimHash(str0);
        String simHash1 = SimHash.getSimHash(str1);
        // 由 simHash值求出相似度
        double similarity = Hamming.getSimilarity(simHash0, simHash1);
        // 把相似度写入最后的结果文件中
        IOText.write("E:/testText/ans1_orig_0.8_add.txt",IOText.printout(originTxtName,copyTxtName,similarity));
    }
}