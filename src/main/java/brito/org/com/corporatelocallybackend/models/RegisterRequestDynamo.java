package brito.org.com.corporatelocallybackend.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterRequestDynamo {

    public String nomeRequisitante;
    public LocalDateTime dataHoraRequisicao;
    public ResultadoRequisicao resultadoRequisicao;

    public RegisterRequestDynamo(String nomeRequisitante, ResultadoRequisicao resultadoRequisicao) {
        this.nomeRequisitante = nomeRequisitante;
        this.dataHoraRequisicao = LocalDateTime.now();
        this.resultadoRequisicao = resultadoRequisicao;
    }
}
