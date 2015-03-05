/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinearSystem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
/**
 *
 * @author pyounglous
 */
public class LinearSystem extends javax.swing.JApplet {
    LinearEq linear;
    javax.swing.Timer timer;
    int NofPtls;
    double a,b,c,d;
    /**
     * Initializes the applet LinearSystem
     */
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
         a=0.08;b=0.6;c=0;d=0;
         NofPtls=2000;
  
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LinearSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LinearSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LinearSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LinearSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();

                    timer = new javax.swing.Timer(1,new aListener());
                    linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public class aListener implements ActionListener 
    {
       
        public void actionPerformed(ActionEvent e) {
                displayPanel.removeAll();
                displayPanel.repaint();
        }
    };
    
     public class MainDisplay extends javax.swing.JPanel{                
        
        MainDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Color color;
            int radius=3;
            int w;
            w=displayPanel.getWidth();
            
            for(int j=0;j<10;j++){
                linear.nextTime();
                for(int i=0;i<NofPtls;i++){
                    color =  Color.getHSBColor(((float)(linear.color[i]+Math.PI/2)/(float)(2*Math.PI)),1f,1f);
                    g.setColor(color);
                    g.fillOval((int)linear.x[i]-radius/2+w/2,-(int)linear.y[i]-radius/2+w/2,radius,radius);
                }
            }
        }
    }
     
     public class DiagramDisplay extends javax.swing.JPanel{                
        
        DiagramDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Color color;
            double tau,delta;
            int radius=6;
            int w;
            Ellipse2D.Double object;
            Shape l;
            Graphics2D g2 = (Graphics2D)g;
            
            w=diagramPanel.getWidth();
            
            // Axis line
            g.setColor(Color.BLACK);
            g.drawLine((int)(0.05*w),(int)(0.5*w),(int)(0.95*w),(int)(0.5*w));
            g.drawLine((int)(0.35*w),(int)(0.05*w),(int)(0.35*w),(int)(0.95*w));
            
            // Transition line
            double x1=0,y1=0,x2,y2;
            g.setColor(Color.BLUE);
            while(x1<9){
                x2=x1+0.01;
                y2=2*Math.pow(x2, 0.5);
                l = new Line2D.Double((double)(0.35*w+(x1)/9.*0.9*w),(double)(w-((y1)/9.*0.9*w+0.5*w)),(double)(0.35*w+(x2)/9.*0.9*w),(double)(w-((y2)/9.*0.9*w+0.5*w)));
                g2.draw(l);
                x1=x2;
                y1=y2;
            }
            x1=0;y1=0;
            while(x1<9){
                x2=x1+0.01;
                y2=-2*Math.pow(x2, 0.5);
                l = new Line2D.Double((double)(0.35*w+(x1)/9.*0.9*w),(double)(w-((y1)/9.*0.9*w+0.5*w)),(double)(0.35*w+(x2)/9.*0.9*w),(double)(w-((y2)/9.*0.9*w+0.5*w)));
                g2.draw(l);
                x1=x2;
                y1=y2;
            }
            
            tau=w-((a+d)/9.*0.9*w+0.5*w);
            delta=0.35*w+(a*d-b*c)/9.*0.9*w;

            object = new Ellipse2D.Double(delta-radius/2,tau-radius/2,radius,radius);
            g2.setColor(Color.RED);
            g2.fill(object);
            
            g.drawString("saddle points",(int)(0.05*getWidth()),(int)(0.4*getHeight()));
            g.drawString("centers",(int)(0.5*getWidth()),(int)(0.5*getHeight()));
            g.drawString("unstable spirals",(int)(0.6*getWidth()),(int)(0.3*getHeight()));
            g.drawString("stable spirals",(int)(0.6*getWidth()),(int)(0.7*getHeight()));
            g.drawString("unstable nodes",(int)(0.4*getWidth()),(int)(0.1*getHeight()));
            g.drawString("stable nodes",(int)(0.4*getWidth()),(int)(0.9*getHeight()));
            
            
            
        }
    }
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        displayPanel = new MainDisplay();
        controlPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        aTextField = new javax.swing.JTextField();
        aSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        cTextField = new javax.swing.JTextField();
        cSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        bTextField = new javax.swing.JTextField();
        bSlider = new javax.swing.JSlider();
        dSlider = new javax.swing.JSlider();
        dTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        diagramPanel = new DiagramDisplay();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setMaximumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setVisible(true);

        displayPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        controlPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LinearSystem/Eq.gif"))); // NOI18N

        jLabel1.setText("a");

        aTextField.setText("0.08");
        aTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aTextFieldActionPerformed(evt);
            }
        });

        aSlider.setMaximum(200);
        aSlider.setMinimum(-200);
        aSlider.setValue(8);
        aSlider.setPreferredSize(new java.awt.Dimension(206, 25));
        aSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                aSliderStateChanged(evt);
            }
        });

        jLabel3.setText("c");

        cTextField.setText("0.00");
        cTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTextFieldActionPerformed(evt);
            }
        });

        cSlider.setMaximum(200);
        cSlider.setMinimum(-200);
        cSlider.setValue(0);
        cSlider.setPreferredSize(new java.awt.Dimension(206, 25));
        cSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cSliderStateChanged(evt);
            }
        });

        jLabel2.setText("b");

        bTextField.setText("0.60");
        bTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTextFieldActionPerformed(evt);
            }
        });

        bSlider.setMaximum(200);
        bSlider.setMinimum(-200);
        bSlider.setValue(60);
        bSlider.setPreferredSize(new java.awt.Dimension(206, 25));
        bSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bSliderStateChanged(evt);
            }
        });

        dSlider.setMaximum(200);
        dSlider.setMinimum(-200);
        dSlider.setValue(0);
        dSlider.setPreferredSize(new java.awt.Dimension(206, 25));
        dSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dSliderStateChanged(evt);
            }
        });

        dTextField.setText("0.00");
        dTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("d");

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(aSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(cSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(1, 1, 1))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        controlPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {exitButton, startButton});

        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(startButton)
                            .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(aSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(11, 11, 11)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(exitButton)
                            .addComponent(dSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        diagramPanel.setBackground(new java.awt.Color(255, 255, 240));

        javax.swing.GroupLayout diagramPanelLayout = new javax.swing.GroupLayout(diagramPanel);
        diagramPanel.setLayout(diagramPanelLayout);
        diagramPanelLayout.setHorizontalGroup(
            diagramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
        diagramPanelLayout.setVerticalGroup(
            diagramPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 324, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(diagramPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diagramPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void aSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_aSliderStateChanged
        a=(double) aSlider.getValue()/100.;
        aTextField.setText(String.format("%.2f", a));
        diagramPanel.removeAll();
        diagramPanel.repaint();
//        linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
    }//GEN-LAST:event_aSliderStateChanged

    private void bSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bSliderStateChanged
        b=(double) bSlider.getValue()/100.;
        bTextField.setText(String.format("%.2f", b));
        diagramPanel.removeAll();
        diagramPanel.repaint();
//        linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
    }//GEN-LAST:event_bSliderStateChanged

    private void dSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dSliderStateChanged
        d=(double) dSlider.getValue()/100.;
        dTextField.setText(String.format("%.2f", d));
        diagramPanel.removeAll();
        diagramPanel.repaint();
//        linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
    }//GEN-LAST:event_dSliderStateChanged

    private void cSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cSliderStateChanged
        c=(double) cSlider.getValue()/100.;
        cTextField.setText(String.format("%.2f", c));
        diagramPanel.removeAll();
        diagramPanel.repaint();
//       linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
    }//GEN-LAST:event_cSliderStateChanged

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButton.setText("Reset");
        
        linear = new LinearEq(a,b,c,d,NofPtls,displayPanel.getWidth());
        for(int j=0;j<1000;j++){
            linear.nextTime();
        }
        timer.start();
        diagramPanel.removeAll();
        diagramPanel.repaint();
    }//GEN-LAST:event_startButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void aTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aTextFieldActionPerformed
        try{
            double value = 100*Double.parseDouble(aTextField.getText());
            if(Math.abs(value)<=200){
                aSlider.setValue((int) (value));
            }else{
                aSlider.setValue(0);
                aTextField.setText("0.00");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            aSlider.setValue(0);
            aTextField.setText("0.00");
        }
        diagramPanel.removeAll();
        diagramPanel.repaint();
    }//GEN-LAST:event_aTextFieldActionPerformed

    private void bTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTextFieldActionPerformed
        try{
            double value = 100*Double.parseDouble(bTextField.getText());
            if(Math.abs(value)<=200){
                bSlider.setValue((int) (value));
            }else{
                bSlider.setValue(0);
                bTextField.setText("0.00");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            bSlider.setValue(0);
            bTextField.setText("0.00");
        }
        diagramPanel.removeAll();
        diagramPanel.repaint();
    }//GEN-LAST:event_bTextFieldActionPerformed

    private void cTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTextFieldActionPerformed
        try{
            double value = 100*Double.parseDouble(cTextField.getText());
            if(Math.abs(value)<=200){
                cSlider.setValue((int) (value));
            }else{
                cSlider.setValue(0);
                cTextField.setText("0.00");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            cSlider.setValue(0);
            cTextField.setText("0.00");
        }
        diagramPanel.removeAll();
        diagramPanel.repaint();
    }//GEN-LAST:event_cTextFieldActionPerformed

    private void dTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dTextFieldActionPerformed
        try{
            double value = 100*Double.parseDouble(dTextField.getText());
            if(Math.abs(value)<=200){
                dSlider.setValue((int) (value));
            }else{
                dSlider.setValue(0);
                dTextField.setText("0.00");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            dSlider.setValue(0);
            dTextField.setText("0.00");
        }
        diagramPanel.removeAll();
        diagramPanel.repaint();
    }//GEN-LAST:event_dTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider aSlider;
    private javax.swing.JTextField aTextField;
    private javax.swing.JSlider bSlider;
    private javax.swing.JTextField bTextField;
    private javax.swing.JSlider cSlider;
    private javax.swing.JTextField cTextField;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JSlider dSlider;
    private javax.swing.JTextField dTextField;
    private javax.swing.JPanel diagramPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables
}

