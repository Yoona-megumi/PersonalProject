package com.ouzhuxuan.utils;

import junit.framework.TestCase;

public class IOTextTest extends TestCase {

    public void testRead() {
        // ·�����ڣ�������ȡ
        String str = IOText.read("E:/testText/orig.txt");
        String[] strings = str.split(" ");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public void testWrite() {
        // ·�����ڣ�����д��
        double[] elem = {0.11, 0.22, 0.33, 0.44, 0.55};
        for (int i = 0; i < elem.length; i++) {
            String s = Double.toString(elem[i]);
            IOText.write( "E:/testText/test.txt",s);
        }
    }

    public void testreadTxtFail() {
        // ·�������ڣ���ȡʧ��
        String str = IOText.read("E:/testText/test.txt");
    }


    public void testwriteTxtFail() {
        // ·������д��ʧ��
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

        // ���ַ����ó���Ӧ�� simHashֵ
        String simHash0 = SimHash.getSimHash(str0);
        String simHash1 = SimHash.getSimHash(str1);
        // �� simHashֵ������ƶ�
        double similarity = Hamming.getSimilarity(simHash0, simHash1);
        // �����ƶ�д�����Ľ���ļ���
        IOText.write("E:/testText/ans1_orig_0.8_add.txt",IOText.printout(originTxtName,copyTxtName,similarity));
    }
}