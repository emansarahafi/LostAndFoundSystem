// Members: Ali Abdulla, Eman Sarah Afi, Sara Abdelhakeem

package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;

public class LostFoundController implements Initializable {
	@FXML
	private Button add;
	@FXML
	private Button export;
	@FXML
	private Button undo;
	@FXML
	private Button delete;
	@FXML
	private ComboBox<String> status;
	@FXML
	private DatePicker date;
	@FXML
	private TextField item;
	@FXML
	private TextArea descrp;
	@FXML
	private TextArea descArea;
	@FXML
	private TableView<Item> tableView;
	@FXML
	private TableColumn<Item, String> locationCol;
	@FXML
	private TableColumn<Item, String> itemCol;
	@FXML
	private TableColumn<Item, String> statusCol;
	@FXML
	private TableColumn<Item, String> IDCol;
	@FXML
	private TableColumn<Item, String> descpCol;
	@FXML
	private TableColumn<Item, LocalDate> dateCol;
	@FXML
	private ComboBox<String> Location;
	@FXML
	private TextField ID;
	@FXML
	private PieChart chart;
	@FXML
	private ListView<String> listView;
	@FXML
	private Label currentDate;
	@FXML
	private Label message;
	@FXML
	String statusList[] = { "Lost", "Found"};
	String locations[] = { "Building A", "Building B", "Building C", "Building D", "GYM" ,"Auditorium", "Library", "Student Commons", "Welcome Center" };
	String list[] = { "Building A", "Building B", "Building C", "Building D", "GYM" ,"Auditorium", "Library", "Student Commons", "Welcome Center", "All Locations" };
	String pieColors [] = {"LIGHTCORAL", "BLUE", "SKYBLUE", "DARKRED", "DARKSLATEBLUE", "RED", "MAROON", "TEAL", "NAVY"};

	ObservableList<Item> data = FXCollections.observableArrayList();
	ObservableList<Item> backup = FXCollections.observableArrayList();
 	ObservableList<PieChart.Data> pieChartData =  FXCollections.observableArrayList();

 	int a,b,c,d,e,f,g,h,j;
    
	 	PieChart.Data BA = new PieChart.Data("Building A",a);
	 	PieChart.Data BB = new PieChart.Data("Building B",b);
	 	PieChart.Data BC = new PieChart.Data("Building C",c);
	 	PieChart.Data BD = new PieChart.Data("Building D",d);
	 	PieChart.Data WC = new PieChart.Data("Welcome Center",e);
	 	PieChart.Data GYM = new PieChart.Data("GYM",f);
	 	PieChart.Data LB = new PieChart.Data("Library",g);
	 	PieChart.Data SC = new PieChart.Data("Student Commons",h);
	 	PieChart.Data AU = new PieChart.Data("Auditorium",j);

