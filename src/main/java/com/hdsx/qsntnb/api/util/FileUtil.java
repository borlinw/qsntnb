package com.hdsx.jhsjgx.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public  class FileUtil {
	public  void fileCope(String url1, String url2) throws Exception {
		FileInputStream in = new FileInputStream(new File(url1));
		FileOutputStream out = new FileOutputStream(new File(url2));
		byte[] buff = new byte[512];
		int n = 0;
		System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径："
		+ url2);
		while ((n = in.read(buff)) != -1) {
		out.write(buff, 0, n);
		}
		out.flush();
		in.close();
		out.close();
		System.out.println("复制完成");
	}
}

