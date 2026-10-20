package com.javier.liebreytortuga;

import java.io.IOException;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class LiebreyTortuga {
    @FXML private Button btnIniciar;
    @FXML private Label lblEstadoLiebre;
    @FXML private Label lblEstadoTortuga;
    @FXML private ProgressBar progressInicioLiebre;
    @FXML private ProgressBar progressInicioTortuga;
    

    @FXML
    private void iniciar() throws IOException {
        btnIniciar.setDisable(true);
        Thread hiloTortuga = new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    for(double i = 0.0; i <= 1.2; i = i + 0.03){
                        final double avanceTortuga = i;
                        Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                progressInicioTortuga.setProgress(avanceTortuga);
                            }
                        });
                        Thread.sleep(300);
                    }
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            lblEstadoTortuga.setText("¡La tortuga ganó la carrera!");
                        }
                    });
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        
        Thread hiloLiebre = new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    double avanceLiebre = 0.0;
                    
                    while(avanceLiebre < 1.0){
                        if(avanceLiebre < 0.4){
                            avanceLiebre += 0.10;
                            final double pos = avanceLiebre;
                            
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    progressInicioLiebre.setProgress(pos);
                                    lblEstadoLiebre.setText("La liebre: ¡va corriendo!");
                                }
                            });
                            Thread.sleep(150);
                        }
                        else if(avanceLiebre >= 0.4 && avanceLiebre < 0.5){
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    lblEstadoLiebre.setText("La liebre: ¡Se durmió!");
                                }
                            });
                            Thread.sleep(5000);
                            avanceLiebre += 0.08;
                            final double pos = avanceLiebre;
                            
                            Platform.runLater(new Runnable(){
                                @Override
                                public void run(){
                                    progressInicioLiebre.setProgress(Math.min(pos, 1.0));
                                    lblEstadoLiebre.setText("La liebre: ¡Despertó y corre!");
                                }
                            });
                            Thread.sleep(150);
                        }
                        else{
                            avanceLiebre += 0.08;
                            final double pos = avanceLiebre;
                            Platform.runLater(new Runnable(){
                            @Override
                            public void run(){
                                progressInicioLiebre.setProgress(Math.min(pos, 1.0));
                                lblEstadoLiebre.setText("La liebre: ¡Corre hacia la meta!");
                            }
                            });
                            Thread.sleep(150);
                        }
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        
        hiloTortuga.start();
        hiloLiebre.start();
    }
}