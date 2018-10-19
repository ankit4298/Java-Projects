package BBMS;


//Donor Entry//----1
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Form1 extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l15,l16,l17,l18,l19,l50,bo1,mandatory,lbl;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t13,t14,t15;
        JButton b1;
	JRadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
	JComboBox  c1;
	Container d;		
	ResultSet rs;
        PreparedStatement ps;
        int count=100;
        
	Form1()
	{

		d=getContentPane();
		d.setLayout(null);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/blood","root","root");
			Statement st=con.createStatement();
                        ps=con.prepareStatement("insert into donor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
                        
                        rs=st.executeQuery("select DID from donor;");
                        while (rs.next()){
                            count++;
                        }
                        rs.close();
                        
		}catch(SQLException | ClassNotFoundException ex){
                     JOptionPane.showMessageDialog(null,"cannot establish connection to database");
                }


		l1=new JLabel("Blood Donor Details",JLabel.CENTER);
		l1.setFont(new Font("Comic Sans MS",Font.BOLD,30));
		l2=new JLabel("Name : (*) ");
		l3=new JLabel("Date of Birth : (*)");
		l4=new JLabel("Age: ");
		l5=new JLabel("Gender: ");
		l6=new JLabel("Occupation: ");
		l7=new JLabel("Organization : ");
		l8=new JLabel("Address");
		l9=new JLabel("Telephone:");
		l10=new JLabel("Mobile No. ");
		l11=new JLabel("E-mail");
		l12=new JLabel("Donate Date: (*)");
		l13=new JLabel("Your Blood Group: ");
		l15=new JLabel("Have you donated Previosly");
		l16=new JLabel("How many times: ");
		l17=new JLabel("Previous Blood Donation Date: ");
		l18=new JLabel("Did you have any discomfort/after Previous donation?: ");
		l19=new JLabel("Have you donated blood in the last 3 months: ");
		l50=new JLabel("DONOR ID: (*)");		
		bo1=new JLabel("");
		bo1.setBackground(Color.lightGray);
		bo1.setBorder(BorderFactory.createEtchedBorder());
		bo1.setOpaque(true);
                mandatory=new JLabel("fields marked with (*) are mandatory");
                lbl=new JLabel("Enter the date in YYYY-MM-DD Format");

		t1=new JTextField();		//name
		t2=new JTextField();		//date of birth
		t3=new JTextField();		//age
		t4=new JTextField();		//occupation
		t5=new JTextField();		//organization
		t6=new JTextField();		//address of communication
		t7=new JTextField();		//telephone
		t8=new JTextField();		//mobile
		t9=new JTextField();		//email
		t10=new JTextField();		//donate times
		t13=new JTextField();		//no of times donated
		t14=new JTextField();		//
		t15=new JTextField();		//DonateID
                
                t15.setText(Integer.toString(count+1));
                t15.setEditable(false);
                
                
		r1=new JRadioButton("Male");
		r2=new JRadioButton("Female");
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
                
                        //auto age via DOB
		t2.addFocusListener(new FocusAdapter(){
		public void focusLost(FocusEvent tt){
		java.util.GregorianCalendar gc=new java.util.GregorianCalendar();
		int yy=gc.get(java.util.Calendar.YEAR);
		int pp=Integer.parseInt(t2.getText().trim().substring(0,t2.getText().trim().indexOf('-')));
		t3.setText((yy-pp)+"");
		}});

		t9.addFocusListener(new FocusAdapter(){
		public void focusLost(FocusEvent tt){
		String str=t9.getText().trim();
		int a=0,d=0;
		for(int i=0;i<str.length();i++)
		{
		char ch=str.charAt(i);
		if(ch=='@') a=a+1;
		if(ch=='.') d=d+1;
		}
		if(!(a==1 && ( d==1 || d==2)))
		{
		t9.setText("");
		JOptionPane.showMessageDialog(null,"Invalid Email Id","Error",JOptionPane.ERROR_MESSAGE);
		}
		}});
		

		r3=new JRadioButton("Yes");
		r3.addActionListener(this);
		r4=new JRadioButton("NO");
		r4.addActionListener(this);
		ButtonGroup bg1=new ButtonGroup();
		bg1.add(r3);
		bg1.add(r4);
		
		r5=new JRadioButton("Yes");
		r6=new JRadioButton("No");
		ButtonGroup bg2=new ButtonGroup();
		bg2.add(r5);
		bg2.add(r6);
	
		r7=new JRadioButton("Yes");
		r8=new JRadioButton("No");
		ButtonGroup bg3=new ButtonGroup();
		bg3.add(r7);
		bg3.add(r8);
	

		String x[]={"A+","A-","B+","B-","O+","O-","AB"};
		c1=new JComboBox(x);

		
		b1=new JButton("NEXT");
                
		b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent tt){
		try
		{
                    ps.setInt(1,Integer.parseInt(t15.getText()));   //DONAR ID
                    ps.setString(2,t1.getText());                   //DNAME
                    java.sql.Date dt=java.sql.Date.valueOf(t2.getText());
                    ps.setDate(3,dt);                               //DOB
                    ps.setInt(4,Integer.parseInt(t3.getText()));    //AGE
                    if(r1.isSelected()==true)                       //GENDER
                        ps.setString(5,"Male");
                    else
                        ps.setString(5,"Female");
                    ps.setString(6,t4.getText());   //Occupation
                    ps.setString(7,t5.getText());   //Organization
                    ps.setString(8,t6.getText());   //Address
                    ps.setString(9,t7.getText());   //Telephone 
                    ps.setString(10,t8.getText());  //Mobile
                    ps.setString(11,t9.getText());  //Email
                    java.sql.Date dt1=java.sql.Date.valueOf(t10.getText());
                    ps.setDate(12,dt1);             //Donation Date
                    ps.setString(13,(String)c1.getSelectedItem());      //BloodGroup
                    if(r3.isSelected()==true)       //Previously Donated
                        ps.setString(14,"YES");
                    else
                        ps.setString(14,"NO");
                    
		if(r3.isSelected()==true)
		{
                    ps.setString(15,t13.getText()); //no. of times donated
                    ps.setString(16,t14.getText());
                    if(r5.isSelected()==true)   //Discomfort
                        ps.setString(17,"YES");
                    else
                        ps.setString(17,"NO");
                    
                    if(r7.isSelected()==true)       //last 3 months
                        ps.setString(18,"YES");
                    else
                        ps.setString(18,"NO");
		}
                else if(r3.isSelected()==false){
                    ps.setString(15,"-");
                    ps.setString(16,"-");
                    ps.setString(17,"-");
                    ps.setString(18,"-");
                }
		
                ps.executeUpdate();     //donar table
                ps.close();
                
                dispose();
                    OfficeForm1 officeForm1 = new OfficeForm1(t15.getText().trim()); 
                    java.sql.Date date=java.sql.Date.valueOf(t10.getText());
                    officeForm1.getData(Integer.parseInt(t15.getText()), t1.getText(), (String)c1.getSelectedItem(),date);
                                        //ID name bloodgroup donationdate
		}catch(SQLException ex){
                    System.out.print(ex);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Enter Fields Correctly","ERROR",JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
		}});
                
		l1.setBounds(0,0,1000,60);

		l2.setBounds(100,75,140,20);
 		t1.setBounds(250,75,180,20);

		l3.setBounds(100,115,140,20);
		t2.setBounds(250,115,180,20);

		l4.setBounds(450,115,140,20);
		t3.setBounds(600,115,150,20);
	
		l5.setBounds(100,155,140,20);
		r1.setBounds(250,155,80,20);
		r2.setBounds(350,155,80,20);

		l6.setBounds(450,155,140,20);
		t4.setBounds(600,155,150,20);		

		l7.setBounds(100,195,140,20);
		t5.setBounds(250,195,180,20);

		l8.setBounds(450,195,140,20);
		t6.setBounds(600,195,150,20);

		l9.setBounds(100,235,140,20);
		t7.setBounds(250,235,180,20);

		l10.setBounds(450,235,140,20);
		t8.setBounds(600,235,150,20);

		l11.setBounds(100,275,140,20);
		t9.setBounds(250,275,180,20);

		l12.setBounds(450,275,140,20);
		t10.setBounds(600,275,150,20);


		l13.setBounds(100,315,140,20);
		c1.setBounds(250,315,180,20);



		l15.setBounds(100,355,180,20);
		r3.setBounds(300,355,60,20);
		r4.setBounds(380,355,60,20);

		l16.setBounds(450,355,140,20);
		t13.setBounds(600,355,140,20);

		l17.setBounds(100,395,320,20);
		t14.setBounds(450,395,140,20);
		
				
		l18.setBounds(100,435,320,15);
		r5.setBounds(450,435,80,20);
		r6.setBounds(540,435,80,20);

		l19.setBounds(100,475,320,20);
		r7.setBounds(450,475,80,20);
		r8.setBounds(540,475,80,20);
		l50.setBounds(450,75,140,20);
		t15.setBounds(600,75,150,20);

		mandatory.setBounds(100,495,320,40);
		lbl.setBounds(115,510,335,55);
		
		b1.setBounds(40,575,100,20);
		bo1.setBounds(30,60,845,500);


                d.add(mandatory);
                d.add(lbl);
			
		d.add(r3);d.add(r4);
		d.add(l1);
		d.add(l2);d.add(t1);
		d.add(l3);d.add(t2);
		d.add(l4);d.add(t3);
		d.add(l5);d.add(t4);
		d.add(l6);d.add(t5);
		d.add(l7);d.add(t6);
		d.add(l8);d.add(t7);
		d.add(l9);d.add(c1);
		d.add(l10);d.add(t8);
		d.add(l11);d.add(t9);
		d.add(l12);d.add(t10);
		d.add(l13);d.add(c1);
		d.add(l15);d.add(t13);
		d.add(l16);d.add(r1);d.add(r2);
			l16.setVisible(false);
			l17.setVisible(false);
			l18.setVisible(false);
			l19.setVisible(false);
			t13.setVisible(false);
			t14.setVisible(false);
			r5.setVisible(false);
			r6.setVisible(false);
			r7.setVisible(false);
			r8.setVisible(false);

		d.add(l17);d.add(l50);
		d.add(l18);d.add(r5);d.add(r6);
		d.add(l19);d.add(r7);d.add(r8);
		d.add(t14);d.add(t15);
		d.add(b1);
		d.add(bo1);
		setSize(900,650);
                setTitle("Donor details");
		show();
	}

	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource()==r3) //gender
		{
			System.out.println("ok");
			l16.setVisible(true);
			l17.setVisible(true);
			l18.setVisible(true);
			l19.setVisible(true);
			t13.setVisible(true);
			t14.setVisible(true);
			r5.setVisible(true);
			r6.setVisible(true);
			r7.setVisible(true);
			r8.setVisible(true);

			
		}
	}
}
	