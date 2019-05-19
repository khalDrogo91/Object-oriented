package com.game.model;

public enum OutcomeType {
    STRIKE("Strike"),
    MULTITASKING("Multistrike"),
    RED_STRIKE("Red strike"),
    STRIKER_STRIKE("Striker strike"),
    DEFUNCT_COIN("Defunct coin"),
    NONE("None");

    private String outcomeType;

    OutcomeType(String type){
        this.outcomeType = type;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public static OutcomeType getOutcomeValue(String type){
        for(OutcomeType enumType : OutcomeType.values()){
            if(enumType.getOutcomeType().equalsIgnoreCase(type)){
                return enumType;
            }
        }
        return null;
    }

}
