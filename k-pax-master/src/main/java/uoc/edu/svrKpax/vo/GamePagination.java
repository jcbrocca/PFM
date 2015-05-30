package uoc.edu.svrKpax.vo;

import java.util.List;

import uoc.edu.svrKpax.util.IntegerWrapper;

public class GamePagination {

	private int offset;
	private int limit;
	private IntegerWrapper total;
	private List<Game> games;
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	public IntegerWrapper getTotal() {
		return total;
	}
	public void setTotal(IntegerWrapper total) {
		this.total = total;
	}
	
}