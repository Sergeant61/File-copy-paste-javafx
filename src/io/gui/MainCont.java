package io.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import io.file.FileCopyPaste;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;


public class MainCont implements Initializable {
	
	@FXML ComboBox<String> diskCombo;
	@FXML RadioButton radioKopyala;
	@FXML RadioButton radioTasi;
	@FXML ListView<String> list;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ToggleGroup group = new ToggleGroup();
		radioKopyala.setSelected(true);
		radioKopyala.setToggleGroup(group);
		radioTasi.setToggleGroup(group);	
		
		List<String> list = FileCopyPaste.fileExists();
		
		String[] mylist = new String[list.size()];
		for(int i=0 ; i < list.size() ; i ++ ){
			mylist[i]=list.get(i);
		}
		
		ObservableList<String> data = FXCollections.observableArrayList(mylist);
		diskCombo.setItems(data);
		
		if(mylist.length > 0){
			diskCombo.setValue(mylist[0]);
		}
		onActionDisk();	
		diskCombo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onActionDisk();				
			}
		});
		
		
	}
	
	@FXML
	public void onClickYap(){
		String dizin1=null,dizin2=null,dosyaIsmi=null;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Kopyalanacak Dosyayý Açýn");
		File file1 = fileChooser.showOpenDialog(null);
		
		dizin1=file1.getPath();
		dosyaIsmi=file1.getName();
		dizin1=dizin1.substring(0, dizin1.length()-dosyaIsmi.length());
		
		fileChooser.setInitialFileName(dosyaIsmi);
		
		File file2 = fileChooser.showSaveDialog(null);
		dizin2=file2.getPath();
		
		//System.out.println(dizin1);
		try {
			FileCopyPaste.copyFile(dizin1, dizin2, dosyaIsmi);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void onActionDisk(){
	
		File[] file = FileCopyPaste.FileList(diskCombo.getValue()+":/");
		String[] mylist = new String[file.length];
		for(int i=0 ; i < file.length ; i ++ ){
			mylist[i]=file[i].getName();
		}
		
		ObservableList<String> data = FXCollections.observableArrayList(mylist);
		list.setItems(data);
	}

}
