package utils;

public class DeliveryOptions {
    public static class ParcelSize {
        public static final String POSTABLE_OR_LETTER = "postable-size";
        public static final String STANDARD_PARCEL = "standrd-size";
    }

    public static class DeliverySpeed {
        public static final String STANDARD = "standard-speed";
        public static final String NEXT_DAY = "nextday-speed";
    }

    public static class DropOffOrCollection {
        public static final String PARCELSHOP_OR_LOCKER = "parcelshop-method";
    }

    public static class ParcelDeliveryLocation {
        public static final String TO_HOME = "courier-delivery-option";
        public static final String TO_PARCELSHOP = "parcelshop-delivery-option";
    }

    public static class Section {
        public static final String PARCEL_SIZE_TEXT = "Parcel size";
        public static final String PARCEL_SIZE_XPATH = "(//*[@class='card__title'])[1]";
        public static final String DELIVERY_SPEED_TEXT = "Delivery speed";
        public static final String DELIVERY_SPEED_XPATH = "(//*[@class='card__title'])[2]";
        public static final String DROP_OFF_OR_COLLECTION_TEXT = "Drop off or collection";
        public static final String DROP_OFF_OR_COLLECTION_XPATH = "(//*[@class='card__title'])[3]";
        public static final String PARCEL_DELIVERY_LOCATION_TEXT = "Parcel delivery location";
        public static final String PARCEL_DELIVERY_LOCATION_XPATH = "(//*[@class='card__title'])[4]";
    }
}
