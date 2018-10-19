package BBMS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BloodBankManagement extends JFrame
{
    
		MenuBar mbr=new MenuBar();
		public BloodBankManagement()
		{
			Container d=getContentPane();
			JLabel l2=new JLabel(new ImageIcon("F:\\Programming\\Netbeans IDE\\BloodBankManagementSystem\\images\\homepage.jpg"));
			l2.setBounds(100,100,200,200);
			d.add(l2);
			setMenuBar(mbr);
			Menu donar=new Menu("Blood Donar Details");
			Menu admin=new Menu("Report");
			mbr.add(donar);
			mbr.add(admin);

			MenuItem don1=new MenuItem("Donar Details");
			don1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent tt){
			new Form1();
			dispose();
			}});			
			
		
			MenuItem adm2=new MenuItem("Blood Donation Report");
			adm2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent tt){
			try{
                            Login login=new Login();
                            dispose();
                            login.setVisible(true);
			}catch(Exception ee){}
			}});
                       
			
			donar.add(don1);
			admin.add(adm2);
			setVisible(true);
			setSize(900,650);
                        setTitle("Blood Donation Management");
			
		}
		public static void main(String args[])
		{
			BloodBankManagement m=new BloodBankManagement();
		}
}
