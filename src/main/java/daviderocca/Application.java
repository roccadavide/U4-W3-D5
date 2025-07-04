package daviderocca;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5pu");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();



        em.close();
        emf.close();


    }
}
