package dev.diasz.model;

public enum SituacaoTarefa {

    EM_ANDAMENTO("Em Andamento"),
    CONCLUIDA("Concluida");

    private String descricao;

    private SituacaoTarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    

   

    
}
