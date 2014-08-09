package com.ofg.microservice.gui.results
import com.ofg.microservice.gui.GuiAPIs
import com.ofg.microservice.gui.IdHolder
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@TypeChecked
@RestController
@RequestMapping(value = "/rest/results")
class ResultsController {

    ResultStorage storage

    @Autowired
    public ResultsController(ResultStorage storage) {
        this.storage = storage
    }

    @RequestMapping(value = "/result", method = RequestMethod.PUT,
            consumes = GuiAPIs.API_V1,
            produces = GuiAPIs.API_V1)
    String send(@RequestBody MatchingResult result) {
        storage.add(result.pairId, result)

        return "accepted"
    }

    @RequestMapping(value = '/pull', method = RequestMethod.POST,
            consumes = GuiAPIs.API_V1,
            produces = GuiAPIs.API_V1)
    ResponseEntity<MatchingResult> pull(@RequestBody IdHolder idHolder) {
        MatchingResult result
        if ((result = storage.get(idHolder.id)) != null) {
            return new ResponseEntity<MatchingResult>(result, HttpStatus.OK)
        }
        else {
            return new ResponseEntity<MatchingResult>(new MatchingResult(), HttpStatus.OK)
        }
    }
}
