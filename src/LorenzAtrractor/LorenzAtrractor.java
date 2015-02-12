/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LorenzAtrractor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.Path2D;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pyounglous
 */
public class LorenzAtrractor extends javax.swing.JApplet {
    double s,r,b;
    double xymax,yzmax,xzmax;
    double x[],y[];
    int x0,y0,z0;
    int Step;
    LorenzEq lorenz;
    Draw Display;
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
            java.util.logging.Logger.getLogger(LorenzAtrractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LorenzAtrractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LorenzAtrractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LorenzAtrractor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        x= new double[Step];
        y= new double[Step];

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    Step=100000;
                    Display = new Draw();
                    Display.draw();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public class Draw{
        public void draw(){
           s=(double) sigmaSlider.getValue()/100.;   b=(double) bSlider.getValue()/100.;  r=(double) rSlider.getValue()/100.;
           sLabel.setText(""+(double) sigmaSlider.getValue()/100.);  rLabel.setText(""+(double) rSlider.getValue()/100.); bLabel.setText(""+(double) bSlider.getValue()/100.);
           x0=x0Slider.getValue();  y0=y0Slider.getValue(); z0=z0Slider.getValue();
           x0Label.setText(""+x0Slider.getValue()); y0Label.setText(""+y0Slider.getValue()); z0Label.setText(""+z0Slider.getValue());
           
           lorenz = new LorenzEq(s,r,b,Step,x0,y0,z0);
           lorenz.TimeEvolution(xymax,yzmax,xzmax);
           MainDisplayTab.remove(rootPane); MainDisplayTab.remove(rootPane);
           MainDisplayTab.repaint(); TimeSeriesDisplayTab.repaint();
        }
    }
    
    public class MainDisplay extends javax.swing.JPanel{                
        
        MainDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int radius=2,w;
            int x1,y1,x2,y2;
            Color color;
            w=getWidth();
            
            switch(MainDisplayTab.getSelectedIndex()){
                    case 0 :
                        x=lorenz.x;
                        y=lorenz.y;
                        break;
                    case 1 :
                        x=lorenz.y;
                        y=lorenz.z;
                        break;
                    case 2 :
                        x=lorenz.x;
                        y=lorenz.z;
                        break;
                    case 3 :
                        x=lorenz.x;
                        y=lorenz.y;
                        break;    
            }
            
            for(int i=0;i<Step-2;i++){
                color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                g.setColor(color);
                x1=(int)((w-10)*x[i])  -radius/2+w/2;
                y1=(int)((w-10)*(-y[i]))  -radius/2+w/2;
                x2=(int)((w-10)*x[i+1])-radius/2+w/2;
                y2=(int)((w-10)*(-y[i+1]))-radius/2+w/2;
                //g.fillOval(x1,y1,radius,radius);
                g.drawLine(x1, y1, x2, y2);
              //  if(x1 < 0)System.out.print("z:"+lorenz.z[i]+"\n");
                //System.out.print("xymax:"+xymax+", w:"+w+", x1:"+x1+"\n");
            }
        }
    }
    public class TimeSeriesDisplay extends javax.swing.JPanel{                
        
        TimeSeriesDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int radius=2,w,h;
            int x1,y1,x2,y2;
            Color color;
            w=getWidth();
            h=getHeight();
            
            switch(TimeSeriesDisplayTab.getSelectedIndex()){
                    case 0 :
                        y=lorenz.z;
                        break;
                    case 1 :
                        y=lorenz.x;
                        break;
                    case 2 :
                        y=lorenz.y;
                        break;
            }            
         
