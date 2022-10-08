import java.awt.Color;

import java.util.List;
import java.util.ArrayList;

import ponto.PontoGr;
import reta.RetaGr;
import reta.Reta;
import circulo.CirculoGr;
import retangulo.RetanguloGr;
import triangulo.TrianguloGr;
import poligono.PoligonoGr;

//classe que armazena o primitivo atual de cada tipo
public class Figura {
    //primitivo atual de cada tipo em memória
    private PontoGr pontoGr;      
    private RetaGr retaGr;
    private CirculoGr circuloGr;    
    private TrianguloGr trianguloGr;
    private RetanguloGr retanguloGr;  
    private PoligonoGr poligonoGr;

    //figura vazia
    public Figura() {
        setPontoGr(null);
        setRetaGr(null);
        setCirculoGr(null);
        setTrianguloGr(null);
        setRetanguloGr(null);
        setPoligonoGr(null);
    }

    public void setPontoGr(PontoGr pontoGr){ this.pontoGr = pontoGr; }

    public PontoGr getPontoGr(){ return this.pontoGr; }

    public void setRetaGr(RetaGr retaGr){ this.retaGr = retaGr; }

    public RetaGr getRetaGr(){ return this.retaGr; }

    public void setCirculoGr(CirculoGr circuloGr){ this.circuloGr = circuloGr; }

    public CirculoGr getCirculoGr(){ return this.circuloGr; }

    public void setRetanguloGr(RetanguloGr retanguloGr){ this.retanguloGr = retanguloGr; }

    public RetanguloGr getRetanguloGr(){ return this.retanguloGr; }

    public void setTrianguloGr(TrianguloGr trianguloGr){ this.trianguloGr = trianguloGr; }

    public TrianguloGr getTrianguloGr(){ return this.trianguloGr; }

    public void setPoligonoGr(PoligonoGr poligonoGr){ this.poligonoGr = poligonoGr; }

    public PoligonoGr getPoligonoGr(){ return this.poligonoGr; }
    
    //metodos para obter o primitivo atual de cada tipo
    public void armazenarPontoGr(int x, int y, String nome, int esp, Color cor){
        setPontoGr(new PontoGr(x, y, cor, nome, esp));
    }

