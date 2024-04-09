package brito.org.com.corporatelocallybackend.config.ssm.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ParameterStoreConfiguration {


    @Value("${path_secrets_manager_token_temperatura_cidade}")
    private String pathSecretsManagerTokenTemperaturaCidade;

    public String getPathSecretsManagerTokenTemperaturaCidade() {
        return pathSecretsManagerTokenTemperaturaCidade;
    }

    public void setPathSecretsManagerTokenTemperaturaCidade(String pathSecretsManagerTokenTemperaturaCidade) {
        this.pathSecretsManagerTokenTemperaturaCidade = pathSecretsManagerTokenTemperaturaCidade;
    }

}
