import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class cifra 
{

	private static final String CIFRA_DEBUG = "[[cifra]] ";
	
	public static String cifraCesar(int chave, String msg) 
	{
		byte charCifrado;
		byte[] msgCifrada = msg.toLowerCase().getBytes();
		
		StringBuilder stringCifrada = new StringBuilder();
		
		System.out.println(CIFRA_DEBUG + "Msg p/ cifrar: " + msg);
		System.out.println(CIFRA_DEBUG + "Deslocamento: " + chave);
		
		for (byte c : msgCifrada) 
		{
			charCifrado = c;
			
			// Garantindo que vamos cifrar somente letras, ignorando caracteres de pontuação e algarismos
			if( ( (c >= 'a') && (c <= 'z') ) )
			{
				charCifrado = (byte) (c + chave);
				
				// Circulando: se o byte c == 'y', chave == 4, charCifrado == 'c'.
				if(charCifrado > 'z') 
				{
					charCifrado -= 26;
				}				
			}
			
			stringCifrada.append((char) charCifrado);
		}

		System.out.println(CIFRA_DEBUG + "Cifrei: " + stringCifrada.toString());
		return stringCifrada.toString();
	}
	
	public static String decifraCesar(int chave, String msg) 
	{

		byte charDecifrado;
		byte[] msgDecifrada = msg.toLowerCase().getBytes();
		
		StringBuilder stringDecifrada = new StringBuilder();

		System.out.println(CIFRA_DEBUG + "Msg p/ decifrar: " + msg);
		System.out.println(CIFRA_DEBUG + "Deslocamento: " + chave);
		
		for (byte c : msgDecifrada) 
		{
			charDecifrado = c;
			
			// Garantindo que vamos cifrar somente letras, ignorando caracteres de pontuação e algarismos
			if( ( (c >= 'a') && (c <= 'z') ) )
			{
				charDecifrado = (byte) (c - chave);
				
				// Circulando: se o byte c == 'b', chave == 4, charCifrado == 'x'.
				if(charDecifrado < 'a') 
				{
					charDecifrado += 26;
				}				
			}
			
			stringDecifrada.append((char) charDecifrado);
		}

		System.out.println(CIFRA_DEBUG + "Decifrei: " + stringDecifrada.toString());
		return stringDecifrada.toString();
	}
	
	public static String sha1Hash(String toHash) 
	{
		byte[] hash;
		String stringHash;
		
		Formatter formatter = new Formatter();

		System.out.println(CIFRA_DEBUG + "Msg p/ calcular hash: " + toHash);
		
		try 
		{
			MessageDigest mesDigest = MessageDigest.getInstance("SHA1");
			
	        hash = mesDigest.digest(toHash.getBytes());
	        
	        for (byte b : hash) 
	        {
	            formatter.format("%02x", b);
	        }	
		}
		catch(NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
        
        stringHash = formatter.toString();
        formatter.close();

		System.out.println(CIFRA_DEBUG + "Hash calculado: " + stringHash);
        return stringHash;
	}
	
}
