package BBMS;

//Blood Bank Use Only//----2
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class OfficeForm1 extends JFrame 
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l13,l14,l15,l16,l17,l18,l19,l50,bo1;
	JTextField t1,t2,t3,t4,t7,t10,t11,t12,t13,t14;
	JButton b1;
	JRadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
	JComboBox  c1;
	Container d;		
	ResultSet rs;
        PreparedStatement ps,ps1;
	String DID="";
        
        String name,Bloodgrp;
        java.sql.Date DonationDate;
        int id;
        
        BloodBankManagement homepage=new BloodBankManagement();
        
        
        public void getData(int id,String name,String Bloodgrp,java.sql.Date Donationdate){
            this.name=name;
            this.Bloodgrp=Bloodgrp;
            this.DonationDate=Donationdate;
            this.id=id;
        }
        
        
	OfficeForm1(String s1)
	{
		DID=s1;
		d=getContentPane();
		d.setLayout(null);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood","root","root");
			Statement st=con.createStatement();
                        ps=con.prepareStatement("insert into officeuse values(?,?,?,?,?,?,?,?,?,?);");
                        ps1=con.prepareStatement("insert into report values(?,?,?,?,?);"); 
                        
		}catch(SQLException | ClassNotFoundException ex){
                     JOptionPane.showMessageDialog(null,"cannot establish connection to database");
                }


		l1=new JLabel("For Blood Bank Use Only",JLabel.CENTER);
		l1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
		l2=new JLabel("General Physical Examination : ");
		l3=new JLabel("Weight: ");
		l4=new JLabel("Normal Pulse: ");
		l5=new JLabel("Haemoglobin: ");
		l6=new JLabel("BP: ");
		l7=new JLabel("Normal Temperature: ");
		l8=new JLabel(" Skin Disease");
		l9=new JLabel("Blood Bag Details:");
		l10=new JLabel("Bag No: (*)");
		l11=new JLabel("Bag Type:");
		l13=new JLabel("Blood Volume(ml): ");
		l50=new JLabel("DONOR ID: ");

						
		bo1=new JLabel("");
		bo1.setBackground(Color.lightGray);
		bo1.setBorder(BorderFactory.createEtchedBorder());
		bo1.setOpaque(true);

		r1=new JRadioButton("Yes");
		r2=new JRadioButton("No");

		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);

		r3=new JRadioButton("Yes");
		r4=new JRadioButton("NO");
		ButtonGroup bg1=new ButtonGroup();
		bg1.add(r3);
		bg1.add(r4);
		
		r5=new JRadioButton("Yes");
		r6=new JRadioButton("No");
		ButtonGroup bg2=new ButtonGroup();
		bg2.add(r5);
		bg2.add(r6);
	
		r7=new JRadioButton("Small");
		r8=new JRadioButton("Big");
		ButtonGroup bg3=new ButtonGroup();
		bg3.add(r7);
		bg3.add(r8);


		t1=new JTextField();		//Donar ID
		t1.setText(DID);
                    t1.setEditable(false);
		t2=new JTextField();		//weight
		t3=new JTextField();		//HB
		t4=new JTextField();		//BP
		t7=new JTextField();		//Bag no
		t10=new JTextField();		//blood volume
		
				
		b1=new JButton("Done");

		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
                    ps.setInt(1,Integer.parseInt(t1.getText()));    //Donar ID
                    ps.setInt(2,Integer.parseInt(t2.getText()));    //Weight
                    ps.setInt(3,Integer.parseInt(t3.getText()));    //HB
                    ps.setInt(4,Integer.parseInt(t4.getText()));    //BP
                    ps.setInt(5,Integer.parseInt(t7.getText()));    //Bag no
                    ps.setInt(6,Integer.parseInt(t10.getText()));    //Blood volume
                    
                    
                    if(r1.isSelected()==true)       //Pulse
                        ps.setString(7,"Yes");
                    else
                        ps.setString(7,"No");
                    
                    if(r3.isSelected()==true)       //Temperature
                        ps.setString(8,"Yes");
                    else
                        ps.setString(8,"No");
                    
                    if(r5.isSelected()==true)       //Skin Disease
                        ps.setString(9,"YES");
                    else
                        ps.setString(9,"No");
                    if(r7.isSelected()==true)        //Bag Type small/big
                    {      
                        ps.setString(10,"Small");
                    }
                    else
                    {
                        ps.setString(10,"Big");
                    }
                    
                    
                        //add to report table
                    ps1.setInt(1,id);               //Donor ID
                    ps1.setString(2,name);          //Donor Name
                    ps1.setString(3,Bloodgrp);      //BloodGroup
                    ps1.setDate(4,DonationDate);    //Donation Date
                    if(r7.isSelected()==true){                    //Bag Size
                        ps1.setString(5,"Small");
                    }
                    else{
                        ps1.setString(5,"Big");
                    }
                    
                    
                    ps1.executeUpdate();
                    ps1.close();
                    ps.executeUpdate();
                    ps.close();
                    
		JOptionPane.showMessageDialog(null,"User Entered Successfully","DONE",JOptionPane.INFORMATION_MESSAGE);
                dispose();
                homepage.setVisible(true);
		}catch(SQLException ex){
                    System.out.print(ex);
                    ex.printStackTrace();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Enter Fields Correctly","ERROR",JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
		}});


		l1.setBounds(0,0,1000,60);

		l2.setBounds(300,70,300,30);
 		l50.setBounds(610,70,100,20);
		t1.setBounds(710,70,100,30);

		l3.setBounds(100,115,140,20);
		t2.setBounds(250,115,180,20);

		l4.setBounds(450,115,140,20);
		r1.setBounds(600,115,70,20);
		r2.setBounds(680,115,70,20);
	
		l5.setBounds(100,155,140,20);
		t3.setBounds(250,155,180,20);

		l6.setBounds(450,155,140,20);
		t4.setBounds(600,155,150,20);		

		l7.setBounds(100,195,140,20);
		r3.setBounds(250,195,75,20);
		r4.setBounds(335,195,75,20);

		l8.setBounds(450,195,140,20);
		r5.setBounds(600,195,70,20);
		r6.setBounds(680,195,70,20);

		l9.setBounds(350,250,300,30);
		

		l10.setBounds(100,315,140,20);
		t7.setBounds(250,315,180,20);

		l11.setBounds(450,315,140,20);
		r7.setBounds(600,315,70,20);
		r8.setBounds(680,315,70,20);

		
		l13.setBounds(450,355,140,20);
		t10.setBounds(600,355,140,20);
		
		b1.setBounds(40,575,100,20);

		bo1.setBounds(30,60,845,500);
		d.add(l1);
		d.add(l2);d.add(t1);
		d.add(l3);d.add(t2);
		d.add(l4);d.add(t3);
		d.add(l5);d.add(t4);
		d.add(l6);
		d.add(l7);
		d.add(l8);d.add(t7);
		d.add(l9);
		d.add(l10);d.add(t10);
		d.add(l13);
		d.add(r1);d.add(r2);
		d.add(r3);d.add(r4);
		d.add(r5);d.add(r6);
		d.add(r7);d.add(r8);
		d.add(l50);
		d.add(b1);
		d.add(bo1);
		setSize(900,650);
                setTitle("Office Use Form");
		show();
	}
}
	