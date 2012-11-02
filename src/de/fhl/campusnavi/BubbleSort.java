package de.fhl.campusnavi;

import java.util.List;

public class BubbleSort {
	public List<POI> sort(List<POI> list) {
		for(int i=0; i < list.size() - 1; i++) {
			list.get(i).calcDistance(list.get(i));
		}
		
		boolean changed = true;
		// Durch Array laufen
		for(int i=0; i < list.size() - 1 && changed; i++) {
			// Gab es eine €nderung?
			changed = false;
			for(int k =0; k < list.size() -1; k++) {
				if (list.get(k-1).getDistance() > list.get(k).getDistance()) {
					POI tmp = list.get(k-1);
					list.set(k-1, list.get(k));
					list.set(k, tmp);
					changed = true;
				}
			}
		}
		return list;
	}
}
