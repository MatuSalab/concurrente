// PCBinario.java
import java.util.concurrent.Semaphore;

public class PCBinario {

    // ===== Buffer circular con semáforos binarios =====
    static class BoundedBuffer {
        private final int[] buf;
        private int head = 0, tail = 0, count = 0;

        private final Semaphore mutex    = new Semaphore(1, true); // binario
        private final Semaphore notFull  = new Semaphore(1, true); // puerta: hay espacio
        private final Semaphore notEmpty = new Semaphore(0, true); // puerta: hay datos

        BoundedBuffer(int cap) { buf = new int[cap]; }

        public void put(int x) {
            notFull.acquireUninterruptibly();          // esperar espacio
            mutex.acquireUninterruptibly();
            buf[tail] = x;
            tail = (tail + 1) % buf.length;
            count++;

            // mantener puertas
            if (count < buf.length) notFull.release(); // sigue habiendo espacio
            if (count == 1)          notEmpty.release(); // vacío -> no vacío

            mutex.release();
        }

        public int take() {
            notEmpty.acquireUninterruptibly();         // esperar dato
            mutex.acquireUninterruptibly();
            int x = buf[head];
            head = (head + 1) % buf.length;
            count--;

            // mantener puertas
            if (count > 0)                 notEmpty.release(); // siguen habiendo datos
            if (count == buf.length - 1)   notFull.release();  // lleno -> no lleno

            mutex.release();
            return x;
        }
    }

    // ===== Productor =====
    static class Productor implements Runnable {
        private final BoundedBuffer q; private final int N; private final int C;
        Productor(BoundedBuffer q, int N, int C) { this.q = q; this.N = N; this.C = C; }
        public void run() {
            for (int i = 1; i <= N; i++) {
                q.put(i);
                System.out.println("Prod " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            // “poison pills” para terminar a C consumidores
            for (int i = 0; i < C; i++) q.put(-1);
        }
    }

    // ===== Consumidor =====
    static class Consumidor implements Runnable {
        private final BoundedBuffer q; private final int id;
        Consumidor(BoundedBuffer q, int id) { this.q = q; this.id = id; }
        public void run() {
            while (true) {
                int x = q.take();
                if (x == -1) { // fin
                    q.put(-1); // reinyecta para el resto
                    break;
                }
                System.out.println("Cons " + id + " Prod " + x);
                try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); break; }
            }
        }
    }

    // ===== Demo =====
    public static void main(String[] args) {
        int CAP = 3;   // capacidad del buffer
        int N   = 10;  // items a producir
        int C   = 2;   // consumidores

        BoundedBuffer q = new BoundedBuffer(CAP);
        new Thread(new Productor(q, N, C), "Productor").start();
        for (int i = 1; i <= C; i++) new Thread(new Consumidor(q, i), "Consumidor-"+i).start();
    }
}

