package demo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class JFXFileChooser extends JFrame {

	private static final long serialVersionUID = 3359548993204704215L;
	private JPanel contentPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFXFileChooser frame = new JFXFileChooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFXFileChooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		JButton btnNewButton = new JButton("Open File");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.BLACK);

		JTextPane textPanel = new JTextPane();
		textPanel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		textPanel.setEditable(false);
		textPanel.setForeground(new Color(0, 100, 0));
		textPanel.setBackground(Color.LIGHT_GRAY);
		textPanel.setBounds(53, 109, 375, 49);
		textPanel.setVisible(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));
				filechooser.setDialogTitle("Select File");
				int result = filechooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					FileInputStream fstream = null;
					try {
						fstream = new FileInputStream(filechooser.getSelectedFile());
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
						String strLine, strArray[] = null;
						double number1, number2, area;
						TreeMap<Double, List<Double>> prices = new TreeMap<Double, List<Double>>();
						while ((strLine = br.readLine()) != null) {
							if (strArray != null) {
								strArray = null;
							}
							// Handling input values by try catch block if provided values are separated by either SPACE or TAB-SPACE.
							try {
								strArray = strLine.split(" ");
								number1 = Double.parseDouble(strArray[0]);
								number2 = Double.parseDouble(strArray[1]);
							} catch (Exception e1) {
								strArray = strLine.split("\t");
								number1 = Double.parseDouble(strArray[0]);
								number2 = Double.parseDouble(strArray[1]);
							}
							area = ((number2 * number2) + (number1 * number1));
							prices.put(area, Arrays.asList(number1, number2));
						}
						int count = 0;
						String op = "";
						for (Entry<Double, List<Double>> entry : prices.entrySet()) {
							op = op + entry.getValue().toString().replace('[', ' ').replace(']', ' ').trim() + "\n";
							count++;
							if (count == 2) {
								break;
							}
						}
						try (Writer writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(
										filechooser.getSelectedFile().getName().split("\\.")[0] + "_out.txt"),
								"utf-8"))) {
							writer.write(op);
							btnNewButton.setVisible(false);
							textPanel.setVisible(true);
							textPanel.setText("Output File Generated Successfully");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						fstream.close();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				filechooser.setVisible(false);
			}
		});
		btnNewButton.setBounds(151, 82, 117, 29);
		contentPanel.add(btnNewButton);
		contentPanel.add(textPanel);
	}
}
