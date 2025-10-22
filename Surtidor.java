public class Surtidor {
    int litros;//Litros de combustible

    public Surtidor(int l){
        this.litros=l;
    }

    public synchronized int cargar(Auto a, int cantidad) {
        if(this.litros>=cantidad) {
            System.out.println("Cargando "+cantidad+" litros al auto "+a.patente);
            this.litros-=cantidad;
            int litros= a.cargarCombustible();
            System.out.println("Quedan "+this.litros+" litros en el surtidor");
        } else {
            System.out.println("No hay suficiente combustible en el surtidor para el auto "+a.patente);
        } return litros;
    }
}
