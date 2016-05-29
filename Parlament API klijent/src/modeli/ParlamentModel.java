package modeli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import parlament.Poslanik;

public class ParlamentModel extends AbstractTableModel {

	private String[] naziviKolona = new String[] { "ID", "Ime", "Prezime", "Datum rodjenja" };
	private LinkedList<Poslanik> poslanici;

	public ParlamentModel(LinkedList<Poslanik> poslanici) {
		if (poslanici == null)
			poslanici = new LinkedList<>();

		this.poslanici = poslanici;
	}

	@Override
	public boolean isCellEditable(int red, int kolona) {
		if (kolona == 0)
			return false;

		return true;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return naziviKolona[arg0];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return naziviKolona.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return poslanici.size();
	}

	@Override
	public Object getValueAt(int red, int kolona) {
		Poslanik p = poslanici.get(red);

		switch (kolona) {
		case 0:
			return p.getId();
		case 1:
			return p.getIme();
		case 2:
			return p.getPrezime();
		case 3:
			return p.getDatumRodjenja();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object o, int red, int kolona) {
		Poslanik p = poslanici.get(red);

		switch (kolona) {
		case 1:
			String ime = o.toString();
			if (ime == null || ime.isEmpty())
				throw new RuntimeException("Ime ne sme biti null ili prazan string");
			else
				p.setIme(ime);
			break;
		case 2:
			String prezime = o.toString();
			if (prezime == null || prezime.isEmpty())
				throw new RuntimeException("Prezime ne sme biti null ili prazan string");
			else
				p.setPrezime(prezime);
			break;
		case 3:
			String datum = o.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
			Date date;
			
			try {
				date = (Date) sdf.parse(datum);
				p.setDatumRodjenja(date);
				
			} catch (ParseException e) {
				new RuntimeException("Datum mora biti u formatu dd.mm.yyyy.");
			}

			break;
		default:
			return;
		}
	}

	public void osveziPodatke(LinkedList<Poslanik> poslanici) {
		if (poslanici == null)
			return;

		this.poslanici = poslanici;
		fireTableDataChanged();
	}
	
	public LinkedList<Poslanik> vratiListu(){
		return poslanici;
	}

}
