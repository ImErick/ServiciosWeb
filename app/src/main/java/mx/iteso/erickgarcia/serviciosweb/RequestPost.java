package mx.iteso.erickgarcia.serviciosweb;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by erickgarcia on 04/04/18
 */

public class RequestPost {

    private URL url;
    private HttpURLConnection httpURLConnection;
    private OutputStreamWriter outputStreamWriter;
    private URLConnection urlConnection;
    private JSONObject jsonObject;
    private InputStream inputStream;
    private String result;

    public String requestPost(String[] values){
        try {
            // url base para el consumo de los servicios web
            url = new URL(Constantes.URL_BASE);
            urlConnection = url.openConnection();
            httpURLConnection = (HttpURLConnection) urlConnection;

            // metodo para enviar la informacion
            httpURLConnection.setRequestMethod("POST");

            // propiedades del encabezado
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoOutput(true); //POST: true GET:false

            // realiza la conexion
            httpURLConnection.connect();

            outputStreamWriter = new OutputStreamWriter(httpURLConnection.getOutputStream());
            jsonObject = new JSONObject();
            jsonObject.put("Nombre", values[1]); //String: nombre
            jsonObject.put("Apellido", values[2]); //String: apellido
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() != 200) // posible error en el request
                inputStream = httpURLConnection.getErrorStream();
            else if (httpURLConnection.getResponseCode() == 200) // exito en el request
                inputStream = httpURLConnection.getInputStream();
            result = convertStreamtoString(inputStream);
        } catch (Exception ex) {
            Log.e("errorWS", ex.toString());
        }
        return result;
    }

    private String convertStreamtoString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line =  null;

        try {
            while ((line = reader.readLine()) !=  null) {
                stringBuilder.append(line + "\r");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
