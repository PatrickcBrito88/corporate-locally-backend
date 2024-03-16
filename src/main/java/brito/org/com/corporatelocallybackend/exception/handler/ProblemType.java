package brito.org.com.corporatelocallybackend.exception.handler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema","Erro de sistema"),
    RECURSO_NAO_ENCONTRADO ("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_NEGOCIO("/erro-de-negocio", "Erro de negócio"),
    ERRO_VALIDACAO("/erro-de-validacao", "Erro de validação");

    private final String title;
    private final String uri;

    ProblemType (String path, String title){
        this.title=title;
        this.uri="https://corporate-apigateway.com.br/locally-backend" + path;
    }

}