    public void armazenarRetaGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        setRetaGr(new RetaGr(x1, y1, x2, y2, cor, nome, esp));
    }
    
    public void armazenarCirculoGr(int xc, int yc, int raio, String nome, int esp, Color cor){
        setCirculoGr(new CirculoGr(xc, yc, raio, cor, nome, esp));
    }
    
    public void armazenarTrianguloGr(int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){
        setTrianguloGr(new TrianguloGr(x1, y1, x2, y2, x3, y3, cor, nome, esp));
    }
    
    public void armazenarRetanguloGr(int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        setRetanguloGr(new RetanguloGr(x1, y1, x2, y2, cor, nome, esp));
    }
    
    public void armazenarPoligonoGr(List<Integer> listaCoord, String nome, int esp, Color cor){
        setPoligonoGr(new PoligonoGr(listaCoord, cor, nome, esp));
    }
    
    //recebe um ponto qualquer como parametro
    public void translacaoPonto(int xLinha, int yLinha){
        PontoGr p = getPontoGr();
        //calcula o deltaX e deltaY entre o centro do primitivo e o ponto qualquer
        double deltaX = xLinha - p.getX();
        double deltaY = yLinha - p.getY();
        //cria uma matriz 3x1 com as coordenadas do ponto
        double coord[][] = {{p.getX()}, {p.getY()}, {1}};
        //metodo que calcula a matriz resultante
        coord = TransformGeom.translacionarPonto(coord, deltaX, deltaY);
        //obtem os novos valores em forma de matriz
        p.setX(coord[0][0]);
        p.setY(coord[1][0]);
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoPonto(int xLinha, int yLinha, int angulo){
        PontoGr p = getPontoGr();
        double coord[][] = {{p.getX()}, {p.getY()}, {1}};
        coord = TransformGeom.rotacionarPonto(coord , xLinha, yLinha, angulo);
        p.setX(coord[0][0]);
        p.setY(coord[1][0]);
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaPonto(int xLinha, int yLinha, double escalaX, double escalaY){
        PontoGr p = getPontoGr();
        double coord[][] = {{p.getX()}, {p.getY()}, {1}};
        coord = TransformGeom.escalarPonto(coord , xLinha, yLinha, escalaX, escalaY);
        p.setX(coord[0][0]);
        p.setY(coord[1][0]);
    }
    //recebe um ponto qualquer como parametro
    public void translacaoReta(int xLinha, int yLinha){
        RetaGr r = getRetaGr();
        //calcula o deltaX e deltaY entre o centro do primitivo e o ponto qualquer
        double deltaX = xLinha - r.calcularCentroX();
        double deltaY = yLinha - r.calcularCentroY();
        double coord[][] = {{r.getP1().getX()}, {r.getP1().getY()}, {1}};
        coord = TransformGeom.translacionarPonto(coord, deltaX, deltaY);
        r.getP1().setX(coord[0][0]);
        r.getP1().setY(coord[1][0]);
        double coord2[][] = {{r.getP2().getX()}, {r.getP2().getY()}, {1}};
        coord2 = TransformGeom.translacionarPonto(coord2, deltaX, deltaY);
        r.getP2().setX(coord2[0][0]);
        r.getP2().setY(coord2[1][0]);
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoReta(int xLinha, int yLinha, int angulo){
        RetaGr r = getRetaGr();
        double coord[][] = {{r.getP1().getX()}, {r.getP1().getY()}, {1}};
        coord = TransformGeom.rotacionarPonto(coord, xLinha, yLinha, angulo);
        r.getP1().setX(coord[0][0]);
        r.getP1().setY(coord[1][0]);
        double coord2[][] = {{r.getP2().getX()}, {r.getP2().getY()}, {1}};
        coord2 = TransformGeom.rotacionarPonto(coord2, xLinha, yLinha, angulo);
        r.getP2().setX(coord2[0][0]);
        r.getP2().setY(coord2[1][0]);
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaReta(int xLinha, int yLinha, double escalaX, double escalaY){
        RetaGr r = getRetaGr();
        double coord[][] = {{r.getP1().getX()}, {r.getP1().getY()}, {1}};
        coord = TransformGeom.escalarPonto(coord, xLinha, yLinha, escalaX, escalaY);
        r.getP1().setX(coord[0][0]);
        r.getP1().setY(coord[1][0]);
        double coord2[][] = {{r.getP2().getX()}, {r.getP2().getY()}, {1}};
        coord2 = TransformGeom.escalarPonto(coord2, xLinha, yLinha, escalaX, escalaY);
        r.getP2().setX(coord2[0][0]);
        r.getP2().setY(coord2[1][0]);
    }
    //recebe um ponto qualquer como parametro
    public void translacaoCirculo(int xLinha, int yLinha){
        CirculoGr c = getCirculoGr();
        //calcula o deltaX e deltaY entre o centro do primitivo e o ponto qualquer
        double deltaX = xLinha - c.getCentro().getX();
        double deltaY = yLinha - c.getCentro().getY();
        double coord[][] = {{c.getCentro().getX()}, {c.getCentro().getY()}, {1}};
        coord = TransformGeom.translacionarPonto(coord, deltaX, deltaY);
        c.getCentro().setX(coord[0][0]);
        c.getCentro().setY(coord[1][0]);
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoCirculo(int xLinha, int yLinha, int angulo){
        CirculoGr c = getCirculoGr();
        double coord[][] = {{c.getCentro().getX()}, {c.getCentro().getY()}, {1}};
        coord = TransformGeom.rotacionarPonto(coord, xLinha, yLinha, angulo);
        c.getCentro().setX(coord[0][0]);
        c.getCentro().setY(coord[1][0]);
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaCirculo(int xLinha, int yLinha, double escalaX, double escalaY){
        CirculoGr c = getCirculoGr();
        double coord[][] = {{c.getCentro().getX()}, {c.getCentro().getY()}, {1}};
        coord = TransformGeom.escalarPonto(coord, xLinha, yLinha, escalaX, escalaY);
        c.getCentro().setX(coord[0][0]);
        c.getCentro().setY(coord[1][0]);
        c.setRaio(c.getRaio()*escalaX);
    }
    //recebe um ponto qualquer como parametro
    public void translacaoTriangulo(int xLinha, int yLinha){
        TrianguloGr t = getTrianguloGr();
        //calcula o deltaX e deltaY entre o centro do primitivo e o ponto qualquer
        double deltaX = xLinha - t.calcularCentroX();
        double deltaY = yLinha - t.calcularCentroY();
        
        double coord[][] = {{t.getReta1().getP1().getX()}, {t.getReta1().getP1().getY()}, {1}};
        coord = TransformGeom.translacionarPonto(coord, deltaX, deltaY);
        t.getReta1().getP1().setX(coord[0][0]);   t.getReta1().getP1().setY(coord[1][0]);
        t.getReta3().getP2().setX(coord[0][0]);   t.getReta3().getP2().setY(coord[1][0]);
        
        double coord2[][] = {{t.getReta1().getP2().getX()}, {t.getReta1().getP2().getY()}, {1}};
        coord2 = TransformGeom.translacionarPonto(coord2, deltaX, deltaY);
        t.getReta1().getP2().setX(coord2[0][0]);   t.getReta1().getP2().setY(coord2[1][0]);
        t.getReta2().getP1().setX(coord2[0][0]);   t.getReta2().getP1().setY(coord2[1][0]);
        
        double coord3[][] = {{t.getReta2().getP2().getX()}, {t.getReta2().getP2().getY()}, {1}};
        coord3 = TransformGeom.translacionarPonto(coord3, deltaX, deltaY);
        t.getReta2().getP2().setX(coord3[0][0]);   t.getReta2().getP2().setY(coord3[1][0]);
        t.getReta3().getP1().setX(coord3[0][0]);   t.getReta3().getP1().setY(coord3[1][0]);
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoTriangulo(int xLinha, int yLinha, int angulo){
        TrianguloGr t = getTrianguloGr();
        double coord[][] = {{t.getReta1().getP1().getX()}, {t.getReta1().getP1().getY()}, {1}};
        coord = TransformGeom.rotacionarPonto(coord, xLinha, yLinha, angulo);
        t.getReta1().getP1().setX(coord[0][0]);   t.getReta1().getP1().setY(coord[1][0]);
        t.getReta3().getP2().setX(coord[0][0]);   t.getReta3().getP2().setY(coord[1][0]);
        
        double coord2[][] = {{t.getReta1().getP2().getX()}, {t.getReta1().getP2().getY()}, {1}};
        coord2 = TransformGeom.rotacionarPonto(coord2, xLinha, yLinha, angulo);
        t.getReta1().getP2().setX(coord2[0][0]);   t.getReta1().getP2().setY(coord2[1][0]);
        t.getReta2().getP1().setX(coord2[0][0]);   t.getReta2().getP1().setY(coord2[1][0]);
        
        double coord3[][] = {{t.getReta2().getP2().getX()}, {t.getReta2().getP2().getY()}, {1}};
        coord3 = TransformGeom.rotacionarPonto(coord3, xLinha, yLinha, angulo);
        t.getReta2().getP2().setX(coord3[0][0]);   t.getReta2().getP2().setY(coord3[1][0]);
        t.getReta3().getP1().setX(coord3[0][0]);   t.getReta3().getP1().setY(coord3[1][0]);
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaTriangulo(int xLinha, int yLinha, double escalaX, double escalaY){
        TrianguloGr t = getTrianguloGr();
        double coord[][] = {{t.getReta1().getP1().getX()}, {t.getReta1().getP1().getY()}, {1}};
        coord = TransformGeom.escalarPonto(coord, xLinha, yLinha, escalaX, escalaY);
        t.getReta1().getP1().setX(coord[0][0]);   t.getReta1().getP1().setY(coord[1][0]);
        t.getReta3().getP2().setX(coord[0][0]);   t.getReta3().getP2().setY(coord[1][0]);
        
        double coord2[][] = {{t.getReta1().getP2().getX()}, {t.getReta1().getP2().getY()}, {1}};
        coord2 = TransformGeom.escalarPonto(coord2, xLinha, yLinha, escalaX, escalaY);
        t.getReta1().getP2().setX(coord2[0][0]);   t.getReta1().getP2().setY(coord2[1][0]);
        t.getReta2().getP1().setX(coord2[0][0]);   t.getReta2().getP1().setY(coord2[1][0]);
        
        double coord3[][] = {{t.getReta2().getP2().getX()}, {t.getReta2().getP2().getY()}, {1}};
        coord3 = TransformGeom.escalarPonto(coord3, xLinha, yLinha, escalaX, escalaY);
        t.getReta2().getP2().setX(coord3[0][0]);   t.getReta2().getP2().setY(coord3[1][0]);
        t.getReta3().getP1().setX(coord3[0][0]);   t.getReta3().getP1().setY(coord3[1][0]);
    }
    //recebe um ponto qualquer como parametro
    public void translacaoRetangulo(int xLinha, int yLinha){
        RetanguloGr r = getRetanguloGr();
        //calcula o deltaX e deltaY entre o centro do primitivo e o ponto qualquer
        double deltaX = xLinha - r.calcularCentroX();
        double deltaY = yLinha - r.calcularCentroY();
        
        double coord[][] = {{r.getDiagonal().getP1().getX()}, {r.getDiagonal().getP1().getY()}, {1}};
        coord = TransformGeom.translacionarPonto(coord, deltaX, deltaY);
        r.getDiagonal().getP1().setX(coord[0][0]);
        r.getDiagonal().getP1().setY(coord[1][0]);
        
        double coord2[][] = {{r.getDiagonal().getP2().getX()}, {r.getDiagonal().getP2().getY()}, {1}};
        coord2 = TransformGeom.translacionarPonto(coord2, deltaX, deltaY);
        r.getDiagonal().getP2().setX(coord2[0][0]);
        r.getDiagonal().getP2().setY(coord2[1][0]);
        
        double coord3[][] = {{r.getDiagonalInversa().getP1().getX()}, {r.getDiagonalInversa().getP1().getY()}, {1}};
        coord3 = TransformGeom.translacionarPonto(coord3, deltaX, deltaY);
        r.getDiagonalInversa().getP1().setX(coord3[0][0]);
        r.getDiagonalInversa().getP1().setY(coord3[1][0]);
        
        double coord4[][] = {{r.getDiagonalInversa().getP2().getX()}, {r.getDiagonalInversa().getP2().getY()}, {1}};
        coord4 = TransformGeom.translacionarPonto(coord4, deltaX, deltaY);
        r.getDiagonalInversa().getP2().setX(coord4[0][0]);
        r.getDiagonalInversa().getP2().setY(coord4[1][0]);
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoRetangulo(int xLinha, int yLinha, int angulo){
        RetanguloGr r = getRetanguloGr();
        
        double coord[][] = {{r.getDiagonal().getP1().getX()}, {r.getDiagonal().getP1().getY()}, {1}};
        coord = TransformGeom.rotacionarPonto(coord, xLinha, yLinha, angulo);
        r.getDiagonal().getP1().setX(coord[0][0]);
        r.getDiagonal().getP1().setY(coord[1][0]);
        
        double coord2[][] = {{r.getDiagonal().getP2().getX()}, {r.getDiagonal().getP2().getY()}, {1}};
        coord2 = TransformGeom.rotacionarPonto(coord2, xLinha, yLinha, angulo);
        r.getDiagonal().getP2().setX(coord2[0][0]);
        r.getDiagonal().getP2().setY(coord2[1][0]);
        
        double coord3[][] = {{r.getDiagonalInversa().getP1().getX()}, {r.getDiagonalInversa().getP1().getY()}, {1}};
        coord3 = TransformGeom.rotacionarPonto(coord3, xLinha, yLinha, angulo);
        r.getDiagonalInversa().getP1().setX(coord3[0][0]);
        r.getDiagonalInversa().getP1().setY(coord3[1][0]);
        
        double coord4[][] = {{r.getDiagonalInversa().getP2().getX()}, {r.getDiagonalInversa().getP2().getY()}, {1}};
        coord4 = TransformGeom.rotacionarPonto(coord4, xLinha, yLinha, angulo);
        r.getDiagonalInversa().getP2().setX(coord4[0][0]);
        r.getDiagonalInversa().getP2().setY(coord4[1][0]);
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaRetangulo(int xLinha, int yLinha, double escalaX, double escalaY){
        RetanguloGr r = getRetanguloGr();
        
        double coord[][] = {{r.getDiagonal().getP1().getX()}, {r.getDiagonal().getP1().getY()}, {1}};
        coord = TransformGeom.escalarPonto(coord, xLinha, yLinha, escalaX, escalaY);
        r.getDiagonal().getP1().setX(coord[0][0]);
        r.getDiagonal().getP1().setY(coord[1][0]);
        
        double coord2[][] = {{r.getDiagonal().getP2().getX()}, {r.getDiagonal().getP2().getY()}, {1}};
        coord2 = TransformGeom.escalarPonto(coord2, xLinha, yLinha, escalaX, escalaY);
        r.getDiagonal().getP2().setX(coord2[0][0]);
        r.getDiagonal().getP2().setY(coord2[1][0]);
        
        double coord3[][] = {{r.getDiagonalInversa().getP1().getX()}, {r.getDiagonalInversa().getP1().getY()}, {1}};
        coord3 = TransformGeom.escalarPonto(coord3, xLinha, yLinha, escalaX, escalaY);
        r.getDiagonalInversa().getP1().setX(coord3[0][0]);
        r.getDiagonalInversa().getP1().setY(coord3[1][0]);
        
        double coord4[][] = {{r.getDiagonalInversa().getP2().getX()}, {r.getDiagonalInversa().getP2().getY()}, {1}};
        coord4 = TransformGeom.escalarPonto(coord4, xLinha, yLinha, escalaX, escalaY);
        r.getDiagonalInversa().getP2().setX(coord4[0][0]);
        r.getDiagonalInversa().getP2().setY(coord4[1][0]);

    }
    //recebe um ponto qualquer como parametro
    public void translacaoPoligono(int xLinha, int yLinha){
        PoligonoGr p = getPoligonoGr();
        Reta retaItem;
        List<Reta> listaR = p.getListaRetas();
        
        int tam = listaR.size();
        retaItem = listaR.get(0);
        //translacao em relacao ao primeiro ponto do poligono
        double deltaX = xLinha - retaItem.getP1().getX();
        double deltaY = yLinha - retaItem.getP1().getY();
        double coord[][] = new double[3][1];
        double coord2[][] = new double[3][1];
        for(int i = 0 ; i < tam ; i++){
            //percorre a lista de retas do poligono
            retaItem = listaR.get(i);
            coord[0][0] = retaItem.getP1().getX();
            coord[1][0] = retaItem.getP1().getY();
            coord[2][0] = 1;
            coord = TransformGeom.translacionarPonto(coord , deltaX, deltaY);
            retaItem.getP1().setX(coord[0][0]);
            retaItem.getP1().setY(coord[1][0]);
            
            coord2[0][0] = retaItem.getP2().getX();
            coord2[1][0] = retaItem.getP2().getY();
            coord2[2][0] = 1;
            coord2 = TransformGeom.translacionarPonto(coord2, deltaX, deltaY);
            retaItem.getP2().setX(coord2[0][0]);
            retaItem.getP2().setY(coord2[1][0]);
        }
    }
    //recebe um ponto qualquer e o angulo da rotacao como parametro
    public void rotacaoPoligono(int xLinha, int yLinha, int angulo){
        PoligonoGr p = getPoligonoGr();
        Reta retaItem;
        List<Reta> listaR = p.getListaRetas();
        
        double coord[][] = new double[3][1];
        double coord2[][] = new double[3][1];
        int tam = listaR.size();
        for(int i = 0 ; i < tam ; i++){
            retaItem = listaR.get(i);
            coord[0][0] = retaItem.getP1().getX();
            coord[1][0] = retaItem.getP1().getY();
            coord[2][0] = 1;
            coord = TransformGeom.rotacionarPonto(coord, xLinha, yLinha, angulo);
            retaItem.getP1().setX(coord[0][0]);
            retaItem.getP1().setY(coord[1][0]);
            
            coord2[0][0] = retaItem.getP2().getX();
            coord2[1][0] = retaItem.getP2().getY();
            coord2[2][0] = 1;
            coord2 = TransformGeom.rotacionarPonto(coord2, xLinha, yLinha, angulo);
            retaItem.getP2().setX(coord2[0][0]);
            retaItem.getP2().setY(coord2[1][0]);
        }
    }
    //recebe um ponto qualquer e os valores de escala como parametro
    public void escalaPoligono(int xLinha, int yLinha, double escalaX, double escalaY){
        PoligonoGr p = getPoligonoGr();
        Reta retaItem;
        List<Reta> listaR = p.getListaRetas();
        
        double coord[][] = new double[3][1];
        double coord2[][] = new double[3][1];
        int tam = listaR.size();
        for(int i = 0 ; i < tam ; i++){
            retaItem = listaR.get(i);
            coord[0][0] = retaItem.getP1().getX();
            coord[1][0] = retaItem.getP1().getY();
            coord[2][0] = 1;
            coord = TransformGeom.escalarPonto(coord, xLinha, yLinha, escalaX, escalaY);
            retaItem.getP1().setX(coord[0][0]);
            retaItem.getP1().setY(coord[1][0]);
            
            coord2[0][0] = retaItem.getP2().getX();
            coord2[1][0] = retaItem.getP2().getY();
            coord2[2][0] = 1;
            coord2 = TransformGeom.escalarPonto(coord2, xLinha, yLinha, escalaX, escalaY);
            retaItem.getP2().setX(coord2[0][0]);
            retaItem.getP2().setY(coord2[1][0]);
        }
    }
}
