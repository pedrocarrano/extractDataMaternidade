package br.com.pgc.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.pgc.model.DadosMaternos;

public class DadosMaternosService {
	
	public String getDadosMaternos(String dadosMaternos){

        String textoInicio = "DADOS MATERNOS";
        String textoFim = "DADOS DO RECÉM-NASCIDO";

        int indiceInicio = dadosMaternos.indexOf(textoInicio);
        int indiceFim = dadosMaternos.indexOf(textoFim);

        if (indiceInicio != -1 && indiceFim != -1) {
            String retorno =  dadosMaternos.substring(indiceInicio + textoInicio.length(), indiceFim);
            retorno = retorno.replaceAll(" HC:", "\nHC:");
            retorno = retorno.replaceAll(" Idade materna:", "\nIdade materna:");
            
            return retorno;
        } 
        return "";
	}

	private String extractValorDadosMaternos(String texto, String chave) {
		Pattern pattern = Pattern.compile("\\(\\s*[Xx]\\s*\\)\\s*(.*?)\\s*(?:\\(|$)");
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            String textoSelecionado = matcher.group(1).trim();
            if (!textoSelecionado.isEmpty()) {
               return textoSelecionado;
            }
            return "";
        }
        return texto !=null && texto.contains("( ") && texto.contains(" )") && DadosMaternos.getByDescricao(chave).getTemMarcacao() ? "" : texto;
	}

	public Map<DadosMaternos, String> extractDadosMaternos(String texto) {
		String[] linhas = texto.split("\n");
		Map<DadosMaternos, String> mapa = new LinkedHashMap<>();
		for (String linha : linhas) {
            String[] partes = linha.split(":");
            if (partes.length == 2) {
                String chave = partes[0].trim();
                String valor = extractValorDadosMaternos(partes[1].trim(), chave);                
                mapa.put(DadosMaternos.getByDescricao(chave), valor);
            }
        }
		return mapa;
	}
}
