import java.util.concurrent.Semaphore;

    /*public static void main(String[] args) {
        Surtidor s = new Surtidor(1000); // capacidad total del surtidor

        Thread[] autos = new Thread[] {
            new Thread(new Auto("A123", 50, 10, s)),   // necesita cargar (justo)
            new Thread(new Auto("B234", 60, 10, s)),  // necesita cargar
            new Thread(new Auto("C345", 40, 35, s)),   // no necesita
            new Thread(new Auto("D456", 60, 30, s)),   // no necesita
            new Thread(new Auto("E567", 50, 0, s)),   // necesita cargar
        };
        for (Thread t : autos) t.start();
    } */
    /*public static void main(String[] args) {
        Secuenciador s = new Secuenciador(new int[]{2,2,5}, 5); // ABBCCC x 4
        new Thread(new HiloLetra(s, 0, 'A')).start();
        new Thread(new HiloLetra(s, 1, 'B')).start();
        new Thread(new HiloLetra(s, 2, 'C')).start();
    } */

    /*public static void main(String[] args) {
        int cantImp=3;
        int cantCli=10;
        Impresoras impresoras = new Impresoras(cantImp);
        for (int i=0; i<=cantCli; i++){
            new Thread(new Cliente(i, impresoras), "Cliente "+i).start();
        }
    } */

public class Main {
   /* public static void main(String[] args) {
        // Semáforos compartidos (fair=true para respetar orden de llegada)
        Semaphore pedido  = new Semaphore(0, true); // pasajero -> taxista
        Semaphore llegada = new Semaphore(0, true); // taxista  -> pasajero

        // Un taxista que atiende a muchos pasajeros
        
        new Thread(new Pasajero(1, pedido, llegada)).start();;
        new Thread(new Pasajero(2, pedido, llegada)).start();;
        new Thread(new Pasajero(3, pedido, llegada)).start();;
        new Thread(new Taxista(pedido, llegada), "Taxista").start();

        // Lanzamos varios pasajeros
    } 
        
    int N = 5;                                   // cantidad de divisiones
        Cadena pantalla = new Cadena(2 * N);         // monitor de salida

        // Lanzar N divisiones (hilos) en cualquier orden
        for (int i = 1; i <= N; i++) {
            new Thread(new Division(i, N, pantalla), "Division-" + i).start();
        }


        public static void main(String[] args) {
        int N = 5;                                   // cantidad de divisiones
        Cadena pantalla = new Cadena(2 * N);         // cantidad de oraciones = 2*N

        for (int i = 1; i <= N; i++) {
            new Thread(new Division(i, N, pantalla)).start();
        }
    }

    public static void main(String[] args) {
        int cantCli=10;
        Impresoras impresoras = new Impresoras(3);
        for (int i=1; i<=cantCli; i++){
            new Thread(new Cliente(i, impresoras), "Cliente "+i).start();
        }
    }

    public static void main(String[] args) {
        Semaphore pedido  = new Semaphore(0, true); // estudiante -> cafetera
        Semaphore aviso = new Semaphore(0, true);  // cafetera   -> estudiante
        Cafetera cafetera = new Cafetera(pedido, aviso);
        Thread hiloCafetera = new Thread(cafetera, "Cafetera");
        Semaphore mutex= new Semaphore(1, true); 
        hiloCafetera.start();

        int cantEstudiantes = 5;
        for (int i = 1; i <= cantEstudiantes; i++) {
            Thread hiloEstudiante = new Thread(new Estudiante(cafetera, i+"", pedido, aviso, mutex), "Estudiante-" + i);
            hiloEstudiante.start();
        }
    }

    public static void main(String[] args) {
        Thread p1 = new Thread(new OrdenProcesos.P1(), "Proceso 1");
        Thread p2 = new Thread(new OrdenProcesos.P2(), "Proceso 2");
        Thread p3 = new Thread(new OrdenProcesos.P3(), "Proceso 3");
        p1.start(); p2.start(); p3.start();
    }


    public static void main(String[] args) {

        Semaphore[] tenedores= new Semaphore[5];
        for (int i = 0; i < tenedores.length; i++) { 
            tenedores[i] =new Semaphore(1);
        }
        for (int i = 0; i < tenedores.length; i++) {
            Semaphore izq= tenedores[i];
            Semaphore der= tenedores[(i+1)%tenedores.length];
            new Thread(new Filosofo(i+1,izq, der)).start();
        }
            

    }

    public static void main(String[] args) {
        Pizarra pizarra = new Pizarra();
        for (int i = 1; i <= 5; i++) {
            new Thread(new UsuarioPizarra(i, pizarra)).start();
        }
    }
    */
    public static void main(String[] args) {

        Semaphore[] tenedores= new Semaphore[5];
        for (int i = 0; i < tenedores.length; i++) { 
            tenedores[i] =new Semaphore(1);
        }
        for (int i = 0; i < tenedores.length; i++) {
            Semaphore izq= tenedores[i];
            Semaphore der= tenedores[(i+1)%tenedores.length]; //F1 toma tenedores 0(i) y 1(d). F2 toma tenedores 1(i) y 2(d). 
            //F3 toma tenedores 2(i) y 3(d). F4 toma tenedores 3(i) y 4(d). F5 toma tenedores 4(i) y 0(d)
            
            new Thread(new Filosofo(i+1,izq, der)).start();
        }
            

    }
    
    
    
}


