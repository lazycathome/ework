package cn.bigdb.gallery.resource.entity;

public enum ResourceType {

	TEXT(0),
	PIC(1),
	VIDEO(2);
	
	private int type;
	
	ResourceType(int type){
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public ResourceType getValue(int type){
		switch (type) {
		case 2:
			return ResourceType.TEXT;

		case 1:
			return ResourceType.PIC;
		case 0:
			return ResourceType.VIDEO;
		default:
			return ResourceType.PIC;
		}
	}

}
