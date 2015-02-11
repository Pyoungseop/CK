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
    double s=10,r=28,b=8/3;
    double xymax,yzmax,xzmax;
    double x[],y[];
    int Step;
    LorenzEq lorenz;
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
                    lorenz = new LorenzEq(s,r,b,Step);
                    lorenz.TimeEvolution(xymax,yzmax,xzmax);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
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
                color =  Color.getHSBColor((i/(float)Step),1f,1f);
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
                color =  Color.getHSBColor((i/(float)Step),1f,1f);
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
                        System.out.print(x[i-1]+" , " + x[i]+" , "+x[i+1]+"="+i+"   "+x1+"\n");
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
             System.out.print(x1+"\n");
            for(int i=start;i<Step-2;i++){
                if(x[i-1]<x[i] && x[i] > x[i+1]){
                    color =  Color.getHSBColor((i/(float)Step),1f,1f);
                    g.setColor(color);
                    y1=(int)((2*w)*(-x[i]))  -radius/2+w-2;
                    g.fillOval(x1,y1,radius,radius);
                    System.out.print(x1+" , " + y1+": "+i+"\n");
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
        Display3D = new MainDisplay();
        LorenzMapPanel = new LorenzMapDisplay();
        TimeSeriesDisplayTab = new javax.swing.JTabbedPane();
        ztDisplay = new TimeSeriesDisplay();
        xtDisplay = new TimeSeriesDisplay();
        ytDisplay = new TimeSeriesDisplay();

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
            .addGap(0, 350, Short.MAX_VALUE)
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
            .addGap(0, 350, Short.MAX_VALUE)
        );
        xzDisplayLayout.setVerticalGroup(
            xzDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
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
            .addGap(0, 350, Short.MAX_VALUE)
        );

        MainDisplayTab.addTab("3D", Display3D);

        LorenzMapPanel.setBackground(new java.awt.Color(255, 255, 255));
        LorenzMapPanel.setMaximumSize(new java.awt.Dimension(300, 300));
        LorenzMapPanel.setMinimumSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout LorenzMapPanelLayout = new javax.swing.GroupLayout(LorenzMapPanel);
        LorenzMapPanel.setLayout(LorenzMapPanelLayout);
        LorenzMapPanelLayout.setHorizontalGroup(
            LorenzMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );
        LorenzMapPanelLayout.setVerticalGroup(
            LorenzMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LorenzMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainDisplayTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LorenzMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Display3D;
    private javax.swing.JPanel LorenzMapPanel;
    private javax.swing.JTabbedPane MainDisplayTab;
    private javax.swing.JTabbedPane TimeSeriesDisplayTab;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JPanel xtDisplay;
    private javax.swing.JPanel xyDisplay;
    private javax.swing.JPanel xzDisplay;
    private javax.swing.JPanel ytDisplay;
    private javax.swing.JPanel yzDisplay;
    private javax.swing.JPanel ztDisplay;
    // End of variables declaration//GEN-END:variables
}

class LorenzEq {
       
    double dt = 0.001;
    int step;
    double s,r,b;
    
    double x[];
    double y[];
    double z[];
    
    public LorenzEq(double s,double r,double b,int Step){
        this.s=s;
        this.r=r;
        this.b=b;
        step=Step;
        x= new double[Step];
        y= new double[Step];
        z= new double[Step];
        x[0]=0;
        y[0]=1;
        z[0]=0;
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
