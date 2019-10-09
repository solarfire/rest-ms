package sco.co.so.web.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sco.co.so.model.Bean;
import sco.co.so.service.api.ResourceService;
import sco.co.so.web.exception.ResourceNotFoundException;

/**
 * Created on 29/10/2017.
 */
@RestController
public class ResourceRestController {

    @Autowired
    private ResourceService resourceService;

    /**
     * Read
     */
    @GetMapping(value="/beans/{id}")
    public Bean get(@PathVariable(value = "id") Long id) {
        Optional<Bean> bean = resourceService.get(id);
        if(bean.isPresent()) {
            return bean.get();
        }
        throw new ResourceNotFoundException(String.format("Resource with id [%s] not Found", id));
    }

    @GetMapping(value="/beans")
    public Collection<Bean> list() {
        return resourceService.list();
    }

    /**
     * Create
     */
    @PostMapping(value="/beans")
    public ResponseEntity<Bean> add(@Valid @RequestBody Bean bean) {
        resourceService.add(bean);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(bean.getId()).toUri();
        return created(location).build();
    }
    /**
     * Update
     */
    @PutMapping(value="/beans")
    public ResponseEntity<Bean> update(@Valid @RequestBody Bean bean) {

        Optional<Bean> updatedBean = resourceService.update(bean);
        if (updatedBean.isPresent()) {
            return noContent().build();
        }
        throw new ResourceNotFoundException(String.format("Resource with id [%s] not Found", bean.getId()));
    }

    /**
     * Delete
     */
    @DeleteMapping(value="/beans/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        Boolean success = resourceService.delete(id);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFoundException(String.format("Resource with id [%s] not Found", id));
    }
}