class LinearEq {
       
    double dt;
    int NofPtls,w;
    double a,b,c,d;
    
    double x[];
    double y[];
    double color[];
    
    public LinearEq(double a,double b,double c,double d,int NofPtls,int width){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.NofPtls = NofPtls;

        dt=0.05;
        w=width;
        x= new double[NofPtls];
        y= new double[NofPtls];
        color= new double[NofPtls];
        for(int i=0;i<NofPtls;i++){
             x[i]=(double)w*(Math.random()-0.5);
             y[i]=(double)w*(Math.random()-0.5);
             color[i]=0;
        }
    }
    
    public void nextTime(){
        double xa,ya,dx,dy;
        for(int i=0;i<NofPtls;i++){
            xa=x[i];ya=y[i];
        // RungeKutta Method
            x[i] = x[i] + RKdx(x[i],y[i],dt);
            y[i] = y[i] + RKdy(x[i],y[i],dt);
            
            dx=xa-x[i];dy=ya-y[i];
            color[i]=Math.atan2(dy, dx);
            if(Math.pow(dx*dx+dy*dy,0.5)<2/(double)w){
                x[i]=(double)w*(Math.random()-0.5);
                y[i]=(double)w*(Math.random()-0.5);
            }
            if(Math.abs(x[i])>w/2){
                x[i]=(double)w*(Math.random()-0.5);
                y[i]=(double)w*(Math.random()-0.5);
            }
            if(Math.abs(y[i])>w/2){
                x[i]=(double)w*(Math.random()-0.5);
                y[i]=(double)w*(Math.random()-0.5);
            }

            
        }
   }
    
    double RKdx(double x,double y,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dx(x        ,y);
        k2 = tau * dx(x + k1/2.,y);
        k3 = tau * dx(x + k2/2.,y);
        k4 = tau * dx(x + k3   ,y);
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
    double RKdy(double x,double y,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dy(x,y        );
        k2 = tau * dy(x,y + k1/2.);
        k3 = tau * dy(x,y + k2/2.);
        k4 = tau * dy(x,y + k3   );
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
    
   double dx(double x,double y){
       return a*x+b*y;
       //return -y+a*x*(x*x+y*y);
       //return -x+a*y+x*x*y;
   } 
   double dy(double x,double y){
       return c*x+d*y;
       //return x+a*y*(x*x+y*y);
       //return b-a*y-x*x*y;
   } 
}
