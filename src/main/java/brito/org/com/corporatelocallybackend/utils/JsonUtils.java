package brito.org.com.corporatelocallybackend.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtils {

    /**
     * Extrai o valor associado a uma chave específica de um JsonNode e o retorna como String.
     *
     * @param jsonNode O JsonNode do qual o valor será extraído.
     * @param key      A chave cujo valor é desejado.
     * @return O valor como String, ou null se a chave não for encontrada.
     */
    public static String extractValueJson(JsonNode jsonNode, String key) {
        if (jsonNode != null && jsonNode.has(key)) {
            JsonNode valueNode = jsonNode.get(key);
            return valueNode.asText();
        } else {
            return null;
        }
    }
}
