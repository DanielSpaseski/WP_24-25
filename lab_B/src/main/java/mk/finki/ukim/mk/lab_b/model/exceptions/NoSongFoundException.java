package mk.finki.ukim.mk.lab_b.model.exceptions;

public class NoSongFoundException extends RuntimeException{
    public NoSongFoundException()  {
        super("NoSongFound");
    }
}
