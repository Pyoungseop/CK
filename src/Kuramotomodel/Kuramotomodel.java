/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kuramotomodel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author pyounglous
 */
public class Kuramotomodel extends javax.swing.JApplet {
    int numberOfParticles;
    int naturalFrequencyMode;
    double couplingStrength;
    KuramotoModelSystem kuramotoSystem;
    javax.swing.Timer timer;
    
    /**
     * Initializes the applet Kuramotomodel
     */
    @Override
    public void init() {
         /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Kuramotomodel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kuramotomodel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kuramotomodel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kuramotomodel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //numberOfParticles  = numOfPtlSlider.getValue() ;
        //couplingStrength   = (double) couplingStrSlider.getValue()/10. ;
        
        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents(); 
                    timer = new javax.swing.Timer(1,new aListener());
                    timer.stop();
                    System.out.println(timer.isRunning());
                    unimodalButton.doClick();
                    naturalFrequencyMode = 1;
                    kuramotoSystem = new KuramotoModelSystem(numberOfParticles,couplingStrength,displayPanel.getWidth(),naturalFrequencyMode);
                    
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
                orderParameterPanel.removeAll();
                if(timer.isRunning()){}else{return;}
                displayPanel.repaint();
                orderParameterPanel.repaint();
            
        }
    };
    // Kuramoto model의 각 입자의 반짝이는 모습을 가시적으로 보여
    public class MainDisplay extends javax.swing.JPanel{                
        
        MainDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int radius;
            
            kuramotoSystem.Model_Dynamics();
            for(int i=0;i<numberOfParticles;i++){
                Color color = new Color(255,255, 0,(int)(255/2+Math.sin(kuramotoSystem.theta[i])*255/2));
                g.setColor(color);
                radius = (int) Math.abs(4+Math.sin(kuramotoSystem.theta[i])*4);
                g.fillOval(kuramotoSystem.positionX[i]-radius/2,kuramotoSystem.positionY[i]-radius/2,radius,radius);
            }
        }
    }
    // Kuramoto model의 각 입자의 phase를 보여주는것으로 
    // ordered or disordered를 파악할 수 있음
    public class OrderParameterDisplay extends javax.swing.JPanel{
        private int radius;
        
        OrderParameterDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g)
        {
            double x,y;
            int center=orderParameterPanel.getWidth()/2;
            super.paintComponent(g);
            g.setColor(Color.blue);
            int phaseRadius = (int)(center*2.5/3);
            radius = (int)(center*2.5/30);
            for(int i=0;i<numberOfParticles;i++){        
                x=phaseRadius*Math.cos(kuramotoSystem.theta[i]);
                y=-phaseRadius*Math.sin(kuramotoSystem.theta[i]);
                g.drawOval((int)x+center-radius/2,(int)y+center-radius/2,radius,radius);
            }
            g.setColor(Color.red);
            g.drawOval(center-phaseRadius,center-phaseRadius,2*phaseRadius,2*phaseRadius);
            
            x=phaseRadius*kuramotoSystem.orderParameter*Math.cos(kuramotoSystem.psi);
            y=-phaseRadius*kuramotoSystem.orderParameter*Math.sin(kuramotoSystem.psi);
            g.fillOval((int)x+center-radius/2,(int)y+center-radius/2,radius,radius);
            g.drawLine(center, center, (int)x+center, (int)y+center);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        displayPanel = new MainDisplay();
        jPanel2 = new javax.swing.JPanel();
        orderParameterPanel = new OrderParameterDisplay();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numOfPtlSlider = new javax.swing.JSlider();
        numOfPtl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        couplingStrSlider = new javax.swing.JSlider();
        couplingStr = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        PauseButton = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        unimodalButton = new javax.swing.JRadioButton();
        bimodalButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();

        jInternalFrame1.setTitle("Kuramoto model");
        jInternalFrame1.setMaximumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setName(""); // NOI18N
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setVisible(true);

        displayPanel.setBackground(new java.awt.Color(0, 0, 0));
        displayPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
        );

        orderParameterPanel.setBackground(new java.awt.Color(255, 255, 255));
        orderParameterPanel.setMaximumSize(new java.awt.Dimension(306, 306));
        orderParameterPanel.setMinimumSize(new java.awt.Dimension(306, 306));
        orderParameterPanel.setPreferredSize(new java.awt.Dimension(306, 306));

        javax.swing.GroupLayout orderParameterPanelLayout = new javax.swing.GroupLayout(orderParameterPanel);
        orderParameterPanel.setLayout(orderParameterPanelLayout);
        orderParameterPanelLayout.setHorizontalGroup(
            orderParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        orderParameterPanelLayout.setVerticalGroup(
            orderParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setText("Number of Particles");

        numOfPtlSlider.setMaximum(1000);
        numOfPtlSlider.setMinimum(1);
        numOfPtlSlider.setValue(500);
        numOfPtlSlider.setMaximumSize(new java.awt.Dimension(306, 54));
        numOfPtlSlider.setMinimumSize(new java.awt.Dimension(306, 54));
        numOfPtlSlider.setPreferredSize(new java.awt.Dimension(306, 54));
        numOfPtlSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numOfPtlSliderStateChanged(evt);
            }
        });
        numOfPtlSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                numOfPtlSliderPropertyChange(evt);
            }
        });

        numOfPtl.setText("500");
        numOfPtl.setPreferredSize(new java.awt.Dimension(40, 27));
        numOfPtl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numOfPtlActionPerformed(evt);
            }
        });

        jLabel2.setText("Coupling Strength");

        couplingStrSlider.setMaximumSize(new java.awt.Dimension(306, 54));
        couplingStrSlider.setMinimumSize(new java.awt.Dimension(306, 54));
        couplingStrSlider.setPreferredSize(new java.awt.Dimension(306, 54));
        couplingStrSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                couplingStrSliderStateChanged(evt);
            }
        });
        couplingStrSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                couplingStrSliderPropertyChange(evt);
            }
        });

        couplingStr.setText("5");
        couplingStr.setPreferredSize(new java.awt.Dimension(40, 27));
        couplingStr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                couplingStrActionPerformed(evt);
            }
        });

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

        PauseButton.setText("Run");
        PauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PauseButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Natural frequency");

        buttonGroup1.add(unimodalButton);
        unimodalButton.setText("Unimodal distribution");
        unimodalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unimodalButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(bimodalButton);
        bimodalButton.setText("Bimodal distribution");
        bimodalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bimodalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(116, 116, 116)
                                .addComponent(numOfPtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(127, 127, 127)
                                .addComponent(couplingStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(numOfPtlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(couplingStrSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addComponent(unimodalButton)
                            .addComponent(bimodalButton))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unimodalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bimodalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(numOfPtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numOfPtlSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(couplingStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(couplingStrSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(exitButton)
                    .addComponent(PauseButton)))
        );

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Kuramotomodel/Kuramotomodel.png"))); // NOI18N

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(orderParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))
                        .addGap(3, 3, 3)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        try {
            jInternalFrame1.setMaximum(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
    }// </editor-fold>//GEN-END:initComponents

    private void numOfPtlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numOfPtlActionPerformed
        // text box에서 바로 입력을 받았을때 최대 최소에 대한 제한조건을 준다.
        try{ 
            int value = Integer.parseInt(numOfPtl.getText());
            if(value<=1000){
                numOfPtlSlider.setValue(value);
            }else{
                numOfPtlSlider.setValue(1000);
                numOfPtl.setText("1000");
            }
      }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
                numOfPtlSlider.setValue(500);
                numOfPtl.setText("500");
        }
    }//GEN-LAST:event_numOfPtlActionPerformed

    private void numOfPtlSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numOfPtlSliderStateChanged
        // Slider로 부터 number of particles의 text box의 text를 재설정
        numOfPtl.setText(""+numOfPtlSlider.getValue());
    }//GEN-LAST:event_numOfPtlSliderStateChanged

    private void couplingStrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_couplingStrActionPerformed
        // text box에서 바로 입력을 받았을때 최대 최소에 대한 제한조건을 준다.
        try{
            double value = 10*Double.parseDouble(couplingStr.getText());
            if(value<=100){
                couplingStrSlider.setValue((int) (value));
            }else{
                couplingStrSlider.setValue(100);
                couplingStr.setText("10.0");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            couplingStrSlider.setValue(50);
            couplingStr.setText("5.0");
        }
    }//GEN-LAST:event_couplingStrActionPerformed

    private void couplingStrSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_couplingStrSliderStateChanged
        // Slider로 부터 couplingStrength의 text box의 text를 재설정
        couplingStr.setText(""+(double) couplingStrSlider.getValue()/10.);
    }//GEN-LAST:event_couplingStrSliderStateChanged

    private void PauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PauseButtonActionPerformed
        if(PauseButton.isSelected()){
            PauseButton.setText("Pause");
            timer.start();
        }else{
            PauseButton.setText("Run");
            timer.stop();
        }
    }//GEN-LAST:event_PauseButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        startButton.setText("Restart");
        // Slider로 부터 couplingStrength와 numberOfParticles를 받는다.
        numberOfParticles = numOfPtlSlider.getValue();
        couplingStrength =(double) couplingStrSlider.getValue()/10.;
        
        // Kuramoto Model를 다시 선언한다.
        kuramotoSystem = new KuramotoModelSystem(numberOfParticles,couplingStrength,displayPanel.getWidth(),naturalFrequencyMode);
        timer.start();
        if(PauseButton.getText()=="Run")PauseButton.doClick();
    }//GEN-LAST:event_startButtonActionPerformed

    private void numOfPtlSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_numOfPtlSliderPropertyChange
     /*  String property = evt.getPropertyName();
       if ( "value".equals(property)) {
           numberOfParticles = (int) evt.getNewValue();
       }        
        */
    }//GEN-LAST:event_numOfPtlSliderPropertyChange

    private void couplingStrSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_couplingStrSliderPropertyChange
        // Slider로 부터 couplingStrength를 받는다.
        couplingStrength = (double) couplingStrSlider.getValue()/10.;
    }//GEN-LAST:event_couplingStrSliderPropertyChange

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void unimodalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unimodalButtonActionPerformed
        naturalFrequencyMode = 1;
    }//GEN-LAST:event_unimodalButtonActionPerformed

    private void bimodalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bimodalButtonActionPerformed
        naturalFrequencyMode = 2;
    }//GEN-LAST:event_bimodalButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton PauseButton;
    private javax.swing.JRadioButton bimodalButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField couplingStr;
    private javax.swing.JSlider couplingStrSlider;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField numOfPtl;
    private javax.swing.JSlider numOfPtlSlider;
    private javax.swing.JPanel orderParameterPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JRadioButton unimodalButton;
    // End of variables declaration//GEN-END:variables
}

