package com.ofg.microservice.config
import com.ofg.infrastructure.discovery.ServiceResolverConfiguration
import com.ofg.microservice.Profiles
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Profile

@TypeChecked
@Configuration
@Import(ServiceResolverConfiguration)
@Profile([Profiles.PRODUCTION, Profiles.DEVELOPMENT])
class ServiceDiscoveryConfiguration {
}
