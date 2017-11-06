/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import view.Componetes.CampoDeTestoFocusListaner;
import controller.ProjetoController;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import model.Projeto;
import view.Componetes.MeuScrollbarUI;

/**
 *
 * @author matheus
 */
public class FormularioDeProjetoPainel extends javax.swing.JPanel implements Painel{
    
    
    private TipoDePainel tipo;
    private String srcProducao, srcTestes;
    Map<?, ?> dados = null;
            
    FormularioDeProjetoPainel(){
        initComponents();
        iniciarlistaners();
    }
    
    
    private boolean cadastarProjeto(){
        if (!todosOsCamposEstaoValidos())
            return false;
        boolean resultado;
        do {
            resultado = ProjetoController.cadastrarProjeto(
                    campoNome.getText(),
                    srcProducao,
                    srcTestes,
                    campoDePFCasoDeUso.getText(),
                    campoDePFCasoDeTeste.getText(),
                    campoDePFRoteiroDeTeste.getText(),
                    campoDescricao.getText()
            );
            if (!resultado){

            }
        }while (!resultado);
        limpar();
        return true;
    }

    /**
     * valida as informações dos campos do formilario
     * @return retorna true apenas quando todos os campos forem validos.
     */
    protected boolean todosOsCamposEstaoValidos() {
        if (campoNome.getText().isEmpty() || campoNome.getText().length() > 44){
            JOptionPane.showMessageDialog(null, "Nome do projeto invalido!", "", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDePFCasoDeTeste.getText().length() > 10 || campoDePFCasoDeUso.getText().length() > 10 || campoDePFRoteiroDeTeste.getText().length() > 10){
            JOptionPane.showMessageDialog(null, "Os prefixos devem ter no maximo 10 caractes cada!", "Prefixo invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (campoDePFCasoDeTeste.getText().isEmpty() || campoDePFCasoDeUso.getText().isEmpty()|| campoDePFRoteiroDeTeste.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Informe todos os prefixos!", "Prefixo invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (srcTestes == null || srcProducao == null){
            JOptionPane.showMessageDialog(null, "Informe os diretórios do projeto!", "src invalido", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Abre uma jenela de seleção de arquivos para o usuario informar ao sistema
     * a pasta raiz do diretorio de produção do software que será testado.
     */
    private void selecionarDiretoriosDeProducao(){
        JFileChooser chooserArquivo = new JFileChooser();
        chooserArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserArquivo.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Selecione o seu diretório raiz dos arquivos .class";
            }
        });
        chooserArquivo.showOpenDialog(null);
        srcProducao = chooserArquivo.getSelectedFile().getAbsolutePath();
        labelSrcProducao.setText(srcProducao);
    }

    /**
     * Abre uma jenela de seleção de arquivos para o usuario informar ao sistema
     * a pasta raiz do diretorio de testes do software que será testado.
     */
    private void selecionarDiretoriosDeTestes(){
        JFileChooser chooserArquivo = new JFileChooser();
        chooserArquivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooserArquivo.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Selecione o seu diretório raiz dos arquivos .class de teste";
            }
        });
        chooserArquivo.showOpenDialog(null);
        srcTestes = chooserArquivo.getSelectedFile().getAbsolutePath();
        labelSrcTestes.setText(srcTestes);
    }
    /**
     * Limpa todas as informações de todos os campos do formulario.
     */
    public void limpar (){
        campoDePFCasoDeTeste.setText("");
        campoDePFCasoDeUso.setText("");
        campoDePFRoteiroDeTeste.setText("");
        labelSrcTestes.setText(" ");
        campoNome.setText("");
        campoDescricao.setText("");
        labelSrcProducao.setText("");
    }
    
    
    

    private void iniciarlistaners(){
        campoNome.addFocusListener(new CampoDeTestoFocusListaner(new Color(51,51,51), bordaInferiorNome));
        campoDePFCasoDeUso.addFocusListener(new CampoDeTestoFocusListaner(new Color(51,51,51), bordaInferiorPFCasoDeUso));
        campoDePFCasoDeTeste.addFocusListener(new CampoDeTestoFocusListaner(new Color(51,51,51), bordaInferiorPFCasoDeTeste));
        campoDePFRoteiroDeTeste.addFocusListener(new CampoDeTestoFocusListaner(new Color(51,51,51), bordaInferiorPFRoteiroDeTeste));
        btnEscolherDiretorioDeProducao.addMouseListener(new MouseAdapter() {
            Color anterior;
            @Override
            public void mouseEntered(MouseEvent e) {
                anterior = btnEscolherDiretorioDeProducao.getBackground();
                btnEscolherDiretorioDeProducao.setBackground(new Color(221,221,221));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEscolherDiretorioDeProducao.setBackground(anterior);
            }
            
        });
        btnEscolherDiretorioDeTestes.addMouseListener(new MouseAdapter() {
            Color anterior;
            @Override
            public void mouseEntered(MouseEvent e) {
                anterior = btnEscolherDiretorioDeTestes.getBackground();
                btnEscolherDiretorioDeTestes.setBackground(new Color(221,221,221));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnEscolherDiretorioDeTestes.setBackground(anterior);
            }
            
        });
        
        btnEscolherDiretorioDeProducao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarDiretoriosDeProducao();
            }
        });
        
        btnEscolherDiretorioDeTestes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selecionarDiretoriosDeTestes();
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formunario = new javax.swing.JPanel();
        campoDeTextoContainer = new javax.swing.JPanel();
        bordaInferiorNome = new javax.swing.JPanel();
        campoNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        prefixos = new javax.swing.JPanel();
        campoDeTextoContainer1 = new javax.swing.JPanel();
        bordaInferiorPFCasoDeUso = new javax.swing.JPanel();
        campoDePFCasoDeUso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoDeTextoContainer11 = new javax.swing.JPanel();
        bordaInferiorPFCasoDeTeste = new javax.swing.JPanel();
        campoDePFCasoDeTeste = new javax.swing.JTextField();
        campoDeTextoContainer12 = new javax.swing.JPanel();
        bordaInferiorPFRoteiroDeTeste = new javax.swing.JPanel();
        campoDePFRoteiroDeTeste = new javax.swing.JTextField();
        Diretorios = new javax.swing.JPanel();
        btnEscolherDiretorioDeProducao = new javax.swing.JButton();
        btnEscolherDiretorioDeTestes = new javax.swing.JButton();
        labelSrcProducao = new javax.swing.JLabel();
        labelSrcTestes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formunario.setBackground(new java.awt.Color(255, 255, 255));
        formunario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white));

        campoDeTextoContainer.setBackground(new java.awt.Color(255, 255, 255));
        campoDeTextoContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        bordaInferiorNome.setBackground(new java.awt.Color(153, 153, 153));
        bordaInferiorNome.setPreferredSize(new java.awt.Dimension(340, 2));

        javax.swing.GroupLayout bordaInferiorNomeLayout = new javax.swing.GroupLayout(bordaInferiorNome);
        bordaInferiorNome.setLayout(bordaInferiorNomeLayout);
        bordaInferiorNomeLayout.setHorizontalGroup(
            bordaInferiorNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bordaInferiorNomeLayout.setVerticalGroup(
            bordaInferiorNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        campoNome.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        campoNome.setForeground(new java.awt.Color(51, 51, 51));
        campoNome.setBorder(null);

        javax.swing.GroupLayout campoDeTextoContainerLayout = new javax.swing.GroupLayout(campoDeTextoContainer);
        campoDeTextoContainer.setLayout(campoDeTextoContainerLayout);
        campoDeTextoContainerLayout.setHorizontalGroup(
            campoDeTextoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoNome)
            .addComponent(bordaInferiorNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );
        campoDeTextoContainerLayout.setVerticalGroup(
            campoDeTextoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campoDeTextoContainerLayout.createSequentialGroup()
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(bordaInferiorNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        jLabel1.setText("Nome:");

        prefixos.setBackground(new java.awt.Color(255, 255, 255));
        prefixos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Prefixos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Ubuntu", 0, 16))); // NOI18N

        campoDeTextoContainer1.setBackground(new java.awt.Color(255, 255, 255));
        campoDeTextoContainer1.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        bordaInferiorPFCasoDeUso.setBackground(new java.awt.Color(153, 153, 153));
        bordaInferiorPFCasoDeUso.setPreferredSize(new java.awt.Dimension(340, 2));

        javax.swing.GroupLayout bordaInferiorPFCasoDeUsoLayout = new javax.swing.GroupLayout(bordaInferiorPFCasoDeUso);
        bordaInferiorPFCasoDeUso.setLayout(bordaInferiorPFCasoDeUsoLayout);
        bordaInferiorPFCasoDeUsoLayout.setHorizontalGroup(
            bordaInferiorPFCasoDeUsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bordaInferiorPFCasoDeUsoLayout.setVerticalGroup(
            bordaInferiorPFCasoDeUsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        campoDePFCasoDeUso.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        campoDePFCasoDeUso.setForeground(new java.awt.Color(51, 51, 51));
        campoDePFCasoDeUso.setBorder(null);

        javax.swing.GroupLayout campoDeTextoContainer1Layout = new javax.swing.GroupLayout(campoDeTextoContainer1);
        campoDeTextoContainer1.setLayout(campoDeTextoContainer1Layout);
        campoDeTextoContainer1Layout.setHorizontalGroup(
            campoDeTextoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoDePFCasoDeUso)
            .addComponent(bordaInferiorPFCasoDeUso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
        );
        campoDeTextoContainer1Layout.setVerticalGroup(
            campoDeTextoContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campoDeTextoContainer1Layout.createSequentialGroup()
                .addComponent(campoDePFCasoDeUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(bordaInferiorPFCasoDeUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Casos de teste:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Casos de Uso:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Roteiros de teste:");

        campoDeTextoContainer11.setBackground(new java.awt.Color(255, 255, 255));
        campoDeTextoContainer11.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        bordaInferiorPFCasoDeTeste.setBackground(new java.awt.Color(153, 153, 153));
        bordaInferiorPFCasoDeTeste.setPreferredSize(new java.awt.Dimension(340, 2));

        javax.swing.GroupLayout bordaInferiorPFCasoDeTesteLayout = new javax.swing.GroupLayout(bordaInferiorPFCasoDeTeste);
        bordaInferiorPFCasoDeTeste.setLayout(bordaInferiorPFCasoDeTesteLayout);
        bordaInferiorPFCasoDeTesteLayout.setHorizontalGroup(
            bordaInferiorPFCasoDeTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bordaInferiorPFCasoDeTesteLayout.setVerticalGroup(
            bordaInferiorPFCasoDeTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        campoDePFCasoDeTeste.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        campoDePFCasoDeTeste.setForeground(new java.awt.Color(51, 51, 51));
        campoDePFCasoDeTeste.setBorder(null);

        javax.swing.GroupLayout campoDeTextoContainer11Layout = new javax.swing.GroupLayout(campoDeTextoContainer11);
        campoDeTextoContainer11.setLayout(campoDeTextoContainer11Layout);
        campoDeTextoContainer11Layout.setHorizontalGroup(
            campoDeTextoContainer11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoDePFCasoDeTeste)
            .addComponent(bordaInferiorPFCasoDeTeste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        campoDeTextoContainer11Layout.setVerticalGroup(
            campoDeTextoContainer11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campoDeTextoContainer11Layout.createSequentialGroup()
                .addComponent(campoDePFCasoDeTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(bordaInferiorPFCasoDeTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        campoDeTextoContainer12.setBackground(new java.awt.Color(255, 255, 255));
        campoDeTextoContainer12.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));

        bordaInferiorPFRoteiroDeTeste.setBackground(new java.awt.Color(153, 153, 153));
        bordaInferiorPFRoteiroDeTeste.setPreferredSize(new java.awt.Dimension(340, 2));

        javax.swing.GroupLayout bordaInferiorPFRoteiroDeTesteLayout = new javax.swing.GroupLayout(bordaInferiorPFRoteiroDeTeste);
        bordaInferiorPFRoteiroDeTeste.setLayout(bordaInferiorPFRoteiroDeTesteLayout);
        bordaInferiorPFRoteiroDeTesteLayout.setHorizontalGroup(
            bordaInferiorPFRoteiroDeTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bordaInferiorPFRoteiroDeTesteLayout.setVerticalGroup(
            bordaInferiorPFRoteiroDeTesteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        campoDePFRoteiroDeTeste.setFont(new java.awt.Font("Ubuntu", 0, 16)); // NOI18N
        campoDePFRoteiroDeTeste.setForeground(new java.awt.Color(51, 51, 51));
        campoDePFRoteiroDeTeste.setBorder(null);

        javax.swing.GroupLayout campoDeTextoContainer12Layout = new javax.swing.GroupLayout(campoDeTextoContainer12);
        campoDeTextoContainer12.setLayout(campoDeTextoContainer12Layout);
        campoDeTextoContainer12Layout.setHorizontalGroup(
            campoDeTextoContainer12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoDePFRoteiroDeTeste)
            .addComponent(bordaInferiorPFRoteiroDeTeste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );
        campoDeTextoContainer12Layout.setVerticalGroup(
            campoDeTextoContainer12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campoDeTextoContainer12Layout.createSequentialGroup()
                .addComponent(campoDePFRoteiroDeTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(bordaInferiorPFRoteiroDeTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout prefixosLayout = new javax.swing.GroupLayout(prefixos);
        prefixos.setLayout(prefixosLayout);
        prefixosLayout.setHorizontalGroup(
            prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefixosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prefixosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prefixosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoContainer11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(prefixosLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoContainer12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        prefixosLayout.setVerticalGroup(
            prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prefixosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDeTextoContainer11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDeTextoContainer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prefixosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDeTextoContainer12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        Diretorios.setBackground(new java.awt.Color(255, 255, 255));
        Diretorios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Diretórios do projeto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Ubuntu", 0, 16))); // NOI18N

        btnEscolherDiretorioDeProducao.setBackground(new java.awt.Color(255, 255, 255));
        btnEscolherDiretorioDeProducao.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnEscolherDiretorioDeProducao.setText("Escolher diretório com as classes de produção");
        btnEscolherDiretorioDeProducao.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        btnEscolherDiretorioDeProducao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEscolherDiretorioDeProducao.setFocusPainted(false);
        btnEscolherDiretorioDeProducao.setRolloverEnabled(false);

        btnEscolherDiretorioDeTestes.setBackground(new java.awt.Color(255, 255, 255));
        btnEscolherDiretorioDeTestes.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        btnEscolherDiretorioDeTestes.setText("Escolher diretório com as classes de teste");
        btnEscolherDiretorioDeTestes.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        btnEscolherDiretorioDeTestes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEscolherDiretorioDeTestes.setFocusPainted(false);
        btnEscolherDiretorioDeTestes.setRolloverEnabled(false);

        labelSrcProducao.setFont(new java.awt.Font("Ubuntu", 2, 16)); // NOI18N
        labelSrcProducao.setForeground(new java.awt.Color(153, 153, 153));
        labelSrcProducao.setText(" ");

        labelSrcTestes.setFont(new java.awt.Font("Ubuntu", 2, 16)); // NOI18N
        labelSrcTestes.setForeground(new java.awt.Color(153, 153, 153));
        labelSrcTestes.setText(" ");

        javax.swing.GroupLayout DiretoriosLayout = new javax.swing.GroupLayout(Diretorios);
        Diretorios.setLayout(DiretoriosLayout);
        DiretoriosLayout.setHorizontalGroup(
            DiretoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiretoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DiretoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DiretoriosLayout.createSequentialGroup()
                        .addComponent(btnEscolherDiretorioDeProducao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSrcProducao, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                    .addGroup(DiretoriosLayout.createSequentialGroup()
                        .addComponent(btnEscolherDiretorioDeTestes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSrcTestes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        DiretoriosLayout.setVerticalGroup(
            DiretoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiretoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DiretoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscolherDiretorioDeProducao)
                    .addComponent(labelSrcProducao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DiretoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEscolherDiretorioDeTestes)
                    .addComponent(labelSrcTestes))
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.lightGray, java.awt.Color.white), javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1)), "Descrição", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ubuntu", 0, 16), new java.awt.Color(51, 51, 51))); // NOI18N

        campoDescricao.setColumns(20);
        campoDescricao.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        campoDescricao.setLineWrap(true);
        campoDescricao.setRows(5);
        campoDescricao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(campoDescricao);

        javax.swing.GroupLayout formunarioLayout = new javax.swing.GroupLayout(formunario);
        formunario.setLayout(formunarioLayout);
        formunarioLayout.setHorizontalGroup(
            formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formunarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(Diretorios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prefixos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(formunarioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formunarioLayout.setVerticalGroup(
            formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formunarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formunarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoDeTextoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(prefixos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Diretorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.getHorizontalScrollBar().setUI(new MeuScrollbarUI());
        jScrollPane1.getVerticalScrollBar().setUI(new MeuScrollbarUI());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formunario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formunario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Diretorios;
    private javax.swing.JPanel bordaInferiorNome;
    private javax.swing.JPanel bordaInferiorPFCasoDeTeste;
    private javax.swing.JPanel bordaInferiorPFCasoDeUso;
    private javax.swing.JPanel bordaInferiorPFRoteiroDeTeste;
    private javax.swing.JButton btnEscolherDiretorioDeProducao;
    private javax.swing.JButton btnEscolherDiretorioDeTestes;
    private javax.swing.JTextField campoDePFCasoDeTeste;
    private javax.swing.JTextField campoDePFCasoDeUso;
    private javax.swing.JTextField campoDePFRoteiroDeTeste;
    private javax.swing.JPanel campoDeTextoContainer;
    private javax.swing.JPanel campoDeTextoContainer1;
    private javax.swing.JPanel campoDeTextoContainer11;
    private javax.swing.JPanel campoDeTextoContainer12;
    private javax.swing.JTextArea campoDescricao;
    private javax.swing.JTextField campoNome;
    private javax.swing.JPanel formunario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelSrcProducao;
    private javax.swing.JLabel labelSrcTestes;
    private javax.swing.JPanel prefixos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void preProcessamentoAntesDeAbrir() {
        tipo = TipoDePainel.FORMULARIO_DE_CRIACAO;
        if (dados != null){
            Projeto p = (Projeto) dados.get("projeto");
            campoDePFCasoDeTeste.setText(p.getPrefixoCT());
            campoDePFCasoDeUso.setText(p.getPrefixoCU());
            campoDePFRoteiroDeTeste.setText(p.getPrefixoRT());
            labelSrcTestes.setText(p.getSrcTestes());
            labelSrcProducao.setText(p.getSrcProducao());
            campoNome.setText(p.getNome());
            campoDescricao.setText(p.getDescricao());
            campoDePFCasoDeTeste.setEditable(false);
            campoDePFCasoDeUso.setEditable(false);
            campoDePFRoteiroDeTeste.setEditable(false);
            srcProducao = p.getSrcProducao();
            srcTestes = p.getSrcTestes();
            tipo = TipoDePainel.FORMULARIO_DE_EDICAO;
        }
    }

    @Override
    public void preProcessamentoAntesDeFechar() {
    }

    @Override
    public Component getCompomenteRaiz() {
        return this;
    }

    @Override
    public void btnNovoOnClick(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void btnSalvarOnClick(MouseEvent e) {
        this.cadastarProjeto();
    }

    @Override
    public void btnCancelarOnClick(MouseEvent e) {
        mainApp.trocarDePainel(Paineis.PROJETOS, null);
    }

    @Override
    public void btnLimparOnClick(MouseEvent e) {
        this.limpar();
    }

    @Override
    public void setData(Map<?, ?> dados) {
        this.dados = dados;
    }

    @Override
    public TipoDePainel pegarTipoDePainel() {
        return tipo;
    }
}
