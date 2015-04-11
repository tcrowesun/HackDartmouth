import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class DataTable {

	public Map<Integer, ArrayList<Entry>> table;


	public DataTable() {

		this.table = new HashMap<Integer, ArrayList<Entry>>();

	}

	public void add(int ID, int X, int Y) {

		Date currentTime = new Date();

		if (!this.table.containsKey(ID)) {
			this.table.put(ID, new ArrayList<Entry>());
			this.table.get(ID).add(new Entry(ID, X, Y, currentTime.getTime()));
		}
		else {	
			this.table.get(ID).add(new Entry(ID, X, Y, currentTime.getTime()));

		}

	}

	public void saveInfo(String path) throws IOException {
		Date currentTime = new Date();
		File file = new File(path + "Stats_" + currentTime.getTime());
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(int id : this.table.keySet()) {
			for(Entry ent : this.table.get(id)) {
				writer.write(id + "," + ent.X + "," + ent.Y + "," + ent.Time);
			}
			writer.close();
		}


	}
}