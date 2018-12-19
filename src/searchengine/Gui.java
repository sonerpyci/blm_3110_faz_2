package searchengine;

import java.awt.*;
import java.sql.SQLException;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Gui extends JFrame implements KeyListener {
	DbService dbService = new DbService();
	static private final String newline = "\n";

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel searchPanel;

	private JTextField searchField;
	private JTextField urlField;
	private JTextField sentenceField;
    private JTextField urlResult;

	private JList<String> searchResults;

    private JScrollPane jScrollPane1;

	JButton olustur;
	ArrayList<Sentence> sentenceList;

	static int sentencecount=0;
	static String fullSentence="";

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

		searchPanel = new JPanel(new BorderLayout());
        searchResults = new JList<String>();

        searchResults.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });

        searchField = new JTextField("Aranacak kelimeleri giriniz");
        jScrollPane1 = new JScrollPane();

        jScrollPane1.setViewportView(searchResults);
        urlResult = new JTextField("Url:");
        urlResult.setEditable(false);

        searchPanel.add(searchField, BorderLayout.PAGE_START);
        searchPanel.add(urlResult, BorderLayout.PAGE_END);
        searchPanel.add(searchResults, BorderLayout.CENTER);

		panel1 = new JPanel(new BorderLayout());
		panel2= new JPanel();
		panel3= new JPanel();

		urlField = new JTextField("Url giriniz");
		sentenceField = new JTextField("Metin giriniz");
	    olustur= new JButton("olustur");

        panel1.add(urlField,BorderLayout.PAGE_START);
	    panel1.add(sentenceField,BorderLayout.CENTER);
	    panel1.add(olustur,BorderLayout.PAGE_END);

	     panel2.setLayout(new GridLayout(20,1));

	     panel3.setLayout(new GridLayout(20,1));

	     tappedPane.add(searchPanel, "Arama");
	     tappedPane.add(panel1,"Metin Girisi");
	     tappedPane.add(panel2,"Cıktı");
	     tappedPane.add(panel3,"URL Indisleme");
	     add(tappedPane);

	     olustur.addActionListener(e -> {
	         String url = urlField.getText();
			 fullSentence = sentenceField.getText();
				String [] parts = fullSentence.split("\\."); // Burada noktayı kaldırıyorlar. Onun yerine tüm punctutationlar kaldırılmalı.
				//System.out.println(String.join(" ", parts));
				for(int i = 0; i < parts.length; i++) {
					String[] words = parts[i].split("\\s+"); // cumleyi kelime kelime ayırıyorlar.
					for (int j = 0; j< words.length; j++) {
						// You may want to check for a non-word character before blindly
						// performing a replacement
						// It may also be necessary to adjust the character class
						words[j] = words[j].replaceAll("[^\\w]", "");
					}
					Sentence wordiex = new Sentence(words, url);
					try{
						int content_id = dbService.saveFullSentence(wordiex);
						dbService.saveShiftedSentences(wordiex.getShiftedSentences(), content_id);
					}catch(SQLException exception){ //Eğer bu hata yenirse db de eklenemedi tarzından uyarı verilecek. ama sistem çaısmaya devam edecek
						exception.printStackTrace();
					}
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
				    text4.setText(url+" ===>\t"+searchEngine.listIndex(i));
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
