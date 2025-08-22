package proyectopelea;
import java.util.Random; // Importa la clase Random para generar valores aleatorios
import java.util.Scanner; // Importa la clase Scanner para leer datos del usuario 

public class BatallaEpica {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);  // Crea un objeto Scanner para leer por consola
        Random random = new Random();              // Crea un objeto Random para generar numeros aleatorios

        // Solicita al usuario la vida inicial de cada personaje
        System.out.print("Ingrese la vida inicial de Deadpool: ");
        int vidaDeadpool = scanner.nextInt();
        System.out.print("Ingrese la vida inicial de Wolverine: ");
        int vidaWolverine = scanner.nextInt();
        
        // Variables para controlar si los personajes debn regenerarse (pierden su turno)
        boolean regenDeadpool = false;
        boolean regenWolverine = false;
        int turno = 1;
        
        // Ciclo principal de la batalla - se repite  mientras ambos personajes tengan vida
        while (vidaDeadpool > 0 && vidaWolverine > 0) {
            System.out.println("\n--- Turno " + turno + " ---");
            Thread.sleep(1000); // Pausa de 1 segundo

            // Deadpool ataca si no está regenerando
            if (!regenDeadpool) {
                int daño = random.nextInt(91) + 10; // Daño aleatorio entre 10 y 100
                if (daño == 100) {
                    regenWolverine = true; // Si hace daño maximo, wolverine pierde su proximo turno
                    System.out.println("¡Deadpool hace daño máximo! Wolverine se regenera el próximo turno.");
                } else {
                    boolean esquiva = random.nextInt(100) < 20; // Wolverine tiene 20% de  evasion
                    if (esquiva) {
                        System.out.println("¡Wolverine esquiva el ataque de Deadpool!");
                    } else {
                        vidaWolverine -= daño; // Se descuenta la vida si no esquiva
                        System.out.println("Deadpool inflige " + daño + " de daño a Wolverine.");
                    }
                }
            } else {
                // Si esta regenerando, deadpool pierde el turno
                System.out.println("Deadpool se está regenerando y no puede atacar.");
                regenDeadpool = false;
            }
                // Si wolverine ya quedo sin vida, se termina la batalla
            if (vidaWolverine <= 0) break;

            // Wolverine ataca si no está regenerando
            if (!regenWolverine) {
                int daño = random.nextInt(111) + 10; // Daño aleatorio entre 10 y 120
                if (daño == 120) {
                    regenDeadpool = true;
                    System.out.println("¡Wolverine hace daño máximo! Deadpool se regenera el próximo turno.");
                } else {
                    boolean esquiva = random.nextInt(100) < 25; // Deadpool tiene 25% de evasion
                    if (esquiva) {
                        System.out.println("¡Deadpool esquiva el ataque de Wolverine!");
                    } else {
                        vidaDeadpool -= daño; // Se descuenta la vida si no esquiva
                        System.out.println("Wolverine inflige " + daño + " de daño a Deadpool.");
                    }
                }
            } else {
                // Si esta regenerando, wolverine pierde el turno 
                System.out.println("Wolverine se está regenerando y no puede atacar.");
                regenWolverine = false;
            }

            // Muestra la vida actual de cada personaje al final del turno
            System.out.println("Vida -> Deadpool: " + Math.max(vidaDeadpool, 0) +
                               " | Wolverine: " + Math.max(vidaWolverine, 0));

            turno++; // Aumenta el numero de turnos
        }

        // Resultado final
        System.out.println("\n--- RESULTADO FINAL ---");
        if (vidaDeadpool <= 0 && vidaWolverine <= 0) {
            System.out.println("¡Empate! Ambos han caído.");
        } else if (vidaDeadpool <= 0) {
            System.out.println("¡Wolverine gana la batalla!");
        } else {
            System.out.println("¡Deadpool gana la batalla!");
        }

        scanner.close(); // Cierra el Scanner al final del programa 
    }
}
