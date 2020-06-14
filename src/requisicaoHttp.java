import java.net.*;
import java.io.*;

public class requisicaoHttp {
	
	public static String GetRequest(String token)throws Exception {
		URL url = new URL("https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token);
	    URLConnection yc = url.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            yc.getInputStream()));
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) 
	        System.out.println(inputLine);
	    in.close();
	    
	    return inputLine;
	}

}
