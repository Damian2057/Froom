package com.froom.items.model.domain

enum class CategoryType(name: String, bodyPart: BodyPart, index: Int) {
    T_SHIRT_TOP("T-Shirt/Top", BodyPart.UPPER_PART, 0),
    TROUSERS("Trousers", BodyPart.LOWER_PART, 1),
    PULLOVER("Pullover", BodyPart.UPPER_PART, 2),
    DRESS("Dress", BodyPart.UPPER_PART, 3),
    COAT("Coat", BodyPart.UPPER_PART, 4),
    SANDALS("Sandals", BodyPart.SHOES, 5),
    SHIRT("Shirt", BodyPart.UPPER_PART, 6),
    SNEAKERS("Sneakers", BodyPart.SHOES, 7),
    BAG("Bag", BodyPart.ACCESSORY, 8),
    ANKLE_BOOTS("Ankle Boots", BodyPart.SHOES, 9),
    UNKNOWN("Unknown", BodyPart.UNKNOWN, -1)
}