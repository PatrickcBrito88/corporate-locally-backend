package brito.org.com.corporatelocallybackend.services;

import brito.org.com.corporatelocallybackend.dtos.TemperaturaAtualCidadeDTO;
import brito.org.com.corporatelocallybackend.dtos.TemperaturaCidadeApiDTO;

public interface ConsultaTemperaturaCidadeService {

    /**
     * @param nomeCidade      nome da cidade a ser consultada
     * @return          objeto {@link TemperaturaCidadeApiDTO} contendo as temperaturas em Celsius e Fahrenheit
     */
    TemperaturaAtualCidadeDTO consultaTemperaturaCidadeApi(String nomeCidade) throws Exception;
}
