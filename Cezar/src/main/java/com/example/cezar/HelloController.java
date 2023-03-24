package com.example.cezar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class HelloController {//19 в списку


    //Tab 1
    @FXML
    private Label label;
    @FXML
    private TextField text;
    @FXML
    private TextField key;
    @FXML
    private TextArea textArea;

    @FXML
    protected void Encrypt() {
        char[] letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();//32
        String text = this.text.getText();
        int error = 0;
        int key = 0;

        if (text.isEmpty()) {
            this.text.setText("");
            this.text.setPromptText("Введіть текст");
            error++;
        }

        try {key = Integer.parseInt(this.key.getText());
        }catch (NumberFormatException e){
            error ++;
            this.key.setText("");
            this.key.setPromptText("Введіть число");
        }

        if (error == 0){

            String cipherText = "";
            text = text.toLowerCase();

                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) == ' ') {
                        cipherText += ' ';
                    } else {
                        for (int j = 0; j < letters.length; j++) {
                            if (text.charAt(i) == letters[j]) {
                                int index = j + key;
                                if (index > 31) {
                                    index = index - 32;
                                }
                                cipherText += letters[index];
                            }
                        }
                    }
                }
                textArea.setText(cipherText);
        }
    }
    @FXML
    protected void AddInFile(){
        String EncryptText = textArea.getText();
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();

            FileWriter writer = new FileWriter(path, false);
            writer.write(EncryptText);
            writer.flush();
            label.setText("Зашифрований текст записано в файл");
        }catch (Exception e) {
            label.setText("Виберіть файл");
        }
    }

//Tab 2
    @FXML
    private Label label2;
    @FXML
    private TextField key2;
    @FXML
    private TextArea textArea2;
    @FXML
    protected void Chosser(){
        String path = "";
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Виберіть файл");
            File file = chooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            label2.setText(path);
        } catch (Exception e) {
            label2.setText("Виберіть файл");
        }
    }
    @FXML
    protected void Decrypt(){
        String text = "";
        int error = 0;
        int key = 0;

        try (FileReader reader = new FileReader(label2.getText())){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()){
                text += scanner.nextLine();
            }
            scanner.close();
        }catch (Exception e){
            label2.setText("Виберіть файл");
            error ++;
        }

        char[] letters = "абвгдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray();//32

        try{key = Integer.parseInt(key2.getText());
        }catch (NumberFormatException e){
            error ++;
            key2.setText("");
            key2.setPromptText("Введіть число");
        }

        if (error == 0){
            String cipherText = "";
            text = text.toLowerCase();

            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) == ' ') {
                    cipherText += ' ';
                } else {
                    for (int j = 0; j < letters.length; j++) {
                        if (text.charAt(i) == letters[j]) {
                            int index = j - key;
                            if (index < 0) {
                                index = index + 32;
                            }
                            cipherText += letters[index];
                        }
                        else if (text.charAt(i) == ' '){
                        }
                    }
                }
            }
            textArea2.setText(cipherText);
        }
    }
}