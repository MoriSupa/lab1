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
		return null;
	}

}
