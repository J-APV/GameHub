package com.example.application.views.store;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("sk_test_51OKcDGJqUTWxqEsite32OmpCkHPjrk4UT4KM5nFBjnTxImpsybmeiKS2NmMSyPpWUXL9AoCA1K3L7T41HPHHOVHC002BuOEiQK")
    private String stripeSecretKey;

    public String createPaymentIntent(int amount, String currency) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        PaymentIntent intent = PaymentIntent.create(
                new PaymentIntentCreateParams.Builder()
                        .setAmount((long) amount)
                        .setCurrency(currency)
                        .build()
        );

        return intent.getClientSecret();
    }
}
