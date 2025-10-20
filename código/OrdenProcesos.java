import java.util.concurrent.Semaphore;

public class OrdenProcesos {
    static int contador=0;
    static Semaphore semProceso1 = new Semaphore(1, true);
    static Semaphore semProceso2 = new Semaphore(0, true);
    static Semaphore semProceso3 = new Semaphore(0, true);

    static class P1 implements Runnable {
        public void run(){
            while (contador<=3){
                try{
                    semProceso1.acquire();
                    System.out.println("Proceso 1");
                    semProceso3.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } contador++;
            }
        }
    }

    static class P2 implements Runnable {
        public void run(){
            while (contador<=3){
                try{
                    semProceso2.acquire();
                    System.out.println("Proceso 2");
                    semProceso1.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static class P3 implements Runnable {
        public void run(){
            while (contador<=3){
                try{
                    semProceso3.acquire();
                    System.out.println("Proceso 3");
                    semProceso2.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
