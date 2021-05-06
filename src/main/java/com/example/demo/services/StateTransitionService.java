package com.example.demo.services;

import com.example.demo.model.StateTransitionDefinition;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StateTransitionService {
    @Autowired
    private KieContainer kieContainer;

    // will be used for validation
    public static Map<String, List<String>> stateTransitions = new HashMap<String, List<String>>() {
        {
            put("draft", Collections.singletonList("published"));
            put("published", Arrays.asList("attested", "invalid"));
        }
    };
    //Read property by id -> jsonNode -> hashmap -> stateTransitionDefinition -> applyrules -> jsonNode -> update

    // publish: true

    // draft: true

    public void makeTransition(final String destinationState, final Map<String, Object> property, List<String> fields, final String currentRole) {
        List<StateTransitionDefinition> stateTransitionDefinitions = fields.stream().map(field -> new StateTransitionDefinition((Map<String, Object>) property.get(field), currentRole, destinationState)).collect(Collectors.toList());
        for (StateTransitionDefinition state : stateTransitionDefinitions) {
            state.print();
        }
        stateTransitionDefinitions.forEach(stateTransitionDefinition -> {
            KieSession kieSession = kieContainer.newKieSession();
            kieSession.insert(stateTransitionDefinition);
            kieSession.fireAllRules();
            kieSession.dispose();
        });


        for (StateTransitionDefinition state : stateTransitionDefinitions) {
            state.print();
        }
    }
}
