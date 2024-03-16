package brito.org.com.corporatelocallybackend.exception.model;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(String nomeCidade) {
        super(String.format("Dados de temperatura não encontrados para a cidade %s ", nomeCidade));
    }

}
