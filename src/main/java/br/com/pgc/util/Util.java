package br.com.pgc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	private static final Map<String, Integer> legendaMap = new HashMap<>();

	 static {
	        legendaMap.put("ÚNICA", 1);
	        legendaMap.put("MÚLTIPLA", 2);
	        legendaMap.put("NÃO", 0);
	        legendaMap.put("SIM", 1);
	        legendaMap.put("VAGINAL", 1);
	        legendaMap.put("CESÁREA", 2);
	        legendaMap.put("FEMININO", 1);
	        legendaMap.put("MASCULINO", 2);
	        legendaMap.put("AIG", 1);
	        legendaMap.put("PIG", 2);
	        legendaMap.put("GIG", 3);
	        legendaMap.put("ALTA", 1);
	        legendaMap.put("TRANSFERÊNCIA", 2);
	        legendaMap.put("ÓBITO", 3);
	        legendaMap.put("DIREITO", 1);
	        legendaMap.put("ESQUERDO",2);
	        legendaMap.put("BILATERAL", 3);
	        legendaMap.put("AMBOS / BILATERAL?", 3);
	        legendaMap.put("VITRECTOMIA OU RETINOPEXIA", 4);
	        legendaMap.put("LASER", 1);
	        legendaMap.put("CRIO", 2);
	        legendaMap.put("ANTIVEF", 3);
	        legendaMap.put("ANTI - VEF1", 3);
	        legendaMap.put("RETINOPEXIA", 4);
	        legendaMap.put("I", 1);
	        legendaMap.put("II", 2);
	        legendaMap.put("III", 3);
	        legendaMap.put("IV", 4);
	}

	 public static String[] dividirTempoInternacao(String texto) {
	        String[] partes = new String[3];
	        Pattern padrao = Pattern.compile("(\\d{2}/\\d{2})-(\\d{2}/\\d{2}(?:/\\d{2})?)\\s+(\\d+) dias");
	        Matcher matcher = padrao.matcher(texto);
	        if (matcher.find()) {
	            partes[0] = matcher.group(1);
	            partes[1] = matcher.group(2);
	            partes[2] = matcher.group(3);
	        }

	        return partes;
	    }
	
	public static String obterValor(String valor) {
		Integer valorTratado = legendaMap.getOrDefault(valor.toUpperCase().trim().replace(",", ""), null); 
		if (valorTratado == null) return valor;
		return valorTratado.toString();
	}
}
