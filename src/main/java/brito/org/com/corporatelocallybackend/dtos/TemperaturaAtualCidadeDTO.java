package brito.org.com.corporatelocallybackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TemperaturaAtualCidadeDTO {
    @JsonProperty("cidade")
    private String nomeCidade;

    @JsonProperty("temperatura_celsius")
    private double temperaturaCelsius;

    @JsonProperty("temperatura_fahreheint")
    private double temperaturaFahreheint;

    public TemperaturaAtualCidadeDTO(String nomeCidade, double temperaturaCelsius, double temperaturaFahreheint) {
        this.nomeCidade = nomeCidade;
        this.temperaturaCelsius = temperaturaCelsius;
        this.temperaturaFahreheint = temperaturaFahreheint;
    }
}
