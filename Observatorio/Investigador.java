package Observatorio;

public class Investigador implements Runnable{

    int id;
    SalaObsMoni sala;

    public Investigador(int i, SalaObsMoni s) {
        id=i; sala=s;
    }

    public void investigar(){
        try {
            System.out.println("Investigador "+id+" esta investigando");
            Thread.sleep(2500);
            System.out.println("Investigador "+id+" termino su investigacion");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run(){
        try {
            sala.iniciarInvestigacion();
            investigar();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            sala.salirInvestigacion();
        }
    }
    
}
