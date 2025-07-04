package daviderocca.entities;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Testo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "codice_isbn")
    protected String codiceISBN;

    protected String titolo;

    @Column(name = "anno_di_pubblicazione")
    protected Year annoDiPubblicazione;

    @Column(name = "numero_pagine")
    protected int numeroPagine;

    public Testo(String codiceISBN, Year annoDiPubblicazione, int numeroPagine) {
        this.codiceISBN = codiceISBN;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public long getId() {
        return id;
    }


    public String getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(String codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Year getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(Year annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Testo{" +
                ", codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
