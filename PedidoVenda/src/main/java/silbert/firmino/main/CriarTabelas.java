package silbert.firmino.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriarTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("sistemaPedidoVenda");
		EntityManager manager = factory.createEntityManager();

		
	}
}
