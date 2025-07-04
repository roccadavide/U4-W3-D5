package daviderocca;

import daviderocca.dao.PrestitoDAO;
import daviderocca.dao.TestoDAO;
import daviderocca.dao.UtenteDAO;
import daviderocca.entities.*;
import daviderocca.enums.GenereLibro;
import daviderocca.enums.PeriodicitàRivista;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5pu");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();

        TestoDAO td = new TestoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);

        //ECCO I REQUISITI DEL PROGETTO

        //AGGIUNTA ELEMENTO
        /*
        Libro l1 = new Libro(
                "9788830101685",
                "Solo è il coraggio",
                Year.of(2022),
                588,
                "Roberto Saviano",
                GenereLibro.ROMANZO);

        Libro l2 = new Libro(
                "9788804670346",
                "Petrolio",
                Year.of(1992),
                672,
                "Pier Paolo Pasolini",
                GenereLibro.ROMANZO);

        Libro l3 = new Libro(
                "9788858551011",
                "Lo strano caso del ladro di spazzatura!",
                Year.of(2025),
                128,
                "Geronimo Stilton",
                GenereLibro.UMORISTICO);

        td.save(l1);
        td.save(l2);
        td.save(l3);

        Rivista r1 = new Rivista(
                "0140430350",
                "Vanity Fair",
                Year.of(1990),
                124,
                PeriodicitàRivista.SETTIMANALE);

        Rivista r2 = new Rivista(
                "8851172615",
                "National Geographic",
                Year.of(2025),
                120,
                PeriodicitàRivista.MENSILE
        );

        td.save(r1);
        td.save(r2);

        Utente u1 = new Utente("Michela", "Rossi", LocalDate.of(2004, 1, 23), 324);
        Utente u2 = new Utente("Ernesto", "Beu", LocalDate.of(2004, 6, 14), 326);
        Utente u3 = new Utente("Luca", "Innocenti", LocalDate.of(2003, 10, 10), 325);

        ud.save(u1);
        ud.save(u2);
        ud.save(u3);

        Prestito p1 = new Prestito(
                u1,
                l1,
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                null
        );

        Prestito p2 = new Prestito(
                u2,
                r1,
                LocalDate.now().minusDays(35),
                LocalDate.now().plusDays(5),
                null
        );


        Prestito p3 = new Prestito(
                u3,
                l2,
                LocalDate.now().minusDays(25),
                LocalDate.now().plusDays(15),
                LocalDate.now()
        );

        pd.save(p1);
        pd.save(p2);
        pd.save(p3); */

        //RIMOZIONE ELEMENTO CON ISBN

       // td.findAndRemoveByISBN("9788858551011");


        //RICERCA ELEMENTO
        try {
            Testo ricercaConISBN = td.findByISBN("9788830101685");
            System.out.println("Trovato il testo: " + ricercaConISBN.getTitolo() + " con ISBN: " + ricercaConISBN.getCodiceISBN());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //RICERCA PER ANNO

        List<Testo> testiDel2020 = td.findByPubblicationYear(Year.of(1992));

        if (testiDel2020.isEmpty()) {
            System.out.println("Nessun testo trovato per l'anno 2020.");
        } else {
            System.out.println("Testi trovati per l'anno 2020:");
            testiDel2020.forEach(System.out::println);
        }

        //RICERCA PER AUTORE






        em.close();
        emf.close();



    }
}
