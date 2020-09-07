package soo.ram.restapi.demo.common;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.validation.Errors;
import soo.ram.restapi.demo.events.EventController;
import soo.ram.restapi.demo.index.IndexController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorResource extends EntityModel<Errors> {

    public ErrorResource(Errors content, Link... links) {
        super(content,links);
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }
}
