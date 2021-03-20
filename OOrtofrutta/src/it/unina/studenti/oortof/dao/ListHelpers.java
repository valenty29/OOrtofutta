package it.unina.studenti.oortof.dao;

import java.util.List;

public class ListHelpers {
	public static <E> void makeCopy(List<E> src, List<E> dest){
		dest.clear();
		
		for(E element: src) {
			dest.add(element);
		}
	}
}
