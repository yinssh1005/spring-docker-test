package com.example.springdockertest.controller;

import com.example.springdockertest.event.PaginatedResultsRetrievedEvent;
import com.example.springdockertest.event.ResourceCreatedEvent;
import com.example.springdockertest.event.SingleResourceRetrievedEvent;
import com.example.springdockertest.exception.CustomException1;
import com.example.springdockertest.exception.CustomException2;
import com.example.springdockertest.exception.MyResourceNotFoundException;
import com.example.springdockertest.persistence.model.FooEntity;
import com.example.springdockertest.services.IFooService;
import com.example.springdockertest.util.RestPreconditions;
import org.assertj.core.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/foos")
public class FooController {

    private static final Logger logger = LoggerFactory.getLogger(FooController.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private IFooService service;

    public FooController() {
        super();
    }

    // API

    // Note: the global filter overrides the ETag value we set here. We can still analyze its behaviour in the Integration Test.
    @GetMapping(value = "/{id}/custom-etag")
    public ResponseEntity<FooEntity> findByIdWithCustomEtag(@PathVariable("id") final Long id,
                                                            final HttpServletResponse response) {
        final FooEntity foo = RestPreconditions.checkFound(service.findById(id));

        eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
        return ResponseEntity.ok()
                .eTag(Long.toString(foo.getVersion()))
                .body(foo);
    }

    // read - one

    @GetMapping(value = "/{id}")
    public FooEntity findById(@PathVariable("id") final Long id, final HttpServletResponse response) {
        try {
            final FooEntity resourceById = RestPreconditions.checkFound(service.findById(id));

            eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
            return resourceById;
        } catch (MyResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", exc);
        }

    }

    // read - all

    @GetMapping
    public List<FooEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(params = {"page", "size"})
    public List<FooEntity> findPaginated(@RequestParam("page") final int page, @RequestParam("size") final int size,
                                         final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<FooEntity> resultPage = service.findPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<FooEntity>(FooEntity.class, uriBuilder, response, page,
                resultPage.getTotalPages(), size));

        return resultPage.getContent();
    }

    @GetMapping("/pageable")
    public List<FooEntity> findPaginatedWithPageable(Pageable pageable, final UriComponentsBuilder uriBuilder,
                                                     final HttpServletResponse response) {
        final Page<FooEntity> resultPage = service.findPaginated(pageable);
        if (pageable.getPageNumber() > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<FooEntity>(FooEntity.class, uriBuilder, response,
                pageable.getPageNumber(), resultPage.getTotalPages(), pageable.getPageSize()));

        return resultPage.getContent();
    }

    // write

    @PostMapping(consumes = {"application/json;charaset=utf-8"})
    @ResponseStatus(HttpStatus.CREATED)
    public FooEntity create(@RequestBody final FooEntity resource, final HttpServletResponse response) {
        Preconditions.checkNotNull(resource);
        final FooEntity foo = service.create(resource);
        final Long idOfCreatedResource = foo.getId();

        eventPublisher.publishEvent(new ResourceCreatedEvent(this, response, idOfCreatedResource));

        return foo;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final FooEntity resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkFound(service.findById(resource.getId()));
        service.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @ExceptionHandler({ CustomException1.class, CustomException2.class })
    public void handleException(final Exception ex) {
        final String error = "Application specific error handling";
        logger.error(error, ex);
    }
}