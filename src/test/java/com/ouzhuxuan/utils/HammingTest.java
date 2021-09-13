package com.ouzhuxuan.utils;

import junit.framework.TestCase;

public class HammingTest extends TestCase {

    public void testHamming() {
        String str0 = IOText.read("E:/testText/orig.txt");
        String str1 = IOText.read("E:/testText/orig_0.8_add.txt");
        int distance = Hamming.getHammingDistance(SimHash.getSimHash(str0), SimHash.getSimHash(str1));
        double similarity = Hamming.getSimilarity(SimHash.getSimHash(str0), SimHash.getSimHash(str1));
        System.out.println("海明距离：" + distance);
        System.out.println("str0和str1的相似度:" + similarity);
    }


}