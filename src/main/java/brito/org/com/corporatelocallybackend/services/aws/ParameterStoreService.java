package brito.org.com.corporatelocallybackend.services.aws;

public interface ParameterStoreService {

    /**
     *
     * @param path  O path para busca do valor no Parameter Store
     * @return      O valor do parameter Store de acordo com o path informado
     */
    String buscaValorParameterStore(String path);
}
