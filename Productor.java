import java.util.concurrent.Semaphore;
public class Productor implements Runnable{ 
    BufferPC buffer; int productos, consumidores;

    public Productor(BufferPC buf, int p, int c){
        this.buffer=buf; this.consumidores=c; this.productos=p;
    }

    public void run(){
        for (int i = 1; i <= productos; i++) {
            buffer.poner(i);
            System.out.println("Prod "+i);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }//for (int i = 1; i <= 2; i++) buffer.poner(-1);
    }
}