	// Event Listener on Button.onAction
	@FXML
	public void addButton(ActionEvent event) {
		listView.getSelectionModel().selectLast();
		    
		//Adding New Record
		tableView.getItems().add(new Item(Location.getValue().toString(),item.getText(),date.getValue(), 
					                 status.getValue().toString(),descrp.getText(),ID.getText()));
		message.setText("A new record is added");
		item.clear();
		descrp.clear();
		ID.clear();
		    
	    pieChartData.removeAll(BA,BB,BC,BD,WC,GYM,LB,SC,AU);
        a=b=c=d=e=f=g=h=j=0;
        
	    //Pie Chart data
	 	for(int i = 0; i < data.size(); i++) {
	 		if (data.get(i).getLocation().toString() == "Building A") 
	 			a+=1;
	 		else if (data.get(i).getLocation().toString() == "Building B") 
	 			b+=1;
	 		else if (data.get(i).getLocation().toString() == "Building C") 
	 			c+=1;
	 		else if (data.get(i).getLocation().toString() == "Building D") 
	 			d+=1;
	 		else if (data.get(i).getLocation().toString() == "Welcome Center") 
	 			e+=1;
	 		else if (data.get(i).getLocation().toString() == "GYM") 
	 			f+=1;
	 	    else if (data.get(i).getLocation().toString() == "Library") 
	 			g+=1;
	 	    else if (data.get(i).getLocation().toString() == "Student Commons") 
				h+=1;
	 	    else if (data.get(i).getLocation().toString() == "Auditorium") 
				j+=1;
	 	}

	 	BA = new PieChart.Data("Building A",a);
	 	BB = new PieChart.Data("Building B",b);
	 	BC = new PieChart.Data("Building C",c);
	 	BD = new PieChart.Data("Building D",d);
	 	WC = new PieChart.Data("Welcome Center",e);
	 	GYM = new PieChart.Data("GYM",f);
	 	LB = new PieChart.Data("Library",g);
	 	SC = new PieChart.Data("Student Common",h);
	 	AU = new PieChart.Data("Auditorium",j);
	 	
	 	//Event Handler to solve a legend issue
	 	if(a > 0)
	 		pieChartData.add(BA);
	 	if(b > 0)
	 		pieChartData.add(BB);
	 	if(c > 0)
	 		pieChartData.add(BC);
	 	if(d > 0)
	 		pieChartData.add(BD);
	 	if(e > 0)
	 		pieChartData.add(WC);
	 	if(f > 0)
	 		pieChartData.add(GYM);
	 	if(g > 0)
	 		pieChartData.add(LB);
	 	if(h > 0)
	 		pieChartData.add(SC);
	 	if(j > 0)
	 		pieChartData.add(AU);
 		chart.setData(pieChartData);

		//Constant Pie chart Colors
		int i = 0;
		  for (PieChart.Data data : pieChartData) {
		    data.getNode().setStyle(
		      "-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
		    );
		    i++;
		  }
		  
		  chart.setLegendVisible(false);
		  chart.setLabelLineLength(25);
		 
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void exportButton(ActionEvent event) throws IOException {
		File file = new File ("TableData.csv");
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		//Insert Column Headers into file
		if(file.length() == 0)
		printWriter.println(
				locationCol.getText() + "," +
				itemCol.getText() + "," +
				dateCol.getText() + "," +
				statusCol.getText() + "," +
				descpCol.getText() + "," +
				IDCol.getText()
				);

		//Insert Column data into file
		for(int i = 0; i < data.size(); i++)
			printWriter.println(
				data.get(i).getLocation().toString() + "," +
				data.get(i).getItem().toString() + "," +
				data.get(i).getDate().toString() + " ," +
				data.get(i).getStatus().toString() + "," + 
				data.get(i).getDescription().toString() + "," +
				data.get(i).getID().toString()
				);
		
		message.setText("Table Data Exported to " + file.getName());
		//Close
		printWriter.close();
	}
	
	// Event Listener on DatePicker[#date].onAction
	@FXML
	public void dateField(ActionEvent event) {
		date.getValue();
	}
		
	// Event Listener on Button.onAction
	@FXML
	public void deleteButton(ActionEvent event){
		Item selectedItem = tableView.getSelectionModel().getSelectedItem();
		backup.add(selectedItem);
		
		//Solution/Handler for IndexOutOfBoundary if only one record is available and the deleted button is pressed
		TableViewSelectionModel<Item> x = tableView.getSelectionModel();
		tableView.setSelectionModel(null);
		tableView.getItems().remove(selectedItem);
		tableView.setSelectionModel(x);
		
	    descArea.clear();
	    undo.setDisable(false);
	    message.setText("The record has been succesfully deleted");
	    
	    pieChartData.removeAll(BA,BB,BC,BD,WC,GYM,LB,SC,AU);
        a=b=c=d=e=f=g=h=j=0;
        
	    //Pie Chart data
	 	for(int i = 0; i < data.size(); i++) {
	 		if (data.get(i).getLocation().toString() == "Building A") 
	 			a= -(a-=1);
	 		else if (data.get(i).getLocation().toString() == "Building B") 
	 			b= -(b-=1);
	 		else if (data.get(i).getLocation().toString() == "Building C") 
	 			c= -(c-=1);
	 		else if (data.get(i).getLocation().toString() == "Building D") 
	 			d= -(d-=1);
	 		else if (data.get(i).getLocation().toString() == "Welcome Center") 
	 			e= -(e-=1);
	 		else if (data.get(i).getLocation().toString() == "GYM") 
	 			f= -(f-=1);
	 	    else if (data.get(i).getLocation().toString() == "Library") 
	 	    	g= -(g-=1);
	 	    else if (data.get(i).getLocation().toString() == "Student Commons") 
	 		   	h= -(h-=1);
	 	    else if (data.get(i).getLocation().toString() == "Auditorium") 
	 		   	j= -(j-=1);
	 	}

	 	BA = new PieChart.Data("Building A",a);
	 	BB = new PieChart.Data("Building B",b);
	 	BC = new PieChart.Data("Building C",c);
	 	BD = new PieChart.Data("Building D",d);
	 	WC = new PieChart.Data("Welcome Center",e);
	 	GYM = new PieChart.Data("GYM",f);
	 	LB = new PieChart.Data("Library",g);
	 	SC = new PieChart.Data("Student Common",h);
	 	AU = new PieChart.Data("Auditorium",j);
	 	
	 	//Event Handler to solve a legend issue
	 	if(a > 0)
	 		pieChartData.add(BA);
	 	if(b > 0)
	 		pieChartData.add(BB);
	 	if(c > 0)
	 		pieChartData.add(BC);
	 	if(d > 0)
	 		pieChartData.add(BD);
	 	if(e > 0)
	 		pieChartData.add(WC);
	 	if(f > 0)
	 		pieChartData.add(GYM);
	 	if(g > 0)
	 		pieChartData.add(LB);
	 	if(h > 0)
	 		pieChartData.add(SC);
	 	if(j > 0)
	 		pieChartData.add(AU);
 		chart.setData(pieChartData);

		//Constant Pie chart Colors
		int i = 0;
		  for (PieChart.Data data : pieChartData) {
		    data.getNode().setStyle(
		      "-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
		    );
		    i++;
		  }
		  
		  chart.setLegendVisible(false);
		  chart.setLabelLineLength(25);
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void undoButton(ActionEvent event) {
	    tableView.getItems().addAll(backup);
	    undo.setDisable(true);
	    backup.clear();
	    
	    pieChartData.removeAll(BA,BB,BC,BD,WC,GYM,LB,SC,AU);
        a=b=c=d=e=f=g=h=j=0;
        
	    //Pie Chart data
	 	for(int i = 0; i < data.size(); i++) {
	 		if (data.get(i).getLocation().toString() == "Building A") 
	 			a+=1;
	 		else if (data.get(i).getLocation().toString() == "Building B") 
	 			b+=1;
	 		else if (data.get(i).getLocation().toString() == "Building C") 
	 			c+=1;
	 		else if (data.get(i).getLocation().toString() == "Building D") 
	 			d+=1;
	 		else if (data.get(i).getLocation().toString() == "Welcome Center") 
	 			e+=1;
	 		else if (data.get(i).getLocation().toString() == "GYM") 
	 			f+=1;
	 	    else if (data.get(i).getLocation().toString() == "Library") 
	 			g+=1;
	 	    else if (data.get(i).getLocation().toString() == "Student Commons") 
				h+=1;
	 	    else if (data.get(i).getLocation().toString() == "Auditorium") 
				j+=1;
	 	}

	 	BA = new PieChart.Data("Building A",a);
	 	BB = new PieChart.Data("Building B",b);
	 	BC = new PieChart.Data("Building C",c);
	 	BD = new PieChart.Data("Building D",d);
	 	WC = new PieChart.Data("Welcome Center",e);
	 	GYM = new PieChart.Data("GYM",f);
	 	LB = new PieChart.Data("Library",g);
	 	SC = new PieChart.Data("Student Common",h);
	 	AU = new PieChart.Data("Auditorium",j);
	 	
	 	//Event Handler to solve a legend issue
	 	if(a > 0)
	 		pieChartData.add(BA);
	 	if(b > 0)
	 		pieChartData.add(BB);
	 	if(c > 0)
	 		pieChartData.add(BC);
	 	if(d > 0)
	 		pieChartData.add(BD);
	 	if(e > 0)
	 		pieChartData.add(WC);
	 	if(f > 0)
	 		pieChartData.add(GYM);
	 	if(g > 0)
	 		pieChartData.add(LB);
	 	if(h > 0)
	 		pieChartData.add(SC);
	 	if(j > 0)
	 		pieChartData.add(AU);
 		chart.setData(pieChartData);

		//Constant Pie chart Colors
		int i = 0;
		  for (PieChart.Data data : pieChartData) {
		    data.getNode().setStyle(
		      "-fx-pie-color: " + pieColors[i % pieColors.length] + ";"
		    );
		    i++;
		  }
		  
		  chart.setLegendVisible(false);
		  chart.setLabelLineLength(25);
	}
	
	@Override 
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		//Clock for Label that shows current time
	    Timeline clock1 = new Timeline(new KeyFrame(Duration.ZERO, z -> 
	    {        
			Calendar calendar = new GregorianCalendar();
	        currentDate.setText(calendar.getTime().toString());
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    
	    //Clock for Label that shows current Table status
	    Timeline clock2 = new Timeline(new KeyFrame(Duration.ZERO, e -> 
	    {        
        	message.setText("Ensure all required fields are filled");
	    }),
	         new KeyFrame(Duration.seconds(8))
	    );
	    
	    //Set Clocks
	    clock1.setCycleCount(Animation.INDEFINITE);
	    clock2.setCycleCount(Animation.INDEFINITE);
	    clock1.play();
	    clock2.play();
	    
	    //ListView Filter
	    listView.getItems().addAll(list);
	    listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2){
				
				descArea.clear();
				listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				if(listView.getSelectionModel().getSelectedItem().toString() != "All Locations" && !data.isEmpty())
				{
					FilteredList<Item> filteredData = new FilteredList<Item>(data, e -> e.getLocation().toString() == listView.getSelectionModel().getSelectedItem().toString());
					tableView.setItems(filteredData);
					
					//Description Area for filtered data
					 tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
						 @Override
							public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
								descArea.setText("Location: "+ filteredData.get(tableView.getSelectionModel().getSelectedIndex()).getLocation().toString() + "\r\n" +
										"Item: "+ filteredData.get(tableView.getSelectionModel().getSelectedIndex()).getItem().toString() + "\r\n" +
										"Date: "+ filteredData.get(tableView.getSelectionModel().getSelectedIndex()).getDate().toString() + "\r\n" +
										"Status: "+ filteredData.get(tableView.getSelectionModel().getSelectedIndex()).getStatus().toString() + "\r\n" +
										"Description: "+ filteredData.get(tableView.getSelectionModel().getSelectedIndex()).getDescription().toString());
							}});
				}
				else 
					tableView.setItems(data);
			}});
	    
        //Description Area
	    tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Item>() {
			@Override
			public void changed(ObservableValue<? extends Item> arg0, Item arg1, Item arg2) {
				descArea.setText("Location: "+ data.get(tableView.getSelectionModel().getSelectedIndex()).getLocation().toString() + "\r\n" +
						"Item: "+ data.get(tableView.getSelectionModel().getSelectedIndex()).getItem().toString() + "\r\n" +
						"Date: "+ data.get(tableView.getSelectionModel().getSelectedIndex()).getDate().toString() + "\r\n" +
						"Status: "+ data.get(tableView.getSelectionModel().getSelectedIndex()).getStatus().toString() + "\r\n" +
						"Description: "+ data.get(tableView.getSelectionModel().getSelectedIndex()).getDescription().toString());
				
			}});
	    
	    //Add to Combo Box
	    Location.setItems(FXCollections.observableArrayList(locations));
	    status.setItems(FXCollections.observableArrayList(statusList));

	    //Table Columns
	    locationCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Location"));
	    itemCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Item"));
	    statusCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Status"));
	    IDCol.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));
	    descpCol.setCellValueFactory(new PropertyValueFactory<Item, String>("Description"));
	    dateCol.setCellValueFactory(new PropertyValueFactory<Item, LocalDate>("Date"));
	    tableView.setItems(data);

	    //Avoid Selection of future dates
		date.setDayCellFactory(param -> new DateCell() {
	        public void updateItem(LocalDate date, boolean empty) {
	            super.updateItem(date, empty);
	            setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
	        }
	    });
		
        //Clock for enabling and disabling buttons
		Timeline clock3 = new Timeline(new KeyFrame(Duration.ZERO, z ->
		   {
		    //Event handler Add Button disable
		    if(
		        Location.getSelectionModel().isEmpty() == false && 
		        item.getText().toString() != "" && 
		        date.getValue() != null && 
		        status.getSelectionModel().isEmpty() == false && 
		        descrp.getText().toString() != ""
		        )
		        	add.setDisable(false);
		        else 
		        	add.setDisable(true);
		       
		    //Event handler Export & delete Button disable
		     if(!data.isEmpty()) {
		    	 export.setDisable(false);
		     	 delete.setDisable(false);
		     }
		      else {
		    	  export.setDisable(true);
		    	  delete.setDisable(true);
		      }
		       
		    }), 
		    	new KeyFrame(Duration.seconds(1))
		    );
	          clock3.setCycleCount(Animation.INDEFINITE);
              clock3.play();
	  }
}