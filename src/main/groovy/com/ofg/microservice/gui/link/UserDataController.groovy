package com.ofg.microservice.gui.link
import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.microservice.config.web.RestTemplate
import com.ofg.microservice.gui.GuiAPIs
import com.wordnik.swagger.annotations.ApiOperation
import groovy.transform.TypeChecked
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

@TypeChecked
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

    @RequestMapping(value = '/link', method = RequestMethod.PUT, produces = GuiAPIs.API_V1, consumes = GuiAPIs.API_V1)
    @ApiOperation(value = 'callback', notes = 'This method is called by MyMOID when payment is processed')
    ResponseEntity<SocialUrl> callback(@RequestBody NewLinkRequest linkRequest) {
        com.google.common.base.Optional<String> url = serviceResolver.getUrl('userDataHolder')

        if (url.present) {
            String linkURL = "${url.get()}/link"
            log.info(linkURL)
            String response = restTemplate.putForObject(linkURL, createEntity(linkRequest), String)

            return new ResponseEntity<SocialUrl>(new SocialUrl(id: response), HttpStatus.OK)
        }

        return new ResponseEntity<SocialUrl>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

    private HttpEntity<Object> createEntity(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(GuiAPIs.USER_DATA_HOLDER_V1));
        return new HttpEntity<Object>(object, headers);
    }

}
