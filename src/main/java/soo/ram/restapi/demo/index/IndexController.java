package soo.ram.restapi.demo.index;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import soo.ram.restapi.demo.events.EventController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class IndexController {

//    ResourceSupport is now RepresentationModel
//
//    Resource is now EntityModel
//
//    Resources is now CollectionModel
//
//    PagedResources is now PagedModel


    @GetMapping("/api")
    public RepresentationModel index(){
        var index = new RepresentationModel();
        index.add(linkTo(EventController.class).withRel("events"));
        return index;
    }

}
