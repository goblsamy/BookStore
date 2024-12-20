package samy.bookstore.samy.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import samy.bookstore.samy.domain.Author;
import samy.bookstore.samy.dto.AuthorCreateCommand;
import samy.bookstore.samy.dto.AuthorInfo;
import samy.bookstore.samy.exceptionhnadling.AuthorNotFoundByIdException;
import samy.bookstore.samy.repository.AuthorRepository;

import java.util.List;



@Service
@Transactional
public class AuthorService {

    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    public Author findById(int authorId) {
        return authorRepository.findById((long) authorId)
                .orElseThrow(() -> new AuthorNotFoundByIdException(authorId));
    }

    public AuthorInfo addAuthor(AuthorCreateCommand command) {
        Author author = modelMapper.map(command, Author.class);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorInfo.class);

    }

    public AuthorInfo findByIdDto(int authorId) {
        Author author = authorRepository.findById((long) authorId)
                .orElseThrow(() -> new AuthorNotFoundByIdException(authorId));
        return modelMapper.map(author, AuthorInfo.class);

    }

    public List<AuthorInfo> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> modelMapper.map(author, AuthorInfo.class)).toList();
    }

    public void delete(Long id) {
        authorRepository.delete(findById(Math.toIntExact(id)));
    }
}
