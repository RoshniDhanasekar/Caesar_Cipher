//compile javac --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls Caesar_Cipher.java
//java --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls Caesar_Cipher
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * @author Roshni Dhanasekar
 * @version 1.0
 */
public class Caesar_Cipher extends Application {
    /**
     * @param stage to set the display of the Grade Calculator
     */
    public void start(Stage stage) {
        Button encryptButton = new Button();
        encryptButton.setText("Encrypt");

        Button decryptButton = new Button();
        decryptButton.setText("Decrypt");

        TextField enteredText = new TextField();
        Label textLabel = new Label("Enter text: ");
        TextField shiftNum = new TextField();
        Label shiftLabel = new Label("Shift: ");

        //result after button is pressed
        Label outputText = new Label();
        Label outputLabel = new Label("Output: ");

        encryptButton.setOnAction(event -> {
                try {
                    //convert the string to a double - throws an error if text entered wasn't a double
                    char[] encryptArr = enteredText.getCharacters().toString().toLowerCase().toCharArray();
                    int shiftKey = Integer.parseInt(shiftNum.getCharacters().toString());

                    //check if entry is valid
                    boolean invalidEntry = false;
                    if (shiftKey <= 0) {
                      invalidEntry = true;
                    } else {
                      for(char c : encryptArr){
                          if(Character.isDigit(c)){
                            invalidEntry = true;
                          }
                      }
                    }
                    if (shiftKey > 26) {
                      shiftKey = shiftKey % 26;
                    }
                    //final output string
                    String result = encrypt(encryptArr, shiftKey);
                    if (!invalidEntry) {
                        outputText.setText(result);
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) { //alert if entry is invalid
                    Alert invalNum = new Alert(Alert.AlertType.ERROR);
                    invalNum.setTitle("Error");
                    invalNum.setHeaderText("Invalid Forms");
                    invalNum.setContentText("At least one of the entries you have inputted is not valid.");
                    invalNum.showAndWait();

                }
            });

        decryptButton.setOnAction(event -> {
                try {
                    //convert the string to a double - throws an error if text entered wasn't a double
                    char[] decryptArr = enteredText.getCharacters().toString().toLowerCase().toCharArray();
                    int shiftKey = Integer.parseInt(shiftNum.getCharacters().toString());

                    //check if entry is valid
                    boolean invalidEntry = false;
                    if (shiftKey <= 0) {
                      invalidEntry = true;
                    } else {
                      for(char c : decryptArr){
                          if(Character.isDigit(c)){
                            invalidEntry = true;
                          }
                      }
                    }
                    if (shiftKey > 26) {
                      shiftKey = shiftKey % 26;
                    }
                    //final output string
                    String result = decrypt(decryptArr, shiftKey);
                    if (!invalidEntry) {
                        outputText.setText(result);
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) { //alert if entry is invalid
                    Alert invalNum = new Alert(Alert.AlertType.ERROR);
                    invalNum.setTitle("Error");
                    invalNum.setHeaderText("Invalid Forms");
                    invalNum.setContentText("At least one of the entries you have inputted is not valid.");
                    invalNum.showAndWait();

                }
            });

        //display positioning
        HBox text = new HBox();
        text.setAlignment(Pos.CENTER);
        text.getChildren().add(textLabel);
        text.getChildren().add(enteredText);

        HBox shiftAmount = new HBox();
        shiftAmount.setAlignment(Pos.CENTER);
        shiftAmount.setSpacing(10);
        shiftAmount.getChildren().addAll(shiftLabel, shiftNum);

        HBox decryptBtn = new HBox();
        decryptBtn.setAlignment(Pos.CENTER);
        decryptBtn.setSpacing(10);
        decryptBtn.getChildren().add(decryptButton);

        HBox encryptBtn = new HBox();
        encryptBtn.setAlignment(Pos.CENTER);
        encryptBtn.setSpacing(10);
        encryptBtn.getChildren().add(encryptButton);

        HBox finalResult = new HBox();
        finalResult.setAlignment(Pos.CENTER);
        finalResult.setSpacing(10);
        finalResult.getChildren().addAll(outputLabel, outputText);

        VBox output = new VBox();
        output.setAlignment(Pos.CENTER);
        output.setSpacing(10);
        output.getChildren().addAll(text, shiftAmount, encryptBtn, decryptBtn, finalResult);

        Scene scene = new Scene(output, 400, 500);
        stage.setTitle("Caesar Cipher Encrypter/Decrypter");
        stage.setScene(scene);
        stage.show();

    }

    /** Encrypt method
     * @param charArr is the character array of the text
     * @param shift is the shift key to change the text
     * @return encrypted text
     */
    private String encrypt(char[] charArr, int shift) {
        int[] ascii = new int[charArr.length];
        //convert char to ascii
        for (int i = 0; i < charArr.length; i++) {
            ascii[i] = (int)charArr[i];
        }
        //shift ascii array
        for (int i = 0; i < ascii.length; i++) {
           if (ascii[i] == 32) { //space is not shifted
              ascii[i] = ascii[i];
           } else if (ascii[i] + shift > 122) {
              ascii[i] = ascii[i] - 26 + shift;
           } else {
              ascii[i] += shift;
           }
        }
        //convert ascii arr to String
        String str = "";
        for (int num: ascii) {
            str += Character.toString((char) num);
        }
        return str;
    }

    /** Decrypt method
     * @param charArr is the character array of the text
     * @param shift is the shift key to change the text
     * @return decrypted text
     */
    private String decrypt(char[] charArr, int shift) {
        int[] ascii = new int[charArr.length];
        //convert char to ascii
        for (int i = 0; i < charArr.length; i++) {
            ascii[i] = (int)charArr[i];
        }
        //shift ascii array
        for (int i = 0; i < ascii.length; i++) {
           if (ascii[i] == 32) { //space is not shifted
              ascii[i] = ascii[i];
           } else if (ascii[i] - shift < 97) {
              ascii[i] = ascii[i] + 26 - shift;
           } else {
              ascii[i] -= shift;
           }
        }
        //convert ascii arr to String
        String str = "";
        for (int num: ascii) {
            str += Character.toString((char) num);
        }
        return str;
    }

}
