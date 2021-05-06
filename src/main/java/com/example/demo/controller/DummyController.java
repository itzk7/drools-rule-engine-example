package com.example.demo.controller;

import com.example.demo.services.StateTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@Controller
public class DummyController {
    @Autowired
    StateTransitionService stateTransitionService;
    @RequestMapping(value = "/hit", method = RequestMethod.GET)
    public ResponseEntity<Object> trigger() {
        HashMap<String, Object> data = new HashMap<String, Object>() {
            {
                put("phone", new HashMap<String, Object>() {
                    {
                        put("state", "published");
                        put("value", "909090909090");
                    }
                });
                put("fromYear", new HashMap<String, Object>() {
                    {
                        put("state", "published");
                        put("value", "2019");
                    }
                });
                put("toYear", new HashMap<String, Object>() {
                    {
                        put("state", "published");
                        put("value", "2024");
                    }
                });
            }
        };
        stateTransitionService.makeTransition(
                "attested",
                data,
                Arrays.asList("phone", "fromYear"),
                "admin"
        );
        stateTransitionService.makeTransition(
                "rejected",
                data,
                Collections.singletonList("toYear"),
                "admin"
        );
        return null;
    }
}
