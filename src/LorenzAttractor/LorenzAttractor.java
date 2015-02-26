/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LorenzAttractor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 *
 * @author pyounglous
 */
public class LorenzAttractor extends javax.swing.JApplet {
    double s,r,b;
    double xymax,yzmax,xzmax;
    double xmid,ymid;
    double x[],y[];
    int x0,y0,z0;
    int Step;
    LorenzEq lorenz;
    Draw Display;
    MaxMinMid range;
    /**
     * Initializes the applet LorenzAtrractor
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
            java.util.logging.Logger.getLogger(LorenzAttractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LorenzAttractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LorenzAttractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LorenzAttractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    Step=StepSlider.getValue();
                    Display = new Draw();
                    range = new MaxMinMid();
                    Display.draw();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public class Draw{
        public void draw(){
           x= new double[Step];
           y= new double[Step];
           s=(double) sigmaSlider.getValue()/100.;   b=(double) bSlider.getValue()/100.;  r=(double) rSlider.getValue()/100.;
           sLabel.setText("     "+(double) sigmaSlider.getValue()/100.);  rLabel.setText("     "+(double) rSlider.getValue()/100.); bLabel.setText("     "+(double) bSlider.getValue()/100.);
           x0=x0Slider.getValue();  y0=y0Slider.getValue(); z0=z0Slider.getValue();
           x0Label.setText("     "+x0Slider.getValue()); y0Label.setText("     "+y0Slider.getValue()); z0Label.setText("     "+z0Slider.getValue());
           
           lorenz = new LorenzEq(s,r,b,Step,x0,y0,z0);
           lorenz.TimeEvolution(xymax,yzmax,xzmax);
           MainDisplayTab.remove(rootPane); MainDisplayTab.remove(rootPane);
           MainDisplayTab.repaint(); TimeSeriesDisplayTab.repaint();
        }
    }

    public class MaxMinMid{
        public void MinToMax(){
          double xmax=x[0],ymax=y[0];
          double xmin=x[0],ymin=y[0];
          double max;
          
          for(int i=0;i<Step-1;i++){
            if(xmax < x[i+1]) xmax=x[i+1]; if(xmin > x[i+1]) xmin=x[i+1];
            if(ymax < y[i+1]) ymax=y[i+1]; if(ymin > y[i+1]) ymin=y[i+1];
          }
      
          if(xmax-xmin > ymax-ymin){xymax = xmax-xmin;} else{xymax = ymax-ymin;}
          xmid=(xmax+xmin)/2.;ymid=(ymax+ymin)/2.;
        }
        
        public void MinToMax(double []y){
          double ymax=y[0],ymin=y[0];
          double max;
          
          for(int i=0;i<Step-1;i++){
            if(ymax < y[i+1]) ymax=y[i+1]; if(ymin > y[i+1]) ymin=y[i+1];
          }
          xymax = ymax-ymin;
          ymid=(ymax+ymin)/2.;
        }
    }

    
    public class MainDisplay extends javax.swing.JPanel{                
        
        MainDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String xl="",yl="";
            int radius=2,w;
            int x1,y1,x2,y2;
            Color color;
            w=getWidth();
            
            switch(MainDisplayTab.getSelectedIndex()){
                    case 0 :
                        x=lorenz.x;
                        y=lorenz.y;
                        xl="x";
                        yl="y";
                        break;
                    case 1 :
                        x=lorenz.y;
                        y=lorenz.z;
                        xl="y";
                        yl="z";
                        break;
                    case 2 :
                        x=lorenz.x;
                        y=lorenz.z;
                        xl="x";
                        yl="z";
                        break;
            }
            
            g.drawLine((int)(0.85*w),(int)(0.95*w),(int)(0.85*w),(int)(0.85*w));
            g.drawLine((int)(0.85*w),(int)(0.95*w),(int)(0.95*w),(int)(0.95*w));
            g.drawString(xl,(int)(0.90*getWidth()),(int)(0.965*getHeight()));
            g.drawString(yl,(int)(0.81*getWidth()),(int)(0.90*getHeight()));
            
            range.MinToMax();
            
            for(int i=0;i<Step-2;i++){
                color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                g.setColor(color);
                x1=(int)((w-10)*(  x[i]  -xmid)/xymax) -radius/2+w/2;
                y1=(int)((w-10)*(-(y[i]  -ymid)/xymax))-radius/2+w/2;
                x2=(int)((w-10)*(  x[i+1]-xmid)/xymax) -radius/2+w/2;
                y2=(int)((w-10)*(-(y[i+1]-ymid)/xymax))-radius/2+w/2;
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
    public class TimeSeriesDisplay extends javax.swing.JPanel{                
        
        TimeSeriesDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            String yl="";
            int radius=2,w,h;
            int x1,y1,x2,y2;
            Color color;
            w=getWidth();
            h=getHeight();
            
            switch(TimeSeriesDisplayTab.getSelectedIndex()){
                    case 0 :
                        y=lorenz.z;
                        yl="z";
                        break;
                    case 1 :
                        y=lorenz.x;
                        yl="x";
                        break;
                    case 2 :
                        y=lorenz.y;
                        yl="y";
                        break;
            }            
            range.MinToMax(y);
            
            g.drawLine((int)(0.02*w),(int)(0.95*h),(int)(0.02*w),(int)(0.05*h));
            g.drawLine((int)(0.02*w),(int)(0.8*h*(ymid/xymax)+h/2-0.05*h),(int)(0.02*w),(int)(0.05*h));
          //g.drawLine((int)(0.02*w),(int)(0.95*h),(int)(0.98*w),(int)(0.95*h));
            g.drawLine((int)(0.02*w),(int)(0.8*h*(ymid/xymax)+h/2-0.05*h),(int)(0.98*w),(int)(0.8*h*(ymid/xymax)+h/2-0.05*h));
            g.drawString("t",(int)(0.95*getWidth()),(int)(0.93*getHeight()));
            g.drawString(yl,(int)(0.01*getWidth()),(int)(0.1*getHeight()));
                        
            for(int i=1;i<Step-2;i++){
                color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                g.setColor(color);
                x1=(int)(0.9*w*i/Step)  -radius/2 + (int)(0.05*w);
                y1=(int)(0.8*h*(-(y[i]  -ymid)/xymax)  -radius/2+h/2-0.05*h);
                x2=(int)(0.9*w*(i+1)/Step)-radius/2+(int)(0.05*w);
                y2=(int)(0.8*h*(-(y[i+1]  -ymid)/xymax)-radius/2+h/2-0.05*h);
                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
         
    public class LorenzMapDisplay extends javax.swing.JPanel{                
        private Image image;
        LorenzMapDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int radius=6,w,check;
            double zn,zn1,zmax,zmin;
            int start=1;
            Color color;
            Ellipse2D.Double object;
            Graphics2D g2 = (Graphics2D)g;
            
            w=getWidth();
            x=lorenz.z;
            zmax=0;zmin=0;zn=0;
            check=0;
            for(int i=1;i<Step-2;i++){
                if(x[i-1]<x[i] && x[i] > x[i+1]){
                    if(check == 0){
                        zmin = x[i];
                        zmax = x[i];
                        check++;
                        start=i;
                    }
                    else{
                        if(zmin > x[i]) zmin = x[i];
                        if(zmax < x[i]) zmax = x[i];
                    }
                }
            }
  
            zn=0.8*w*(x[start]-zmin)/(zmax-zmin);
            
            for(int i=start+1;i<Step-2;i++){
                if(x[i-1]<x[i] && x[i] > x[i+1]){
                    color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                    g2.setColor(color);
                    
                    zn1=0.8*w*(x[i]-zmin)/(zmax-zmin);
                    
                    object = new Ellipse2D.Double(zn+0.1*w-radius/2,-zn1+0.9*w-radius/2,radius,radius);
                    g2.fill(object);
                    zn=zn1;
                }
            }
            g.setColor(Color.RED);
            g.drawLine((int)(0.1*w),(int)(0.9*w),(int)(0.9*w),(int)(0.1*w));
            
            g.setColor(Color.BLACK);
            g.drawLine((int)(0.1*w),(int)(0.92*w),(int)(0.1*w),(int)(0.08*w));
            g.drawLine((int)(0.08*w),(int)(0.9*w),(int)(0.92*w),(int)(0.9*w));

            znLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAtrractor/zn.gif"))); // NOI18N
            zn1Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAtrractor/zn1.png"))); // NOI18N
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        MainDisplayTab = new javax.swing.JTabbedPane();
        xyDisplay = new MainDisplay();
        yzDisplay = new MainDisplay();
        xzDisplay = new MainDisplay();
        Display3D = new LorenzMapDisplay();
        znLabel = new javax.swing.JLabel();
        zn1Label = new javax.swing.JLabel();
        TimeSeriesDisplayTab = new javax.swing.JTabbedPane();
        ztDisplay = new TimeSeriesDisplay();
        xtDisplay = new TimeSeriesDisplay();
        ytDisplay = new TimeSeriesDisplay();
        jPanel1 = new javax.swing.JPanel();
        x0Slider = new javax.swing.JSlider();
        y0Slider = new javax.swing.JSlider();
        z0Slider = new javax.swing.JSlider();
        sigmaSlider = new javax.swing.JSlider();
        rSlider = new javax.swing.JSlider();
        bSlider = new javax.swing.JSlider();
        x0Label = new javax.swing.JLabel();
        y0Label = new javax.swing.JLabel();
        z0Label = new javax.swing.JLabel();
        sLabel = new javax.swing.JLabel();
        rLabel = new javax.swing.JLabel();
        bLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        StepSlider = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        TmaxLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setMaximumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setNormalBounds(new java.awt.Rectangle(0, 0, 800, 600));
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setVisible(true);

        MainDisplayTab.setMaximumSize(new java.awt.Dimension(358, 387));
        MainDisplayTab.setMinimumSize(new java.awt.Dimension(358, 387));
        MainDisplayTab.setPreferredSize(new java.awt.Dimension(358, 387));

        xyDisplay.setBackground(new java.awt.Color(254, 254, 254));
        xyDisplay.setMaximumSize(new java.awt.Dimension(350, 350));
        xyDisplay.setMinimumSize(new java.awt.Dimension(350, 350));
        xyDisplay.setName(""); // NOI18N
        xyDisplay.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout xyDisplayLayout = new javax.swing.GroupLayout(xyDisplay);
        xyDisplay.setLayout(xyDisplayLayout);
        xyDisplayLayout.setHorizontalGroup(
            xyDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        xyDisplayLayout.setVerticalGroup(
            xyDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("x-y Plane", xyDisplay);

        yzDisplay.setBackground(new java.awt.Color(254, 254, 254));
        yzDisplay.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout yzDisplayLayout = new javax.swing.GroupLayout(yzDisplay);
        yzDisplay.setLayout(yzDisplayLayout);
        yzDisplayLayout.setHorizontalGroup(
            yzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        yzDisplayLayout.setVerticalGroup(
            yzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("y-z Plane", yzDisplay);

        xzDisplay.setBackground(new java.awt.Color(247, 246, 246));

        javax.swing.GroupLayout xzDisplayLayout = new javax.swing.GroupLayout(xzDisplay);
        xzDisplay.setLayout(xzDisplayLayout);
        xzDisplayLayout.setHorizontalGroup(
            xzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );
        xzDisplayLayout.setVerticalGroup(
            xzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("x-z Plane", xzDisplay);

        Display3D.setBackground(new java.awt.Color(254, 254, 254));
        Display3D.setPreferredSize(new java.awt.Dimension(350, 350));

        znLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        znLabel.setText("               ");

        zn1Label.setMaximumSize(new java.awt.Dimension(17, 45));
        zn1Label.setMinimumSize(new java.awt.Dimension(17, 45));
        zn1Label.setPreferredSize(new java.awt.Dimension(17, 45));

        javax.swing.GroupLayout Display3DLayout = new javax.swing.GroupLayout(Display3D);
        Display3D.setLayout(Display3DLayout);
        Display3DLayout.setHorizontalGroup(
            Display3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Display3DLayout.createSequentialGroup()
                .addContainerGap(175, Short.MAX_VALUE)
                .addComponent(znLabel)
                .addGap(124, 124, 124))
            .addGroup(Display3DLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(zn1Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Display3DLayout.setVerticalGroup(
            Display3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Display3DLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(zn1Label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(znLabel)
                .addGap(22, 22, 22))
        );

        MainDisplayTab.addTab("Lorenz Map", Display3D);

        TimeSeriesDisplayTab.setMaximumSize(new java.awt.Dimension(358, 387));

        ztDisplay.setBackground(new java.awt.Color(254, 254, 254));
        ztDisplay.setMaximumSize(new java.awt.Dimension(350, 350));
        ztDisplay.setMinimumSize(new java.awt.Dimension(350, 350));
        ztDisplay.setName(""); // NOI18N

        javax.swing.GroupLayout ztDisplayLayout = new javax.swing.GroupLayout(ztDisplay);
        ztDisplay.setLayout(ztDisplayLayout);
        ztDisplayLayout.setHorizontalGroup(
            ztDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        ztDisplayLayout.setVerticalGroup(
            ztDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        TimeSeriesDisplayTab.addTab("z vs t Graph", ztDisplay);

        xtDisplay.setBackground(new java.awt.Color(254, 254, 254));
        xtDisplay.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout xtDisplayLayout = new javax.swing.GroupLayout(xtDisplay);
        xtDisplay.setLayout(xtDisplayLayout);
        xtDisplayLayout.setHorizontalGroup(
            xtDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        xtDisplayLayout.setVerticalGroup(
            xtDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        TimeSeriesDisplayTab.addTab("x vs t Graph", xtDisplay);

        ytDisplay.setBackground(new java.awt.Color(247, 246, 246));

        javax.swing.GroupLayout ytDisplayLayout = new javax.swing.GroupLayout(ytDisplay);
        ytDisplay.setLayout(ytDisplayLayout);
        ytDisplayLayout.setHorizontalGroup(
            ytDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
        );
        ytDisplayLayout.setVerticalGroup(
            ytDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        TimeSeriesDisplayTab.addTab("y vs t Graph", ytDisplay);

        jPanel1.setMaximumSize(new java.awt.Dimension(405, 405));
        jPanel1.setMinimumSize(new java.awt.Dimension(405, 405));

        x0Slider.setValue(0);
        x0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        x0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                x0SliderStateChanged(evt);
            }
        });

        y0Slider.setValue(1);
        y0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        y0Slider.setVerifyInputWhenFocusTarget(false);
        y0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                y0SliderStateChanged(evt);
            }
        });

        z0Slider.setValue(0);
        z0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        z0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                z0SliderStateChanged(evt);
            }
        });

        sigmaSlider.setMaximum(10000);
        sigmaSlider.setMinimum(1);
        sigmaSlider.setValue(1000);
        sigmaSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        sigmaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sigmaSliderStateChanged(evt);
            }
        });

        rSlider.setMaximum(7426);
        rSlider.setMinimum(1);
        rSlider.setValue(2800);
        rSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        rSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rSliderStateChanged(evt);
            }
        });

        bSlider.setMaximum(899);
        bSlider.setMinimum(1);
        bSlider.setValue(267);
        bSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        bSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bSliderStateChanged(evt);
            }
        });

        x0Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/x0.gif"))); // NOI18N
        x0Label.setText("vx0");

        y0Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/y0.gif"))); // NOI18N
        y0Label.setText("vy0");

        z0Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/z0.gif"))); // NOI18N
        z0Label.setText("vz0");

        sLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/s.gif"))); // NOI18N
        sLabel.setText("vs");

        rLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/r.gif"))); // NOI18N
        rLabel.setText("vr");

        bLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/b.gif"))); // NOI18N
        bLabel.setText("vb");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LorenzAttractor/LorenzEq.gif"))); // NOI18N

        StepSlider.setMaximum(100000);
        StepSlider.setMinimum(100);
        StepSlider.setValue(50000);
        StepSlider.setPreferredSize(new java.awt.Dimension(216, 54));
        StepSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                StepSliderStateChanged(evt);
            }
        });

        jLabel8.setText("Tmax");

        TmaxLabel.setText("vtmax");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TmaxLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(StepSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(y0Label)
                                            .addComponent(y0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rLabel)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(x0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(x0Label)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(sLabel)
                                            .addComponent(sigmaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(z0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(z0Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bLabel)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel8)))
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bSlider, rSlider, sigmaSlider, x0Slider, y0Slider, z0Slider});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sLabel)
                        .addGap(0, 0, 0)
                        .addComponent(sigmaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(x0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(x0Label))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rLabel)
                                .addGap(0, 0, 0)
                                .addComponent(rSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(y0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(y0Label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(z0Label)
                        .addGap(0, 0, 0)
                        .addComponent(z0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bLabel)
                        .addGap(0, 0, 0)
                        .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TmaxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StepSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bSlider, rSlider, sigmaSlider, x0Slider, y0Slider, z0Slider});

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimeSeriesDisplayTab, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(MainDisplayTab, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainDisplayTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TimeSeriesDisplayTab, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void rSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rSliderStateChanged
        Display.draw();
    }//GEN-LAST:event_rSliderStateChanged

    private void sigmaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sigmaSliderStateChanged
        int max;
        max = (int)(s*(s+b+3)/(s-b-1)*300);
        if(max > 59000) max = 59000;
        rSlider.setMaximum(max);
        rSlider.setMinimum(100);
        bSlider.setMaximum((int)(s*100)-101);
        bSlider.setMinimum(1);
        Display.draw();
    }//GEN-LAST:event_sigmaSliderStateChanged

    private void bSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bSliderStateChanged
        int max;
        max = (int)(s*(s+b+3)/(s-b-1)*300);
        if(max > 59000) max = 59000;
        rSlider.setMaximum(max);
        rSlider.setMinimum(100);
        Display.draw();
    }//GEN-LAST:event_bSliderStateChanged

    private void x0SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_x0SliderStateChanged
        Display.draw();
    }//GEN-LAST:event_x0SliderStateChanged

    private void y0SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_y0SliderStateChanged
        Display.draw();
    }//GEN-LAST:event_y0SliderStateChanged

    private void z0SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_z0SliderStateChanged
        Display.draw();
    }//GEN-LAST:event_z0SliderStateChanged

    private void StepSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_StepSliderStateChanged
        Step=StepSlider.getValue();
        TmaxLabel.setText(""+(int)(StepSlider.getValue()*0.001));
        Display.draw();
    }//GEN-LAST:event_StepSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Display3D;
    private javax.swing.JTabbedPane MainDisplayTab;
    private javax.swing.JSlider StepSlider;
    private javax.swing.JTabbedPane TimeSeriesDisplayTab;
    private javax.swing.JLabel TmaxLabel;
    private javax.swing.JLabel bLabel;
    private javax.swing.JSlider bSlider;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel rLabel;
    private javax.swing.JSlider rSlider;
    private javax.swing.JLabel sLabel;
    private javax.swing.JSlider sigmaSlider;
    private javax.swing.JLabel x0Label;
    private javax.swing.JSlider x0Slider;
    private javax.swing.JPanel xtDisplay;
    private javax.swing.JPanel xyDisplay;
    private javax.swing.JPanel xzDisplay;
    private javax.swing.JLabel y0Label;
    private javax.swing.JSlider y0Slider;
    private javax.swing.JPanel ytDisplay;
    private javax.swing.JPanel yzDisplay;
    private javax.swing.JLabel z0Label;
    private javax.swing.JSlider z0Slider;
    private javax.swing.JLabel zn1Label;
    private javax.swing.JLabel znLabel;
    private javax.swing.JPanel ztDisplay;
    // End of variables declaration//GEN-END:variables
}

class LorenzEq {
       
    double dt;
    int step;
    double s,r,b;
    
    double x[];
    double y[];
    double z[];
    
    public LorenzEq(double s,double r,double b,int Step,int x0,int y0,int z0){
        this.s=s;
        this.r=r;
        this.b=b;
        step=Step;
        dt=0.001;
        x= new double[Step];
        y= new double[Step];
        z= new double[Step];
        x[0]=x0;
        y[0]=y0;
        z[0]=z0;
    }
    
    public void TimeEvolution(double xymax,double yzmax,double xzmax){
      int i;
      double max;
      double xmax,xmin,ymax,ymin,zmax,zmin;
      xmax=0;xmin=0;
      ymax=0;ymin=0;
      zmax=0;zmin=0;
      xymax=0;yzmax=0;xzmax=0;
            
      for(i=0;i<step-1;i++){

        // Euler's Method
/*        x[i+1] = x[i] + dt*dx(x[i],y[i],z[i]);
        y[i+1] = y[i] + dt*dy(x[i],y[i],z[i]);
        z[i+1] = z[i] + dt*dz(x[i],y[i],z[i]);*/

