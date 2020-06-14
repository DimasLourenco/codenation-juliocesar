import java.net.*;
import java.io.*;

public class requisicaoHttp {
	
	private final String CODENATION_URL = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
	private final String HTTP_DEBUG = "[[HTTP]] ";
	private String token;
	
	public requisicaoHttp(String token) 
	{
		this.token = token;
	}
	
	public String getToken() 
	{
		return this.token;
	}
	
	public String GetRequest()
	{

		StringBuilder responseString = new StringBuilder();
		String inputLine = "";
		
		try 
		{
			URL url = new URL(CODENATION_URL + this.token);	//	MalformedURLException
			URLConnection yc = url.openConnection();		//	IOException
			BufferedReader in = new BufferedReader( new InputStreamReader( yc.getInputStream() ) );	//	IOException
			
			while ((inputLine = in.readLine()) != null) 
			{
				responseString.append(inputLine);
				System.out.println(HTTP_DEBUG + inputLine);
			}
			
			in.close();
			
		}
		catch(MalformedURLException malEx) 
		{
			System.out.println(HTTP_DEBUG + "URL mal formada :( ");
		}
		catch(IOException ioEx)
		{
			System.out.println(HTTP_DEBUG + "Erro de IO!");
			ioEx.printStackTrace();
			return "";
		}
	    
	    return responseString.toString();
	}

}
