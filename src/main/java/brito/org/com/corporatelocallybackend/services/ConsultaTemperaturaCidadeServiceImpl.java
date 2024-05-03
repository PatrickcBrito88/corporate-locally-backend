package brito.org.com.corporatelocallybackend.services;

import brito.org.com.corporatelocallybackend.config.ssm.configuration.ParameterStoreConfiguration;
import brito.org.com.corporatelocallybackend.dtos.TemperaturaAtualCidadeDTO;
import brito.org.com.corporatelocallybackend.dtos.TemperaturaCidadeApiDTO;
import brito.org.com.corporatelocallybackend.exception.model.CidadeNaoEncontradaException;
import brito.org.com.corporatelocallybackend.http.ConsultaTemperaturaCidadesHttp;
import brito.org.com.corporatelocallybackend.services.aws.SecretsManagerService;
import brito.org.com.corporatelocallybackend.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultaTemperaturaCidadeServiceImpl implements ConsultaTemperaturaCidadeService{

    private final ConsultaTemperaturaCidadesHttp consultaTemperaturaCidadesHttp;
    private final ParameterStoreConfiguration parameterStoreConfiguration;
    private final SecretsManagerService secretsManagerService;

    public ConsultaTemperaturaCidadeServiceImpl(ConsultaTemperaturaCidadesHttp consultaTemperaturaCidadesHttp,
                                                ParameterStoreConfiguration parameterStoreConfiguration,
                                                SecretsManagerService secretsManagerService) {
        this.consultaTemperaturaCidadesHttp = consultaTemperaturaCidadesHttp;
        this.parameterStoreConfiguration = parameterStoreConfiguration;
        this.secretsManagerService = secretsManagerService;
    }

    @Override
    public TemperaturaAtualCidadeDTO consultaTemperaturaCidadeApi(String nomeCidade) throws Exception {
        String pathSecretToken = parameterStoreConfiguration.getPathSecretsManagerTokenTemperaturaCidade();
        JsonNode tokenJson = secretsManagerService.buscarSecretsManager(pathSecretToken);
        String token = JsonUtils.extractValueJson(tokenJson, "token");

        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key", token);

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
