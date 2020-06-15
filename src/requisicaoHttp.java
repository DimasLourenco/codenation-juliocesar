import java.net.*;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.*;

public class requisicaoHttp {
	
	private final String CODENATION_GET_URL = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
	private final String CODENATION_POST_URL = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";
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
			URL url = new URL(CODENATION_GET_URL + this.token);	//	MalformedURLException
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

	public String PostRequest()
	{
		String postResponse = "";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost uploadFile = new HttpPost(CODENATION_POST_URL + this.token);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		// This attaches the file to the POST:
		File f = new File("answer.json");
		
		try 
		{
			builder.addBinaryBody(
			    "answer",
			    new FileInputStream(f),
			    ContentType.APPLICATION_OCTET_STREAM,
			    f.getName()
			);

			HttpEntity multipart = builder.build();
			uploadFile.setEntity(multipart);
			CloseableHttpResponse response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			
			postResponse = EntityUtils.toString(responseEntity, "UTF-8");
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println(HTTP_DEBUG + "Não foi encontrado o arquivo para enviar");
			e.printStackTrace();
		} 
		catch (UnsupportedOperationException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			System.out.println(HTTP_DEBUG + "Falha no POST");
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		return postResponse;
	}
	
}
