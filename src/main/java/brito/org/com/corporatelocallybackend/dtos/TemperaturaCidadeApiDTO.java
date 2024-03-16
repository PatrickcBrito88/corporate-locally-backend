package brito.org.com.corporatelocallybackend.dtos;

import lombok.Data;

@Data
public class TemperaturaCidadeApiDTO {

    private String city;
    private double temperature;
    private double temperatureC;
    private double temperatureF;
}
