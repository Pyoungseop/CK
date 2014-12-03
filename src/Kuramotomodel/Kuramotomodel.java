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
/**
 *
 * @author pyounglous
 */
public class Kuramotomodel extends javax.swing.JApplet {
    int numberOfParticles;
    double couplingStrength;
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
        numberOfParticles = 1;
        couplingStrength = 1;
        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    javax.swing.Timer timer = new javax.swing.Timer(100,new aListener());
                    timer.start();                    
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
    public void stop()
    {
        
    }
    public class aListener implements ActionListener 
    {
       
        public void actionPerformed(ActionEvent e) {
            System.out.println("On Timer!!");
            displayPanel.removeAll();
            displayPanel.repaint();
            
            orderParameterPanel.removeAll();
            orderParameterPanel.repaint();
        }
    };
    
    public class MainDisplay extends javax.swing.JPanel{                
        private int radius,x[],y[];
        private double theta[],omega[];
        MainDisplay(){
            super();
            radius = 0 ;
            theta= new double[1000];
            omega= new double[1000];
            x= new int[1000];
            y= new int[1000];
            for(int i=0;i<1000;i++){
                theta[i] = Math.random();
                omega[i] = Math.random();
                x[i] = (int)(Math.random()*456);
                y[i] = (int)(Math.random()*456);
            }
        }
        
        public void paintComponent(Graphics g)
        {   
            final int STEP = 5;
            super.paintComponent(g);
            
            for(int i=0;i<numberOfParticles;i++){
                omega[i] += theta[i];
                Color color = new Color(255,255, 0,(int)(255/2+Math.sin(omega[i])*255/2));
                g.setColor(color);
                radius = (int) Math.abs(10+Math.sin(omega[i])*10);
                g.fillOval(x[i]-radius/2,y[i]-radius/2,radius,radius);
            }
        }
    }
    public class OrderParameterDisplay extends javax.swing.JPanel{
        private int radius;
        private double[] theta,omega;
        OrderParameterDisplay(){
            super();
            radius = 0;
            theta= new double[1000];
            omega= new double[1000];
            for(int i=0;i<1000;i++){
                theta[i] = Math.random();
                omega[i] = Math.random();
            }
            
        }
        
        public void paintComponent(Graphics g)
        {
            double x,y;
            super.paintComponent(g);
            g.setColor(Color.blue);
            radius = 10;
            
            for(int i=0;i<numberOfParticles;i++){
                omega[i] += theta[i];
                x=100*Math.cos(omega[i]);
                y=-100*Math.sin(omega[i]);
                g.drawOval((int)x+148,(int)y+148,radius,radius);
            }
            g.setColor(Color.red);
            g.drawOval(53,53,200,200);
            
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
        jPanel2 = new javax.swing.JPanel();
        orderParameterPanel = new OrderParameterDisplay();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numOfPtlSlider = new javax.swing.JSlider();
        numOfPtl = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        couplingStrSlider = new javax.swing.JSlider();
        couplingStr = new javax.swing.JTextField();
        startToPauseButton = new javax.swing.JToggleButton();
        resetButton = new javax.swing.JButton();

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

        orderParameterPanel.setBackground(new java.awt.Color(255, 255, 255));
        orderParameterPanel.setPreferredSize(new java.awt.Dimension(306, 306));

        javax.swing.GroupLayout orderParameterPanelLayout = new javax.swing.GroupLayout(orderParameterPanel);
        orderParameterPanel.setLayout(orderParameterPanelLayout);
        orderParameterPanelLayout.setHorizontalGroup(
            orderParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        orderParameterPanelLayout.setVerticalGroup(
            orderParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        jLabel1.setText("Number of Particles");

        numOfPtlSlider.setMaximum(1000);
        numOfPtlSlider.setMinimum(1);
        numOfPtlSlider.setValue(500);
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

        couplingStr.setText("50");
        couplingStr.setPreferredSize(new java.awt.Dimension(40, 27));
        couplingStr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                couplingStrActionPerformed(evt);
            }
        });

        startToPauseButton.setText("Start");
        startToPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startToPauseButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numOfPtlSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numOfPtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(couplingStrSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(couplingStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startToPauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(resetButton)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {resetButton, startToPauseButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startToPauseButton)
                    .addComponent(resetButton)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orderParameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(orderParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        numOfPtl.setText(""+numOfPtlSlider.getValue());
        numberOfParticles = numOfPtlSlider.getValue();
        //displayPanel.removeAll();
        //displayPanel.repaint();
    }//GEN-LAST:event_numOfPtlSliderStateChanged

    private void couplingStrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_couplingStrActionPerformed
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
        couplingStr.setText(""+(double) couplingStrSlider.getValue()/10.);
    }//GEN-LAST:event_couplingStrSliderStateChanged

    private void startToPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startToPauseButtonActionPerformed
        if(startToPauseButton.isSelected()){
            startToPauseButton.setText("Pause");
        }else{
            startToPauseButton.setText("Start");
        }
    }//GEN-LAST:event_startToPauseButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetButtonActionPerformed

