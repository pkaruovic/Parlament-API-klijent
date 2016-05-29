package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modeli.ParlamentModel;
import parlament.Poslanik;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.UIManager;

public class ParlamentGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnVratiClanove;
	private JButton btnPopuniTabelu;
	private JButton btnOsveziClanove;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;

	public ParlamentGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				KontrolerGUI.ugasiProgram();
			}
		});
		setTitle("Clanovi parlamenta");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 562, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getScrollPane_1_1(), BorderLayout.SOUTH);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setModel(new ParlamentModel(null));
			table.getModel().addTableModelListener(new TableModelListener() {

				@Override
				public void tableChanged(TableModelEvent e) {
					int red = e.getFirstRow();
					int kolona = e.getColumn();
					String s = (String) table.getModel().getValueAt(red, kolona);
					table.getModel().setValueAt(s, red, kolona);
				}
			});

		}
		return table;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(130, 10));
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(getBtnVratiClanove());
			panel.add(getBtnPopuniTabelu());
			panel.add(getBtnIzmeniClanove());
		}
		return panel;
	}

	private JButton getBtnVratiClanove() {
		if (btnVratiClanove == null) {
			btnVratiClanove = new JButton("Vrati clanove");
			btnVratiClanove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KontrolerGUI.vratiClanove();
				}
			});
			btnVratiClanove.setPreferredSize(new Dimension(115, 23));
		}
		return btnVratiClanove;
	}

	private JButton getBtnPopuniTabelu() {
		if (btnPopuniTabelu == null) {
			btnPopuniTabelu = new JButton("Popuni tabelu");
			btnPopuniTabelu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					KontrolerGUI.popuniTabelu();
				}
			});
			btnPopuniTabelu.setPreferredSize(new Dimension(115, 23));
		}
		return btnPopuniTabelu;
	}

	private JButton getBtnIzmeniClanove() {
		if (btnOsveziClanove == null) {
			btnOsveziClanove = new JButton("Izmeni clanove");
			btnOsveziClanove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KontrolerGUI.izmeniClanove();
				}
			});
			btnOsveziClanove.setPreferredSize(new Dimension(115, 23));
		}
		return btnOsveziClanove;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void osveziTabelu(LinkedList<Poslanik> poslanici) {
		((ParlamentModel) table.getModel()).osveziPodatke(poslanici);
	}

	public JTable vratiTabelu(){
		return table;
	}
	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setPreferredSize(new Dimension(50, 70));
			scrollPane_1.setBorder(new TitledBorder(new LineBorder(null), "STATUS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			scrollPane_1.setViewportView(getTextArea());
		}
		return scrollPane_1;
	}
	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setPreferredSize(new Dimension(50, 50));
		}
		return textArea;
	}
}
