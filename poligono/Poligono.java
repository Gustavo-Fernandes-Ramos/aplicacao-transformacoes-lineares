package poligono;
import java.util.List;
import java.util.ArrayList;
import reta.Reta;

public class Poligono {
    // par = x, impar = y
    List<Reta> listaRetas = new ArrayList<Reta>();
    
    public Poligono(List<Integer> coord){
        int x1 = 0; int y1 = 0; int x2 = 0; int y2 = 0; 
        
        int aux, tam = coord.size();
        
        List<Reta> retas = new ArrayList<Reta>();
        //armazena todas as coordenadas duas vezes, ja que sera armazenada como uma lista de retas
        for(int i = 0 ; i < tam ; i++){
            aux = i%4;
            switch(aux){
                case 0:
                    x1 = coord.get(i);
                    break;
                case 1:
                    y1 = coord.get(i);
                    break;
                case 2:
                    x2 = coord.get(i);
                    break;
                case 3:
                    y2 = coord.get(i);
                    retas.add(new Reta(x1, y1, x2, y2));
                    break;
                default:
                    break;
            }
        }
        setListaRetas(retas);
    }
    
    public void setListaRetas(List<Reta> retas){
        this.listaRetas = retas;
    }
    
    public List<Reta> getListaRetas(){
        return this.listaRetas;
    }
    public List<Integer> obterCoordenadas(){
        Reta retaItem;
        List<Reta> listaR = getListaRetas();
        List<Integer> coord = new ArrayList<Integer>();
        
        int tam  = listaR.size();
        for(int i = 0 ; i < tam ; i++){
            retaItem = listaR.get(i);
            coord.add((int)retaItem.getP1().getX());
            coord.add((int)retaItem.getP1().getY());
            coord.add((int)retaItem.getP2().getX());
            coord.add((int)retaItem.getP2().getY());
        }
        return coord;
    }
    public String toString(){
        String s;
        List<Reta> r = getListaRetas();
        int tam = r.size();
        s = "poligono: ";
        for(int i = 0; i < tam ; i++){
            s = s + "reta " + i + " = " + r.get(i).toString();
        }
        return s;
    }
}
