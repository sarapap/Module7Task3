package datasource;

import jakarta.persistence.*;
import javafx.scene.control.Alert;

public class MariaDbJpaConnection {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    public static EntityManager getInstance() {
        try {
            if (em==null) {
                if (emf==null) {
                    emf = Persistence.createEntityManagerFactory("CurrencyMariaDbUnit");
            }
            em = emf.createEntityManager();

        }
        } catch (Exception e) {
            showErrorDialog("Error", "Failed to connect to database.");
        }
        return em;
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
