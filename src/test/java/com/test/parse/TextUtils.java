package com.test.parse;

import com.mysql.cj.util.StringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.test.parse.WriteExcel.writeExcel;

public class TextUtils {


    static String[] pre = {"一","二","三","四","五","六","七","八","九","十"};

    //新概念单词转化
    @Test
    public  void test()
    {
        String content = "";
        List list = new ArrayList<String[]>();
        Pattern p = Pattern.compile("\\s+");
        String w = "";


        content = ReadText.readFile("C:\\Users\\Administrator\\Desktop\\word.txt");
        String[] slips = content.split("\n");
        String[] words  = new String[2];

        if(slips != null && slips.length != 0)
        {
            String class_1 = "";
            String[] line ;
            boolean decide;
            for (int i=1;  i<slips.length; i++)
                if (!StringUtils.isNullOrEmpty(slips[i])) {
                    decide = false;
                    for (int j = 0 ; j<pre.length;j++){
                        if(slips[i].startsWith(pre[j])){
                            class_1 = slips[i];
                            decide = true;
                        }
                    }

                    if (decide) {
                        continue;
                    }


                    String[] wait_l = delSpace(slips[i]).split("[1-9]\\W");



                    String s = "";
                    if(wait_l.length>=1){
                        for (int q = 0; q<wait_l.length;q++){
                            line =  new String[3];
                            line[0] = class_1;

                            words = delNum_brack(wait_l[q]);
                            if (words == null)
                                continue;

                            line[1] = words[0];
                            line[2] = words[1];
                            list.add(line);
                        }
                    }

                }
        }

        for (int j=0; j<list.size(); j++){
            for (String s : (String[]) list.get(j)){
                System.out.print(s+" ");
            }
            System.out.println();
        }


        writeExcel(list, 3, "C:\\Users\\Administrator\\Desktop\\新概念第一册.xlsx");




    }



    //使用 Java 正则表达式，去除数字序号和括号
    public static String[] delNum_brack(String str) {
        String[] s = new String[2];

        if (StringUtils.isNullOrEmpty(str)|| isNumeric(str)) {
            return null;
        }


        String word  = "";
        String quStr = "";
        try {
            word = str.substring(0,str.indexOf("("));
            quStr=str.substring(str.indexOf("(")+1,str.indexOf(")"));

            s[0] = word;
            s[1] = quStr;
        }
        catch (StringIndexOutOfBoundsException e){
            System.out.println(str+"######"+word +"....."+ quStr);
        }
//        strDelSpace.replaceAll(regEndSpace, "");
        return s;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }



    private static String  getInBrack(String gSQL){
        String quStr=gSQL.substring(gSQL.indexOf("(")+1,gSQL.indexOf(")"));
        if(StringUtils.isNullOrEmpty(quStr)){
            return "";
        }
        return quStr;
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
