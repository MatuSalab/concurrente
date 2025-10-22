public class SynchronizedObjectCounter {
     private int c = 0; 
     public  void increment(){ 
                   synchronized ((Integer.valueOf(c))) {c++;}
           }
     public  void decrement() { 
                    synchronized (this) {c--;} 
           } 
     public int value() {return c;} 
}
