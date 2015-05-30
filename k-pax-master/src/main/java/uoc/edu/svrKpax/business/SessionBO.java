package uoc.edu.svrKpax.business;

import uoc.edu.svrKpax.vo.User;


public interface SessionBO {

	public String newSession(String uidUser);
	public String refreshSession(String uidUser);
	public User validateSession(String uidSession);
}
