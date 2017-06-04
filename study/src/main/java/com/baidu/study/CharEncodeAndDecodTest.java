package com.baidu.study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class CharEncodeAndDecodTest {

	public static void main(String[] args) {
		String file = "/Users/zhanghongbin01/test.txt";

		// 把字符串写到文件中进行存储
		OutputStreamWriter writer;
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				writer = new OutputStreamWriter(out, "UTF-8");
				try {
					writer.write("这是要写入到文件的中文字符\n");
					writer.flush(); // 把缓冲区中的数据强制刷到磁盘中去
					writer.close(); // 关闭流
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 把文件中存储的东西读出来
		try {
			InputStream in = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(in);
			StringBuilder builder = new StringBuilder();
			char[] buf = new char[1024];
			try {
				if (reader.read(buf) > 0) {
					builder.append(buf);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(builder.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
