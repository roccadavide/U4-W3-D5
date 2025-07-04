package daviderocca.dao;

import daviderocca.entities.Prestito;
import daviderocca.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDAO {

    private final EntityManager entityManager;

    public PrestitoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Prestito newPrestito) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newPrestito);
        transaction.commit();
        System.out.println("Il prestito " + newPrestito.getId() + " dell'item " + newPrestito.getElementoPrestato().getTitolo() + " è stato creato correttamente");
    }

    public Prestito findById(long idPrestito) {
        Prestito found = entityManager.find(Prestito.class, idPrestito);
        if (found == null) throw new NotFoundException(idPrestito);
        return found;
    }

    public List<Prestito> findBorrowedItemByUserCard(int numeroDiTessera) {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroDiTessera = :numeroDiTessera", Prestito.class
        );
        query.setParameter("numeroDiTessera", numeroDiTessera);
        return query.getResultList();
    }

    public List<Prestito> findExpiredBorrows() {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL", Prestito.class
        );
        query.setParameter("oggi", LocalDate.now());
        return query.getResultList();
    }
}