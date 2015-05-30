package uoc.edu.svrKpax.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.util.IntegerWrapper;
import uoc.edu.svrKpax.vo.Game;

import org.hibernate.*;

public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames() {
		return getHibernateTemplate().find("from Game where grantPublicAccess = 1");
	}

	@Override
	public Game getGame(int idGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("idGame", idGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addGame(Game objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delGame(Game objGame) {
		getHibernateTemplate().delete(objGame);
	}

	@Override
	public Game getGameUid(String uidGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("secretGame", uidGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
	
	@Override
	public Game getGameByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("name", name));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

    @SuppressWarnings("unchecked") 
	@Override
	public List<Game> getUserGames(String username) {
		String SQL="SELECT g.* FROM `Game` g, `GameDeveloper` gd, `User` u WHERE u.login=:username AND u.idUser = gd.idUser AND gd.idGame = g.idGame;";
		SQLQuery query=getSession().createSQLQuery(SQL);
    
    	query.addEntity(Game.class);
        query.setParameter("username", username);
        List<Game> list= query.list();
        return list;
	}

    @SuppressWarnings("unchecked") 
	@Override
	public List<Game> getDeveloperGame(String developer) {
		DetachedCriteria criteria =   DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.eq("developer", developer));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}

    @SuppressWarnings("unchecked") 
	@Override
	public List<Game> getNotDeveloperGame(String developer) {
		DetachedCriteria criteria =  DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.ne("developer", developer)).add(Restrictions.eq("publicAcces", 1));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}
	
    @SuppressWarnings("unchecked") 
	@Override
	public List<Game> getUnauthorizedGame(){
		DetachedCriteria criteria =  DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.eq("publicAcces", 0));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}


    /* Corral */
  
    @SuppressWarnings("unchecked")
	@Override
	public List<Game> getGamesSearch(String text, Integer offset, Integer limit, IntegerWrapper total) {
		
		//Extract parameters from text
		String wName = text.split("#_#")[0];
		String wCategory = text.split("#_#")[1];
		String wPlatform = text.split("#_#")[2];
		String wSkill = text.split("#_#")[3];
		String wTag = text.split("#_#")[4];
		String wKeyMeta = text.split("#_#")[5];
		String wValueMeta = text.split("#_#")[6];
		String wSort = text.split("#_#")[7];
		
		//Extract id that satisfy the filter and by order

		Criteria criteria = getSession().createCriteria(Game.class, "game");
		criteria.setProjection(Projections.distinct(Projections.property("idGame")));
		criteria.add(Restrictions.eq("publicAcces", 1));
		criteria.createAlias("game.tags", "tag", Criteria.LEFT_JOIN);
		criteria.createAlias("game.metadatas", "metadata", Criteria.LEFT_JOIN);

		List<Integer> listId = new ArrayList<Integer>();
		List<Integer> listIdPagination = new ArrayList<Integer>();
		List<Game> list = new ArrayList<Game>();
		
		//Filter
		if(wName.trim().length() > 0)
			criteria.add(Restrictions.like("name", "%"+wName+"%"));
		if(!"0".equals(wCategory))
			criteria.add(Restrictions.eq("idCategory", new Integer(wCategory)));
		if(!"0".equals(wPlatform))
			criteria.add(Restrictions.eq("idPlatform", new Integer(wPlatform)));
		if(!"0".equals(wSkill))
			criteria.add(Restrictions.eq("idSkill", new Integer(wSkill)));
		if(wTag.trim().length() > 0) {
			criteria.add(Restrictions.like("tag.tag", "%"+wTag+"%"));
		}
		if((!"0".equals(wKeyMeta)) && (wValueMeta.trim().length() > 0)){
			criteria.add(Restrictions.eq("metadata.keyMeta", wKeyMeta));
			criteria.add(Restrictions.like("metadata.valueMeta", "%"+wValueMeta+"%"));
		}

		//Order
		if("1".equals(wSort)) {
			criteria.addOrder(Order.asc("name"));
		}
		else if("2".equals(wSort)) {
			criteria.addOrder(Order.asc("idCategory"));
		}
		else if("3".equals(wSort)) {
			criteria.addOrder(Order.asc("idPlatform"));
		}
		else if("4".equals(wSort)) {
			criteria.addOrder(Order.asc("idSkill"));
		}
		
		//Distinct
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		//List
		listId = criteria.list();
		
		//Paginate
		Integer listIdSize = listId.size();
		total.setInteger(listIdSize);
		
		//If listId contain id's
		if (listIdSize > 0) {
		
			if (offset + limit < listIdSize) {
				listIdPagination = listId.subList(offset, offset + limit);
			} else {
				listIdPagination = listId.subList(offset, listIdSize);
			}
			
			//Extract all attributes from database (filter by id and order)
			criteria = getSession().createCriteria(Game.class, "game");
			
			//Filter
			criteria.add(Restrictions.in("idGame", listIdPagination));
			
			//Order
			if("1".equals(wSort)) {
				criteria.addOrder(Order.asc("name"));
			}
			else if("2".equals(wSort)) {
				criteria.addOrder(Order.asc("idCategory"));
			}
			else if("3".equals(wSort)) {
				criteria.addOrder(Order.asc("idPlatform"));
			}
			else if("4".equals(wSort)) {
				criteria.addOrder(Order.asc("idSkill"));
			}
			
			//List
			list = criteria.list();
		
		}
		
        return list;
	}
	
	@Override
	public List<Game> getSimilarGames(String idGame){
		String HQLSQL = "select {g.*} from Game g " +
				" inner join TotalGameSimilitudeView tgsv on g.idGame = tgsv.idGame " +
				" where tgsv.similarToIdGame = " + idGame + " " +
				" order by tgsv.totalSimilitude desc " +
				" limit 2";
		
		try
		{
			org.hibernate.Session s = getSession();
			Transaction t = s.getTransaction();
			t.begin();
			@SuppressWarnings("unchecked")
			List<Game> games = s.createSQLQuery(HQLSQL)
			.addEntity("g", Game.class).list();
			t.commit();
			releaseSession(s);
			
			return games;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
