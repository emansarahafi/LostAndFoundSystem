// Members: Ali Abdulla, Eman Sarah Afi, Sara Abdelhakeem

package application;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public class Item {
	//Attributes
	private SimpleStringProperty itemName;
	private SimpleStringProperty location;
	private SimpleStringProperty status;
	private LocalDate date;
	private SimpleStringProperty description;
	private SimpleStringProperty ID;

	//Constructors
	Item(String location1, String itemName1, LocalDate date1, String status1, String description1, String ID1)
	{
		this.itemName = new SimpleStringProperty(itemName1);
		this.location = new SimpleStringProperty(location1);
		this.status = new SimpleStringProperty(status1);
		this.date = date1;
		this.description = new SimpleStringProperty(description1);
		this.ID = new SimpleStringProperty(ID1);
	}

	//Setters
	public void setItem(String itemName1) {itemName.set(itemName1);}
	public void setLocation(String location1) {location.set(location1);;}
	public void setStatus(String status1) {status.set(status1);}
	public void setDate(LocalDate date1) {this.date = date1;}
	public void setDescription(String description1) {description.set(description1);}
	public void setID(String ID1) {ID.set(ID1);}

	//Getters
	public String getItem() {return itemName.get();}
	public String getLocation() {return location.get();}
	public String getStatus() {return status.get();}
	public LocalDate getDate() {return date;}
	public String getDescription() {return description.get();}
	public String getID() {return ID.get();}

}