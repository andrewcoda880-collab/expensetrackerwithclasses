//Note from Angel, you need to have javafx installed to run this
//This isn't connected to anything from the app just yet, so ignore it if testing out the app interface thing
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import java.util.Scanner;

public class PieChartCreator extends Application {
 
    @Override public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Expense Tracker ===");
        System.out.print("Enter expense amount for Bills: $");
        double expense1 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Rent: $");
        double expense2 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Food: $");
        double expense3 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Entertainment: $");
        double expense4 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Takeout: $");
        double expense5 = scanner.nextDouble();
        
        scanner.close();
        
        Scene scene = new Scene(new Group());
        stage.setTitle("Expenses");
        stage.setWidth(500);
        stage.setHeight(500);
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Bills", expense1),
                new PieChart.Data("Rent", expense2),
                new PieChart.Data("Food", expense3),
                new PieChart.Data("Entertainment", expense4),
                new PieChart.Data("Takeout", expense5));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Expenses");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
