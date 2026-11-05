package model;

public interface AuthorizationStrategy {
    void authenticate(PaymentStrategy paymentStrategy);
}
