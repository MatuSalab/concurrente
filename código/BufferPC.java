import java.util.concurrent.Semaphore;

public class BufferPC {

    int[] buf; int cabeza=0, cola=0, cont=0;
    Semaphore mutex = new Semaphore(1);
    Semaphore hayLugar= new Semaphore(1);
    Semaphore vacio= new Semaphore(0);
    

    public BufferPC(int cant){
        buf= new int[cant];
    }

    public int getLongi(){
        return buf.length;
    }

    public void poner(int n){
        
            hayLugar.acquireUninterruptibly();
            mutex.acquireUninterruptibly();
            buf[cola]=n;
            cola=(cola+1)% buf.length; cont++;

            if(cont<buf.length){hayLugar.release();}
            if(cont==1){vacio.release();}

            mutex.release();
        
    }

    public int tomar(){
        vacio.acquireUninterruptibly();
        mutex.acquireUninterruptibly();
        int aux=buf[cabeza];
        cabeza=(cabeza+1)% buf.length; cont--;

        if(cabeza>0){vacio.release();}
        if(cont==buf.length-1){hayLugar.release();}

        mutex.release(); return aux;
        
    }
}
