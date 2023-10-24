package br.com.pgc.model;

public enum DadosMaternos {
	NOME(-1, "Nome", false),
	HC(1, "HC", false),
	IDADE_MATERNA(2, "Idade materna", false),
	COR(3, "Cor", true),
	GESTACAO(4, "Gestação", true),
	HIPERTENSAO_ARTERIAL(5, "Hipertensão arterial crônica ou gestacional", true),
	DIABETES_MELLITUS(6, "Diabetes mellitus de qualquer tipo ou gestacional", true),
	CORTICOIDE_ANTENATAL(7, "Administração antenatal de corticóide", true),
	TIPO_DE_PARTO(8, "Tipo de parto", true),
    DATA_DE_NASCIMENTO(9, "Data de nascimento", false);

    private String descricao;
    private int coluna;
    private boolean temMarcacao;
    
    
    public static DadosMaternos getByDescricao(String nomeProcurado){
    	for (DadosMaternos dado : DadosMaternos.values()) {
    		if (dado.getDescricao().equals(nomeProcurado)) {	
    			return dado;
    		}
    	}
		return null;	    	
    }
    

    DadosMaternos(int coluna, String descricao, boolean temMarcacao) {
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



