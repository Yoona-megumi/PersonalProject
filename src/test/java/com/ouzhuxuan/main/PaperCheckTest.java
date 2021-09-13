package com.ouzhuxuan.main;

import junit.framework.TestCase;

import static com.ouzhuxuan.main.PaperCheck.main;

public class PaperCheckTest<s> extends TestCase {

    public void testPaperCheck(){
        main(new String[]{"E:/testText/orig.txt","E:/testText/orig_0.8_add.txt","E:/testText/ans1_orig_0.8_add.txt"});
    }

}