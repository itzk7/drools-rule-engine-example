package com.example.demo.model;

import java.util.Map;

public class StateTransitionDefinition {
    Map<String, Object> attribute;
    String currentRole;
    String destinationState;

    public StateTransitionDefinition(Map<String, Object> attribute, String currentRole, String destinationState) {
        this.attribute = attribute;
        this.currentRole = currentRole;
        this.destinationState = destinationState;
    }

    public void setState() {
        attribute.put("state", destinationState);
    }

    public void print() {
        System.out.println(attribute.toString());
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }
}
