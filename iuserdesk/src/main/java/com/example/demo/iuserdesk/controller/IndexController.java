package com.example.demo.iuserdesk.controller;

import com.example.demo.model.TotalVotos;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class IndexController implements Initializable {

    @FXML
    BarChart<String, Integer> graficaVotosFx;
    @FXML
    TextField nombreVotanteFx;
    @FXML
    ChoiceBox<String> seleccionDeCandidatoFx;
    @FXML
    Button botonVotarFx;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicializarChoice();
        graficaVotosFx.getStylesheets().add(getClass().getResource("/fxml/css/colores.css").toExternalForm());

    }

    public void inicializarChoice() {
        seleccionDeCandidatoFx.getItems().addAll("Chespirito del Ocho", "Chefso Villa", "Lolita la del Barrio", "Nulo");
        seleccionDeCandidatoFx.setValue("Nulo");
    }

    public void agregarVotoDB() {
        String nombre = nombreVotanteFx.getText();
        String voto = seleccionDeCandidatoFx.getValue();
        nombreVotanteFx.clear();
        seleccionDeCandidatoFx.setValue("Nulo");

        mandarVotoSocket(nombre, voto);
        mostrarVentana(nombre);
        cargarPantalla();
    }
    public void cargarPantalla(){
        obtenerResultados();
    }

    public void mostrarVentana(String nombre) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(nombre + " \ntu voto ha sido guardado exitosamente!");
        alert.showAndWait();
    }

    public void mandarVotoSocket(String nombre, String voto) {
        try (Socket socket = new Socket("localhost", 12345)) {
            // Crear un objeto con los datos del voto
            Map<String, String> datosVoto = new HashMap<>();
            datosVoto.put("nombreVotante", nombre);
            datosVoto.put("candidato", voto);
            // Convertir el objeto a JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(datosVoto);

            // Enviar el JSON por el socket
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            writer.println(json);  // se manda una línea con el JSON
            System.out.println("📤 JSON enviado al servidor: " + json);

        } catch (Exception e) {
            System.err.println("❌ Error al enviar el JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void obtenerResultados() {
        // Crear un hilo para obtener los resultados y actualizar la UI sin bloquearla


                try (Socket socket = new Socket("localhost", 5001)) {
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeObject("1");  // Enviar "1" como un objeto serializado
                    out.flush();


                    // Leer la respuesta (resultados de los votos)
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    TotalVotos votosRecibidos = (TotalVotos) in.readObject();  // Recibe el objeto TotalVotos

                    // Actualizar la gráfica en el hilo principal
                     mostrarGraficaDatos(votosRecibidos);

                } catch (Exception e) {
                    System.err.println("❌ Error al obtener los resultados: " + e.getMessage());
                    e.printStackTrace();
                }



    }

    public void mostrarGraficaDatos(TotalVotos votosRecibidos) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Candidatos");
        series.getData().add(new XYChart.Data<>("Chespirito del Ocho", votosRecibidos.votoCandidato1));
        series.getData().add(new XYChart.Data<>("Chefso Villa", votosRecibidos.votoCandidato2));
        series.getData().add(new XYChart.Data<>("Lolita la del Barrio", votosRecibidos.votoCandidato3));
        series.getData().add(new XYChart.Data<>("Nulo", votosRecibidos.votoCandidatoNulo));

        graficaVotosFx.getData().clear();  // Limpiar los datos antiguos de la gráfica
        graficaVotosFx.getData().addAll(series);  // Agregar los nuevos datos a la gráfica
    }
}
