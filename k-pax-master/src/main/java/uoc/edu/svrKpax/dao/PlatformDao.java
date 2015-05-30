package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Platform;

public interface PlatformDao {
	
	public List<Platform> getAllPlatforms();
	public Platform getPlatform(int idPlatform);
}

