public class Cliente implements Runnable {

    private int id; private Impresoras impresoras;

    public Cliente (int i, Impresoras im){
        this.id=i; this.impresoras=im;
    }

    public void run(){
        try {
            int impAux=impresoras.tomarImpresora();
            System.out.println("Cliente "+id+" usando impresora "+impAux);
            Thread.sleep(1000);
            System.out.println("Cliente "+id+" liberando impresora "+impAux);
            impresoras.liberarImpresora(impAux);
        } catch (InterruptedException e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }
    }
    
}
