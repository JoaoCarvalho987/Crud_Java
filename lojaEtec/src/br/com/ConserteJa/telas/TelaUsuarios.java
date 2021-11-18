package br.com.ConserteJa.telas;
import java.sql.*;
import br.com.ConserteJa.dal.conecta;
import javax.swing.JOptionPane;
public class TelaUsuarios extends javax.swing.JInternalFrame {

     
    Connection conexao;
    PreparedStatement pst;
    PreparedStatement pstA;
    ResultSet rs;
    ResultSet rsA;
    
    private void limpar(){
        id.setText(null);
        nome.setText(null);
                tel.setText(null);
                email.setText(null);
                senha.setText(null);
                Perfil.setSelectedItem(null);
    }
    private void consultar(){
        String sql = "SELECT * FROM usuarios WHERE idUser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                nome.setText(rs.getString(2));
                tel.setText(rs.getString(3));
                email.setText(rs.getString(4));
                senha.setText(rs.getString(5));
                Perfil.setSelectedItem(rs.getString(6));
            } else{
                JOptionPane.showMessageDialog(null, "Usuário não encontrado/cadastrado!");
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void adicionar(){
        String sql = "INSERT INTO usuarios(idUser, nome, fone, email, senha, perfil) values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.setString(2, nome.getText());
            pst.setString(3, tel.getText());
            pst.setString(4, email.getText());
            String senhazinha = new String(senha.getPassword());
            pst.setString(5, senhazinha);
            pst.setString(6, Perfil.getSelectedItem().toString());
            if (id.getText().isEmpty() || nome.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty() || senha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos os dados!");
            }else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado!");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void update(){
        String sql = "UPDATE usuarios set nome=?, fone=?, email=?, senha=?, perfil=? where idUser=?";
        String sqlA = "SELECT * FROM usuarios WHERE idUser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nome.getText());
            pst.setString(2, tel.getText());
            pst.setString(3, email.getText());
            String senhazinha = new String(senha.getPassword());
            pst.setString(4, senhazinha);
            pst.setString(5, Perfil.getSelectedItem().toString());
            pst.setString(6, id.getText());
            if (id.getText().isEmpty() || nome.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty() || senha.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            }else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    pstA = conexao.prepareStatement(sqlA);
                    pstA.setString(1, id.getText());
                    rsA = pstA.executeQuery();
                    if (rsA.next()){
                        JOptionPane.showMessageDialog(null, "Dados do usuário "+ rsA.getString(2) + " foram atualizados!");
                    }
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void deletar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja mesmo remover este usuário?", "Atenção!", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE from usuarios WHERE idUser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, id.getText());
                int apa = pst.executeUpdate();
                if (apa > 0){
                    JOptionPane.showMessageDialog(null, "Usuário removido");
                    limpar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    public TelaUsuarios() {
        initComponents();
        conexao = conecta.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Perfil = new javax.swing.JComboBox<>();
        nome = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        adicionar = new javax.swing.JButton();
        procurar = new javax.swing.JButton();
        editar = new javax.swing.JButton();
        deletar = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        senha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de usuários");

        jLabel1.setText("Nome");

        jLabel2.setText("Telefone");

        jLabel3.setText("email");

        jLabel4.setText("Senha");

        Perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel5.setText("ID:");

        adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ConserteJa/icones/create.png"))); // NOI18N
        adicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        procurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ConserteJa/icones/pesquisar.png"))); // NOI18N
        procurar.setToolTipText("");
        procurar.setPreferredSize(new java.awt.Dimension(80, 80));
        procurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procurarActionPerformed(evt);
            }
        });

        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ConserteJa/icones/update.png"))); // NOI18N
        editar.setPreferredSize(new java.awt.Dimension(80, 80));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });

        deletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ConserteJa/icones/delete.png"))); // NOI18N
        deletar.setPreferredSize(new java.awt.Dimension(80, 80));
        deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarActionPerformed(evt);
            }
        });

        senha.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(8, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tel, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(email)
                            .addComponent(nome)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(senha))
                        .addGap(18, 18, 18)
                        .addComponent(Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(procurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_adicionarActionPerformed

    private void procurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procurarActionPerformed
        consultar();
    }//GEN-LAST:event_procurarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        update();
    }//GEN-LAST:event_editarActionPerformed

    private void deletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarActionPerformed
        deletar();
    }//GEN-LAST:event_deletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Perfil;
    private javax.swing.JButton adicionar;
    private javax.swing.JButton deletar;
    private javax.swing.JButton editar;
    private javax.swing.JTextField email;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nome;
    private javax.swing.JButton procurar;
    private javax.swing.JPasswordField senha;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
