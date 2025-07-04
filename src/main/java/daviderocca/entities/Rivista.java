package daviderocca.entities;

import daviderocca.enums.PeriodicitàRivista;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Year;

public class Rivista extends Testo{

    protected PeriodicitàRivista periodicitàRivista;

    public Rivista(String codiceISBN, Year annoDiPubblicazione, int numeroPagine, PeriodicitàRivista periodicitàRivista) {
        super(codiceISBN, annoDiPubblicazione, numeroPagine);
        this.periodicitàRivista = periodicitàRivista;
    }

    @Enumerated(EnumType.STRING)
    public PeriodicitàRivista getPeriodicitàRivista() {
        return periodicitàRivista;
    }

    public void setPeriodicitàRivista(PeriodicitàRivista periodicitàRivista) {
        this.periodicitàRivista = periodicitàRivista;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicitàRivista=" + periodicitàRivista +
                ", codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
