package com.cdtn.computerstore.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/webhook/stripe")
public class StripeWebhookController {
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostMapping
    public ResponseEntity<?> handleWebhookEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String signature) {
        Stripe.apiKey = stripeApiKey;
        Event event = null;
        try {
//            event = Webhook.constructEvent(payload, signature, "whsec_0mLarkK5Hxrw6lWIqi4D0iKbf4Foac5x");
            event = Webhook.constructEvent(payload, signature, "whsec_6c342954c1f85f2bd43f84409b92f976d2e87e7e5c0e97a63fed37e7d065fc63");
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

//        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
//        StripeObject stripeObject = null;
//
//        if (dataObjectDeserializer.getObject().isPresent()) {
//            stripeObject = dataObjectDeserializer.getObject().get();
//        } else {
//            // Deserialization failed, probably due to an API version mismatch.
//            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
//            // instructions on how to handle this case, or return an error here.
//        }

        switch (event.getType()) {
            case "checkout.session.completed":
                System.out.println("customer has successfully authorized the debit payment");
//                StripeObject session = stripeObject;
                Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                System.out.println(session);
                System.out.println("pending");
                //update order
                //+ status -> pending
                if (Objects.equals(session.getPaymentStatus(), "paid")) {
                    // Fulfill the purchase
                    //set status "PENDING -> SUCCESS"
                    System.out.println("success");
                    System.out.println(session.getClientReferenceId());
                }
                // ...
                break;
            case "checkout.session.async_payment_succeeded":
                // ...
                break;
            // ... handle other event types
            default:
                // Unexpected event type
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}