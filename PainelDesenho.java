import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.List;
import java.util.ArrayList;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import circulo.FiguraCirculos;
import triangulo.FiguraTriangulos;
import retangulo.FiguraRetangulos;
import poligono.FiguraPoligonos;

public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {
    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    ModoDeUso modo = ModoDeUso.DESENHO;  //modo de uso ao clicar no painel de desenho

    Color corAtual;       // Cor atual do primitivo
    int angulo;           // Angulo de rotacao
    double escalaX;          //fator de escala eixo x
    double escalaY;          //fator de escala eixo y
    int esp;              // Diametro do ponto
    int raio;             // Raio do circulo

    // capturam clicks no painel de desenho para gerar primitivos graficos
    int x, y, x1, y1, x2, y2, x3, y3;
    
    //figura que armazena os primitivos atualmente selecionados
    Figura figura = new Figura();

    //lista de coordenadas do poligono
    List<Integer> listaCoord = new ArrayList<Integer>();

    // selecionar cada click do mouse ao desenhar um triangulo ou poligono
    private int qtdDeClicks = 0;
    
    // selecionar cada click do mouse ao desenhar uma reta, retangulo e circulo
    private boolean primeiraVez = true;

    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, Color corAtual, int esp){
        setTipo(tipo);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);

        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);
    }
    //setters e getters
    public void setModoDeUso(ModoDeUso modo){ this.modo = modo; }
    
    public ModoDeUso getModoDeUso(){ return this.modo; }
    
    public void setTipo(TipoPrimitivo tipo){ this.tipo = tipo; }

    public TipoPrimitivo getTipo(){ return this.tipo; }
    
    public void setAngulo(int angulo){ this.angulo = angulo; }

    public int getAngulo(){ return this.angulo; }

    public void setEsp(int esp){ this.esp = esp; }

    public int getEsp(){ return this.esp; }
    
    public void setEscalaX(double escalaX){ this.escalaX = escalaX; }

    public double getEscalaX(){ return this.escalaX; }
    
    public void setEscalaY(double escalaY){ this.escalaY = escalaY; }

    public double getEscalaY(){ return this.escalaY; }

    public void setCorAtual(Color corAtual){ this.corAtual = corAtual; }

    public Color getCorAtual(){ return this.corAtual; }

    public void setMsg(JLabel msg){ this.msg = msg; }

    public JLabel getMsg(){ return this.msg; }

    public int getRaio() { return raio; }

    public void setRaio(int raio) { this.raio = raio; }

    public int getQtdDeClicks() { return this.qtdDeClicks; }

    public void setQtdDeClicks(int qtdDeClicks) { this.qtdDeClicks = qtdDeClicks; }

    public boolean getPrimeiraVez() { return this.primeiraVez; }

    public void setPrimeiraVez(boolean primeiraVez){ this.primeiraVez = primeiraVez; }

    public void paintComponent(Graphics g) { desenharPrimitivos(g); }

    // Capturando os Eventos com o mouse
    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();
        //caso a aplicacao esteja em modo desenho
        if(modo == ModoDeUso.DESENHO){

            if (tipo == TipoPrimitivo.PONTO){
                x = e.getX();
                y = e.getY();
                figura.armazenarPontoGr(x, y, "", getEsp(), getCorAtual());
                paint(g);
            }else if (tipo == TipoPrimitivo.RETA || tipo == TipoPrimitivo.RETANGULO){
                
                if (primeiraVez == true) {
                    //primeiro click
                    x1 = e.getX();
                    y1 = e.getY();
                    primeiraVez = false;
                } else {
                    //segundo click
                    x2 = e.getX();
                    y2 = e.getY();
                    primeiraVez = true;
                    //armazena ou uma reta ou um retangulo
                    if(tipo == TipoPrimitivo.RETA) figura.armazenarRetaGr( x1, y1, x2, y2, "", getEsp(), getCorAtual()); //armazena reta
                    else figura.armazenarRetanguloGr(x1, y1, x2, y2, "", getEsp(), getCorAtual()); //armazena retangulo
                    paint(g);
                } 

            }else if (tipo == TipoPrimitivo.CIRCULO){

                if (primeiraVez == true) {
                    //primeiro click
                    x1 = e.getX();
                    y1 = e.getY();
                    primeiraVez = false;
                } else {
                    //segundo click
                    x2 = (int)e.getX();
                    y2 = (int)e.getY();
                    primeiraVez = true;
                    raio = (int)Math.sqrt(Math.pow((y2-y1), 2) + Math.pow((x2-x1), 2));  // calcula o raio
                    setRaio(raio);
                    figura.armazenarCirculoGr(x1, y1, getRaio(), "", getEsp(), getCorAtual());
                    paint(g);
                } 

            }else if (tipo == TipoPrimitivo.TRIANGULO){

                if (qtdDeClicks == 0) {
                    //primeiro click
                    x1 = e.getX();
                    y1 = e.getY();
                    qtdDeClicks++;
                } else if(qtdDeClicks == 1){
                    //segundo click
                    x2 = e.getX();
                    y2 = e.getY();
                    qtdDeClicks++;
                } else{
                    //terceiro click
                    x3 = e.getX();
                    y3 = e.getY();
                    qtdDeClicks = 0;
                    figura.armazenarTrianguloGr(x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
                    paint(g);
                }

            }else if (tipo == TipoPrimitivo.POLIGONO){

                if(qtdDeClicks == 0) listaCoord = new ArrayList<Integer>();  //cria uma nova lista de coordenadas
                x = e.getX();
                y = e.getY();
                //todas as coordenadas são armazenadas duas vezes para criar uma lista de retas
                listaCoord.add(x);
                listaCoord.add(y);
                if(e.getButton() == MouseEvent.BUTTON1) {
                    if(qtdDeClicks == 0){
                        //primeiro click
                        x1 = x;  
                        y1 = y;
                    }else{
                        //clicks intermediarios
                        listaCoord.add(x);
                        listaCoord.add(y);
                    }
                    qtdDeClicks++;
                }else if(e.getButton() == MouseEvent.BUTTON3) {
                    //ultimo click
                    listaCoord.add(x);   
                    listaCoord.add(y);
                    listaCoord.add(x1); 
                    listaCoord.add(y1);
                    qtdDeClicks = 0;
                    figura.armazenarPoligonoGr(listaCoord, "", getEsp(), getCorAtual());
                    paint(g);
                }
            }
            
            //captura de coordenadas para transformacoes graficas
        }else if(modo == ModoDeUso.TRANSLACAO){
            //metodos de translaçao
            x = e.getX();
            y = e.getY();
            if (tipo == TipoPrimitivo.PONTO){
                //caso a figura ja tenha sido definida
                if(figura.getPontoGr() != null){
                    figura.translacaoPonto(x, y);//tem como parametro o ponto qualquer obtido
                    paint(g);
                }
            }else if (tipo == TipoPrimitivo.RETA){
                if(figura.getRetaGr() != null){
                    figura.translacaoReta(x, y);
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.CIRCULO){
                if(figura.getCirculoGr() != null){
                    figura.translacaoCirculo(x, y);
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.TRIANGULO){
                if(figura.getTrianguloGr() != null){
                    figura.translacaoTriangulo(x, y); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.RETANGULO){
                if(figura.getRetanguloGr() != null){
                    figura.translacaoRetangulo(x, y); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.POLIGONO){
                if(figura.getPoligonoGr() != null){
                    figura.translacaoPoligono(x, y); 
                    paint(g);
                }
            }

        }
        else if(modo == ModoDeUso.ROTACAO){
            //metodos de rotaçao
            x = e.getX();
            y = e.getY();
            if (tipo == TipoPrimitivo.PONTO){
                //caso a figura ja tenha sido definida
                if(figura.getPontoGr() != null){
                    figura.rotacaoPonto(x, y, getAngulo()); //recebe angulo atual como parametro
                    paint(g);
                }
            }else if (tipo == TipoPrimitivo.RETA){
                if(figura.getRetaGr() != null){
                    figura.rotacaoReta(x, y, getAngulo()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.CIRCULO){
                if(figura.getCirculoGr() != null){
                    figura.rotacaoCirculo(x, y, getAngulo()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.TRIANGULO){
                if(figura.getTrianguloGr() != null){
                    figura.rotacaoTriangulo(x, y, getAngulo());
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.RETANGULO){
                if(figura.getRetanguloGr() != null){
                    figura.rotacaoRetangulo(x, y, getAngulo());
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.POLIGONO){
                if(figura.getPoligonoGr() != null){
                    figura.rotacaoPoligono(x, y, getAngulo()); 
                    paint(g);
                }
            }

        }else if(modo == ModoDeUso.ESCALA){
            //metodos de escala
            x = e.getX();
            y = e.getY();
            if (tipo == TipoPrimitivo.PONTO){
                //caso a figura ja tenha sido definida
                if(figura.getPontoGr() != null){
                    figura.escalaPonto(x, y, getEscalaX(), getEscalaY()); //recebe os fatores de multiplicacao de cada eixo para escala
                    paint(g);
                }
            }else if (tipo == TipoPrimitivo.RETA){
                if(figura.getRetaGr() != null){
                    figura.escalaReta(x, y, getEscalaX(), getEscalaY()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.CIRCULO){
                if(figura.getCirculoGr() != null){
                    figura.escalaCirculo(x, y, getEscalaX(), getEscalaY()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.TRIANGULO){
                if(figura.getTrianguloGr() != null){
                    figura.escalaTriangulo(x, y, getEscalaX(), getEscalaY()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.RETANGULO){
                if(figura.getRetanguloGr() != null){
                    figura.escalaRetangulo(x, y, getEscalaX(), getEscalaY()); 
                    paint(g);
                }
            }else if (tipo==TipoPrimitivo.POLIGONO){
                if(figura.getPoligonoGr() != null){
                    figura.escalaPoligono(x, y, getEscalaX(), getEscalaY()); 
                    paint(g);
                }
            }

        }
    }

    public void mouseMoved(MouseEvent e) {
        //exibe a posicao do cursor, o tipo primitivo atual e o modo de uso atual
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo() + " - " + getModoDeUso());
    }
    
    //metodo que exibe os primitivos
    public void desenharPrimitivos(Graphics g){
        //exibe o ponto qualquer selecionado
        if(modo == ModoDeUso.TRANSLACAO || modo == ModoDeUso.ROTACAO || modo == ModoDeUso.ESCALA){
            FiguraPontos.desenharPonto(g, x, y, "", 6, Color.BLUE);
        }
        //exibe os primitivos na tela
        if (tipo == TipoPrimitivo.PONTO){
            FiguraPontos.desenharPonto(g, (int)figura.getPontoGr().getX(), (int)figura.getPontoGr().getY(), figura.getPontoGr().getNomePto(), 
                figura.getPontoGr().getDiametro(), figura.getPontoGr().getCorPto());
        }else if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharRetaMp(g, (int)figura.getRetaGr().getP1().getX(), (int)figura.getRetaGr().getP1().getY(), 
                (int)figura.getRetaGr().getP2().getX(), (int)figura.getRetaGr().getP2().getY(), figura.getRetaGr().getNomeReta(), 
                figura.getRetaGr().getEspReta(), figura.getRetaGr().getCorReta());
                //desenha o ponto médio da reta
            FiguraPontos.desenharPonto(g, (int)figura.getRetaGr().calcularCentroX(), (int)figura.getRetaGr().calcularCentroY(), "", 6, Color.BLUE);
        }else if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCirculos.desenharCirculoMp(g, (int)figura.getCirculoGr().getCentro().getX(), (int)figura.getCirculoGr().getCentro().getY(), 
                (int)figura.getCirculoGr().getRaio(), figura.getCirculoGr().getNomeCirculo(), 
                figura.getCirculoGr().getEspCirculo(), figura.getCirculoGr().getCorCirculo());
        }else if (tipo==TipoPrimitivo.TRIANGULO){
            FiguraTriangulos.desenharTrianguloMp(g, (int)figura.getTrianguloGr().getReta1().getP1().getX(), (int)figura.getTrianguloGr().getReta1().getP1().getY(), 
                (int)figura.getTrianguloGr().getReta1().getP2().getX(), (int)figura.getTrianguloGr().getReta1().getP2().getY(), 
                (int)figura.getTrianguloGr().getReta2().getP2().getX(), (int)figura.getTrianguloGr().getReta2().getP2().getY(), 
                figura.getTrianguloGr().getNomeTriangulo(), figura.getTrianguloGr().getEspTriangulo(),figura.getTrianguloGr().getCorTriangulo());
        }else if (tipo==TipoPrimitivo.RETANGULO){
            //um modo exibe o retangulo definido por uma reta, o outro exibe o retangulo definido por duas retas
            if(modo == ModoDeUso.DESENHO){
                //retangulo definido por uma reta
                FiguraRetangulos.desenharRetanguloMp(g, (int)figura.getRetanguloGr().getDiagonal().getP1().getX(), (int)figura.getRetanguloGr().getDiagonal().getP1().getY(), 
                    (int)figura.getRetanguloGr().getDiagonal().getP2().getX(), (int)figura.getRetanguloGr().getDiagonal().getP2().getY(), 
                    figura.getRetanguloGr().getNomeRetangulo(), figura.getRetanguloGr().getEspRetangulo(), figura.getRetanguloGr().getCorRetangulo());
            }else if(modo == ModoDeUso.TRANSLACAO || modo == ModoDeUso.ROTACAO || modo == ModoDeUso.ESCALA){
                //retangulo definido por duas retas
                FiguraRetangulos.desenharRetanguloQualquer(g, (int)figura.getRetanguloGr().getDiagonal().getP1().getX(), (int)figura.getRetanguloGr().getDiagonal().getP1().getY(), 
                    (int)figura.getRetanguloGr().getDiagonal().getP2().getX(), (int)figura.getRetanguloGr().getDiagonal().getP2().getY(), 
                    (int)figura.getRetanguloGr().getDiagonalInversa().getP1().getX(), (int)figura.getRetanguloGr().getDiagonalInversa().getP1().getY(), 
                    (int)figura.getRetanguloGr().getDiagonalInversa().getP2().getX(), (int)figura.getRetanguloGr().getDiagonalInversa().getP2().getY(),
                    figura.getRetanguloGr().getNomeRetangulo(), figura.getRetanguloGr().getEspRetangulo(), figura.getRetanguloGr().getCorRetangulo());
            }
            
        }else if (tipo==TipoPrimitivo.POLIGONO){
            FiguraPoligonos.desenharPoligonoMp(g, figura.getPoligonoGr().obterCoordenadas(), figura.getPoligonoGr().getNomePoligono(), 
                figura.getPoligonoGr().getEspPoligono(), figura.getPoligonoGr().getCorPoligono());
        }
    }
    
    public void apagarFigura(){
        //cria uma nova figura, perdendo a referencia da anterior
        figura = new Figura();
    }
    
    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mouseDragged(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseClicked(MouseEvent e) { }

}
