package daviderocca.entities;

import daviderocca.enums.GenereLibro;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Year;

@Entity
public class Libro extends Testo{

    private String autore;

    @Column(name = "genere_libro")
    @Enumerated(EnumType.STRING)
    private GenereLibro genereLibro;

    public Libro() {
    }

    public Libro(String codiceISBN, String titolo, Year annoDiPubblicazione, int numeroPagine, String autore, GenereLibro genereLibro) {
        super(codiceISBN, titolo, annoDiPubblicazione, numeroPagine);
        this.autore = autore;
        this.genereLibro = genereLibro;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    @Enumerated(EnumType.STRING)
    public GenereLibro getGenereLibro() {
        return genereLibro;
    }

    public void setGenereLibro(GenereLibro genereLibro) {
        this.genereLibro = genereLibro;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "genereLibro=" + genereLibro +
                ", codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", autore='" + autore + '\'' +
                '}';
    }
}
