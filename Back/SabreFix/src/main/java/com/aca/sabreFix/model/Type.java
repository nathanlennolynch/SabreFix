package com.aca.sabreFix.model;

public enum Type {
    SWORD, AXE, MACE, STAFF;
    public static Type convertStringToType(String value) {
        Type myType = null;

        for (Type type : Type.values()) {
            if (type.toString().equalsIgnoreCase(value)) {
                myType = type;
                break;
            }
        }

        return myType;
    }
}
