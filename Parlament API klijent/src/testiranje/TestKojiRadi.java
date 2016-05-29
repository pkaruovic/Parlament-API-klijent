package testiranje;

import java.text.ParseException;
import java.util.List;

import parlament.ParlamentAPIKomunikacija;
import parlament.Poslanik;

public class TestKojiRadi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ParlamentAPIKomunikacija p = new ParlamentAPIKomunikacija();
	
			try {
				List<Poslanik> lista = p.vratiPoslanike();
				System.out.println(lista);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
