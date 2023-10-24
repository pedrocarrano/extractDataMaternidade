package br.com.pgc.model;

public enum DadosRecemNascido {
    NOME(-1, "Nome", false),
    INDICE_DE_APGAR(-1, "Índice de Apgar", true),
    HC(1, "HC", true),
    DATA_DE_NASCIMENTO(9, "Data de nascimento", false),
    SEXO(10, "Sexo", true),
    IDADE_GESTACIONAL(11, "Idade gestacional", true),
    PESO_AO_NASCER(12, "Peso ao nascer (g)", true),
    PIG_AIG_GIG(13, "Peso ao nascer (g)", true),
    PESO_2_SEMANA(14, "Peso na 2ª semana", false),
    PESO_6_SEMANA(15, "Peso na 6ª semana", false),
    GANHO_DE_PESO_PROPORCIONADO(16, "Ganho de Peso Proporcionado", false),
    PESO_MINIMO(17, "Peso mínimo", false),
    DIAS_VIDA(18, "dias de vida", false),	
    TEMPO_RECUPERACAO_NUTRICIONAL(19, "Tempo de recuperação nutricional", false),
    APGAR1(20, "APGAR1", true),
    APGAR5(21, "APGAR5", true),
    REANIMACAO(22, "Reanimação", true),
    VENTILACAO_MECANICA(23, "Uso de Ventilação Mecânica antes do diagnóstico", true),
    QTD_VENTILACAO_MECANICA(24, "Qtd de dias uso de Ventilação Mecânica antes do diagnóstico", true),	  
    OXIGENOTERAPIA(27, "Uso de Oxigenoterapia antes do diagnóstico", true),
    QTD_OXIGENOTERAPIA(28, "Uso de Oxigenoterapia antes do diagnóstico", true),
    CONCENTRADO_HEMACIAS(29, "Concentrado de hemácias antes do diagnóstico", true),
    QTD_DIAS_CONCENTRADO_HEMACIAS(30, "Qtd Dias de Concentrado de hemácias antes do diagnóstico", true),
    CANAL_ARTERIAL(33, "Persistência do Canal Arterial", true),
    CLINICO(34, "Clínico", true), 
    CIRURGICO(35, "Cirurgico", true), 
    DISPLASIA_BRONCOPULMONAR(36, "Displasia Broncopulmonar", true),
    DIAS_VIDA_28(37, "28 Dias de Vida", true),
    SEMANAS_36(38, "36 Semanas", true),
    HEMORRAGIA_INTRAVENTRICULAR(39, "Hemorragia peri-intraventricular antes do diagnóstico", true),
    GRAU_MAXIMO(40, "Grau Máximo", true),
    SEPSE(41, "Sepse antes do diagnóstico", true),
    COMPROVADA_CULTURA_POSITIVA(43, "Comprovada por cultura positiva", true),
    INICIO_TRATAMENTO(44, "Inicio do tratamento", true),
    FIM_TRATAMENTO(45, "Fim do tratamento", true),
    TEMPO_INTERNACAO(46, "Tempo de internação", true),  
    OBITO(47, "Óbito", true),
    DATA_TRATAMENTO(-1, "Data do tratamento", true),
    IC_NO_DIAGNOSTICO(-1, "IC no diagnóstico", false),
    GRAMAS_POR_DIA(-1, "Gramas/dia", false),
    FATORES_ASSOCIADOS(-1, "Os fatores associados serão contados até", false),
	DIAGNOSTICADO_COM(-1, "Diagnosticado com", true);

	private String descricao;
    private int coluna;
    private boolean temMarcacao;
    
    
    public static DadosRecemNascido getByDescricao(String nomeProcurado){
    	for (DadosRecemNascido dado : DadosRecemNascido.values()) {
    		if (dado.getDescricao().equals(nomeProcurado)) {	
    			return dado;
    		}
    	}
		return null;	    	
    }
    

    DadosRecemNascido(int coluna, String descricao, boolean temMarcacao) {
    	 this.descricao = descricao;
	     this.coluna = coluna;
	     this.temMarcacao = temMarcacao;
	}

    public String getDescricao() {
        return descricao;
    }
    
    public int getColuna() {
    	return coluna;
    }
    
    public boolean getTemMarcacao() {
    	return temMarcacao;
    }
    
}