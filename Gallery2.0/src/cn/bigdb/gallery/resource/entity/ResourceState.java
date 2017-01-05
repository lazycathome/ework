package cn.bigdb.gallery.resource.entity;

public enum ResourceState {

	DELETE(0),
	OFFLINE(1),
	ONLINE(2);
	
	private int state;
	
	ResourceState(int state){
		this.state = state;
	}
	
	public int getState() {
		return state;
	}

	public ResourceState getValue(int state){
		switch (state) {
		case 2:
			return ResourceState.ONLINE;

		case 1:
			return ResourceState.OFFLINE;
		case 0:
			return ResourceState.DELETE;
		default:
			return ResourceState.ONLINE;
		}
	}
}
