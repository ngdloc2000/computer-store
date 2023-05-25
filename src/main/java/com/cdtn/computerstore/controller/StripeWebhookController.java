package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.entity.Transactions;
import com.cdtn.computerstore.service.ITransactionsService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebhookController {

    private final ITransactionsService iTransactionsService;
    public StripeWebhookController(ITransactionsService iTransactionsService) {
        this.iTransactionsService = iTransactionsService;
    }

    @PostMapping
    public ResponseEntity<?> handleWebhookEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String signature) {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, signature, "whsec_0mLarkK5Hxrw6lWIqi4D0iKbf4Foac5x");
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        switch (event.getType()) {
            case "checkout.session.completed":
                System.out.println("customer has successfully authorized the debit payment");
//                StripeObject session = stripeObject;
                Session session = (Session) event.getDataObjectDeserializer().getObject().get();

                Transactions transactions = new Transactions();
                transactions.setPaymentHash(session.getId());
                transactions.setResponseData(session.toJson());
                iTransactionsService.saveDataResponse(transactions);

                //update order
                //+ status -> pending
                if (Objects.equals(session.getPaymentStatus(), "paid")) {
                    // Fulfill the purchase
                    //set status "PENDING -> SUCCESS"
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