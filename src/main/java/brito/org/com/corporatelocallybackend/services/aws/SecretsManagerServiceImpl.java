package brito.org.com.corporatelocallybackend.services.aws;

import brito.org.com.corporatelocallybackend.exception.model.NegocioException;
import brito.org.com.corporatelocallybackend.utils.MessageUtils;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SecretsManagerServiceImpl implements SecretsManagerService {

    private final AWSSecretsManager client;

    public SecretsManagerServiceImpl(AWSSecretsManager client) {
        this.client = client;
    }

    @Override
    public JsonNode buscarSecretsManager(String secretPath) {
        ObjectMapper objectMapper = new ObjectMapper();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretPath);

        GetSecretValueResult getSecretValueResponse = null;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw new NegocioException("Erro");
        }

        if (getSecretValueResponse == null) {
            throw new NullPointerException("Nullpointer");
        }

        String secrets = getSecretValueResponse.getSecretString();
        if (secrets != null) {
            try {
                JsonNode secretsJson = objectMapper.readTree(secrets);
                return secretsJson;
            } catch (IOException e) {
                throw new NegocioException(
                        MessageUtils.buscaMensagemValidacao(
                                "secrets.manager.credenciais.erro.buscar", secretPath, e
                        )
                );
            }
        } else {
            throw new NullPointerException(MessageUtils.buscaMensagemValidacao("credencial.nao.encontrada"));
        }
    }

}
