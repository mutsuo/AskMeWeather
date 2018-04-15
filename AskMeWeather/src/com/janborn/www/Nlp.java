package com.janborn.www;

import java.util.List;

public class Nlp extends GetNlp {
	private String text;
	private List<Items> items;
	
	public class Items{
		private String item;
		private String ne;
		private String pos;
		private int byte_offset;
		private int byte_lenght;
		private String uri;
		private String formal;
		private String[]basic_words;
		private Loc_details Loc_details;
		
		public class Loc_details{
			private String type;
			private int byte_offset;
			private int byte_length;
		}

		public String getItem() {
			return item;
		}

		public String getNe() {
			return ne;
		}

		public String getPos() {
			return pos;
		}

		public int getByte_offset() {
			return byte_offset;
		}

		public int getByte_lenght() {
			return byte_lenght;
		}

		public String getUri() {
			return uri;
		}

		public String getFormal() {
			return formal;
		}

		public String[] getBasic_words() {
			return basic_words;
		}

		public Loc_details getLoc_details() {
			return Loc_details;
		}
		
	}
	
	
	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Items getItems() {
		return items;
	}


	public void setItems(Items items) {
		this.items = items; //±ØÐëÐÞ¸Ä
	}


	public Nlp() {
		super();
		// TODO Auto-generated constructor stub
	}

}