class KuramotoModelSystem {
   static double pi=Math.atan(1.)*4;
   double couplingStrength;
   double orderParameter,psi;
   int positionX[],positionY[];
   double theta[],omega[] ;
      
   int    numberOfParticles ;
       
// model definition
   public KuramotoModelSystem(int NofPtl,double couplingK,int width,int NFmode) {
       
       double rand = 0;
      numberOfParticles = NofPtl;
      couplingStrength =  couplingK;
      
      positionX = new int[numberOfParticles] ;
      positionY = new int[numberOfParticles] ;
      theta     = new double[numberOfParticles] ;      
      omega     = new double[numberOfParticles] ;
      
      // 모든 입자의 위치와 고유진동수, 현재 phase를 초기화한다.
      for(int i=0;i<NofPtl;i++) { 
         positionX[i] =(int)(Math.random()*width);
         positionY[i] =(int)(Math.random()*width);
         if(NFmode==1){ 
             rand=rand_normal()+1;
         }else{
             rand=rand_normal()+1;
             if(Math.random()>0.5){
                 rand=rand_normal()+2;
             }
         }
         omega[i]     = rand;
         theta[i]     = Math.random()*2*pi;      
      }
      double sinPsi=0,cosPsi=0;
      
      // order parameter를 구하기위해 모든 입자의 vector를 합한다.
      for(int i=0;i<numberOfParticles;i++){
        sinPsi += Math.sin(theta[i])/(double) numberOfParticles;
        cosPsi += Math.cos(theta[i])/(double) numberOfParticles;
      }
      
      // order parameter의 크기
      orderParameter = Math.sqrt((sinPsi*sinPsi + cosPsi * cosPsi));
      
      // order parameter의 phase
      psi =Math.atan2(sinPsi, cosPsi);
      
}
       
// time evolution 
   void Model_Dynamics() {
      int randomNumber,i;
      double tr,wr,sinPsi,cosPsi;
      
      double tau=0.01;
      
      for(i=0;i<numberOfParticles;i++){
        randomNumber = i;
        wr = omega[randomNumber];
        tr = theta[randomNumber];
        
        //theta[randomNumber] += RungeKutta(wr,tr,tau); // RungeKutta Method
        theta[randomNumber] += tau*dtheta(wr,tr); // Euler's Method

        // theta의 범위를 -pi에서 +pi로 재설정한다.
        while(theta[randomNumber]>1*pi) theta[randomNumber] -= 2.*pi;
        while(theta[randomNumber]<-1*pi) theta[randomNumber] += 2.*pi;
      }
       
      cosPsi = 0; sinPsi = 0;
      // order parameter를 구하기위해 모든 입자의 vector를 합한다.
      for(i=0;i<numberOfParticles;i++){
        sinPsi += Math.sin(theta[i])/(double) numberOfParticles;
        cosPsi += Math.cos(theta[i])/(double) numberOfParticles;
      }
      // order parameter의 크기
      orderParameter = Math.sqrt((sinPsi*sinPsi + cosPsi * cosPsi));
      
      // order parameter의 phase
      psi =Math.atan2(sinPsi, cosPsi);      
      
   }
   
   double dtheta(double w,double theta){
       return w + orderParameter*couplingStrength*Math.sin(psi - theta);
   }
   
   double RungeKutta(double wr,double tr,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dtheta(wr,tr);
        k2 = tau * dtheta(wr,tr + k1/2.);
        k3 = tau * dtheta(wr,tr + k2/2.);
        k4 = tau * dtheta(wr,tr + k3);
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
   double rand_normal()
   {
        double u,v,s ;
        u = 2.*Math.random()-1. ;
        v = 2.*Math.random()-1. ;
        s = u*u+v*v ;
        while(s >= 1.0) {
                u = 2.*Math.random()-1. ;
                v = 2.*Math.random()-1. ;
                s = u*u+v*v ;
        }
        return(u*Math.sqrt(-2.*Math.log(s)/s)) ;
   }
}