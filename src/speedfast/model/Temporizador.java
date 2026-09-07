package speedfast.model;
import java.util.Random;

/**
 * Clase de utilidad encargada de simular pausas de tiempo en el sistema.
 */
public class Temporizador {

    /**
     * Método encargado de pausar (pausa corta) la ejecución un tiempo aleatorio, simulando un proceso en curso.
     */
    public static void pausaCorta() {

        Random random = new Random();

        try {

            Thread.sleep(200 + random.nextInt(700));

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    /**
     * Método encargado de pausar (pausa larga) la ejecución un tiempo aleatorio, simulando un proceso en curso.
     */
    public static void pausaLarga() {

        Random random = new Random();

        try {

            Thread.sleep(1000 + random.nextInt(1000));

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}