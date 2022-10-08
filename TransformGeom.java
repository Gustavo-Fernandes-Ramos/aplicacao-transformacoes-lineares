
import java.awt.Graphics;
import java.awt.Color;
//metodos de transformacoes lineares
public class TransformGeom{
    
    public static double[][] translacionarPonto(double[][] matriz, double deltaX, double deltaY){
        double matrizTranslacao[][] = {{1, 0, deltaX}, {0, 1, deltaY}, {0, 0, 1}}; //matriz de translacao
        double matrizRes[][] = new double[3][1]; //matriz resultante da transformacao
        double soma;
        
        //multiplica as matrizes
        for(int i = 0 ; i < 3 ; i++){
            soma = 0;
            for(int j = 0 ; j < 3 ; j++) soma = soma + (matrizTranslacao[i][j] * matriz[j][0]);
            matrizRes[i][0] = soma;
        }
        return matrizRes;
    }
    
    public static double[][] rotacionarPonto(double [][] matriz, double xLinha, double yLinha, int angulo){
        //calcula seno e cosseno do angulo parametro
        double seno = Math.sin(Math.PI/180*angulo);
        double cos = Math.cos(Math.PI/180*angulo);
        double matrizRotacao[][] = {{cos, -seno, xLinha*(1 - cos) + yLinha*seno},  //matriz de rotacao
                                        {seno, cos, yLinha*(1 - cos) - xLinha*seno}, 
                                        {0, 0, 1}};
        double matrizRes[][] = new double[3][1];
        double soma;
        
        for(int i = 0 ; i < 3 ; i++){
            soma = 0;
            for(int j = 0 ; j < 3 ; j++) soma = soma + (matrizRotacao[i][j] * matriz[j][0]);
            matrizRes[i][0] = soma;
        }
        return matrizRes;
    }
    
    public static double[][] escalarPonto(double [][] matriz, double xLinha, double yLinha, double escalaX, double escalaY ){

        double matrizRotacao[][] = {{escalaX, 0, xLinha*(1 - escalaX)},   //matriz de escala
                                    {0, escalaY, yLinha*(1 - escalaY)}, 
                                        {0, 0, 1}};
        double matrizRes[][] = new double[3][1];
        double soma;
        
        for(int i = 0 ; i < 3 ; i++){
            soma = 0;
            for(int j = 0 ; j < 3 ; j++) soma = soma + (matrizRotacao[i][j] * matriz[j][0]);
            matrizRes[i][0] = soma;
        }
        return matrizRes;
    }
    
}
