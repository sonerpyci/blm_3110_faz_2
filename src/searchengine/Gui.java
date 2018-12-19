package searchengine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Gui extends JFrame implements KeyListener {
	
	static private final String newline = "\n";
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JTextField text;
	private JTextField text2;
	JButton olustur;
	ArrayList<Sentence> sentenceList;
    static int sentencecount=0;
	static String ex=""; 
	
	public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
			try {
				Gui frame = new Gui();
				frame.setSize(1500, 600);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Gui() {
		sentenceList = new ArrayList<Sentence>();

	    setTitle("Metin Indeksleme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tappedPane = new JTabbedPane();
		panel1 = new JPanel(new BorderLayout());
		panel2= new JPanel();
		panel3= new JPanel();

		 text = new JTextField("Url giriniz");
		 text2 = new JTextField("Metin giriniz");
	     olustur= new JButton("Olustur"); 
	    
	     panel1.add(text,BorderLayout.PAGE_START);
	     panel1.add(text2,BorderLayout.CENTER);
	     panel1.add(olustur,BorderLayout.PAGE_END);
	     panel2.setLayout(new GridLayout(20,1));
	     panel3.setLayout(new GridLayout(20,1));

	     tappedPane.add(panel1,"Metin Girisi");
	     tappedPane.add(panel2,"Cıktı");
	     tappedPane.add(panel3,"URL Indisleme");
	     add(tappedPane);

	     olustur.addActionListener(e -> {
	         String ex2 = text.getText();
				ex = text2.getText();
				String [] parts = ex.split("\\.");
				for(int i = 0; i < parts.length; i++) {
					String[] words = parts[i].split("\\s+");
					for (int j = 0; j< words.length; j++) {
						// You may want to check for a non-word character before blindly
						// performing a replacement
						// It may also be necessary to adjust the character class
						words[j] = words[j].replaceAll("[^\\w]", "");
					}

					Sentence wordiex = new Sentence(words);
					sentenceList.add(wordiex);

					sentencecount++;
				}

				Engine searchEngine = new Engine();

				for(int i = 0; i < sentencecount; i++) {
				    searchEngine.addSentence(sentenceList.get(i));
				}

				//searchEngine.listItems();
                searchEngine.sort();
				/*��kt� sayfas�nda for i�inde print i�lemini buraya yap*/
				for(int i = 0; i < searchEngine.getCountSort(); i++) {
				    JTextField text3 = new JTextField();
				    text3.setBackground(Color.gray);
				    text3.setText(searchEngine.listIndex(i));
				    panel2.add(text3);
				    panel2.revalidate();
				    panel2.repaint();
				}
				/*Url sayfas�nda for i�inde print i�lemini buraya yap*/
				for(int i= 0 ; i < searchEngine.getCountSort(); i++) {
				    JTextField text4 = new JTextField();
				    text4.setBackground(Color.gray);
				    text4.setText(ex2+"/"+searchEngine.listIndex(i));
				    panel3.add(text4);
				    panel3.revalidate();
				    panel3.repaint();
				}
			});
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {

	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}
}
