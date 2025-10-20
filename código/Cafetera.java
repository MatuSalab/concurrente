import java.util.concurrent.Semaphore;

public class Cafetera implements Runnable{
    Semaphore semPedido;
    Semaphore semAviso;

    public Cafetera(Semaphore pedido, Semaphore aviso) {
        this.semPedido = pedido;
        this.semAviso = aviso;
    }

    public void usar(String id){
        try{
            System.out.println("Estudiante "+id+" usando la cafetera");
            Thread.sleep(300); // Simula el tiempo de usar la cafetera
            System.out.println("Cafetera usada");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void reponerCafe(){
        try{
            System.out.println("Reponiendo café");
            Thread.sleep(500); // Simula el tiempo de reponer café
            System.out.println("Café repuesto");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public void run(){
        while(true){
            try {
                semPedido.acquire();
                reponerCafe();
                semAviso.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }


} 
    

