package lab1.handler;

//do anything you want:'Niu1234'

import org.json.JSONObject;

public class Retriever implements I_Retriever {
	private String url;
	
	public Retriever(String _url){
		this.url=_url;		
	}	
	
	@Override
	public JSONObject retrieve() {
		//use the this.url as file path
	     //从给定位置获取文件
        File file = new File(url);
        BufferedReader reader = null;
        //返回值,使用StringBuffer
        StringBuffer data = new StringBuffer();
        //
        try {
            reader = new BufferedReader(new FileReader(file));
            //每次读取文件的缓存
            String temp = null;
            while((temp = reader.readLine()) != null){
                data.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件流
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONTokener jsonTokener = new JSONTokener(data.toString()); 
        JSONObject retJSONObject;
        retJSONObject=(JSONObject)jsonTokener.nextValue();  
		return retJSONObject;
	}

}
