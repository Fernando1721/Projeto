/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Teste.ProjectExec;
import javax.swing.JOptionPane;

/**
 *
 * @author é o r's
 */
public class Seconds implements Runnable {
    
    
    @Override
    public void run() {
        int counter = Integer.parseInt(ProjectExec.txtSeconds.getText());
        
        
        for(;;){
            ProjectExec.txtSeconds.setText(counter + "");
            counter++;
            
            if(counter == 59){
                JOptionPane.showMessageDialog(null, "Game Over");
                System.exit(0);
            }
            
            try{
                Thread.sleep(1000);
            } catch(Exception e){
            }
            
        }
        
    }
    
}
