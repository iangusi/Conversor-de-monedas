import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultasAPI {
    public Moneda convertir(String base, String target, float amount){
        String direccion = "https://v6.exchangerate-api.com/v6/4962980a1f7446653777c8d2/pair/"+
                base+"/"+target+"/"+amount;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        }catch (IOException | InterruptedException e){
            throw new RuntimeException("Ocurrió un error al hacer la conversión.");
        }
    }
}
