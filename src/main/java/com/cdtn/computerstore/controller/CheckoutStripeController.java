package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.order.request.OrderCheckoutRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/create-checkout-session")
public class CheckoutStripeController {
//    Stripe.apiKey = 'pk_test_51NBEtDGbLaki56uH8dbAEZJtpiUJ3E2DuqskReMLFNeWtuXDZMnDoQAhJrP9tJNQVo0LTglgwecDbkOMx45BSR7q00vM8QLRMv';
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostMapping
    public ResponseEntity<String> createCheckoutSession(@RequestBody List<OrderCheckoutRequest> orderCheckoutRequestList) {
        Stripe.apiKey = stripeApiKey;
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (OrderCheckoutRequest orderCheckoutRequest : orderCheckoutRequestList) {
            lineItems.add(
                SessionCreateParams.LineItem.builder()
                    .setPriceData(
                        SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("usd")
                            .setProductData(
                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(orderCheckoutRequest.getProductName())
                                .addImage(orderCheckoutRequest.getImageProduct())
                                .build()
                            )
                            .setUnitAmount(orderCheckoutRequest.getAmount()) // Amount in cents
                            .build()
                    )
                    .setQuantity(orderCheckoutRequest.getQuantity())
                    .build()
            );
        }
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addAllLineItem(lineItems)
                .setSuccessUrl("https://quanna.shop")
                .setCancelUrl("https://quanna.shop")
                .build();
//        SessionCreateParams params = SessionCreateParams.builder()
//                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setClientReferenceId(checkoutRequest.getClientId())
//                .setSuccessUrl("https://quanna.shop")
//                .setCancelUrl("https://quanna.shop")
//                .addLineItem(SessionCreateParams.LineItem.builder()
//                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
//                                .setCurrency(checkoutRequest.getCurrency())
//                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                        .setName(checkoutRequest.getProductName())
//                                        .addImage(checkoutRequest.getImageProduct())
//                                        .build())
//                                .setUnitAmount(checkoutRequest.getAmount())
//                                .build())
//                        .setQuantity(checkoutRequest.getQuantity())
//                        .build())
//                .build();

        Session session;
        try {
            session = Session.create(params);
            return ResponseEntity.ok(session.getId());
        } catch (StripeException e) {
            System.out.println(e);
            // Handle any Stripe API exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Checkout Session");
        }
    }
}