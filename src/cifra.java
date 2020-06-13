import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class cifra 
{

	public static String cifraCesar(int chave, String msg) 
	{
		byte charCifrado;
		byte[] msgCifrada = msg.toLowerCase().getBytes();
		
		StringBuilder stringCifrada = new StringBuilder();
		
		for (byte c : msgCifrada) 
		{
			// Garantindo que vamos cifrar somente letras, ignorando caracteres de pontuação e algarismos
			if( ( (c >= 'a') && (c <= 'z') ) )
			{
				charCifrado = (byte) (c + chave);
				
				// Circulando: se o byte c == 'y', chave == 4, charCifrado == 'c'.
				if(charCifrado > 'z') 
				{
					charCifrado -= 26;
				}
				
				stringCifrada.append((char) charCifrado);
			}
		}
		
		return stringCifrada.toString();
	}
	
	public static String decifraCesar(int chave, String msg) 
	{

		byte charCifrado;
		byte[] msgDecifrada = msg.toLowerCase().getBytes();
		
		StringBuilder stringDecifrada = new StringBuilder();
		
		for (byte c : msgDecifrada) 
		{
			// Garantindo que vamos cifrar somente letras, ignorando caracteres de pontuação e algarismos
			if( ( (c >= 'a') && (c <= 'z') ) )
			{
				charCifrado = (byte) (c - chave);
				
				// Circulando: se o byte c == 'b', chave == 4, charCifrado == 'x'.
				if(charCifrado < 'a') 
				{
					charCifrado += 26;
				}
				
				stringDecifrada.append((char) charCifrado);
			}
		}
		
		return stringDecifrada.toString();
	}
	
	public static String sha1Hash(String toHash) 
	{
		byte[] hash;
		String stringHash;
		
		Formatter formatter = new Formatter();
		
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
         
        return stringHash;
	}
	
}
