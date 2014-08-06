package com.ofg.base

import com.ofg.infrastructure.base.MvcIntegrationSpec
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [ServiceDiscoveryStubbingApplicationConfiguration], loader = SpringApplicationContextLoader)
@ActiveProfiles(com.ofg.microservice.Profiles.TEST)
class MicroserviceMvcIntegrationSpec extends MvcIntegrationSpec {


}
