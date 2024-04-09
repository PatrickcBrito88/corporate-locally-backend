package brito.org.com.corporatelocallybackend.services.aws;

import com.fasterxml.jackson.databind.JsonNode;

public interface SecretsManagerService {

    JsonNode buscarSecretsManager(String path);
}
