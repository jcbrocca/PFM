package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.MetaData;

public interface MetaDataDao {
	
	public List<MetaData> getAllMetaDatasGame(int idGame);
	public List<MetaData> getAllMetaDatas();
	public MetaData getMetaData(int idMetaData);
	public void addMetaDataGame(MetaData metaData);
	public void delMetaDataGame(MetaData metaData);
}
