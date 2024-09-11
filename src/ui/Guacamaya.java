package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
     */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar
     * los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
     */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out
                    .println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println(
                    "5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "
                            + calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "
                            + calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "
                            + calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las " + precios.length + " referencias vendidas en el dia, "
                            + consultarReferenciasSobreLimite(limite) + " superaron el limite minimo de ventas de "
                            + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de
     * referencias de producto diferentes
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de
     * almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    public static int total_p = 0;

    /**
     * Descripcion: Solicita al usuario los precios y cantidades de cada referencia
     * vendida en el día y los almacena en los arreglos correspondientes
     * pre: Los arreglos precios y unidades deben estar inicializados
     * pos: Los datos de precios y unidades quedan almacenados en los arreglos
     * correspondientes
     */
    public static void solicitarDatos() {

        for (int i = 0; i < unidades.length; i++) {
            System.out.println("Ingrese el precio del producto " + (i + 1));
            precios[i] = reader.nextDouble();
            System.out.println("Ingrese la cantidad vendida del producto " + (i + 1));
            unidades[i] = reader.nextInt();

        }

    }

    /**
     * Descripcion: Calcula la cantidad total de unidades vendidas en el día sumando
     * los valores del arreglo de unidades
     * 
     * @return int La cantidad total de unidades vendidas
     */
    public static int calcularTotalUnidadesVendidas() {
        int totalUnidades = 0;
        for (int i = 0; i < unidades.length; i++) {
            totalUnidades += unidades[i];
        }
        return totalUnidades;

    }

    /**
     * Descripcion: Calcula el precio promedio de las referencias vendidas, sumando
     * todos los precios y dividiéndolos por la cantidad de referencias
     * 
     * @return double El precio promedio de las referencias vendidas
     */
    public static double calcularPrecioPromedio() {
        double suma_p = 0;
        if (precios.length == 0) {
            return 0;
        }
        for (double precio : precios) {
            suma_p += precio;

        }

        return suma_p / precios.length;

    }

    /**
     * Descripcion: Calcula las ventas totales del día, multiplicando la cantidad de
     * unidades vendidas por el precio de cada referencia
     * 
     * @return double El total de dinero recaudado en ventas durante el día
     */
    public static double calcularVentasTotales() {
        double ventas_t = 0;
        for (int i = 0; i < unidades.length; i++) {
            ventas_t += unidades[i] * precios[i];
        }
        return ventas_t;

    }

    /**
     * Descripcion: Consulta cuántas referencias superan un límite de ventas,
     * multiplicando la cantidad de unidades por su precio y comparando con el
     * límite
     * 
     * @param limite El límite mínimo de ventas para la comparación
     * @return int El número de referencias que superaron el límite de ventas
     */
    public static int consultarReferenciasSobreLimite(double limite) {
        int conteo = 0;
        for (int i = 0; i < unidades.length; i++) {
            if (unidades[i] * precios[i] > limite) {
                conteo++;
            }
        }
        return conteo;
    }

}
