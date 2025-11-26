package com.desktop;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilsLoginData {

    private static final String FILE_NAME = "loginData.json";

    private String nom;
    private String ip;

    public UtilsLoginData() {
        loadOrCreate("");
    }

    // Cargar el JSON si existe, si no crearlo
    private void loadOrCreate(String name) {
        try {
            File file = new File(FILE_NAME);
            JSONObject data;

            if (!file.exists()) {
                // Crear archivo nuevo
                data = new JSONObject();
                data.put("nom", name);
                data.put("IP", "matrixplay2.ieti.site");
                save(data);
            } else {
                // Leer archivo existente
                String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
                data = content.isEmpty() ? new JSONObject() : new JSONObject(content);
            }

            // Asignar datos a variables
            this.nom = data.optString("nom", "");
            this.ip  = data.optString("IP", "matrixplay2.ieti.site");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Guardar datos en el fichero
    public void save(JSONObject data) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(data.toString(4)); // pretty print
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters para recuperar los datos
    public String getNom() {
        return nom;
    }

    public String getIp() {
        return ip;
    }
}

