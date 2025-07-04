package daviderocca.dao;

import daviderocca.entities.Prestito;
import daviderocca.entities.Testo;
import daviderocca.entities.Utente;
import daviderocca.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class UtenteDAO {

    private final EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Utente newUtente) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newUtente);
        transaction.commit();
        System.out.println("L'utente " + newUtente.getNome() + "|| N° tessera: " + newUtente.getNumeroDiTessera() + " è stato creato correttamente");
    }

    public Utente findById(long idUtente) {
        Utente found = entityManager.find(Utente.class, idUtente);
        if (found == null) {
            throw new NotFoundException(idUtente);
        }
        return found;
    }

}
