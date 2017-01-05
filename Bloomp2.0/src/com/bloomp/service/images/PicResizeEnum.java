package com.bloomp.service.images;

public interface PicResizeEnum {
	
	enum Business_PIC {

		WIDTH("100"), HEIGHT("75"), SCALE("0.75");

		private String name;

		public String getName() {
			return name;
		}

		Business_PIC(String name) {
			this.name = name;
		}

	}
	
	enum Activity_PIC {

//		WIDTH("48,72"), HEIGHT("48,72"), SCALE("1");
		WIDTH("72"), HEIGHT("72"), SCALE("1");

		private String name;

		public String getName() {
			return name;
		}

		Activity_PIC(String name) {
			this.name = name;
		}

	}
	
	enum Topic_PIC {

//		WIDTH("48,72"), HEIGHT("48,72"), SCALE("1");
		WIDTH("72"), HEIGHT("72"), SCALE("1");
		
		private String name;

		public String getName() {
			return name;
		}

		Topic_PIC(String name) {
			this.name = name;
		}

	}
	
	
	enum ZhangMen_PIC {

//		WIDTH("36,48,60"), HEIGHT("36,48,60"), SCALE("1");
		WIDTH("72"), HEIGHT("72"), SCALE("1");

		private String name;

		public String getName() {
			return name;
		}

		ZhangMen_PIC(String name) {
			this.name = name;
		}

	}
	
	enum Circle_PIC {

//		WIDTH("36,48,72"), HEIGHT("36,48,72"), SCALE("1");
		WIDTH("72"), HEIGHT("72"), SCALE("1");

		private String name;

		public String getName() {
			return name;
		}

		Circle_PIC(String name) {
			this.name = name;
		}
	}
			
	enum Zhangxin_PIC {

		WIDTH("240,80"), HEIGHT("80,240");

		private String name;

		public String getName() {
			return name;
		}

		Zhangxin_PIC(String name) {
			this.name = name;
		}
	}
	
	enum Nengliangka_PIC {

		WIDTH("72,180"), HEIGHT("72,180");

		private String name;

		public String getName() {
			return name;
		}

		Nengliangka_PIC(String name) {
			this.name = name;
		}
	}	
	
}
