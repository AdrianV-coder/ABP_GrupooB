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
import java.util.Base64;

@Service
public class OdooService {

    private final String odooUrl = "http://4.211.191.132:8069";  // La URL base de tu instancia de Odoo

    private final String ODOO_URL = "http://4.211.191.132:8069/jsonrpc";
    private final String DB_NAME = "abp_grupob";
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    private RestTemplate restTemplate = new RestTemplate();

    // Método para autenticar en Odoo
    public String authenticate() {
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

    private Integer getPartnerIdByEmail(String email, int uid) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");
        params.put("args", new Object[] {
                DB_NAME,
                uid,
                PASSWORD,
                "res.partner",
                "search",
                new Object[][] {{"email", "=", email}}
        });

        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);
            if (response.getBody() == null) {
                throw new RuntimeException("Respuesta nula de Odoo al buscar el partner por email");
            }

            Object result = response.getBody().get("result");
            if (result == null || !(result instanceof java.util.List)) {
                return null;
            }

            java.util.List<Integer> partnerIds = (java.util.List<Integer>) result;
            return partnerIds.isEmpty() ? null : partnerIds.get(0);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String obtenerFacturaUrlPorCorreo(String email, int uid) {
        try {
            // Crear un RestTemplate o usar alguna librería específica para la comunicación con Odoo (por ejemplo, xmlrpc o JSON-RPC)
            RestTemplate restTemplate = new RestTemplate();

            // Aquí realizaríamos la lógica para consultar la base de datos de Odoo, encontrar la factura asociada al correo
            // En este ejemplo asumimos que existe un endpoint para obtener las facturas por correo

            String url = odooUrl + "/get-invoice-by-email";
            // Enviar el correo y UID para obtener el ID de la factura asociada
            String invoiceId = restTemplate.getForObject(url + "?email=" + email + "&uid=" + uid, String.class);

            if (invoiceId != null) {
                // Si se encuentra la factura, generar la URL para el reporte
                return odooUrl + "/report/html/account.report_invoice/" + invoiceId;
            } else {
                return null; // Si no se encuentra la factura
            }
        } catch (Exception e) {
            // Manejo de excepciones (log, rethrow, etc.)
            return null;
        }
    }


    // Método para crear un nuevo partner
    public String createPartner(String name, String email) {
        String authResult = authenticate();
        if (authResult == null) {
            throw new RuntimeException("No se pudo autenticar en Odoo");
        }
        int uid = Integer.parseInt(authResult);

        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");

        Map<String, Object> values = new HashMap<>();
        values.put("name", name);
        values.put("email", email);
        values.put("x_premium", true);

        Object[] args = new Object[] {
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

            return result.toString();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el partner: " + e.getMessage());
        }
    }

    // Método para obtener el ID de la factura de un partner
    public Integer getInvoiceIdForUser(int uid, int userId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "object");
        params.put("method", "execute");
        params.put("args", new Object[] {
                DB_NAME,
                uid,
                PASSWORD,
                "account.move",
                "search",
                new Object[][] {{"partner_id", "=", userId}, {"move_type", "=", "out_invoice"}}
        });

        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);
            if (response.getBody() == null) {
                throw new RuntimeException("Respuesta nula de Odoo al buscar la factura");
            }

            Object result = response.getBody().get("result");
            if (result == null || !(result instanceof java.util.List)) {
                return null;
            }

            java.util.List<Integer> invoiceIds = (java.util.List<Integer>) result;
            return invoiceIds.isEmpty() ? null : invoiceIds.get(0);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para generar el PDF de la factura
    public byte[] generatePdfForInvoice(int invoiceId, int uid) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("jsonrpc", "2.0");
        payload.put("method", "call");

        Map<String, Object> params = new HashMap<>();
        params.put("service", "report");
        params.put("method", "get_pdf");
        params.put("args", new Object[] {
                DB_NAME,
                uid,
                PASSWORD,
                "account.move",
                new Integer[] {invoiceId}
        });

        payload.put("params", params);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(ODOO_URL, request, Map.class);
            if (response.getBody() == null) {
                throw new RuntimeException("Respuesta nula de Odoo al generar el PDF");
            }

            Object result = response.getBody().get("result");
            if (result == null) {
                return null;
            }

            String base64Pdf = result.toString();
            return Base64.getDecoder().decode(base64Pdf);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return null;
        }
    }
}
