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
        System.out.println("Expense Tracker");
        System.out.print("Enter expense amount for Food: ");
        double expense1 = scanner.nextDouble();//Scanners to get input for categories that are going to be used in pie chart
        
        System.out.print("Enter expense amount for Transport: ");
        double expense2 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Entertainment: ");
        double expense3 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Bills: ");
        double expense4 = scanner.nextDouble();
        
        System.out.print("Enter expense amount for Other: ");
		  scanner.nextLine();
        double expense5 = scanner.nextDouble();
        scanner.nextLine();
        scanner.close();
        
        Scene scene = new Scene(new Group());
        stage.setTitle(Constants.APP_TITLE);  // Using constant from Constants class
        stage.setWidth(Constants.WINDOW_WIDTH);  // Using constant from Constants class
        stage.setHeight(Constants.WINDOW_HEIGHT);  // Using constant from Constants class
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Food", expense1),//Creates different section of the pie chart
                new PieChart.Data("Transport",expense2),
                new PieChart.Data("Entertainment", expense3),
                new PieChart.Data("Bills",expense4),
                new PieChart.Data("Other", expense5));
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
