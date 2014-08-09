package com.ofg.microservice.gui.results

import groovy.transform.TypeChecked
import org.springframework.stereotype.Component

@TypeChecked
@Component
class ResultStorage {

    private Map<String, MatchingResult> results = new HashMap<>()

    boolean add(String key, MatchingResult value) {
        return results.put(key, value)
    }

    MatchingResult get(String key) {
        return results.remove(key)
    }
}
