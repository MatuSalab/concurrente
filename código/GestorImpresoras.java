import java.util.concurrent.Semaphore;

public class GestorImpresoras {
    public Semaphore [] impresoras;
    Semaphore mutex, aviso, contar;

    public GestorImpresoras(int c){
        this.mutex=new Semaphore(1);
        this.aviso= new Semaphore(0);
        this.contar=new Semaphore(1);
        impresoras= new Semaphore[c];
        for (int i = 0; i < impresoras.length; i++) {
            impresoras[i]= new Semaphore(1);
        }
    }


    public int adquirir(){
        int n=-1;
        try {
            while(true){
                System.out.println("Ejecuta");
                mutex.acquire();
                System.out.println(mutex.availablePermits());
                for (int i = 0; i < impresoras.length; i++) {
                    if(impresoras[i].tryAcquire()){
                        mutex.release();
                        n=i;
                    }
                }
                boolean enBlock=contar.tryAcquire();
                aviso.acquire();

                if(enBlock) aviso.release();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }    
        
        return n;
    }

    public void liberar(int id){
        impresoras[id].release();

        if(!contar.tryAcquire()) aviso.release();
        else contar.release();
    }
}
