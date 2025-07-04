package daviderocca.dao;

import daviderocca.entities.Libro;
import daviderocca.entities.Testo;
import daviderocca.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.Year;
import java.util.List;

public class TestoDAO {
    private final EntityManager entityManager;

    public TestoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Testo newTesto) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newTesto);
        transaction.commit();
        System.out.println("Il testo \"" + newTesto.getTitolo() + "\" è stato creato correttamente.");
    }

    public Testo findByISBN(String ISBN) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM Testo t WHERE t.codiceISBN = :isbn", Testo.class);
        query.setParameter("isbn", ISBN);
        List<Testo> results = query.getResultList();
        if (results.isEmpty()) {
            throw new NotFoundException(ISBN);
        }
        return results.getFirst();
    }

    public void findAndRemoveByISBN(String ISBN) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM Testo t WHERE t.codiceISBN = :isbn", Testo.class);
        query.setParameter("isbn", ISBN);
        List<Testo> results = query.getResultList();

        if (!results.isEmpty()) {
            entityManager.getTransaction().begin();
            entityManager.remove(results.getFirst());
            entityManager.getTransaction().commit();
            System.out.println("Testo rimosso con successo.");
        } else {
            throw new NotFoundException(ISBN);
        }
    }

    public List<Testo> findByPubblicationYear(Year annoDiPubblicazione) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM Testo t WHERE t.annoDiPubblicazione = :anno", Testo.class);
        query.setParameter("anno", annoDiPubblicazione);
        return query.getResultList();
    }

    // dato che solo i libri hanno l'autore l'ho cambiato che torni solo libri
    public List<Libro> findByAuthor(String autore) {
        TypedQuery<Libro> query = entityManager.createQuery("SELECT l FROM Libro l WHERE LOWER(l.autore) = LOWER(:autore)", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Testo> findByTitleOrLetters(String titoloTotaleParziale) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM Testo t WHERE LOWER(t.titolo) LIKE LOWER(:titolo)", Testo.class);
        query.setParameter("titolo", "%" + titoloTotaleParziale + "%");
        return query.getResultList();
    }
}
