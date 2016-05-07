package Price_Change_App.Price_Change_App;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.restlet.data.Form;
import org.restlet.data.Parameter;

/**
 * Hello world!
 *
 */
public class App extends ServerResource
{
    public static void main( String[] args ) throws Exception
    {
    	
    
    	
    	new Server(Protocol.HTTP,3000,App.class).start();
        System.out.println( "Hello World!" );
       
       // db.mynewcollection.insert({ "foo" : "bar" });
    }
    @Get  
    public String toString() { 
    	String key=null;
    	String value=null;
    	Form form=getRequest().getResourceRef().getQueryAsForm();
		for (Parameter parameter : form) {
			key=parameter.getName();
			value=parameter.getValue();
			//			System.out.print("parameter " + parameter.getName());
			//			System.out.println("/" + parameter.getValue());
		}
//    	Semantics3Class s=new Semantics3Class();
//    	JSONArray result=null;
//    	try {
//			result=s.findProduct();
//		} catch (OAuthMessageSignerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OAuthExpectationFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OAuthCommunicationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//       return result.toString();  
    	MongoConnection mongo=new MongoConnection();
    	System.out.print(mongo.giveProduct(value));
       return mongo.giveProduct(value);
    }

}
