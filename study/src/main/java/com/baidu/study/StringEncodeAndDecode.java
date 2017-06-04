package com.baidu.study;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class StringEncodeAndDecode {

	public static void Test1() {
		String str = "这是一个中文字符";
		byte[] b = str.getBytes();		//转换成为字节
		System.out.println("字节："+b);
		
		try {
			//字节转换为字符
			String newStr = new String(b, "UTF-8");
			System.out.println("转换后的字符："+newStr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static void Test2() {
		//创建转换器
		Charset charset = Charset.forName("UTF-8");
		String str = "这是一个中文字符";
		//编码
		ByteBuffer b = charset.encode(str);
		//解码
		CharBuffer newStr = charset.decode(b);
		
		System.out.println("编码结果："+b.array());
		System.out.println("解码结果："+newStr.toString());
	}
	
	
	public static void main(String[] args) {
		Test2();
	}

}
