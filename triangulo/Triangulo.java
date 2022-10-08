package triangulo;
import reta.Reta;
import ponto.Ponto;

public class Triangulo {

    private Reta reta1;
    private Reta reta2;
    private Reta reta3;

    private Ponto pontoCentro;

    public Triangulo(double x1, double y1, double x2, double y2, double x3, double y3) {
        setReta1(new Reta(x1, y1, x2, y2));
        setReta2(new Reta(x2, y2, x3, y3));
        setReta3(new Reta(x3, y3, x1, y1));
    }

    public Triangulo(Reta reta1, Reta reta2, Reta reta3) {
        setReta1(reta1);
        setReta2(reta2);
        setReta3(reta3);
    }

    public Triangulo(Ponto p1, Ponto p2, Ponto p3){
        setReta1(new Reta(p1, p2));
        setReta2(new Reta(p2, p3));
        setReta3(new Reta(p3, p1));
    }

    public void setReta1(Reta reta1){
        this.reta1 = reta1;
    }

    public Reta getReta1(){
        return this.reta1;
    }

    public void setReta2(Reta reta2){
        this.reta2 = reta2;
    }

    public Reta getReta2(){
        return this.reta2;
    }

    public void setReta3(Reta reta3){
        this.reta3 = reta3;
    }

    public Reta getReta3(){
        return this.reta3;
    }
    //metodos para calcular o centro do primitivo
    public double calcularCentroX(){
        double x;  
        x = (getReta1().getP1().getX() + getReta1().getP2().getX() + getReta2().getP2().getX())/3;
        return x;
    }
    public double calcularCentroY(){
        double y;  
        y = (getReta1().getP1().getY() + getReta1().getP2().getY() + getReta2().getP2().getY())/3;  
        return y;
    }

    public String toString(){
        String s;
        s = "Reta1: " + getReta1().toString() + " Reta2: " + getReta2().toString() + " Reta3: " + getReta3().toString();
        return s;
    }
}
