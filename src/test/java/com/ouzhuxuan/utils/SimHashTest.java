package com.ouzhuxuan.utils;

import junit.framework.TestCase;

public class SimHashTest extends TestCase {

    public void testSimHash(){
        String str0 = IOText.read("E:/testText/orig.txt");
        String str1 = IOText.read("E:/testText/orig_0.8_add.txt");
        System.out.println(SimHash.getSimHash(str0));
        System.out.println(SimHash.getSimHash(str1));
    }
}