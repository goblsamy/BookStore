package samy.bookstore.samy.exceptionhnadling;

public class BookNotFoundByIdException extends RuntimeException {
    private int bookId;

    public BookNotFoundByIdException(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }
}
