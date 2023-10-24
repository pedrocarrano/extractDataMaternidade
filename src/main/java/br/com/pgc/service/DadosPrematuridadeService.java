package br.com.pgc.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.pgc.model.DadosRetinopatiaPrematuridade;

public class DadosPrematuridadeService {
	
	public String getDadosPrematuridade(String dadosPrematuridade) {

		String textoInicio = "DADOS REFERENTES À RETINOPATIA DA PREMATURIDADE";

		int indiceInicio = dadosPrematuridade.indexOf(textoInicio);

		if (indiceInicio != -1) {
			String retorno = dadosPrematuridade.substring(indiceInicio + textoInicio.length());
			retorno = retorno.replaceAll("HC:", "\nHC:");
			retorno = retorno.replaceAll("Data do primeiro exame:", "\nData do primeiro exame:");
			retorno = retorno.replaceAll("IC no primeiro exame:", "\nIC no primeiro exame:");
			retorno = retorno.replaceAll("Data do diagnóstico:", "\nData do diagnóstico:");
			retorno = retorno.replaceAll("IC no diagnóstico:", "\n IC no diagnóstico:");
			retorno = retorno.replaceAll("IC no tratamento:", "\nIC no tratamento:");
			retorno = retorno.replaceAll("Peso de nascimento =", "\nPeso de nascimento:");
			retorno = retorno.replaceAll("Idade gestacional =", "\nIdade gestacional:");
			retorno = retorno.replaceAll("CH até a 6ª semana", "\nCH até a 6ª semana:");
			retorno = retorno.replaceAll("CH até a 2ª semana", "\nCH até a 2ª semana:");
			retorno = retorno.replaceAll("VM até a 6ª semana", "\nVM até a 6ª semana:");
			retorno = retorno.replaceAll("VM até a 2ª semana", "\nVM até a 2ª semana:");
			retorno = retorno.replaceAll("Peso na 2ª semana =", "\nPeso na 2ª semana:");
			retorno = retorno.replaceAll("Peso na 6ª semana =", "\nPeso na 6ª semana:");
			retorno = retorno.replaceAll("Data do tratamento:", "\nData do tratamento:");
			retorno = retorno.replaceAll(", no ", "\nPiora da doença no:");
			retorno = retorno.replaceAll("com pior grau:", "com pior grau ");
			retorno = retorno.replaceAll("Se ROP presente, o diagnóstico se deu:", "").replaceAll("(?m)^[ \t]*\r?\n",
					"");
			String[] linhas = retorno.split("\n");
			linhas[7] = "Se ROP presente, o diagnóstico se deu: " + linhas[7];
			StringBuilder novoTexto = new StringBuilder();
			for (String linha : linhas) {
				novoTexto.append(linha).append("\n");
			}

			return novoTexto.toString();
		}
		return "";
	}
	
	public Map<DadosRetinopatiaPrematuridade, String> extractPrematuridade(String texto) {
		String[] linhas = texto.split("\n");
		Map<DadosRetinopatiaPrematuridade, String> mapa = new LinkedHashMap<>();
		for (String linha : linhas) {
			String[] partes = linha.split(":");
			if (partes.length == 2) {
				String chave = partes[0].trim();
				String valor = extractValorPrematuridade(partes[1].trim(), chave);                
				mapa.put(DadosRetinopatiaPrematuridade.getByDescricao(chave), valor);
			}
		}		
		return mapa;
	}
	
	private static String extractValorPrematuridade(String texto, String chave) {
		Pattern pattern = Pattern.compile("\\(\\s*[Xx]\\s*\\)\\s*(.*?)\\s*(?:\\(|$)");
        Matcher matcher = pattern.matcher(texto);
        while (matcher.find()) {
            String textoSelecionado = matcher.group(1).trim();
            if (!textoSelecionado.isEmpty()) {
               return textoSelecionado;
            }
            return "";
        }
        return texto !=null && texto.contains("( ") && texto.contains(" )") && DadosRetinopatiaPrematuridade.getByDescricao(chave).getTemMarcacao() ? "" : texto;
	}
	
}
