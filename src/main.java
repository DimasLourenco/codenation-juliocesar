import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

public class main {

	public static void main(String[] args)
	{

		requisicaoHttp httpHandler;
		JSONObject json;
		
		String token;
		String textoCifrado;
		String textoDecifrado;
		String sha1;
		String respostaHttpGet;
		String respostaHttpPost;
		
		int chave;
		
		Scanner inputScan = new Scanner(System.in);
		
		System.out.println("Informe o token para fazer a requisição à API do CodeNation: ");
		token = inputScan.nextLine();
		
		httpHandler = new requisicaoHttp(token);
		respostaHttpGet = httpHandler.GetRequest();
		
		try(FileWriter answer = new FileWriter("answer.json"))
		{
			
			answer.write(respostaHttpGet);
			answer.flush();
			answer.close();
		}
		catch(IOException ioEx) 
		{
			System.out.println("ERRO: Falha na escrita do json em arquivo!");
		}

		json = new JSONObject( respostaHttpGet );
		
		chave = manipulacaoJson.getNumeroCasas(json);
		System.out.println("Li a chave: " + chave);
		
		textoCifrado = manipulacaoJson.getTextoParaDecifrar(json);
		System.out.println("Li o texto: " + textoCifrado);
		
		textoDecifrado = cifra.decifraCesar(chave, textoCifrado);
		System.out.println("Decifrei para o seguinte: " + textoDecifrado);
		
		sha1 = cifra.sha1Hash(textoDecifrado);
		System.out.println("Calculei o seguinte hash: " + sha1);
		
		json = manipulacaoJson.setTextoDecifrado(json, textoDecifrado);
		json = manipulacaoJson.setHash(json, sha1);
		
		try(FileWriter answer = new FileWriter("answer.json"))
		{
			
			answer.write(json.toString());
			answer.flush();
			answer.close();
		}
		catch(IOException ioEx) 
		{
			System.out.println("ERRO: Falha na escrita do json em arquivo!");
		}

		respostaHttpPost = httpHandler.PostRequest();
		System.out.println("Resposta ao POST: " + respostaHttpPost);

		inputScan.close();
				
    }
}