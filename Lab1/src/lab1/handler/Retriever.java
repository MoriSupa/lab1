package lab1.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//do anything you want:'Niu1234'

import org.json.JSONObject;
import org.json.JSONTokener;

public class Retriever implements I_Retriever {
	private final static String FILEPATH = "src/lab1/data/data.json";

	public Retriever() {
	}

	@Override
	public JSONObject retrieve() {
		// use the this.url as file path
		// 从给定位置获取文件
		File file = new File(FILEPATH);
		BufferedReader reader = null;
		// 返回值,使用StringBuffer
		StringBuffer data = new StringBuffer();
		//
		try {
			reader = new BufferedReader(new FileReader(file));
			// 每次读取文件的缓存
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				data.append(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭文件流
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		JSONTokener jsonTokener = new JSONTokener(data.toString());
		JSONObject retJSONObject;
		retJSONObject = (JSONObject) jsonTokener.nextValue();
		return retJSONObject;
	}

}