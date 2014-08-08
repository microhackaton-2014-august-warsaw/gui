package com.ofg.microservice.gui.link
import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.resttemplate.RestTemplate
import com.ofg.microservice.gui.GuiAPIs
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.HandlerMapping

import javax.servlet.http.HttpServletRequest

@RestController("/rest")
@RequestMapping("/rest")
@Slf4j
class UserDataController {

    private ServiceResolver serviceResolver

    private RestTemplate restTemplate = new RestTemplate()

    @Autowired
    UserDataController(ServiceResolver serviceResolver) {
        this.serviceResolver = serviceResolver
    }

    private ResponseEntity<String> handleRequest(String dependencyName, Closure<String> operation, HttpServletRequest request) {
        com.google.common.base.Optional<String> url = serviceResolver.getUrl('userDataHolder')

        if (url.present) {
            String reqURL = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE)
            String linkURL = "${url.get()}${reqURL.substring('/rest'.length())}"
            log.info(linkURL)
            String response = operation(linkURL)

            return new ResponseEntity<String>(response, HttpStatus.OK)
        }

        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @RequestMapping(value = '/link/**', method = RequestMethod.PUT, produces = GuiAPIs.API_V1, consumes = GuiAPIs.API_V1)
    ResponseEntity<String> linkPUT(@RequestBody String json, HttpServletRequest request) {
        log.info("PUT PUT PUT")

        return handleRequest('userDataHolder',
                {link -> return restTemplate.putForObject(link, createEntity(json), String)},
                request)
    }

    @RequestMapping(value = '/link/**', method = RequestMethod.POST, produces = GuiAPIs.API_V1, consumes = GuiAPIs.API_V1)
    ResponseEntity<String> linkPOST(@RequestBody String json, HttpServletRequest request) {
        log.info("POST POST POST")

        return handleRequest('userDataHolder',
                {link -> return restTemplate.postForObject(link, createEntity(json), String)},
                request)
    }

    @RequestMapping(value = '/link/**', method = RequestMethod.GET, produces = GuiAPIs.API_V1, consumes = GuiAPIs.API_V1)
    ResponseEntity<String> linkGET(HttpServletRequest request) {
        log.info("GET GET GET")

        restTemplate.setInterceptors([new ContentTypeInterceptor(GuiAPIs.USER_DATA_HOLDER_V1)])

        return handleRequest('userDataHolder',
                {link -> return restTemplate.getForObject(link, String)},
                request)
    }

    private HttpEntity<Object> createEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(GuiAPIs.USER_DATA_HOLDER_V1));
        return new HttpEntity<Object>(object, headers);
    }
}
