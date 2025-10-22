import java.util.concurrent.Semaphore;

public class Editor implements Runnable{
    EsperaEditor e;

    public Editor(EsperaEditor e){
        this.e=e;
    }

    public void run(){
        while(true){
            Imagen aux=e.tomar();
            if(aux!=null){
                System.out.println("Editando "+aux.id);
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    // TODO: handle exception
                } System.out.println("Edición finalizada. Guardando "+aux.id);
            }
        }
    }
}
