import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {
    
    Semaphore tenedorI, tenedorD;
    int id;


    public Filosofo(int n, Semaphore i, Semaphore d){
        this.id=n; this.tenedorD=d; this.tenedorI=i;
    }

    public void pensar(){
        try {
            System.out.println("El filósofo "+this.id+" esta pensando....");
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void comer(){
        try {
            System.out.println("El filósofo "+this.id+" esta comiendo....");
            Thread.sleep(500);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    /*if(this.id==5){ 
                    tenedorD.acquire(); tenedorI.acquire();
                    comer();
                    tenedorD.release(); System.out.println("El filósofo "+id+" dejó su tenedor derecho");
                    tenedorI.release(); System.out.println("El filósofo "+id+" dejó su tenedor izquierdo");
                }
                else{ 
                    tenedorI.acquire(); tenedorD.acquire();
                    comer();
                    tenedorI.release(); System.out.println("El filósofo "+id+" dejó su tenedor izquierdo");
                    tenedorD.release(); System.out.println("El filósofo "+id+" dejó su tenedor derecho");
                } */
    public void run(){
        
        try {
            while(true){
                pensar();
                if(this.id==5){ 
                    tenedorD.acquire(); tenedorI.acquire();
                    comer();
                    System.out.println("El filósofo "+id+" dejó su tenedor derecho");tenedorD.release();
                    System.out.println("El filósofo "+id+" dejó su tenedor izquierdo");tenedorI.release(); 
                }
                else{ 
                    tenedorI.acquire(); tenedorD.acquire();
                    comer();
                    System.out.println("El filósofo "+id+" dejó su tenedor izquierdo"); tenedorI.release(); 
                    System.out.println("El filósofo "+id+" dejó su tenedor derecho"); tenedorD.release(); 
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}

