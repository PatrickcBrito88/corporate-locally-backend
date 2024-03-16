package brito.org.com.corporatelocallybackend.services;

import brito.org.com.corporatelocallybackend.dtos.TemperaturaAtualCidadeDTO;
import brito.org.com.corporatelocallybackend.dtos.TemperaturaCidadeApiDTO;
import brito.org.com.corporatelocallybackend.exception.model.CidadeNaoEncontradaException;
import brito.org.com.corporatelocallybackend.exception.model.NegocioException;
import brito.org.com.corporatelocallybackend.http.ConsultaTemperaturaCidadesHttp;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultaTemperaturaCidadeServiceImpl implements ConsultaTemperaturaCidadeService{

    private final ConsultaTemperaturaCidadesHttp consultaTemperaturaCidadesHttp;

    public ConsultaTemperaturaCidadeServiceImpl(ConsultaTemperaturaCidadesHttp consultaTemperaturaCidadesHttp) {
        this.consultaTemperaturaCidadesHttp = consultaTemperaturaCidadesHttp;
    }

    @Override
    public TemperaturaAtualCidadeDTO consultaTemperaturaCidadeApi(String nomeCidade) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key","0bbccdb927msh08e1ebaba66d054p18a1d1jsn050f852744c6");

        TemperaturaCidadeApiDTO temperaturaCidadeApiDTO =
                consultaTemperaturaCidadesHttp.consultaTemperaturaCidades(nomeCidade, headers);

        if (temperaturaCidadeApiDTO.getCity().equals("")){
            throw new CidadeNaoEncontradaException(nomeCidade);
        }

        return new TemperaturaAtualCidadeDTO(
                temperaturaCidadeApiDTO.getCity(),
                temperaturaCidadeApiDTO.getTemperatureC(),
                temperaturaCidadeApiDTO.getTemperatureF());
    }

}
