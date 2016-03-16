public class IPFormat {
	
	public IPFormat() {} // constructor nulo
	
	public static String convertDirection(long directionDec){
		
		String binaryCode= convertDirectiontoBin(directionDec, 32);
		
		//console.log("IP Direction (binary code)= "+binaryCode);
		String directionBin= convertBinarytoDirBin(binaryCode);
		//console.log("IP Direction (binary)= "+directionBin);
		
		String direction= convertBinDirtoDir(directionBin);
		
	return direction;
	}
	
		/* convert a direction a decimal value
		*/
	public static long convertDirtoDec(String direction){
		String[] oct=direction.split("\\.");
		String dirBin= convertDirectiontoBin(Long.parseLong(oct[0]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[1]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[2]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[3]), 8);
		dirBin= dirBin.replace(".","");
	return convertBintoDec(dirBin);
	}
	
	public static String convertDirtoBin(String direction){
		//System.out.println(directionBin);
		String[] oct=direction.split("\\.");
		//System.out.println(oct[0]);
		String dirBin= convertDirectiontoBin(Long.parseLong(oct[0]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[1]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[2]), 8)+"."+
					   convertDirectiontoBin(Long.parseLong(oct[3]), 8);
	return dirBin;
	}
	
	public static String convertBinDirtoDir(String directionBin){
		String[] oct=directionBin.split("\\.");
	return convertBintoDec(oct[0])+"."+convertBintoDec(oct[1])+"."+convertBintoDec(oct[2])+"."+convertBintoDec(oct[3]);
	}
	
	public static long convertBintoDec(String binvalue){
		long decvalue=0;
		for (int i=0;i<binvalue.length();i++){
			if (binvalue.charAt(i)!='0') {
				decvalue+=Math.pow(2,binvalue.length()-i-1);
				int tmp= binvalue.length()-i-1;
			}
		}		
	return decvalue;
	}
	
	public static String convertBinarytoDirBin(String binaryCode){
	String directionBin="";
		
		for (int i=1;i<33-binaryCode.length();i++)
			directionBin = directionBin + "0";

		directionBin= directionBin + binaryCode;
		
			if (directionBin.charAt(8)!='.')
				directionBin= directionBin.substring(0,8)+"."+directionBin.substring(8,directionBin.length());
		
			if (directionBin.charAt(17)!='.')
				directionBin= directionBin.substring(0,17)+"."+directionBin.substring(17,directionBin.length());
		
			if (directionBin.charAt(26)!='.')
				directionBin= directionBin.substring(0,26)+"."+directionBin.substring(26,directionBin.length());
		
	return directionBin;
	}
	
	public static String convertDirectiontoBin(long directionDec, int longitud){
		/*convert here direction from decimal to binary
		*/
			long dividendo=directionDec;
			int i=1;
			int[] arrayBin = new int[longitud+1];
			long resto=0; long cociente=0;
			do{
			cociente= dividendo/2;
			resto= dividendo%2;
			arrayBin[i]= (int)resto;			
			dividendo= cociente;
			i++;
			}while((int)cociente!=0);
		
			String cadAux="";
			int j=1;	
			for (int k=longitud;k>0;k--){
				cadAux= cadAux + arrayBin[k];
				j++;
			}
			
			String directionBin= cadAux;
			

	return directionBin;
	}
	
}