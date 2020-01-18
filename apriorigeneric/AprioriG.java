package apriorigeneric; 
import java.util.*; 
/** * * @author VIDHYA 
*/ public class AprioriG extends javax.swing.JFrame { 
/** 
* Creates new form AprioriG 
*/ 
public AprioriG() { 
initComponents(); 
} 
/** 
* This method is called from within the constructor to initialize the form. 
* WARNING: Do NOT modify this code. The content of this method is always 
* regenerated by the Form Editor. 
*/ 
@SuppressWarnings("unchecked") 
// <editor-fold defaultstate="collapsed" desc="Generated Code"> 
private void initComponents() { 
jLabel1 = new javax.swing.JLabel(); 
jLabel2 = new javax.swing.JLabel(); 
jTextField1 = new javax.swing.JTextField(); 
jButton1 = new javax.swing.JButton(); 
jLabel4 = new javax.swing.JLabel(); 
jTextField3 = new javax.swing.JTextField(); 
setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 
jLabel1.setText("Apriori"); 
jLabel2.setText("Enter Support Threshold"); 
jButton1.setText("Find"); 
jButton1.addActionListener(new java.awt.event.ActionListener() { 
public void actionPerformed(java.awt.event.ActionEvent evt) { 
jButton1ActionPerformed(evt); 
} 
}); 
jLabel4.setText("Enter Confidence Threshold "); 
javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane()); 
getContentPane().setLayout(layout); 
layout.setHorizontalGroup( 
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
.addGroup(layout.createSequentialGroup() 
.addContainerGap() 
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
.addComponent(jLabel2) 
.addComponent(jLabel4)) 
.addGap(70, 70, 70) 
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
.addGroup(layout.createSequentialGroup() 
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
.addComponent(jLabel1) 
.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, 
javax.swing.GroupLayout.PREFERRED_SIZE)) 
.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) 
.addGroup(layout.createSequentialGroup() 
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, 
javax.swing.GroupLayout.PREFERRED_SIZE) 
.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE) 
.addComponent(jButton1) 
.addGap(79, 79, 79)))) 
); 
layout.setVerticalGroup( 
layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) 
.addGroup(layout.createSequentialGroup() 
.addGap(30, 30, 30) 
.addComponent(jLabel1) 
.addGap(45, 45, 45) 
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) 
.addComponent(jLabel2) 
.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 
javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE) 
.addComponent(jButton1)) 
.addGap(34, 34, 34) 
.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) 
.addComponent(jLabel4) 
.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 
javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)) 
.addContainerGap(70, Short.MAX_VALUE)) 
); 
pack(); 
}// </editor-fold> 
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 
// TODO add your handling code here: 
float supportThreshold,confThres; 
List<ItemDesc> tL = new ArrayList<>(); 
List<ItemDesc> aL = new ArrayList<>(); 
supportThreshold = Integer.parseInt(jTextField1.getText()); 
confThres = Integer.parseInt(jTextField3.getText()); 
System.out.println(); 
int i=1; 
AprioriGeneric aG = new AprioriGeneric(supportThreshold); 
tL = aG.getTransaction(); 
do{ 
tL=aG.makeCombinations(tL,tL); 
i++; 
} 
while(tL.size()>1); 
aG.makeAsso(confThres); 
} 
/** 
* @param args the command line arguments 
*/ 
public static void main(String args[]) { 
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
java.util.logging.Logger.getLogger(AprioriG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
} catch (InstantiationException ex) { 
java.util.logging.Logger.getLogger(AprioriG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
} catch (IllegalAccessException ex) { 
java.util.logging.Logger.getLogger(AprioriG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
} catch (javax.swing.UnsupportedLookAndFeelException ex) { 
java.util.logging.Logger.getLogger(AprioriG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); 
} 
//</editor-fold> 
/* Create and display the form */ 
} 
// Variables declaration - do not modify 
private javax.swing.JButton jButton1; 
private javax.swing.JLabel jLabel1; 
private javax.swing.JLabel jLabel2; 
private javax.swing.JLabel jLabel4; 
private javax.swing.JTextField jTextField1; 
private javax.swing.JTextField jTextField3; 
// End of variables declaration 
} 