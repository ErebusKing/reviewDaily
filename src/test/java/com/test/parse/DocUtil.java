package com.test.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class DocUtil {
    /**
     * 读取doc文件内容
     *
     * @param file
     *            想要读取的文件对象
     * @return 返回文件内容
     * @throws IOException
     */
    public static String doc2String(FileInputStream fs) throws IOException {
        StringBuilder result = new StringBuilder();
        WordExtractor re = new WordExtractor(fs);
        result.append(re.getText());
        re.close();
        return result.toString();
    }

    public static String doc2String(File file) throws IOException {
        return doc2String(new FileInputStream(file));
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Mickey\\Desktop\\word.docx");

        try
        {
            System.out.println(doc2String(file));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}