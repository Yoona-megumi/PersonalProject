package com.ouzhuxuan.main;

import com.ouzhuxuan.utils.Hamming;
import com.ouzhuxuan.utils.IOText;
import com.ouzhuxuan.utils.SimHash;

public class PaperCheck {
    public static void main(String[] args) {
        // 从命令行输入的路径名读取对应的文件，将文件的内容转化为对应的字符串
        String originTxtName = IOText.getTxtName(args[0]);
        String copyTxtName = IOText.getTxtName(args[1]);
        String ansTxt = IOText.getTxtName(args[2]);
        String str0 = IOText.read(args[0]);
        String str1 = IOText.read(args[1]);

        // 由字符串得出对应的 simHash值
        String simHash0 = SimHash.getSimHash(str0);
        String simHash1 = SimHash.getSimHash(str1);

        // 由 simHash值求出相似度
        double similarity = Hamming.getSimilarity(simHash0, simHash1);

        // 把相似度写入最后的结果文件中
        IOText.write(args[2],IOText.printout(originTxtName,copyTxtName,similarity));

        System.out.println("查重结果已写入" + ansTxt);

        // 退出程序
        System.exit(0);
    }
}
