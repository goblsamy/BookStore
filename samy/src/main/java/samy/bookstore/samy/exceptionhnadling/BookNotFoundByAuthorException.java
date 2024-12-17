package samy.bookstore.samy.exceptionhnadling;

public class BookNotFoundByAuthorException extends RuntimeException {

    private String authorName;

    public BookNotFoundByAuthorException(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }
}
