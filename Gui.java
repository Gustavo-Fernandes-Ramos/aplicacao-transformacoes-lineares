import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 1;
    
    //angulo de rotacao atual
    private int anguloAtual = 45;
    
    //fator de escala, eixos x e y
    private double escalaXAtual = 2;
    private double escalaYAtual = 2;
    
    // barra de menu primitivos
    private JToolBar barraComandos = new JToolBar();
    //barra de menu transformacoes
    private JToolBar barraTransformacao = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, espAtual);

    // Botoes para desenhar primitivos
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbRetaMp = new JButton("Reta");
    private JButton jbCirculoMp = new JButton("Circulo");
    private JButton jbTrianguloMp = new JButton("Triangulo");
    private JButton jbRetanguloMp = new JButton("Retangulo");
    private JButton jbPoligonoMp = new JButton("Poligono");
    private JButton jbCor = new JButton("Cor");
    
    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: " + String.format("%-5s", 1));
    private JSlider jsEsp = new JSlider(1, 50, 1);
    
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbSair = new JButton("Sair");
    
    //botoes para transformacoes geometricas
    private JButton jbTranslacao = new JButton("Translacao");
    private JButton jbRotacao = new JButton("Rotacao");
    private JButton jbEscala = new JButton("Escala");
    
    //Slider que define o angulo das rotacoes
    private JLabel jlAngulo = new JLabel("   Angulo: " + String.format("%-5s", anguloAtual));
    private JSlider jsAngulo = new JSlider(-180, 180, anguloAtual);
    
    //Slider que define a proporcao de escala eixo x
    private JLabel jlEscalaX = new JLabel("   Escalas X: ");
    private JTextField jtEscalaX = new JTextField(String.format("%s", escalaXAtual), 7);
    private JButton jbEscalaOK = new JButton("OK");
    
    //Slider que define a proporcao de escala eixo y
    private JLabel jlEscalaY = new JLabel(" Y: ");
    private JTextField jtEscalaY = new JTextField(String.format("%s", escalaXAtual), 7);
    // Construtor
    public Gui(int larg, int alt) {

        super("Transformacoes 2D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        
        //insere componentes para desenho grafico
        barraComandos.add(jbPonto);  //Botoes primitivos graficos
        barraComandos.add(jbRetaMp);
        barraComandos.add(jbCirculoMp);
        barraComandos.add(jbTrianguloMp);
        barraComandos.add(jbRetanguloMp);
        barraComandos.add(jbPoligonoMp);
        barraComandos.add(jbCor);  //Cor do primitivo
        barraComandos.add(jbLimpar);  //limpar componentes da tela
        
        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbSair); // Botao de Cores
        
        //insere componentes para transformacoes graficas
        barraTransformacao.add(jbTranslacao); //Botoes de transformacoes
        barraTransformacao.add(jbRotacao);
        barraTransformacao.add(jbEscala);
        
        //adiciona slider de angulo de rotacao
        barraTransformacao.add(jlAngulo);
        barraTransformacao.add(jsAngulo);
        areaDesenho.setAngulo(anguloAtual);
        
        //adiciona sliders de escala
        barraTransformacao.add(jlEscalaX);
        barraTransformacao.add(jtEscalaX);
        areaDesenho.setEscalaX(escalaXAtual);
        barraTransformacao.add(jlEscalaY);
        barraTransformacao.add(jtEscalaY);
        barraTransformacao.add(jbEscalaOK);
        areaDesenho.setEscalaY(escalaYAtual);
        barraTransformacao.add(msg);

        // adiciona os componentes com os respectivos layouts
        add(barraComandos, BorderLayout.NORTH);        
        add(areaDesenho, BorderLayout.CENTER);                
        add(barraTransformacao, BorderLayout.SOUTH);

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.PONTO;
            reconfigurarAmbiente(tipoAtual);
        });              
        jbRetaMp.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETA;
            reconfigurarAmbiente(tipoAtual);
        });            
        jbCirculoMp.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.CIRCULO;
            reconfigurarAmbiente(tipoAtual);
        });     
        jbTrianguloMp.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.TRIANGULO;
            reconfigurarAmbiente(tipoAtual);
        });   
        jbRetanguloMp.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.RETANGULO;
            reconfigurarAmbiente(tipoAtual);
        });  
        jbPoligonoMp.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.POLIGONO;
            reconfigurarAmbiente(tipoAtual);
        });
        //eventos de transformacao grafica
        jbTranslacao.addActionListener(e -> {
            areaDesenho.setModoDeUso(ModoDeUso.TRANSLACAO);
        });
        jbRotacao.addActionListener(e -> {
            areaDesenho.setModoDeUso(ModoDeUso.ROTACAO);
        });
        jbEscala.addActionListener(e -> {
            areaDesenho.setModoDeUso(ModoDeUso.ESCALA);
        });
        jbLimpar.addActionListener(e -> {
            tipoAtual = TipoPrimitivo.NENHUM;
            areaDesenho.apagarFigura(); //apaga a figura da memoria
            areaDesenho.removeAll();
            reconfigurarAmbiente(tipoAtual);
            repaint();        
        });  
        jbCor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
            if (c != null){ 
                corAtual = c; // pega do chooserColor 
            }
            areaDesenho.setCorAtual(corAtual); // cor atual
        });  
        jsAngulo.addChangeListener(e -> {
            anguloAtual = jsAngulo.getValue(); //obtem angulo atual
            jlAngulo.setText("   Angulo: " + String.format("%-5s", anguloAtual));
            areaDesenho.setAngulo(anguloAtual);        
        });    
        jsEsp.addChangeListener(e -> {
            espAtual = jsEsp.getValue();  //obtem espessura atual
            jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
            areaDesenho.setEsp(espAtual);        
        });   
        //botao que le os campos de texto para escala
        jbEscalaOK.addActionListener(e -> {
            try{
                double escX = Double.parseDouble(jtEscalaX.getText());
                double escY = Double.parseDouble(jtEscalaY.getText());
                //valores devem estar contidos no intervalo fechado [0, 6]
                if(escX > 0.0 && escX < 6.0 && escY > 0.0 && escY < 6.0 ){
                    escalaXAtual = escX;
                    escalaYAtual = escY;
                    //define a escala atual
                    areaDesenho.setEscalaX(escalaXAtual); 
                    areaDesenho.setEscalaY(escalaYAtual);   
                }else{
                    JOptionPane.showMessageDialog(null, "número inválido, os valores devem ser maiores que 0 e menores que 6!");
                }
                
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "número inválido, digíte apenas numeros!");
            }catch(Exception ex){ 
                ex.printStackTrace();
            }
        });
        jbSair.addActionListener(e -> {
            System.exit(0);
        }); 
    }
    //configura ambiente para o padrao inicial
    public void reconfigurarAmbiente(TipoPrimitivo tipo){
        areaDesenho.setTipo(tipo);
        areaDesenho.setQtdDeClicks(0); //utilizado para capturar clicks ao desenhar triangulos e poligonos
        areaDesenho.setPrimeiraVez(true);  //utilizado para capturar clicks ao desenhar circulos, retas e retangulos
        areaDesenho.setModoDeUso(ModoDeUso.DESENHO); //seta o modo atual de uso da aplicacao
    }
}