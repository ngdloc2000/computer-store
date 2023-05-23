package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.entity.Transactions;
import com.cdtn.computerstore.service.ITransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/paypal")
public class PaypalWebhookController {

    private final ITransactionsService iTransactionsService;
    private boolean isAuthentic = true;
    public PaypalWebhookController(ITransactionsService iTransactionsService) {
        this.iTransactionsService = iTransactionsService;
    }

    @PostMapping
    public ResponseEntity<?> handleWebhookEvent(@RequestBody String payload) {
        // ...

        if (isAuthentic) {
            try {
                // Process the webhook event here

                // Save the JSON data to the database
                Transactions webhookResponse = new Transactions();
                webhookResponse.setResponseData(payload);
                iTransactionsService.saveDataResponse(webhookResponse);

                // Return a successful response
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                // Handle any exceptions that occur during event processing
                // Log the error or perform appropriate error handling
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            // Handle invalid or unauthorized webhook events
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
