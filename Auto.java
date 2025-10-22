public class Auto implements Runnable {
    String patente;
    private int capacidad, combustible;
    Surtidor s;


    public Auto(String patente,int capacidad, int combustible, Surtidor s) {
        this.patente = patente; this.combustible = combustible; this.capacidad=capacidad; this.s=s;
    }

    public int cargarCombustible() {
        int c= this.capacidad - this.combustible;
        
        this.combustible = this.capacidad;
        try {
            Thread.sleep(500);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Hilo detenido");
        }
        return c;
    }

    public int getCapacidad() {
        return this.capacidad;
    }

    public int getCombustibleActual() {
        return this.combustible;
    }

    @Override
    public void run() {
        if (this.combustible <= this.capacidad * 0.2) {
            int falta = capacidad - this.combustible;
            if (falta > 0) {
                int cargado = s.cargar(this, falta);
                if (cargado == 0) {
                    System.out.println("Surtidor sin combustible. El auto "+this.patente+" sigue con "+ this.combustible);
                } else {
                    System.out.println(this.patente+" cargó "+ falta+" litros");
                }
            }
        } else {
            System.out.println(this.patente+" no necesitó cargar. Combustible: "+this.combustible+" Capacidad: "+this.capacidad);
        }
    }
}