        // RungeKutta Method
        x[i+1] = x[i] + RKdx(x[i],y[i],z[i],dt);
        y[i+1] = y[i] + RKdy(x[i],y[i],z[i],dt);
        z[i+1] = z[i] + RKdz(x[i],y[i],z[i],dt);
        
        if(xmax < x[i+1]) xmax=x[i+1]; if(xmin > x[i+1]) xmin=x[i+1];
        if(ymax < y[i+1]) ymax=y[i+1]; if(ymin > y[i+1]) ymin=y[i+1];
        if(zmax < z[i+1]) zmax=z[i+1]; if(zmin > z[i+1]) zmin=z[i+1];
      }
      
/*      
      if(xmax-xmin > ymax-ymin){xymax = xmax-xmin;} else{xymax = ymax-ymin;}
      if(ymax-ymin > zmax-zmin){yzmax = ymax-ymin;} else{yzmax = zmax-zmin;}
      if(xmax-xmin > zmax-zmin){xzmax = xmax-xmin;} else{xzmax = zmax-zmin;}
      
      max = xymax;
      if(xymax < yzmax) max=yzmax;
      if(yzmax < xzmax) max=xzmax;
      
      for(i=0;i<step-1;i++){
        x[i] =(x[i]-(xmax+xmin)/2.)/max;
        y[i] =(y[i]-(ymax+ymin)/2.)/max;
        z[i] =(z[i]-(zmax+zmin)/2.)/max;
      }
 */
   }
    
    double RKdx(double x,double y,double z,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dx(x        ,y,z);
        k2 = tau * dx(x + k1/2.,y,z);
        k3 = tau * dx(x + k2/2.,y,z);
        k4 = tau * dx(x + k3   ,y,z);
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
    double RKdy(double x,double y,double z,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dy(x,y        ,z);
        k2 = tau * dy(x,y + k1/2.,z);
        k3 = tau * dy(x,y + k2/2.,z);
        k4 = tau * dy(x,y + k3   ,z);
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
    double RKdz(double x,double y,double z,double tau){
       double k1,k2,k3,k4;
        k1 = tau * dz(x,y,z        );
        k2 = tau * dz(x,y,z + k1/2.);
        k3 = tau * dz(x,y,z + k2/2.);
        k4 = tau * dz(x,y,z + k3   );
        
        return (k1+2*k2+2*k3+k4)/6.;
   }
    
   double dx(double xi,double yi,double zi){
       return s*(yi-xi);
   } 
   double dy(double xi,double yi,double zi){
       return r*xi-yi-xi*zi;
   } 
   double dz(double xi,double yi,double zi){
       return xi*yi-b*zi;
   } 
    
}
