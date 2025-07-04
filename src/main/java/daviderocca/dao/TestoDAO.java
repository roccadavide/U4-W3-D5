package daviderocca.dao;

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
        System.out.println("Il testo " + newTesto.getTitolo() + " è stato creato correttamente");
    }

    public void findAndRemoveByISBN(String ISBN) {
        Testo found = entityManager.find(Testo.class, ISBN);
        if (found == null) {
            throw new NotFoundException(ISBN);
        } else {
            entityManager.remove(found);
            entityManager.getTransaction().commit();
        }
    }

    public Testo findByISBN(String ISBN) {
        Testo found = entityManager.find(Testo.class, ISBN);
        if (found == null) {
            throw new NotFoundException(ISBN);
        }
        return found;
    }

    public List<Testo> findByPubblicationYear(Year annoDiPubblicazione) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM testo t WHERE t.anno_di_pubblicazione = :annoDiPubblicazione", Testo.class);
        query.setParameter("annoDiPubblicazione", annoDiPubblicazione);
        return query.getResultList();
    }

    public List<Testo> findByAuthor(String autore) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM testo t WHERE t.autore = :autore", Testo.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Testo> findByTitleOrLetters(String titoloTotaleParziale) {
        TypedQuery<Testo> query = entityManager.createQuery("SELECT t FROM testo t WHERE LOWER(t.titolo) LIKE LOWER(:titoloTotaleParziale)", Testo.class);
        query.setParameter("titoloTotaleParziale", "%" + titoloTotaleParziale + "%");
        return query.getResultList();
    }

}
