package com.ofg.base

import com.ofg.infrastructure.base.MvcWiremockIntegrationSpec
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [ServiceDiscoveryStubbingApplicationConfiguration], loader = SpringApplicationContextLoader)
@ActiveProfiles(com.ofg.microservice.Profiles.TEST)
class MicroserviceMvcWiremockSpec extends MvcWiremockIntegrationSpec {
}
