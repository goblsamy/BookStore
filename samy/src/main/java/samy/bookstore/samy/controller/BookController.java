package samy.bookstore.samy.controller;


import jakarta.validation.Valid;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samy.bookstore.samy.dto.BookCreateCommand;
import samy.bookstore.samy.dto.BookInfo;
import samy.bookstore.samy.dto.BookInfoWithoutAuthorId;
import samy.bookstore.samy.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {


    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping
    public ResponseEntity<BookInfo> addBook(@Valid @RequestBody BookCreateCommand command) {
        log.info("HTTP req POST /api/books, with command: " + command.toString());
        BookInfo bookInfo = bookService.addBook(command);
        return new ResponseEntity<>(bookInfo, HttpStatus.CREATED);
    }

    @GetMapping("/id/{bookId}")
    public ResponseEntity<BookInfo> findBookById(@PathVariable("bookId") Integer bookId) {
        log.info("HTTP req GET /api/books/id, with id: " + bookId);
        BookInfo bookInfo = bookService.findById(bookId);
        return new ResponseEntity<>(bookInfo, HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<BookInfoWithoutAuthorId>> findAll() {
        log.info("HTTP req GET /api/books");
        List<BookInfoWithoutAuthorId> bookInfos = bookService.findAll();
        return new ResponseEntity<>(bookInfos, HttpStatus.OK);
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<List<BookInfo>> findBookByAuthorName(@PathVariable("authorName") String authorName) {
        log.info("HTTP req GET /api/books, with authorName: " + authorName);
        List<BookInfo> bookInfos = bookService.findByName(authorName);
        return new ResponseEntity<>(bookInfos, HttpStatus.OK);
    }
}
