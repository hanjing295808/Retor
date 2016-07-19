package cn.tena.android_0616_viewpager;

public class Chengyu {
		private String id;//≥…”ÔID
		private String name;//≥…”Ô
		private String spell;//∆¥“Ù
		private String content;//Ω‚ Õ
		private String derivation;//µ‰π 
		private String samples;// æ¿˝		
		public Chengyu() {
			super();
		}
		public Chengyu(String id, String name, String spell, String content,
				String derivation, String samples) {
			super();
			this.id = id;
			this.name = name;
			this.spell = spell;
			this.content = content;
			this.derivation = derivation;
			this.samples = samples;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSpell() {
			return spell;
		}
		public void setSpell(String spell) {
			this.spell = spell;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getDerivation() {
			return derivation;
		}
		public void setDerivation(String derivation) {
			this.derivation = derivation;
		}
		public String getSamples() {
			return samples;
		}
		public void setSamples(String samples) {
			this.samples = samples;
		}
		@Override
		public String toString() {
			return name;
		}
		
}
