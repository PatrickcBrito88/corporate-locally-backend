package brito.org.com.corporatelocallybackend.http;

import brito.org.com.corporatelocallybackend.dtos.TemperaturaCidadeApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "consulta-temperaturas-cidade", url = "${consulta.temperaturas.cidades.url}")
public interface ConsultaTemperaturaCidadesHttp {

    /**
     *
     * @param city      nome da cidade a ser consultada
     * @param headers   headers da requisição contendo o api-key
     * @return          objeto {@link TemperaturaCidadeApiDTO} contendo as temperaturas em Celsius e Fahrenheit
     */
    @GetMapping
    TemperaturaCidadeApiDTO consultaTemperaturaCidades(@RequestParam String city,
                                                       @RequestHeader Map<String, String> headers);

}
