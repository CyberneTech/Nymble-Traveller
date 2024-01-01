package com.task.nymble.TravelPackageManagement.service;

public class Constants {

    public static final String WALLET_AMOUNT="amount";
    public static final String ACTIVITY_NAME="activityName";
    public static final String PASSENGER_NUMBER="passengerNumber";
    public static final String PACKAGE_ID = "travelPackageId";

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
