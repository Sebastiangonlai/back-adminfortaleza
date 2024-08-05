package backend.academia.fortaleza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
        abrirNavegadorConRuntime("http://localhost:8080/swagger-ui/index.html");
	}

    private static void abrirNavegadorConRuntime(String url) {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (Exception e) {
            System.err.println("ERROR AL ABRIR EL NAVEGADOR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }
}
