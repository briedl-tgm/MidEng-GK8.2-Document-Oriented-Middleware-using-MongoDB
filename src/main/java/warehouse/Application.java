package warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import warehouse.model.ProductData;
import warehouse.repository.WarehouseRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private WarehouseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll(); // Startet jedes Mal mit einer sauberen DB

		String[] categories = {"Getraenk", "Waschmittel", "Tierfutter", "Reinigung", "Elektronik", "Obst"};
		String[] warehouses = {"W-1", "W-2", "W-3", "W-4", "W-5"};

		for (int i = 1; i <= 300; i++) {
			String wId = warehouses[i % warehouses.length];
			String cat = categories[i % categories.length];
			String pId = String.format("P-%04d", i);
			String pName = "Testprodukt " + i;
			double qty = Math.floor(Math.random() * 500); // Zufällige Menge 0-500

			repository.save(new ProductData(wId, pId, pName, cat, qty));
		}
		System.out.println("Erfolgreich 300 Produkte für die Vertiefung angelegt.");
	}

}
