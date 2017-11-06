package fatec.app.view;

import fatec.app.process.Fila;
import fatec.app.process.FilaBatch;
import fatec.app.process.FilaInterativa;
import fatec.app.process.FilaSistema;
import fatec.app.process.Processo;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 *
 * @author Vinicius Lelis
 */
public class FXMLDocumentController implements Initializable {

    private Fila sistema;
    private FilaInterativa interativa;
    private Fila batch;

    @FXML
    private AnchorPane AnchorPane;

    /*
        Botões dos processos de sistema
     */
    @FXML
    private Button sy01;
    @FXML
    private Button sy02;
    @FXML
    private Button sy03;
    @FXML
    private Button sy04;

    /*
        Botões do processo interativo
     */
    @FXML
    private Button int01;
    @FXML
    private Button int02;
    @FXML
    private Button int03;
    @FXML
    private Button int04;

    /*
        Botões dos processos batch
     */
    @FXML
    private Button batch01;
    @FXML
    private Button batch02;
    @FXML
    private Button batch03;
    @FXML
    private Button batch04;
    //

    @FXML
    private Rectangle retangulo;
    @FXML
    private Button syLineB;
    @FXML
    private Button intLineB;
    @FXML
    private Button batchLineB;

    public FXMLDocumentController() {
        sistema = new FilaSistema();
        interativa = new FilaInterativa(this);
        batch = new FilaBatch();
    }

    public Button getSy01() {
        return sy01;
    }

    public void setSy01(Button sy01) {
        this.sy01 = sy01;
    }

    public Button getSy02() {
        return sy02;
    }

    public void setSy02(Button sy02) {
        this.sy02 = sy02;
    }

    public Button getSy03() {
        return sy03;
    }

    public void setSy03(Button sy03) {
        this.sy03 = sy03;
    }

    public Button getSy04() {
        return sy04;
    }

    public void setSy04(Button sy04) {
        this.sy04 = sy04;
    }

    public Button getInt01() {
        return int01;
    }

    public void setInt01(Button int01) {
        this.int01 = int01;
    }

    public Button getInt02() {
        return int02;
    }

    public void setInt02(Button int02) {
        this.int02 = int02;
    }

    public Button getInt03() {
        return int03;
    }

    public void setInt03(Button int03) {
        this.int03 = int03;
    }

    public Button getInt04() {
        return int04;
    }

    public void setInt04(Button int04) {
        this.int04 = int04;
    }

    public Button getBatch01() {
        return batch01;
    }

    public void setBatch01(Button batch01) {
        this.batch01 = batch01;
    }

    public Button getBatch02() {
        return batch02;
    }

    public void setBatch02(Button batch02) {
        this.batch02 = batch02;
    }

    public Button getBatch03() {
        return batch03;
    }

    public void setBatch03(Button batch03) {
        this.batch03 = batch03;
    }

    public Button getBatch04() {
        return batch04;
    }

    public void setBatch04(Button batch04) {
        this.batch04 = batch04;
    }

    private Processo newProcess() {

        int id = Integer.parseInt(JOptionPane.showInputDialog("Insira o ID do processo"));
        int prioridade = Integer.parseInt(JOptionPane.showInputDialog("Insira A prioridade do processo"));
        int tempo = Integer.parseInt(JOptionPane.showInputDialog("Insira o tempo de execução do processo"));

        return new Processo(id, prioridade, 0);
    }

    @FXML
    private void addProcessoSistema(ActionEvent event) {
        sistema.addProcesso(newProcess());
    }

    @FXML
    private void addProcessoInterativo(ActionEvent event) {
        interativa.addProcesso(newProcess());
    }

    @FXML
    private void addProcessoBatch(ActionEvent event) {
        batch.addProcesso(newProcess());
    }

    @FXML
    private void atualizar(ActionEvent event) {
        new Thread(interativa).start();
    }

    // Alterar cores dos botões
    private String setVazio() {
        return "-fx-background-color:FFFFFF;";
    }

    private String setExecutando() {
        return "-fx-background-color:#79ff75;";
    }

    private void setAguardando(Button button) {
        button.setStyle("-fx-background-color:#f1ff1f;");
    }

    private void setPronto(Button button) {
        button.setStyle("-fx-background-color:#ff28de;");
    }

    private void setLineExecutando(Button button) {
        button.setStyle("-fx-background-color:#79ff75;");
    }

    private void setLineVazio(Button button) {
        button.setStyle("-fx-background-color:WHITE;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
