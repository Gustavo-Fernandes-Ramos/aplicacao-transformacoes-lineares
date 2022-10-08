package retangulo;
import reta.Reta;
import ponto.Ponto;

public class Retangulo{
    
    private Reta diagonal;
    private Reta diagonalInversa;
    //construtor que define apenas uma reta
    public Retangulo(double x1, double y1, double x2, double y2){
        setDiagonal(new Reta(x1, y1, x2, y2));
        setDiagonalInversa(new Reta(x1, y2, x2, y1));
    }
    //construtor que define duas retas
    public Retangulo(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
        setDiagonal(new Reta(x1, y1, x2, y2));
        setDiagonalInversa(new Reta(x3, y3, x4, y4));
    }
    
    public Retangulo(Ponto p1, Ponto p2){
        setDiagonal(new Reta(p1, p2));
    }
    
    public Retangulo(Reta r){
        setDiagonal(r);
    }
    
    public void setDiagonal(Reta diagonal){
        this.diagonal = diagonal;
    }
    public Reta getDiagonal(){
        return this.diagonal;
    }
    
    public void setDiagonalInversa(Reta diagonalInversa){
        this.diagonalInversa = diagonalInversa;
    }
    public Reta getDiagonalInversa(){
        return this.diagonalInversa;
    }
    
    //metodos para calcular o centro do primitivo
    public double calcularCentroX(){
        double x;  
        x = (getDiagonal().getP1().getX() + getDiagonal().getP2().getX())/2;
        return x;
    }
    public double calcularCentroY(){
        double y;  
        y = (getDiagonal().getP1().getY() + getDiagonal().getP2().getY())/2;  
        return y;
    }
    
    public String toString(){
        String s;
        s = "Diagonal do retangulo: " + getDiagonal().toString();
        return s;
    }
}