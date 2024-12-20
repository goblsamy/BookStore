package samy.bookstore.samy.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samy.bookstore.samy.dto.AuthorCreateCommand;
import samy.bookstore.samy.dto.AuthorInfo;
import samy.bookstore.samy.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Slf4j
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping
    public ResponseEntity<AuthorInfo> addAuthor(@Valid @RequestBody AuthorCreateCommand command) {
        log.info("HTTP req POST /api/authors, with command: " + command.toString());
        AuthorInfo authorInfo = authorService.addAuthor(command);
        return new ResponseEntity<>(authorInfo, HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorInfo> findAuthorById(@PathVariable("authorId") Integer id) {
        log.info("HTTP req GET /api/authors, with id: " + id);
        AuthorInfo authorInfo = authorService.findByIdDto(id);
        return new ResponseEntity<>(authorInfo, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AuthorInfo>> findAllAuthors() {
        log.info("HTTP req GET /api/authors");
        List<AuthorInfo> authorInfos = authorService.findAll();
        return new ResponseEntity<>(authorInfos, HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable("authorId") Long id) {
        log.info("HTTP req DELETE /api/authors, with id: " + id);
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
