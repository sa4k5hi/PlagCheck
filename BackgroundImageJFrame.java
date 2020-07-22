import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.lang.*;


class BackgroundImageJFrame extends JFrame implements ActionListener
{   
    static JLabel l1,l2,l3,l4,l5;
    static JButton button1;
    static JButton button2,button3;
    static JProgressBar jp;
    String str1 ="";
    String str2 ="";
    int a =0;

    public BackgroundImageJFrame()
    {
        setTitle("Files Comparator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon("/home/saakshi/Documents/java_learning/parag_neha_me_jp/razor.jpg")));
        l1 = new JLabel("Select the first file");
        l1.setForeground(Color.WHITE);
        l2 = new JLabel("Select the second file");
        l2.setForeground(Color.WHITE);
        l3 = new JLabel();
        l4 = new JLabel();
        l5 = new JLabel();
        l3.setForeground(Color.YELLOW);
        l4.setForeground(Color.YELLOW);
        button1 = new JButton("choose file 1");
        button2 = new JButton("choose file 2");
        button3 = new JButton("Start");
        button1.setBounds(350,300,150,30);
        button2.setBounds(1250,300,150,30);
        button3.setBounds(820,750,150,30);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        l1.setBounds(350,150,300,30);
        l2.setBounds(1250,150,300,30);
        l3.setBounds(350,250,300,30);
        l4.setBounds(1250,250,300,30);
        l5.setBounds(820,850,300,100);
        l5.setForeground(Color.YELLOW);
        l5.setBackground(Color.WHITE);
        add(button1);
        add(button2);
        add(button3);
        jp = new JProgressBar();
        jp.setValue(0);
        jp.setStringPainted(true);
        jp.setBounds(700,650,400,30);
        jp.setBackground(Color.WHITE);
        add(jp);
        setSize(399,399);
        setSize(1700,1000);
    }
    public static void fill()
    {
        int i =0;
        try
        {
            while(i <=100){
                jp.setValue(i+1);

                Thread.sleep(100);
                i+=2;
            }
        }
        catch ( Exception e)
        {

        }
    }



    public void actionPerformed(ActionEvent e)
    {
        String str = e.getActionCommand();

        if(str.equals("choose file 1"))
        {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            
            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION)
            {
                l3.setText(j.getSelectedFile().getAbsolutePath());
                str1 = j.getSelectedFile().getAbsolutePath();
                
                
            } 
           
        }
        if(str.equals("choose file 2"))
        {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION)
            {
                l4.setText(j.getSelectedFile().getAbsolutePath());
                
                str2 = j.getSelectedFile().getAbsolutePath();

            }
           
        }
         
        if(str.equals("Start")) 
        {
            
          Double d  = display(str1,str2);
          l5.setText(d +  " % Matched");

        }

        


    }
    Double display(String s1,String s2)
    {   
        Double plague=0.0;
        System.out.println(s1 + " " + s2 );
        try {
            Map<String, Integer> map = new HashMap<String, Integer>();
            Scanner scanner = new Scanner(new File(s1));
            while (scanner.hasNextLine()) {
                //System.out.println(scanner.nextLine());
                String s = scanner.nextLine();
                //System.out.println(s);
                for( String word : s.split( " "))
                {
                    Integer i = map.get(word);
                    if( i == null)
                    {       // add(l2);

                        map.put(word, 1);
                    }
                    else
                    {
                        map.put(word, i+1);
                    }
                }
            }
            scanner.close();
            long noofwords=0;
            for(String word: map.keySet())
            {
                String key = word;
                int value = map.get(word);
                System.out.println(key + "  " + value);
                noofwords+=value;
            }
            System.out.println("---------------------------");
            Map<String,Integer> mapp = new HashMap<String, Integer>();
            scanner = new Scanner(new File(s2));
            while(scanner.hasNextLine()){
            
                String p = scanner.nextLine();
                //System.out.println(p);
            
                for( String word : p.split( " "))
                {
                    Integer i = mapp.get(word);
                    if( i == null)
                    {
                        mapp.put(word, 1);
                    }   
                    else
                    {
                        mapp.put(word, i+1);
                    }
                }

            }
            scanner.close();
            
            for(String word : mapp.keySet())
            {
                String key = word;
                int value = mapp.get(word);
                //noofwords += value;
                System.out.println(key + " " + value);
            }
          /*  for(String word : map.keySet())
            {
                String key = word;
                int value = mapp.get(word);
                noofwords += value;
                System.out.println(key + " " + value);
            }*/
            //long noofdiffwords=0,nomatchfound=0,noofsamewords=0;
            long noofdiffwords_s=0;
            
            for(String a : map.keySet())
            {   
                System.out.println("..............");
                    String key1 = a;
                    int val1 = map.get(a);

                //int flag=0;
                for(String b : mapp.keySet())
                {   
                    //System.out.println("############");
                    String key2 = b;
                    int val2 = mapp.get(b);

                    if(key1.equals(key2))
                    {   
                        
                        if(val1>val2)
                        {noofdiffwords_s += Math.abs(val1 - val2);}
                        map.put(key1, 0);
                        mapp.put(key2, 0);
                        System.out.println("************");
                        //flag=1;
                        break;

                        //}
                        //else
                          //  System.out.println("@@@");
                    }
                }
            }
            int sum=0;  
            for(String a : map.keySet())
            {   
                System.out.println("..............");
                    String key1 = a;
                    int val1 = map.get(a);
                    sum=sum+val1;
            }

            long noofsamewords_s=noofwords-noofdiffwords_s-1;
            
            //noofsamewords/=2;
            System.out.println(noofdiffwords_s+ " " + (noofwords-1) +" " +noofsamewords_s);
            System.out.println("--------------------Check completed---------------");
        
            double p=(noofwords-1);
            double nosws=noofsamewords_s;

            plague=(nosws/p);
            
            //double q=(noofdiffwords+noofsamewords)*1.0;
            //plague = ((p-q)/p);
            plague=(plague*100.0)/100.0;
            System.out.println(((plague)*100.00) +"% matched"
            );
            fill();
        }
    
         catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return plague*100.0;
        
    }

        
    


    public static void main(String args[])
    {
        BackgroundImageJFrame b = new BackgroundImageJFrame();
        //System.out.println(b.str3 + "* " + b.str1 );
        button1.addActionListener(b);
        button2.addActionListener(b);
        button3.addActionListener(b);
      // System.out.println(b.str1 );
        
    }
    

} 