    private void numOfPtlSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_numOfPtlSliderPropertyChange
     /*  String property = evt.getPropertyName();
       if ( "value".equals(property)) {
           numberOfParticles = (int) evt.getNewValue();
       }        
        */
    }//GEN-LAST:event_numOfPtlSliderPropertyChange

    private void couplingStrSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_couplingStrSliderPropertyChange
        couplingStrength = (double) couplingStrSlider.getValue()/10.;
    }//GEN-LAST:event_couplingStrSliderPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField couplingStr;
    private javax.swing.JSlider couplingStrSlider;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField numOfPtl;
    private javax.swing.JSlider numOfPtlSlider;
    private javax.swing.JPanel orderParameterPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JToggleButton startToPauseButton;
    // End of variables declaration//GEN-END:variables
}

/*

class KuramotoModelSystem {
   static double pi;
   double couplingStrength;
   double orderParameter,psi;
   double positionX[],positionY[];
   double theta[],newTheta[],omega[] ;
      
   int    numberOfParticles ;
       
// model definition
   public KuramotoModelSystem(int NofPtl,double couplingK) {
      pi = Math.atan(1.)*4;
      numberOfParticles = NofPtl;
      couplingStrength =  couplingK;
      
      positionX = new double[numberOfParticles] ;
      positionY = new double[numberOfParticles] ;
      theta     = new double[numberOfParticles] ;
      newTheta     = new double[numberOfParticles] ;
      omega     = new double[numberOfParticles] ;
      
      psi =0;
      orderParameter =0;
      
      for(int i=0;i<NofPtl;i++) { 
         positionX[i] = Math.random();
         positionY[i] = Math.random();
         omega[i]     = Math.random();
         theta[i] = Math.random();
         psi += theta[i]/(double)numberOfParticles;
      }
      double sinPsi=0,cosPsi=0;
      
      for(int i=0;i<numberOfParticles;i++){
        sinPsi += Math.sin(theta[i] - psi);
        cosPsi += Math.cos(theta[i] - psi);
      }
      orderParameter = Math.sqrt((sinPsi*sinPsi + cosPsi * cosPsi)) / (double) (numberOfParticles);
}
       
// time evolution 
   void Model_Dynamics(double t) {
      int randomNumber,i;
      double tr,wr,sinPsi,cosPsi;
      double k1,k2,k3,k4;
      double tau=0.01;
      
      for(i=0;i<numberOfParticles;i++){
        randomNumber = (int) Math.random()*numberOfParticles;
        wr = omega[randomNumber];
        tr = theta[randomNumber];
        k1 = tau * dtheta(wr,tr);
        k2 = tau * dtheta(wr,tr + k1/2.);
        k3 = tau * dtheta(wr,tr + k2/2.);
        k4 = tau * dtheta(wr,tr + k3);
        theta[randomNumber] += (k1+2*k2+2*k3+k4)/6.;

        while(theta[randomNumber]>1*pi) theta[randomNumber] -= 2.*pi;
        while(theta[randomNumber]<-1*pi) theta[randomNumber] += 2.*pi;
        t = t + tau;
        cosPsi = 0; sinPsi = 0; psi=0;
          
        for(i=0;i<numberOfParticles;i++){
            psi += theta[i]/numberOfParticles;
        }
        for(i=0;i<numberOfParticles;i++){
              sinPsi += Math.sin(theta[i] - psi);
              cosPsi += Math.cos(theta[i] - psi);
        }
        orderParameter = Math.sqrt((sinPsi*sinPsi + cosPsi * cosPsi)) / (double) (numberOfParticles);

        while(psi>1*pi) psi -= 2.*pi;
        while(psi<-1*pi) psi += 2.*pi;
      }
   }
   
   double dtheta(double w,double theta){
       return w + orderParameter*couplingStrength*Math.sin(psi - theta);
   }

   void paint(Graphics g) {
      int i,x,y ;

//      g.setColor(Color.white) ;
//      g.fillRect(0,0,dimX,dimY) ;

     for(i=0;i<NofPtl;i++) {
         if(opinion[i]==1) g.setColor(Color.red) ;
         else g.setColor(Color.blue) ;
         x = (int)(positionX[i]/Width*(double)dimX)%dimX ;
         y = (int)(positionY[i]/Width*(double)dimX)%dimX ;
         g.fillRect(x,y,2,2) ;
       
      }
   }
   void clear(Graphics g) {
      g.setColor(Color.white) ;
      g.fillRect(0,0,dimX,dimY) ;
   }
}
*/