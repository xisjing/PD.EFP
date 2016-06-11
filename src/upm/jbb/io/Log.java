package upm.jbb.io;

public enum Log {
	NONE(0), INFO(1), WARNING(2), ERROR(3), DEBUG(4);
	private final int level;
	
	private Log(int level){
		this.level=level;
	}

	public int getLevel() {
		return level;
	}

}
