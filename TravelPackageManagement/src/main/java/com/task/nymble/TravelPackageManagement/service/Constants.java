package com.task.nymble.TravelPackageManagement.service;

public class Constants {

    public static final String WALLET_AMOUNT="amount";

    public enum MembershipType {
        STANDARD,
        GOLD,
        PREMIUM;

        public double getPrice(double originalPrice) {
            switch (this) {
                case GOLD:
                    return originalPrice * 0.9; // 10% discount
                case PREMIUM:
                    return 0; // Free for premium members
                default:
                    return originalPrice; // No discount for standard members and other cases
            }
        }
    }
}
