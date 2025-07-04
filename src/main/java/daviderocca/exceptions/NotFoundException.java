package daviderocca.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String ISBN) {
        super("Il testo con ISBN " + ISBN + " non è stato trovato!");
    }

    public NotFoundException(Long idUtente) {
        super("L'utente con id: " + idUtente + " non è stato trovato!");
    }
}
