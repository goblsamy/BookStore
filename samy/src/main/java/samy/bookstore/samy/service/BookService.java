package samy.bookstore.samy.service;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samy.bookstore.samy.domain.Author;
import samy.bookstore.samy.domain.Book;
import samy.bookstore.samy.dto.BookCreateCommand;
import samy.bookstore.samy.dto.BookInfo;
import samy.bookstore.samy.dto.BookInfoWithoutAuthorId;
import samy.bookstore.samy.exceptionhnadling.BookNotFoundByAuthorException;
import samy.bookstore.samy.exceptionhnadling.BookNotFoundByIdException;
import samy.bookstore.samy.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private AuthorService authorService;
    private ModelMapper modelMapper;


    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }


    public BookInfo addBook(BookCreateCommand command) {
        Book bookToAdd = modelMapper.map(command, Book.class);
        Author author = authorService.findById(command.getAuthorId());
        bookToAdd.setAuthor(author);
        bookRepository.save(bookToAdd);
        BookInfo bookInfo = modelMapper.map(bookToAdd, BookInfo.class);
        bookInfo.setAuthorId(Math.toIntExact(author.getId()));
        return bookInfo;

    }

    public BookInfo findById(Integer bookId) {
        Book book = basicFindById(Long.valueOf(bookId));
        BookInfo bookInfo = modelMapper.map(book, BookInfo.class);
        bookInfo.setAuthorId(Math.toIntExact(book.getAuthor().getId()));
        return bookInfo;
    }

    public List<BookInfoWithoutAuthorId> findAll() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookInfoWithoutAuthorId.class)).toList();
    }

    public List<BookInfo> findByName(String authorName) {
        List<Book> books = bookRepository.findByName(authorName);
        if (books.isEmpty()) {
            throw new BookNotFoundByAuthorException(authorName);
        }
        return books.stream()
                .map(book -> {
                    BookInfo bookInfo = modelMapper.map(book, BookInfo.class);
                    bookInfo.setAuthorId(Math.toIntExact(book.getAuthor().getId()));
                    return bookInfo;
                })
                .collect(Collectors.toList());

    }

    public List<BookInfo> findBooksBetweenDates(LocalDate fromDate, LocalDate toDate) {
        List<Book> books = bookRepository.findPublishedDateBetween(fromDate, toDate);
        return books.stream().map(book -> modelMapper.map(book, BookInfo.class)).toList();

    }

    public BookInfo borrowBook(Long bookId) {
        Book book = basicFindById(bookId);
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        BookInfo bookInfo = modelMapper.map(book, BookInfo.class);
        bookInfo.setAuthorId(Math.toIntExact(book.getAuthor().getId()));
        return bookInfo;
    }

    public Book basicFindById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundByIdException(Math.toIntExact(bookId)));
    }
}
