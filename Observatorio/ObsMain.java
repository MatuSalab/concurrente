package Observatorio;

public class ObsMain {
    public static void main(String[] args) {
        SalaObs sala = new SalaObs();
        

        for (int i = 1; i <= 12; i++) {
                new Thread(new VisitanteObs(i, sala, false)).start();
        }
        new Thread(new Mantenimiento(1, sala)).start();
        new Thread(new Mantenimiento(2, sala)).start();

        new Thread(new VisitanteObs(13, sala, true)).start();

        for (int i = 14; i <= 30; i++) {
            new Thread(new VisitanteObs(i, sala, false)).start();
        }

        new Thread(new Investigador(1, sala)).start();
        

    }
}
