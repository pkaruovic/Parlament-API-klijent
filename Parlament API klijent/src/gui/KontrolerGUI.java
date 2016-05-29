package gui;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.google.gson.*;

import modeli.ParlamentModel;
import parlament.ParlamentAPIKomunikacija;
import parlament.Poslanik;
import pomocnemetode.RadSaFajlovima;

public class KontrolerGUI {

	private static ParlamentGUI glavniProzor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniProzor = new ParlamentGUI();
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void ugasiProgram(){
		int povratnaVrednost = JOptionPane.showConfirmDialog(glavniProzor, "Da li zelite da izadjete iz programa?","Iskljuci",JOptionPane.YES_NO_OPTION);
		
		if(povratnaVrednost == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	public static void vratiClanove() {

		try {
			RadSaFajlovima.upisiPoslanikeUFajl();
			dodajUStatus("Poslanici su preuzeti sa servisa\n");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(glavniProzor, "Doslo je do greske prilikom ucitavanja poslanika", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void dodajUStatus(String tekst) {
		glavniProzor.getTextArea().append(tekst);
	}

	public static void popuniTabelu() {
		try {
			glavniProzor.osveziTabelu(RadSaFajlovima.ucitajPoslanikeIzFajla());
			
			dodajUStatus("Tabela je popunjena podacima preuzetim sa servisa\n");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(glavniProzor, "Doslo je do greske prilikom popunjavanja tabele", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void izmeniClanove() {
		JTable table = glavniProzor.vratiTabelu();
		final ParlamentModel model = (ParlamentModel) table.getModel();
//		model.addTableModelListener(new TableModelListener() {
//
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				int red = e.getFirstRow();
//				int kolona = e.getColumn();
//				String s = (String) model.getValueAt(red, kolona);
//				model.setValueAt(s, red, kolona);
//			}
//		});
		
		try {
			RadSaFajlovima.upisiIzmenjenePoslanike(model.vratiListu());
			dodajUStatus("Izmenjeni podaci su sacuvani \n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, "Doslo je do greske prilikom upisivanja u fajl" , "Greska",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace(System.out);
		}
	}

}
