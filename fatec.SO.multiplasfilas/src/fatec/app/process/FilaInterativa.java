package fatec.app.process;

import fatec.app.view.FXMLDocumentController;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

public class FilaInterativa extends Fila implements Escalonamento, Runnable {

    private int tempoLimite = 10;
    private Processo[] processos;
    private int primeiro;
    private int ultimo;
    private int total;
    private FXMLDocumentController controller;

    public FilaInterativa(FXMLDocumentController controller) {
        setPrioridade(3);
        primeiro = 0;
        ultimo = 0;
        total = 0;
        processos = new Processo[4];
        this.controller = controller;
    }

    public Processo[] getProcessos() {
        return processos;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public void addProcesso(Processo processo) {
        if (isFull()) {
            throw new RuntimeException("A fila de processos está cheia");
        }
        escalonamento(processo);
    }

    public Processo removeProcesso() {
        if (isEmpty()) {
            throw new RuntimeException("A fila de processos está vazia");
        }
        Processo temp = processos[primeiro];
        total--;
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return total == 0;
    }

    public boolean isFull() {
        return total == processos.length;
    }

    @Override
    public void escalonamento(Processo processo) {

        /*
            Lógica das cores dos botões
            arrayDeProcessos[0] = A mesma posição ocupada pelo processo
            será a pintada na interface
         */
        processos[ultimo] = processo;

        if (ultimo == 0) {
            Button temp = controller.getInt01();
            temp.setStyle("-fx-background-color:#ff28de;");
            temp.setText("" + total);
            controller.setInt01(temp);
        } else if (ultimo == 1) {
            Button temp = controller.getInt02();
            temp.setStyle("-fx-background-color:#ff28de;");
            temp.setText("" + total);
            controller.setInt01(temp);
        } else if (ultimo == 2) {
            Button temp = controller.getInt03();
            temp.setStyle("-fx-background-color:#ff28de;");
            temp.setText("" + total);
            controller.setInt01(temp);
        } else if (ultimo == 3) {
            Button temp = controller.getInt04();
            temp.setStyle("-fx-background-color:#ff28de;");
            temp.setText("" + total);
            controller.setInt01(temp);
        }
        ultimo = (ultimo + 1) % processos.length;
        total++;
    }

    @Override
    public void run() {
        System.out.println("Cheguei aqui");
        Button temp;
        while (!isEmpty()) {
            for (int i = 0; i < processos.length; i++) {
                for (int j = 0; j < tempoLimite; j++) {
                    switch (i) {
                        case 0:
                            temp = controller.getInt01();
                            temp.setStyle("-fx-background-color:#79ff75;");
                            controller.setInt01(temp);
                            break;
                        case 1:
                            temp = controller.getInt02();
                            temp.setStyle("-fx-background-color:#79ff75;");
                            controller.setInt01(temp);
                            break;
                        case 2:
                            temp = controller.getInt03();
                            temp.setStyle("-fx-background-color:#79ff75;");
                            controller.setInt01(temp);
                            break;
                        case 3:
                            temp = controller.getInt04();
                            temp.setStyle("-fx-background-color:#79ff75;");
                            controller.setInt01(temp);
                            break;
                        default:
                            break;
                    }
                    if (!processos[i].getExec()) {
                        try {
                            System.out.println(i);
                            Thread.sleep(3 * 1000);
                        } catch (InterruptedException ex) {
                            JOptionPane.showMessageDialog(null, "Erro ao tentar executar o processo interativo");
                        }

                        processos[i].setTempo(processos[i].getTempo() - j);
                    }
                }
                if (processos[i].getTempo() < 0) {
                    processos[i].setExec(false);
                    removeProcesso();
                }
            }
        }
    }
}
