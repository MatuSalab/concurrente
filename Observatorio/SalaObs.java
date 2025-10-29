package Observatorio;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SalaObs {
    int contador; 
    private final Lock entrar= new ReentrantLock();
    private final Condition noLlena= entrar.newCondition();
    //private final Condition noVacio= entrar.newCondition();

    //Para silla de ruedas
    private final Condition silla= entrar.newCondition();
    int limite=10; boolean sillaEspera=false; boolean sillaRecorriendo=false;

    //Para mantenimiento
    int contadorMantenimiento; boolean mantenimientoEspera=false; boolean mantenimientoEnCurso=false;
    private final Condition salaVacia= entrar.newCondition();

    //Para investigador
    boolean investigadorEspera= false; boolean investigadorEnCurso=false;
    private final Condition entraInvestigador= entrar.newCondition();

    public SalaObs(){}

    public void limitar(){
        this.limite=5;
    }
    public void quitarLimite(){
        this.limite=10;
    }

    public void ingresar(VisitanteObs vis) throws InterruptedException{
        entrar.lock();
        try {
            while(contador==limite || sillaEspera || mantenimientoEnCurso || mantenimientoEspera){
                noLlena.await();
            }
            contador++; vis.visitanteIngresado();
            
        } finally{
            entrar.unlock();
        }
    }

    public void salir() throws InterruptedException{
        entrar.lock();
        try{
            contador--;
            if(mantenimientoEspera && contador==0){
                salaVacia.signalAll();
            } else if(sillaEspera && contador<=4 && !mantenimientoEnCurso && !mantenimientoEspera){
                silla.signal();
            }else{
                noLlena.signalAll();
            }
        } finally{
            entrar.unlock();
        }
    }

    public void ingresarSilla(VisitanteObs vis) throws InterruptedException{
        
        entrar.lock();
        try{
            this.limite=5;
            this.sillaEspera=true;

            while(contador>=5 || mantenimientoEnCurso  || mantenimientoEspera){
                silla.await();
            }
            contador++; vis.visitanteIngresado();
            

            this.sillaEspera=false;
            this.sillaRecorriendo=true;

            noLlena.signalAll();
        }finally{
            entrar.unlock();
        }
        
    }

    public void salirSilla(){
        entrar.lock();
        try{
            sillaRecorriendo=false;
            limite=10;
            contador--;
            noLlena.signalAll();
        }finally{entrar.unlock();}
    }

    public void iniciarMantenimiento() throws InterruptedException{
        entrar.lock();
        try{
            if(!mantenimientoEnCurso){
                mantenimientoEspera=true;
                while(contador>0){
                    salaVacia.await();
                }

                mantenimientoEspera=false;
                mantenimientoEnCurso=true;
            }
            contadorMantenimiento++;
        }finally{
            entrar.unlock();
        }
    }

    public void finalizarMantenimiento(){
        entrar.lock();
        try{
            contadorMantenimiento--;
            if(contadorMantenimiento==0){
                mantenimientoEnCurso=false;

                if(sillaEspera && contador<=4){
                    silla.signal();
                }

                noLlena.signalAll();
            }
        } finally{
            entrar.unlock();
        }
    }

    
}
