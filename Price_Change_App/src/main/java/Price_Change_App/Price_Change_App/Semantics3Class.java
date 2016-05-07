package Price_Change_App.Price_Change_App;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.semantics3.api.Products;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class Semantics3Class {


	Products products = new Products(
			"SEM3EB9FA80D90526297A14A03F4C7146EE7",
			"NTg2MWM2ZjkzNTNmNDU3NWZhNzRiMDVkYWFmYTlhYjc"
			);
	public JSONArray findProduct(String searchvalue) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException, URISyntaxException{
		products.productsField("search",searchvalue);
		JSONObject results = products.getProducts();
		results = products.get();
		System.out.println("results ");
		
	JSONArray res=results.getJSONArray("results");
	JSONArray details =new JSONArray();
	
	for(int i=0;i<res.length();i++)
	{
		JSONObject obj =new JSONObject();
		obj.put("price", res.getJSONObject(i).getString("price"));
		//obj.put("url", res.getJSONObject(i).getString("url"));
		obj.put("images",  res.getJSONObject(i).getJSONArray("images").get(0).toString());
		obj.put("sem3_id",  res.getJSONObject(i).getString("sem3_id"));
		obj.put("name", searchvalue);
		boolean breakloop=false;
		for(int j=0;j<res.getJSONObject(i).getJSONArray("sitedetails").length();j++)
		{
			
			for(int k=0;k<res.getJSONObject(i).getJSONArray("sitedetails").getJSONObject(j).getJSONArray("latestoffers").length();k++)
			{
				
				if(res.getJSONObject(i).getJSONArray("sitedetails").getJSONObject(j).getJSONArray("latestoffers").getJSONObject(k).getString("price").equals(obj.getString("price"))){
					obj.put("url", res.getJSONObject(i).getJSONArray("sitedetails").getJSONObject(j).getString("url"));
					breakloop=true;
					break;
				}
			}
			if(breakloop==true)
			{
				break;
			}
		}
		details.put(obj);
		
	}
	System.out.println(details);
	return details;
//		JSONArray sitedetails=new JSONArray();
//		for(int i=0;i<res.length();i++)
//		{
//			for(int j=0;j<res.getJSONObject(i).getJSONArray("sitedetails").length();j++)
//			{
//				sitedetails.put(res.getJSONObject(i).getJSONArray("sitedetails").getJSONObject(j));
//			}
//
//		}
//		//System.out.println("sutedetails "+sitedetails);
//		JSONArray latestoffers=new JSONArray();
//		System.out.println("sitedetails"+results.getJSONArray("results").getJSONObject(0).getJSONArray("sitedetails").getJSONObject(0).getJSONArray("latestoffers"));
//		for(int j=0;j<sitedetails.length();j++)
//		{
//			for(int i=0;i<sitedetails.getJSONObject(j).getJSONArray("latestoffers").length();i++)
//			{
//				//if(sitedetails.getJSONArray(j))
//				latestoffers.put(sitedetails.getJSONObject(j).getJSONArray("latestoffers").getJSONObject(i).put("name", sitedetails.getJSONObject(j).getString("name")).put("url", sitedetails.getJSONObject(j).getString("url")));
//			}
//		}
//		System.out.println("latestoffers"+latestoffers.getJSONObject(0).getString("name"));
//
//		List<JSONObject> jsonArr=new ArrayList<JSONObject>();
//		for(int k=0;k<latestoffers.length();k++)
//		{
//
//			jsonArr.add(latestoffers.getJSONObject(k));
//		}
//
//		Collections.sort(jsonArr, new Comparator<JSONObject>(){
//			private static final String sort_field = "price";
//			public int compare(JSONObject o1, JSONObject o2) {
//
//				String valA = new String();
//				String valB = new String();
//
//				try {
//					valA = (String) o1.get(sort_field);
//					valB = (String) o2.get(sort_field);
//				} 
//				catch (JSONException e) {
//					//do something
//				}
//			if(Double.parseDouble(valA)<=Double.parseDouble(valB))
//			{
//				return -1;
//			}else
//			{
//				return +1;
//			}
//			//	return valA.compareTo(valB);
//				// TODO Auto-generated method stub
//
//			}
//
//		});
//		System.out.println("\n Sorted Array");
//		JSONArray sorted=new JSONArray();
//		for(int h=0;h<jsonArr.size();h++)
//		{
//			sorted.put(jsonArr);
//			System.out.println(jsonArr.get(h));
//			System.out.println("\n");
//		}
		
		//JSONObject resultToReturn=sorted.toString();
		////	
		//////	for (JSONObject result : res.getValuesAs(JSONObject.class)) {
		//////		         System.out.print(result.getJSONObject("from").getString("name"));
		//////		         System.out.print(": ");
		//////		       //  System.out.println(result.getString("message", ""));
		//////		         System.out.println("-----------");
		//////		    }
		////	for(int k=0;k<latestoffers.length();k++){
		////		System.out.println(latestoffers.getJSONObject(k).getString("price"));
		////	}
//System.out.println(1049<480);
		
		

	}


}
