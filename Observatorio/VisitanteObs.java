package Observatorio;

public class VisitanteObs implements Runnable{
    int id;
    SalaObsMoni sala;
    boolean sillaDeRuedas;
    boolean ingreso;

    public VisitanteObs(int i, SalaObsMoni s, boolean si) {
        id=i; sala=s; sillaDeRuedas=si; ingreso=false;

    }

    public boolean usaSilla(){
        return sillaDeRuedas;
    }

    public boolean estadoVisitante(){
        return ingreso;
    }

    public void visitanteIngresado(){
        ingreso=true;
    }

    public void recorrerSilla(){
        try {
            System.out.println("Visitante en silla de ruedas "+id+" recorre el museo, limito la sala a 5");
            Thread.sleep(2000);
            System.out.println("Visitante en silla de ruedas "+id+" terminó el recorrido");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void recorrer(){
        try {
            
                System.out.println("Visitante "+id+" recorre el museo");
                Thread.sleep(1000);
                System.out.println("Visitante "+id+" terminó el recorrido");
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run() {
        try{
            if(usaSilla()){
                sala.ingresarSilla(this);
                recorrerSilla();
                sala.salirSilla();
            }else{
                sala.ingresar(this);
                recorrer();
                sala.salir();
            }
            
            
        } catch(InterruptedException e){
            
        }
    }
}
