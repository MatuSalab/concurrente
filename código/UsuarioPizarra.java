import java.util.concurrent.Semaphore;

public class UsuarioPizarra implements Runnable{
    int id; Pizarra pizarra;

    public UsuarioPizarra(int i, Pizarra p){
        this.id=i; this.pizarra=p;
    }

    public void run(){
        try {
            while(true){
                Thread.sleep(1000);

                if(pizarra.tomar()){
                    try {
                        System.out.println("El usuario "+id+" tomó la pizarra. (OCUPADA)");
                        Thread.sleep(1000);
                        
                    } finally {
                        pizarra.liberar();
                        System.out.println("El usuario "+id+" liberó la pizarra. (LIBERADA)");
                    }
                }else{
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            // TODO: handle exception

            Thread.currentThread().interrupt();
        }
    }


}
