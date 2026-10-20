package com.javier.conteoclick;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class ConteoClick {
    @FXML private TextField txtContador;
    @FXML private Button btnContar;
    @FXML private ProgressBar progressBar;
    @FXML private Button btnEsperar;

    private int numeroClics = 0;

    @FXML
    private void Contar() {
        numeroClics++;
        txtContador.setText(String.valueOf(numeroClics));
    }

    @FXML
    private void Esperar() {
        btnEsperar.setDisable(true);
        
        Thread relojEspera = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i <= 10; i++) {
                        final double avBarra = i / 10.0;
                        
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(avBarra);
                            }
                        });
                        
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            btnEsperar.setDisable(false);
                        }
                    });
                }
            }
        });
        
        relojEspera.start();
    }
}