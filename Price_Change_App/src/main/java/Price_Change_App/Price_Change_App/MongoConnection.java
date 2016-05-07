package Price_Change_App.Price_Change_App;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class MongoConnection {


	public DB connectToMongo(){
		MongoClientURI uri  = new MongoClientURI("mongodb://admin:adminadmin@ds013222.mlab.com:13222/product_details");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		return db;
	}

	public String giveProduct(String searchvalue){
		DB db=connectToMongo();
		DBCollection product_details = db.getCollection("product_details");
		//product_details.
		DBObject findquery=new BasicDBObject("product.name",searchvalue);
		DBCursor docs=product_details.find(findquery);
		// result=null;
		JSONArray resArr=new JSONArray();
		
		if(docs.hasNext()==false)
		{
			JSONObject returnvalue=new JSONObject();
		
			resArr=callSem3(searchvalue);
			//System.out.println("docs  "+docs.hasNext());
			returnvalue.put("details", sortJsonArray(resArr));
			System.out.println(returnvalue);
			return returnvalue.toString();
		}else
		{
			JSONObject returnvalue=new JSONObject();
			while(docs.hasNext())
			{
				
				resArr.put(JSONObject.wrap(docs.next().get("product")));
				//System.out.println(resArr.get(0).getClass());
				//	System.out.println(docs.next().get("product"));
			}
			returnvalue.put("details", sortJsonArray(resArr));
			System.out.println(returnvalue);
			return returnvalue.toString();
		//	System.out.println();
		
			
		//		if(docs.hasNext()&&docs.next().get("iphone")==null)
		//		{
		//			System.out.println("inside1");

		//			Semantics3Class s=new Semantics3Class();
		//			System.out.println("inside2");
		//	    	JSONArray result=null;
		//	    	try {
		//				result=s.findProduct();
		//			} catch (OAuthMessageSignerException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			} catch (OAuthExpectationFailedException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			} catch (OAuthCommunicationException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			} catch (IOException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			} catch (URISyntaxException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}
		//	    	//System.out.println(result.getJSONObject(0).toString());
		//	    	String ress=result.getJSONObject(0).toString();
		//	    	DBObject dbo=(DBObject) JSON.parse(ress);
		//	    	DBObject resultreturned=new BasicDBObject("product",dbo);
		//	    	product_details.insert(resultreturned);
		//	    	return result.toString();
		//	    	//product_details.
		//		}
		//		if(docs.hasNext())
		//		{
		//		return docs.next().get("product").toString();
		//		}
		//		else

		//if(product_details.)
	}
	};
public JSONArray sortJsonArray(JSONArray resArr){
	List<JSONObject> jsonArr=new ArrayList<JSONObject>();
	for(int k=0;k<resArr.length();k++)
	{
			
		jsonArr.add(resArr.getJSONObject(k));
	}
	Collections.sort(jsonArr, new Comparator<JSONObject>(){
		private static final String sort_field = "price";
		public int compare(JSONObject o1, JSONObject o2) {

			String valA = new String();
			String valB = new String();

			try {
				valA = (String) o1.get(sort_field);
				valB = (String) o2.get(sort_field);
			} 
			catch (JSONException e) {
				//do something
			}
			if(Double.parseDouble(valA)<=Double.parseDouble(valB))
			{
				return -1;
			}else
			{
				return +1;
			}
			//	return valA.compareTo(valB);
			// TODO Auto-generated method stub

		}
	});
	JSONArray sorted=new JSONArray();
	for(int h=0;h<jsonArr.size();h++)
	{
		sorted.put(jsonArr.get(h));
		System.out.println(jsonArr.get(h));
		System.out.println("\n");
	}
	return sorted;
}
		
		
	public JSONArray callSem3(String searchvalue){
		DB db=connectToMongo();
		DBCollection product_details = db.getCollection("product_details");
		Semantics3Class s=new Semantics3Class();
		System.out.println("inside2");
		JSONArray result=null;
		try {
			result=s.findProduct(searchvalue);
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(result.getJSONObject(0).toString());
		for(int i=0;i<result.length();i++)
		{
			String ress=result.getJSONObject(i).toString();
			DBObject dbo=(DBObject) JSON.parse(ress);
			DBObject resultreturned=new BasicDBObject("product",dbo);
			product_details.insert(resultreturned);
		}
		return result;
		//return null;
	}
}
