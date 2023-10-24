package br.com.pgc.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.pgc.model.DadosRecemNascido;

public class DadosRecemNascidoService {
	
	public String getDadosRecemNascido(String dadosRecemNascido){

        String textoInicio = "DADOS DO RECÉM-NASCIDO";
        String textoFim = "DADOS REFERENTES À RETINOPATIA DA PREMATURIDADE";

        int indiceInicio = dadosRecemNascido.indexOf(textoInicio);
        int indiceFim = dadosRecemNascido.indexOf(textoFim);

        if (indiceInicio != -1 && indiceFim != -1) {
            String retorno =  dadosRecemNascido.substring(indiceInicio + textoInicio.length(), indiceFim);
            retorno = retorno.replaceAll(" HC:", "\nHC:");
            retorno = retorno.replaceAll(" IC no diagnóstico:", "\nIC no diagnóstico:");
            retorno = retorno.replaceAll("Reanimação", "\nReanimação:");
            retorno = retorno.replaceAll(" Data do tratamento:", "\nData do tratamento:");
            retorno = retorno.replaceAll(" comprovada por cultura positiva ", "\nComprovada por cultura positiva:");
            retorno = retorno.replaceAll(" diagnosticado com ", "\nDiagnosticado com:");
            retorno = retorno.replaceAll(" gramas/dia: ", "\nGramas/dia:");
            retorno = retorno.replaceAll(" grau máximo: ", "\nGrau Máximo:");
            retorno = retorno.replaceAll("Hemorragia peri-intraventricular antes do diagnóstico", "Hemorragia peri-intraventricular antes do diagnóstico:");
            retorno = retorno.replaceAll("Sepse antes do diagnóstico", "Sepse antes do diagnóstico:");
            Pattern padrao = Pattern.compile("Persistência do Canal Arterial:(.*?)Data do tratamento:", Pattern.DOTALL);
            Matcher matcher = padrao.matcher(retorno);
            String textoEspecifico ="";
            if (matcher.find()) {
                textoEspecifico = "Persistência do Canal Arterial:" + matcher.group(1).trim();                
            }
            Pattern padrao2 = Pattern.compile("Displasia Broncopulmonar:(.*?)Hemorragia", Pattern.DOTALL);
            Matcher matcher2 = padrao2.matcher(retorno);
            String textoEspecifico2 ="";
            if (matcher2.find()) {
                textoEspecifico2 = "Displasia Broncopulmonar:" + matcher2.group(1).trim();                
            }
            return retorno + textoEspecifico.replaceAll("sim", "sim\nClínico: ").replaceAll("tratamento clínico antes do diagnóstico", "tratamento clínico antes do diagnóstico\nCirurgico: ")
            			   +"\n"+textoEspecifico2.replaceAll("Diagnosticado com:", "28 Dias de Vida: ").replaceAll("vida", "vida\n36 Semanas: ");
        } 
        return "";
	}
	
	public Map<DadosRecemNascido, String> extractRecemNascidos(String texto) {
		String[] linhas = texto.split("\n");
		Map<DadosRecemNascido, String> mapa = new LinkedHashMap<>();
		for (String linha : linhas) {
            String[] partes = linha.split(":");
            if (partes.length == 2) {
                String chave = partes[0].trim();
                String valor = extractValorRecemNascidos(partes[1].trim(), chave);                
                mapa.put(DadosRecemNascido.getByDescricao(chave), valor);
            }
        }
		return mapa;
	}
	
	private String extractValorRecemNascidos(String texto, String chave) {
		Pattern pattern = Pattern.compile("\\(\\s*[Xx]\\s*\\)\\s*(.*?)\\s*(?:\\(|$)");
		String valor1 = "";
		if(DadosRecemNascido.PESO_AO_NASCER.getDescricao().equals(chave)) {
			Pattern pattern2 = Pattern.compile("(\\d+g) (.*)");
	        Matcher matcher2 = pattern2.matcher(texto);
	        if (matcher2.matches()) {
	        	valor1 = matcher2.group(1);
	        }
		}
		if(DadosRecemNascido.INDICE_DE_APGAR.getDescricao().equals(chave)) {
			texto = texto.replace("(", "");
			texto = texto.replace(")", "");
		}
		if(DadosRecemNascido.GANHO_DE_PESO_PROPORCIONADO.getDescricao().equals(chave)) {
			texto = texto.replace("%  e em", "");
		}
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            String textoSelecionado = matcher.group(1).trim();
            if (!textoSelecionado.isEmpty()) {
               return valor1 + " " +textoSelecionado;
            }
            return "";
        }
        return texto.contains("( ") && texto.contains(" )") && DadosRecemNascido.getByDescricao(chave).getTemMarcacao() ? "" : texto;
	}
	
		
	
}
