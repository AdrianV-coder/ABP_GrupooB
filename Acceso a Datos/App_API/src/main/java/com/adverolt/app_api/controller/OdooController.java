package com.adverolt.app_api.controller;

import com.adverolt.app_api.service.OdooService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/odoo")
public class OdooController {

    @Autowired
    private OdooService odooService;

    private String partnerId;

    // 1. Endpoint para autenticar en Odoo
    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate() {
        try {
            String uid = odooService.authenticate();
            if (uid == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
            }
            return ResponseEntity.ok(Map.of("uid", uid));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error en autenticación: " + e.getMessage()));
        }
    }

    // 2. Endpoint para obtener la URL de la factura por correo electrónico
    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String, String>> obtenerFacturaPorCorreo(@PathVariable String email) {
        try {
            // Obtener el token de autenticación desde el servicio Odoo
            String authResult = odooService.authenticate();
            if (authResult == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No se pudo autenticar en Odoo"));
            }

            int uid = Integer.parseInt(authResult);  // Usuario autenticado

            // Obtener la URL de la factura en función del correo (debe retornar la URL directamente)
            String reportUrl = odooService.obtenerFacturaUrlPorCorreo(email, uid);

            if (reportUrl == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró factura para el correo proporcionado"));
            }

            // Responder con la URL
            return ResponseEntity.ok(Map.of("url", reportUrl));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al obtener la factura: " + e.getMessage()));
        }
    }

    // 3. Endpoint para crear un nuevo partner usando parámetros en la URL
    @PostMapping("/create-partner")
    public String createPartner(
            @RequestParam String name,
            @RequestParam String email) {
        try {
            partnerId = odooService.createPartner(name, email);

            return "Nuevo partner creado con ID: " + partnerId;

        } catch (Exception e) {
            return "Error al crear el partner: " + e.getMessage();
        }
    }

    @GetMapping("partner-id")
    public ResponseEntity<String> returnString() {
        System.out.println("El partner: " + partnerId);
        return ResponseEntity.ok(partnerId);
    }

    // 4. Endpoint para obtener la factura de un partner por su ID (esto también podría devolver la URL)
    @GetMapping("/invoice/{partnerId}")
    public ResponseEntity<?> getInvoice(@PathVariable int partnerId) {
        try {
            String authResult = odooService.authenticate();
            if (authResult == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo autenticar en Odoo.");
            }

            int uid = Integer.parseInt(authResult);
            Integer invoiceId = odooService.getInvoiceIdForUser(uid, partnerId);

            if (invoiceId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "No se encontró factura para el partner con ID " + partnerId));
            }

            // Generar la URL de la factura en formato HTML
            String reportUrl = "http://4.211.191.132:8069/report/html/account.report_invoice/" + invoiceId;

            return ResponseEntity.ok(Map.of("url", reportUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al obtener la factura: " + e.getMessage()));
        }
    }

    // 5. Endpoint para generar el PDF de una factura por su ID (Este endpoint ahora es innecesario si solo usamos URLs)
    @GetMapping("/invoice-pdf/{invoiceId}")
    public ResponseEntity<?> getInvoicePdf(@PathVariable int invoiceId) {
        try {
            String authResult = odooService.authenticate();
            if (authResult == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se pudo autenticar en Odoo.");
            }

            int uid = Integer.parseInt(authResult);
            byte[] pdfBytes = odooService.generatePdfForInvoice(invoiceId, uid);

            if (pdfBytes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo generar el PDF de la factura.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("factura_" + invoiceId + ".pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al generar el PDF: " + e.getMessage()));
        }
    }
}
