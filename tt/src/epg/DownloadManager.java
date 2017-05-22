package epg;

import java.util.Calendar;
import java.util.Map;
import java.util.Set;

public class DownloadManager {

	Calendar initialDate;
	Map<String, Boolean> cbStates;
	
	public DownloadManager(Calendar initialDate, Map<String, Boolean> cbStates) {
		this.initialDate = initialDate;
		this.cbStates = cbStates;
	}

	public void execute() {
		Set<String> state = cbStates.keySet();
		for(String e : state) {
			if (cbStates.get(e) == true) {
				try {
					DownloadThread dt = (DownloadThread)Class.forName(e+"DownloadThread").newInstance();
					dt.execute();
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
