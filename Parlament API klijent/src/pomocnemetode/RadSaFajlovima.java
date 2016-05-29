package pomocnemetode;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import parlament.ParlamentAPIKomunikacija;
import parlament.Poslanik;

public class RadSaFajlovima {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

	public static LinkedList<Poslanik> ucitajPoslanikeIzFajla() throws Exception {

		LinkedList<Poslanik> poslanici = new LinkedList<>();

		FileReader in = new FileReader("data/serviceMembers.json");

		JsonArray jsonNiz = new GsonBuilder().create().fromJson(in, JsonArray.class);

		in.close();

		for (int i = 0; i < jsonNiz.size(); i++) {

			JsonObject jObj = (JsonObject) jsonNiz.get(i);

			Poslanik p = new Poslanik();

			p.setId(jObj.get("id").getAsInt());
			p.setIme(jObj.get("name").getAsString());
			p.setPrezime(jObj.get("lastName").getAsString());
			if (jObj.get("birthDate") != null) {
				p.setDatumRodjenja(sdf.parse(jObj.get("birthDate").getAsString()));
			}
			poslanici.add(p);
		}
		return poslanici;
	}

	public static void upisiPoslanikeUFajl() throws Exception {
		JsonArray jsonNiz = new ParlamentAPIKomunikacija().vratiPoslanikeUJsonFormatu();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")));

		String s = new GsonBuilder().setPrettyPrinting().create().toJson(jsonNiz);

		out.println(s);

		out.close();

	}

	public static void upisiIzmenjenePoslanike(LinkedList<Poslanik> poslanici) throws Exception {
		JsonArray membersJson = serialize(poslanici);

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/updatedMembers.json")));

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String membersString = gson.toJson(membersJson);

		out.println(membersString);
//		System.out.println(membersString);
		out.close();

	}

	public static JsonArray serialize(LinkedList<Poslanik> poslanici) {
		JsonArray poslaniciNiz = new JsonArray();

		for (int i = 0; i < poslanici.size(); i++) {
			Poslanik p = poslanici.get(i);

			JsonObject poslanikJson = new JsonObject();
			poslanikJson.addProperty("id", p.getId());
			poslanikJson.addProperty("ime", p.getIme());
			poslanikJson.addProperty("prezime", p.getPrezime());
			if(p.getDatumRodjenja() != null)
				poslanikJson.addProperty("datumRodjenja", sdf.format(p.getDatumRodjenja()));

			poslaniciNiz.add(poslanikJson);
		}

		return poslaniciNiz;
	}
}
