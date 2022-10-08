package retangulo;

import java.awt.*;

public class FiguraRetangulos {
    //metodo que desenha um retangulo definido por uma reta, sendo sua base sempre paralela ao eixo das abscissas
    public static void desenharRetanguloMp(Graphics g, int x1, int y1, int x2, int y2, String nome, int esp, Color cor){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, cor, nome, esp);
        r.desenharRetanguloMp(g);
    }
    
    //metodo que desenha um retangulo qualquer definido por duas retas
    public static void desenharRetanguloQualquer(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, String nome, int esp, Color cor){
        RetanguloGr r = new RetanguloGr(x1, y1, x2, y2, x3, y3, x4, y4, cor, nome, esp);
        r.desenharRetanguloQualquer(g);
    }
}
