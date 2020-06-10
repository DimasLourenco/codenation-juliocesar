
import org.json.JSONException;
import org.json.JSONObject;
/**
 *   "manipulacaoJson"
 *   
 *   Classe com métodos estáticos para fazer as manipulações necessárias
 * do arquivo JSON esperado no programa.
 *   Os métodos aqui são específicos para o desafio do AceleraDev, fazendo
 * somente os acessos e edições no JSON conforme necessário para o desafio.
 * 
 *
 */
public class manipulacaoJson 
{

	/**
	 *   Retorna o valor para a tag "numero_casas",
	 * contendo a qtde. de casas para ser utilizada
	 * na cifra de César.
	 * 
	 * 
	 * @param jo
	 *			JSONObject a ser lido
	 * @return int com o valor da tag "numero_casas"
	 */
	public static int getNumeroCasas(JSONObject jo) 
	{
		int numeroCasas;

		try 
		{
			numeroCasas = jo.getInt("numero_casas");
		}
		// Segundo a documentação da api, JSONException será lançada quando o valor da tag não for um int...
		catch(JSONException typeEx) 
		{
			System.out.println("Erro: a tag \"numero_casas\" não possui um valor do tipo int!");
			System.out.println("\nMensagem de erro:" + typeEx.getMessage());
			return 0;
		}
		// ...para os outros casos (que talvez não ocorram), deixamos esse catch "default"
		catch(Exception ex) 
		{
			System.out.println("Erro ao tentar ler a tag \"numero_casas\"!");
			System.out.println("\nMensagem de erro:" + ex.getMessage());
			return 0;
		}
		
		return numeroCasas;
	}

	/**
	 *   Retorna o valor para a tag "cifrado",
	 * contendo o texto para decifrarmos usando
	 * a cifra de César.
	 * 
	 * 
	 * @param jo
	 *			JSONObject a ser lido
	 * @return String com o valor da tag "cifrado"
	 */
	public static String getTextoParaDecifrar(JSONObject jo) 
	{
		String textoParaDecifrar;

		try 
		{
			textoParaDecifrar = jo.getString("cifrado");
		}
		// Segundo a documentação da api, JSONException será lançada quando o valor da tag não for uma String...
		catch(JSONException typeEx) 
		{
			System.out.println("Erro: a tag \"cifrado\" não possui um valor do tipo String!");
			System.out.println("\nMensagem de erro:" + typeEx.getMessage());
			return "";
		}
		// ...para os outros casos (que talvez não ocorram), deixamos esse catch "default"
		catch(Exception ex) 
		{
			System.out.println("Erro ao tentar ler a tag \"cifrado\"!");
			System.out.println("\nMensagem de erro:" + ex.getMessage());
			return "";
		}
		
		return textoParaDecifrar;
	}

	/**
	 *   Retorna o valor para a tag "token",
	 * contendo o token referência na resposta
	 * HTTP.
	 * 
	 * 
	 * @param jo
	 *			JSONObject a ser lido
	 * @return String com o valor da tag "token"
	 */
	public static String getToken(JSONObject jo) 
	{
		String token;

		try 
		{
			token = jo.getString("token");
		}
		// Segundo a documentação da api, JSONException será lançada quando o valor da tag não for uma String...
		catch(JSONException typeEx) 
		{
			System.out.println("Erro: a tag \"token\" não possui um valor do tipo String!");
			System.out.println("\nMensagem de erro:" + typeEx.getMessage());
			return "";
		}
		// ...para os outros casos (que talvez não ocorram), deixamos esse catch "default"
		catch(Exception ex) 
		{
			System.out.println("Erro ao tentar ler a tag \"token\"!");
			System.out.println("\nMensagem de erro:" + ex.getMessage());
			return "";
		}
		
		return token;
	}

	/**
	 *   Grava o texto decifrado na tag
	 * "decifrado", e retorna um JSON com
	 * o texto decifrado.
	 * 
	 * 
	 * @param jo
	 *			JSONObject de referência
	 * @param textoDecifrado
	 * 			Texto decifrado pela cifra de César
	 * @return JSONObject com a tag "decifrado" preenchida
	 */
	public static JSONObject setTextoDecifrado(JSONObject jo, String textoDecifrado) 
	{
		JSONObject newJo = jo;

		try 
		{
			newJo.put("decifrado", textoDecifrado);
		}
		catch(Exception ex) 
		{
			System.out.println("Erro ao tentar escrever o texto decifrado!");
			System.out.println("\nMensagem de erro:" + ex.getMessage());
			return null;
		}
		
		return newJo;
	}

	/**
	 *   Grava o hash calculado na tag
	 * "resumo_criptografico", e retorna 
	 * um JSON contendo o hash.
	 * 
	 * 
	 * @param jo
	 *			JSONObject de referência
	 * @param hash
	 * 			Hash do texto decifrado
	 * @return JSONObject com a tag "resumo_criptografico" preenchida
	 */
	public static JSONObject setHash(JSONObject jo, String hash) 
	{
		JSONObject newJo = jo;

		try 
		{
			newJo.put("resumo_criptografico", hash);
		}
		catch(Exception ex) 
		{
			System.out.println("Erro ao tentar escrever o hash!");
			System.out.println("\nMensagem de erro:" + ex.getMessage());
			return null;
		}
		
		return newJo;
	}
	
}
