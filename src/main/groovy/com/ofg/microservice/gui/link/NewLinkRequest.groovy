package com.ofg.microservice.gui.link

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class NewLinkRequest {
    String twitter
    String facebook
    String googleplus
}
