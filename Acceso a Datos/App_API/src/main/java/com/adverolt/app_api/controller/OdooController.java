package com.adverolt.app_api.controller;

import com.adverolt.app_api.service.OdooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odoo")
public class OdooController {

    @Autowired
    private OdooService odooService;

    @PostMapping("/create")
    public String createPartner(@RequestParam String name,
                                @RequestParam String email) {
        try {
            // Llamada al servicio para crear el partner
            String partnerId = odooService.createPartner(name, email);
            return "Nuevo partner creado con ID: " + partnerId;
        } catch (Exception e) {
            // Manejo de errores
            return "Error al crear el partner: " + e.getMessage();
        }
    }

    @GetMapping("/authenticate")
    public String authenticate() {
        return odooService.authenticate();
    }

    @GetMapping("/partners")
    public String searchPartners() {
        int uid = Integer.parseInt(odooService.authenticate());
        return odooService.searchPartners(uid);
    }

    @PostMapping("/premium/{partnerId}")
    public ResponseEntity<String> updatePartnerPremium(@PathVariable("partnerId") int partnerId) {
        try {
            String result = odooService.updatePartnerPremium(partnerId);
            return ResponseEntity.ok("Partner actualizado: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar partner: " + e.getMessage());
        }
    }

    @GetMapping("/generate-pdf/{userId}")
    public ResponseEntity<String> generateInvoicePdf(@PathVariable("userId") Integer userId) {
        // Verificar si se ha recibido un ID de usuario
        if (userId == null) {
            return ResponseEntity.badRequest().body("El userId es requerido");
        }

        // Llamar al servicio Odoo para obtener el PDF de la factura asociada al usuario
        String pdfUrl = odooService.getInvoicePdfForUser(userId);

        if (pdfUrl == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo generar el PDF");
        }

        // Devolver la URL del PDF o el PDF en base64
        return ResponseEntity.ok(pdfUrl);
    }
}