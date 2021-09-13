package com.ouzhuxuan.utils;

import java.io.*;
import java.math.BigDecimal;


public class IOText {
    /**
     * ��ȡĿ���ļ��ķ���
     * �����ļ��ľ���·�������ļ�����ת��ΪString�ַ������
     *
     * @param absolutePath �ļ�����·��
     * @return
     */
    public static String read(String absolutePath) {
        // 1.�����ַ����������������ַ�������
        Reader reader = null;
        StringBuilder builder = null;
        try {
            reader = new FileReader(absolutePath);
            builder = new StringBuilder();
            // 2.�����ַ�����
            char[] chs = new char[1024];
            // ����һ����������¼��������Ч�ַ���.
            int len;
            while((len = reader.read(chs)) != -1) {
                String s = new String(chs, 0, len);
                builder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.�ر���Դ
            try {
                // �ж�������Ƿ�Ϊ��
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ���ض�ȡ���
        return builder.toString();
    }

    /**
     * ���ļ�д�����ݵķ���
     * �������ݺ��ļ��ľ���·��
     * @param absolutePath �ļ�����·��
     * @param txt ����
     */
    public static void write(String absolutePath,String txt) {

        Writer writer = null;
        try {
            //1.���������ļ��������ַ����������
            File file = new File(absolutePath);
            writer = new FileWriter(file);
            //2.д����
            writer.write(txt);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.�ر���Դ
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
     * ��ȡ�ļ���
     * @param absolutePath �ļ�����·��
     * @return �ļ���
     */
    public static String getTxtName(String absolutePath) {
        int index = absolutePath.lastIndexOf("/");
        return absolutePath.substring(index + 1);
    }

    public static String printout(String originName, String copyName, double Similarity) {
        BigDecimal bigDecimal = new BigDecimal((Similarity * 100) + "");
        // �Բ����ʽ��б���С�������λ�������봦��
        BigDecimal res = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return "ԭ�ģ�" + originName + "\n" +
                "��Ϯ�����ģ�" + copyName + "\n" +
                "������Ϊ��" + res + "%";
    }
}
