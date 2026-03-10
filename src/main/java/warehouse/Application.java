package warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import warehouse.model.ProductData;
import warehouse.model.WarehouseData;
import warehouse.repository.WarehouseRepository;
import java.time.LocalDateTime;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private WarehouseRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();

		String[] categories = {"Getraenk", "Waschmittel", "Tierfutter", "Reinigung", "Elektronik", "Obst"};

		// 5 Lagerhäuser erstellen
		for (int w = 1; w <= 5; w++) {
			WarehouseData warehouse = new WarehouseData(
					"W-0" + w, "Lager " + w, "Strasse " + w, "Stadt", "Postleitzahl", "Austria",
					LocalDateTime.now().toString()
			);

			// Jedem Lager 60 Produkte hinzufügen (insg. 300)
			for (int p = 1; p <= 60; p++) {
				String pId = "P-" + w + "-" + p;
				warehouse.addProduct(new ProductData(pId, "Produkt " + p, categories[p % 6], Math.random() * 500, "Packung"));
			}
			repository.save(warehouse);
		}
		System.out.println("300 Produkte in 5 Lagerhäusern verschachtelt angelegt.");
	}
}