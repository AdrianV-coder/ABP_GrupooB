package com.adverolt.app_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class OdooService {

    private final String ODOO_URL = "http://4.211.191.132:8069/jsonrpc";
    private final String DB_NAME = "abp_grupob";
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    private RestTemplate restTemplate = new RestTemplate();

    public String authenticate() {
        // Construir el payload según JSON-RPC
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "common");
        params.put("method", "login");
        params.put("args", new Object[]{DB_NAME, USERNAME, PASSWORD});

        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);

            if (response.getBody() == null) {
                throw new RuntimeException("La respuesta de Odoo es nula");
            }

            Object result = response.getBody().get("result");
            if (result == null) {
                throw new RuntimeException("Autenticación fallida: respuesta inesperada de Odoo - " + response.getBody());
            }

            return result.toString();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String searchPartners(int uid) {
        // Construir el payload según JSON-RPC
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");
        params.put("args", new Object[]{DB_NAME, uid, PASSWORD, "res.partner", "search", new Object[][]{}});

        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);

            if (response.getBody() == null) {
                throw new RuntimeException("La respuesta de Odoo es nula");
            }

            Object result = response.getBody().get("result");
            if (result == null) {
                throw new RuntimeException("Error en la búsqueda de partners: respuesta inesperada de Odoo - " + response.getBody());
            }

            return result.toString();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createPartner(String name, String email) {
        // Primero se autentica en Odoo
        String authResult = authenticate();
        if (authResult == null) {
            throw new RuntimeException("No se pudo autenticar en Odoo");
        }
        int uid = Integer.parseInt(authResult);

        // Construir el payload para la creación del partner
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");

        // Los valores para el nuevo partner: puedes agregar más campos si es necesario
        Map<String, Object> values = new HashMap<>();
        values.put("name", name);  // Nombre del partner
        values.put("email", email);  // Correo electrónico del partner
        values.put("x_premium", true);  // Para establecer el usuario como premium desde el inicio

        // Armar los argumentos según el protocolo JSON-RPC de Odoo:
        // [db, uid, password, modelo, método, {values}]
        Object[] args = new Object[]{
                DB_NAME,
                uid,
                PASSWORD,
                "res.partner",
                "create",
                values
        };

        params.put("args", args);
        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);
            if (response.getBody() == null) {
                throw new RuntimeException("Respuesta nula de Odoo al crear el partner");
            }
            Object result = response.getBody().get("result");
            if (result == null) {
                throw new RuntimeException("Error al crear el partner: " + response.getBody());
            }
            return result.toString();  // Esto devolverá el ID del nuevo partner creado
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String updatePartnerPremium(int partnerId) {
        // Primero se autentica en Odoo
        String authResult = authenticate();
        if (authResult == null) {
            throw new RuntimeException("No se pudo autenticar en Odoo");
        }
        int uid = Integer.parseInt(authResult);

        // Construir el payload para la actualización (método write)
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");

        // Los valores a actualizar: premium en true
        Map<String, Object> values = new HashMap<>();
        values.put("x_premium", true);

        // Armar los argumentos según el protocolo JSON-RPC de Odoo:
        // [db, uid, password, modelo, método, [ids], {values}]
        Object[] args = new Object[]{
                DB_NAME,
                uid,
                PASSWORD,
                "res.partner",
                "write",
                new int[]{partnerId},
                values
        };

        params.put("args", args);
        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);
            if (response.getBody() == null) {
                throw new RuntimeException("Respuesta nula de Odoo al actualizar");
            }
            Object result = response.getBody().get("result");
            if (result == null) {
                throw new RuntimeException("Error en la actualización: " + response.getBody());
            }
            return result.toString();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

}