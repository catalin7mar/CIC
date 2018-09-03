
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static javax.swing.UIManager.get;
import static javax.swing.UIManager.getString;


 
public class ServiceClient {
	public static void main(String[] args) {
		String string = "";
		try {
			InputStream inputStream = new FileInputStream("rows.json");
			InputStreamReader inputReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inputReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}

			JSONObject jsonObject = new JSONObject(string);

			JSONArray data = jsonObject.getJSONArray("results");

			int length = data .length();

			JSONObject jo = new JSONObject();



			if(data!=null) {


				for (int i = 0; i < length; i++) {

					JSONObject jsonObjects = data.optJSONObject(i);
					String title = jsonObjects.optString("title");
					String locations = jsonObjects.optString("locations");


					jo.put("title", title);
					jo.put("locations", locations);

					System.out.println(jo);
				}
			}

			try {
				URL url = new URL("http://localhost:8080/CIC/api/service");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jo.toString());
				out.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				while (in.readLine() != null) {
				}
				System.out.println("Erfolgreich gestartet");
				in.close();
			} catch (Exception e) {
				System.out.println("Fehler");
				System.out.println(e);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}