            for(int i=1;i<Step-2;i++){
                color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                g.setColor(color);
                x1=(int)(0.9*w*i/Step)  -radius/2 + (int)(0.05*w);
                y1=(int)(0.9*h*(-y[i]))  -radius/2+h/2;
                x2=(int)(0.9*w*(i+1)/Step)-radius/2+(int)(0.05*w);
                y2=(int)(0.9*h*(-y[i+1]))-radius/2+h/2;
//                g.fillOval(x1,y1,radius,radius);
                g.drawLine(x1, y1, x2, y2);
//                System.out.print(y1+"  " + h+ "\n");
              //  if(x1 < 0)System.out.print("z:"+lorenz.z[i]+"\n");
                //System.out.print("xymax:"+xymax+", w:"+w+", x1:"+x1+"\n");
            }
        }
    }
         
    public class LorenzMapDisplay extends javax.swing.JPanel{                
        
        LorenzMapDisplay(){
            super();
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int radius=4,w,check;
            int x1,y1,zmax,zmin,start=1;
            Color color;
            w=getWidth();
            x=lorenz.z;
            zmax=0;zmin=0;x1=0;
            check=0;
            for(int i=1;i<Step-2;i++){
                if(x[i-1]<x[i] && x[i] > x[i+1]){
                    if(check == 0){
                        x1=(int)(2*w*(-x[i]))-radius/2+w-2;
               //         System.out.print(x[i-1]+" , " + x[i]+" , "+x[i+1]+"="+i+"   "+x1+"\n");
                        zmin=x1;
                        check++;
                        start=i;
                    }
                    else if(zmin > x[i]){
                        zmin = (int)x[i];
                    }
                    
                    if(zmax < x[i]){
                        zmax = (int)x[i];
                    }
                }
            }
             //System.out.print(x1+"\n");
            for(int i=start;i<Step-2;i++){
                if(x[i-1]<x[i] && x[i] > x[i+1]){
                    color =  Color.getHSBColor((i/(float)(Step*10/7)),1f,1f);
                    g.setColor(color);
                    y1=(int)((2*w)*(-x[i]))  -radius/2+w-2;
                    g.fillOval(x1,y1,radius,radius);
               //     System.out.print(x1+" , " + y1+": "+i+"\n");
                    x1=y1;
                }
            }
            g.setColor(Color.RED);
            g.drawLine(-2, w, w, -2);
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
        TimeSeriesDisplayTab = new javax.swing.JTabbedPane();
        ztDisplay = new TimeSeriesDisplay();
        xtDisplay = new TimeSeriesDisplay();
        ytDisplay = new TimeSeriesDisplay();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        x0Slider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        y0Slider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        z0Slider = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        sigmaSlider = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        rSlider = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        bSlider = new javax.swing.JSlider();
        x0Label = new javax.swing.JLabel();
        y0Label = new javax.swing.JLabel();
        z0Label = new javax.swing.JLabel();
        sLabel = new javax.swing.JLabel();
        rLabel = new javax.swing.JLabel();
        bLabel = new javax.swing.JLabel();

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
            .addGap(0, 370, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("x-y Plane", xyDisplay);

        yzDisplay.setBackground(new java.awt.Color(254, 254, 254));
        yzDisplay.setPreferredSize(new java.awt.Dimension(350, 350));

        javax.swing.GroupLayout yzDisplayLayout = new javax.swing.GroupLayout(yzDisplay);
        yzDisplay.setLayout(yzDisplayLayout);
        yzDisplayLayout.setHorizontalGroup(
            yzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        yzDisplayLayout.setVerticalGroup(
            yzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("y-z Plane", yzDisplay);

        xzDisplay.setBackground(new java.awt.Color(247, 246, 246));

        javax.swing.GroupLayout xzDisplayLayout = new javax.swing.GroupLayout(xzDisplay);
        xzDisplay.setLayout(xzDisplayLayout);
        xzDisplayLayout.setHorizontalGroup(
            xzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        xzDisplayLayout.setVerticalGroup(
            xzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("x-z Plane", xzDisplay);

        Display3D.setBackground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout Display3DLayout = new javax.swing.GroupLayout(Display3D);
        Display3D.setLayout(Display3DLayout);
        Display3DLayout.setHorizontalGroup(
            Display3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        Display3DLayout.setVerticalGroup(
            Display3DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
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
            .addGap(0, 766, Short.MAX_VALUE)
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
            .addGap(0, 766, Short.MAX_VALUE)
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
            .addGap(0, 766, Short.MAX_VALUE)
        );
        ytDisplayLayout.setVerticalGroup(
            ytDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );

        TimeSeriesDisplayTab.addTab("y vs t Graph", ytDisplay);

        jPanel1.setMaximumSize(new java.awt.Dimension(405, 405));
        jPanel1.setMinimumSize(new java.awt.Dimension(405, 405));

        jLabel1.setText("x0");

        x0Slider.setValue(0);
        x0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        x0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                x0SliderStateChanged(evt);
            }
        });

        jLabel2.setText("y0");

        y0Slider.setValue(1);
        y0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        y0Slider.setVerifyInputWhenFocusTarget(false);
        y0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                y0SliderStateChanged(evt);
            }
        });

        jLabel3.setText("s");

        z0Slider.setValue(0);
        z0Slider.setPreferredSize(new java.awt.Dimension(180, 50));
        z0Slider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                z0SliderStateChanged(evt);
            }
        });

        jLabel4.setText("z0");

        sigmaSlider.setMaximum(10000);
        sigmaSlider.setMinimum(1);
        sigmaSlider.setValue(1000);
        sigmaSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        sigmaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sigmaSliderStateChanged(evt);
            }
        });

        jLabel5.setText("r");

        rSlider.setMaximum(10000);
        rSlider.setMinimum(1);
        rSlider.setValue(2800);
        rSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        rSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rSliderStateChanged(evt);
            }
        });

        jLabel6.setText("b");

        bSlider.setMaximum(10000);
        bSlider.setMinimum(1);
        bSlider.setValue(267);
        bSlider.setPreferredSize(new java.awt.Dimension(180, 50));
        bSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bSliderStateChanged(evt);
            }
        });

        x0Label.setText("jLabel7");

        y0Label.setText("jLabel8");

        z0Label.setText("jLabel9");

        sLabel.setText("jLabel10");

        rLabel.setText("jLabel11");

        bLabel.setText("jLabel12");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(x0Label)
                    .addComponent(y0Label)
                    .addComponent(z0Label))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rLabel)
                    .addComponent(sLabel)
                    .addComponent(bLabel))
                .addGap(3, 3, 3))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(x0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(y0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(z0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(sigmaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(x0Label)
                    .addComponent(sLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sigmaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(x0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(y0Label))
                        .addGap(12, 12, 12)
                        .addComponent(y0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(z0Label)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(z0Slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(bLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimeSeriesDisplayTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(MainDisplayTab, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainDisplayTab, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
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
        Display.draw();
    }//GEN-LAST:event_sigmaSliderStateChanged

    private void bSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bSliderStateChanged
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Display3D;
    private javax.swing.JTabbedPane MainDisplayTab;
    private javax.swing.JTabbedPane TimeSeriesDisplayTab;
    private javax.swing.JLabel bLabel;
    private javax.swing.JSlider bSlider;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
        dt=65./(double)step;
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
        x[i+1] = x[i] + dt*dx(x[i],y[i],z[i]);
        y[i+1] = y[i] + dt*dy(x[i],y[i],z[i]);
        z[i+1] = z[i] + dt*dz(x[i],y[i],z[i]);
        
        if(xmax < x[i+1]) xmax=x[i+1]; if(xmin > x[i+1]) xmin=x[i+1];
        if(ymax < y[i+1]) ymax=y[i+1]; if(ymin > y[i+1]) ymin=y[i+1];
        if(zmax < z[i+1]) zmax=z[i+1]; if(zmin > z[i+1]) zmin=z[i+1];
      }
      
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
