package com.test.parse;

import com.mysql.cj.util.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.test.parse.WriteExcel.writeExcel;

public class TextUtils {


//    public String text2String(){
//
//    }

    @Test
    public  void test()
    {
        String content = "";
        List list = new ArrayList<String[]>();
        Pattern p = Pattern.compile("\\s+");
        String w = "";


        content = ReadText.readFile("C:\\Users\\Mickey\\Desktop\\word.txt");
        String[] slips = content.split("\n");


        if(slips != null && slips.length != 0)
        {
            String[] line = null;
            for (int i=1;  i<slips.length; i++)
                if (!StringUtils.isNullOrEmpty(slips[i])) {
/*                    if(slips[i].startsWith("Word List")){
                        continue;
                    }*/
                    Matcher m = p.matcher(delSpace(slips[i]));
                    w = m.replaceAll(" ");
                    line = w.split(" ");
                    list.add(line);
                }
        }

        writeExcel(list, 3, "C:\\Users\\Mickey\\Desktop\\考研英语.xls");



        for (int j=0; j<list.size(); j++){
            for (String s : (String[]) list.get(j)){
                System.out.print(s+" ");
            }
            System.out.println();
        }

    }


    //使用 Java 正则表达式,去除两边空格。
    public static String delSpace(String str) {

        if (str == null) {
            return null;
        }

        String regStartSpace = "^[　 ]*";
        String regEndSpace = "[　 ]*$";

        // 连续两个 replaceAll
        // 第一个是去掉前端的空格， 第二个是去掉后端的空格
        String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");

        return strDelSpace;
    }

}
