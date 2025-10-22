public class Consumidor implements Runnable{
    BufferPC buffer; int id;
    public Consumidor(BufferPC b, int i){this.buffer=b; this.id=i;}

    public void run(){
        while(true){
            int aux=-1;
            if (buffer.tomar()<buffer.getLongi()){
                aux= buffer.tomar();
                System.out.println("Consumidor "+id+" consumiendo producto "+(aux+1));
            }
            
            if(aux==-1){
                buffer.poner(aux); break;
            }
            
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
