package br.com.pgc.model;

public enum DadosRetinopatiaPrematuridade {
    NOME(-1, "Nome", false),
    HC(-1, "HC", false),
    DATA_DE_NASCIMENTO(3, "Data de nascimento", false),
    CH_ATE_6_SEMANA(32, "CH até a 6ª semana", true),
    CH_ATE_2_SEMANA(31, "CH até a 2ª semana", true),
    VM_ATE_2_SEMANA(25, "VM até a 2ª semana", true),
    VM_ATE_6_SEMANA(26, "VM até a 6ª semana", true),
    DATA_DO_PRIMEIRO_EXAME(48, "Data do primeiro exame", false),
    IC_PRIMEIRO_EXAME(49, "IC no primeiro exame", false),
    IG_CORRIGIDA_PRIMEIRO_EXAME(50, "IG corrigida no primeiro exame", false),
    NUMERO_DE_EXAMES_ATE_ALTA(51, "Números de exames feitos até alta", false),
    ROP_PRESENTE(52, "ROP presente", true),
    DATA_DO_DIAGNOSTICO(53, "Data do diagnóstico", false),
    IC_DIAGNOSTICO(54, "IC no diagnóstico", false),
    IG_CORRIGIDA_DIAGNOSTICO(55, "IG corrigida no diagnóstico", false),
    SE_ROP_PRESENTE(56, "Se ROP presente, o diagnóstico se deu", true),
    HOUVE_PIORA_DA_DOENCA(57, "Houve piora da doença", true),
    PIORA_DA_DOENCA_NO(58, "Piora da doença no", true),
    ESTAGIO(59, "Estágio", true),
    ZONA(60, "Zona", true),
    PRESENCA_DOENCA_PLUS(61, "Presença de doença plus", true),
    EXTENSAO(62, "Extensão", true),
    OLHOS(63, "Olhos", true),
    TRATAMENTO(64, "Tratamento", true),
    DATA_DO_TRATAMENTO(65, "Data do tratamento", false),
    IC_TRATAMENTO(66, "IC no tratamento", false),
    IG_CORRIGIDA_TRATAMENTO(67, "IG corrigida no tratamento", false),
    TEMPO_ENTRE_DIAGNOSTICO_INTERVENCAO_TRATAMENTO(68, "Tempo entre diagnóstico de intervenção e tratamento", false),
    REATIVACAO(69, "Reativação", true),
    NOVA_INTERVENCAO(70, "Nova intervenção", true),
    ROP_SCORE(71, "ROPScore", false),
    PESO_DE_NASCIMENTO(-1, "Peso de nascimento", false),
    IDADE_GESTACIONAL(-1, "Idade gestacional", false),
    PESO_2_SEMANA(-1, "Peso na 2ª semana", false),
    PESO_6_SEMANA(-1, "Peso na 6ª semana", false);

	private String descricao;
    private int coluna;
    private boolean temMarcacao;
    
    
    public static DadosRetinopatiaPrematuridade getByDescricao(String nomeProcurado){
    	for (DadosRetinopatiaPrematuridade dado : DadosRetinopatiaPrematuridade.values()) {
    		if (dado.getDescricao().equals(nomeProcurado)) {	
    			return dado;
    		}
    	}
		return null;	    	
    }
    

    DadosRetinopatiaPrematuridade(int coluna, String descricao, boolean temMarcacao) {
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