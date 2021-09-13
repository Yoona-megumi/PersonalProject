package com.ouzhuxuan.main;

import com.ouzhuxuan.utils.Hamming;
import com.ouzhuxuan.utils.IOText;
import com.ouzhuxuan.utils.SimHash;

public class PaperCheck {
    public static void main(String[] args) {
        // �������������·������ȡ��Ӧ���ļ������ļ�������ת��Ϊ��Ӧ���ַ���
        String originTxtName = IOText.getTxtName(args[0]);
        String copyTxtName = IOText.getTxtName(args[1]);
        String ansTxt = IOText.getTxtName(args[2]);
        String str0 = IOText.read(args[0]);
        String str1 = IOText.read(args[1]);

        // ���ַ����ó���Ӧ�� simHashֵ
        String simHash0 = SimHash.getSimHash(str0);
        String simHash1 = SimHash.getSimHash(str1);

        // �� simHashֵ������ƶ�
        double similarity = Hamming.getSimilarity(simHash0, simHash1);

        // �����ƶ�д�����Ľ���ļ���
        IOText.write(args[2],IOText.printout(originTxtName,copyTxtName,similarity));

        System.out.println("���ؽ����д��" + ansTxt);

        // �˳�����
        System.exit(0);
    }
}
