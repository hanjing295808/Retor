package com.tedu.word.entity;

public class Word {
		private long id;
		private String en;
		private String zh;
		
		public Word() {
			super();
		}

		public Word(String en, String zh) {
			super();
			this.en = en;
			this.zh = zh;
		}

		public String getEn() {
			return en;
		}

		public void setEn(String en) {
			this.en = en;
		}

		public String getZh() {
			return zh;
		}

		public void setZh(String zh) {
			this.zh = zh;
		}

		@Override
		public String toString() {
			return "Word [en=" + en + ", zh=" + zh + "]";
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

	
}
