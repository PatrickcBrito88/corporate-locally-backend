package brito.org.com.corporatelocallybackend.resources;

import brito.org.com.corporatelocallybackend.dtos.TemperaturaAtualCidadeDTO;
import brito.org.com.corporatelocallybackend.services.ConsultaTemperaturaCidadeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${application.path}/cidades")
public class ConsultaCidadesResource {

    private final ConsultaTemperaturaCidadeService consultaTemperaturaCidadeService;

    public ConsultaCidadesResource(ConsultaTemperaturaCidadeService consultaTemperaturaCidadeService) {
        this.consultaTemperaturaCidadeService = consultaTemperaturaCidadeService;
    }

    @GetMapping("/consulta-temperatura-atual")
    public TemperaturaAtualCidadeDTO consultaTemperaturaCidade(@RequestParam String nomeCidade) throws Exception {
       return consultaTemperaturaCidadeService.consultaTemperaturaCidadeApi(nomeCidade);
    }

}
