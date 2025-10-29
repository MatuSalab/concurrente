package Observatorio;

public class Investigador implements Runnable{

    int id; SalaObs sala;
    public Investigador(int i, SalaObs s){
        id=i; sala=s;
    }

    public void investigar(){
        try {
            System.out.println("Investigador "+id+" esta investigando");
            Thread.sleep(5000);
            System.out.println("Investigador "+id+" termino su investigacion");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run(){
        try {
            sala.ingresarInvestigador();
            investigar();
            sala.salirInvestigador();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
