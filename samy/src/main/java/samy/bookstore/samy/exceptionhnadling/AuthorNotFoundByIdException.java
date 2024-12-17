package samy.bookstore.samy.exceptionhnadling;

public class AuthorNotFoundByIdException extends RuntimeException {

    private int authorId;

    public AuthorNotFoundByIdException(int authorId) {
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
