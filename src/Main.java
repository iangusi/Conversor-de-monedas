import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        String menu = """
                     1 ARS - Peso argentino
                     2 BOB - Boliviano boliviano
                     3 BRL - Real brasileño
                     4 CLP - Peso chileno
                     5 COP - Peso colombiano
                     6 USD - Dólar estadounidense
                    """;
        int opc1, opc2;
        float amount;

        while (true) {
            opc1=-1;
            opc2=-1;
            amount=0;

            System.out.println("\n------| Conversion de monedas");
            System.out.println("[Si quiere salir escriba 0]\n");
            System.out.print(menu);

            try {
                while (opc1 < 0 || opc1 > 6) {
                    System.out.print("Selecciona tu moneda base (1-6): ");
                    opc1 = Integer.parseInt(leer.nextLine());
                }
                if (opc1 == 0) {
                    break;
                }
                while (opc2 < 0 || opc2 > 6) {
                    System.out.print("Selecciona la moneda objetivo (1-6): ");
                    opc2 = Integer.parseInt(leer.nextLine());
                }
                if (opc2 == 0) {
                    break;
                }
                System.out.println();

                while (amount <= 0) {
                    System.out.print("Ingrese la cantidad a convertir: ");
                    amount = Float.parseFloat(leer.nextLine());
                }

                String[] monedas = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};
                String base = monedas[opc1 - 1], target = monedas[opc2 - 1];

                ConsultasAPI consultasAPI = new ConsultasAPI();
                Moneda moneda = consultasAPI.convertir(base, target, amount);

                System.out.println();
                System.out.println(amount + " [" + base + "] = "
                        + moneda.conversion_result() + " [" + target + "]." +
                        " La tasa de conversion es de: " + moneda.conversion_rate());

            } catch (NumberFormatException e) {
                System.out.println(">>> Debe ingresar datos numericos");
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(">>> "+e.getMessage());
            }
        }
        System.out.println("FIN DEL PROGRAMA");
    }
}