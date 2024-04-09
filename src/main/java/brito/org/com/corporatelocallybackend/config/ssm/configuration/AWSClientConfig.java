package brito.org.com.corporatelocallybackend.config.ssm.configuration;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSClientConfig {

    @Value("${application.aws.secretsmanager.endpoint}")
    private String endpoint;

    @Value("${application.aws.secretsmanager.region}")
    private String region;

    @Bean
    public AWSSecretsManager awsSecretsManagerProvider() {
        AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
        AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
        clientBuilder.setEndpointConfiguration(config);
        return clientBuilder.build();
    }

}
