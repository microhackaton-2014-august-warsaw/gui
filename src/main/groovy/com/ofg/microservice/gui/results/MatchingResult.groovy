package com.ofg.microservice.gui.results

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class MatchingResult {
    String pairId
    List<Relationship> relationships
}

@TypeChecked
@Canonical
class Relationship  {
   String correlatorType
   List<Relation>  relations
}

@TypeChecked
@Canonical
class Relation {
    Integer score
    String description
}