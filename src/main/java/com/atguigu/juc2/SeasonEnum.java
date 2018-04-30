package com.atguigu.juc2;

public enum SeasonEnum {
	
	ONE(1,"春天"),TWO(2,"夏天"),THREE(3,"秋天"),FOUR(4,"冬天");
	
	private int code;
	private String season;
	
	private SeasonEnum(int code, String season) {
		this.code = code;
		this.season = season;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}
	
	public static SeasonEnum foreachSeasonEnum(int index) {
		for (SeasonEnum element : values()) {
			if(element.getCode()==index) {
				return element;
			}
		}
		
		return null;
	}

}
