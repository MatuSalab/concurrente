package Observatorio;

public class Mantenimiento implements Runnable{
    int id; SalaObs sala; boolean limpio;
    public Mantenimiento(int i, SalaObs s){
        id=i; sala=s; limpio=false;
    }

    public void hacerMantenimiento(){
        try {
            System.out.println("El personal "+id+" esta ralizando mantenimiento");
            Thread.sleep(2000);
            limpio=true;
            System.out.println("El personal "+id+" termino el mantenimiento");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run(){
        try{
            sala.iniciarMantenimiento();
            hacerMantenimiento();
            
        }catch(InterruptedException e){}
        finally{
            sala.finalizarMantenimiento();
            
        }
    }
}
