package br.forsign.allo.provedor.domain;

public enum TipoUpload {
    SERVICO("Servico"),
    PERFIL("Perfil");

    private String description;

    TipoUpload(String description){
        this.description = description;
    }
}
