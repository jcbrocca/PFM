package uoc.edu.svrKpax.rest;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import uoc.edu.svrKpax.business.CategoryBO;
import uoc.edu.svrKpax.business.CommentBO;
import uoc.edu.svrKpax.business.GameBO;
import uoc.edu.svrKpax.business.GameInstanceBO;
import uoc.edu.svrKpax.business.GameLikeBO;
import uoc.edu.svrKpax.business.GameScoreBO;
import uoc.edu.svrKpax.business.TagBO;
import uoc.edu.svrKpax.business.PlatformBO;
import uoc.edu.svrKpax.business.SkillBO;
import uoc.edu.svrKpax.business.MetaDataBO;

import com.sun.jersey.api.json.JSONWithPadding;
import com.sun.jersey.spi.inject.Inject;

@SuppressWarnings("deprecation")
@Path("/game/jsonp/")
public class Jsonp {

	@Inject
	private GameBO gBo;
	@Inject
	private GameLikeBO lBo;
	@Inject
	private GameInstanceBO iBo;
	@Inject
	private GameScoreBO scBo;
	@Inject
	private CategoryBO catBo;
	@Inject
	private TagBO tagBo;
	@Inject
	private CommentBO comBo;
	@Inject
	private PlatformBO platBo;
	@Inject
	private SkillBO skillBo;
	@Inject
	private MetaDataBO mBo;
	
	/* GAMES */
	/*@GET
	@Path("/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getGamesJsonp(@PathParam("param") String campusSession,@QueryParam("jsoncallback") String callback) {
		//return gBo.listGames(campusSession);
		return new JSONWithPadding(gBo.listGames(campusSession), callback);
	}*/
	
	@POST
	@Path("/{param}/list/{idOrderer}/{idFilterer}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getGamesJsonp(@PathParam("param") String campusSession, @PathParam("idOrderer") int idOrderer, @PathParam("idFilterer") int idFilterer, @FormParam("fields") List<String> fields, @FormParam("values") List<String> values, @QueryParam("jsoncallback") String callback) {
		//return gBo.listGames(campusSession);
		return new JSONWithPadding(gBo.listGames(campusSession, idOrderer, idFilterer, fields, values), callback);
	}
		
	@GET
	@Path("/{param}/get/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getGameJsonp(@PathParam("id") String idGame,@PathParam("param") String secretSession,@QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(gBo.getGame(idGame,secretSession),callback);
	}

	@GET
	@Path("game/{session}/list/{text}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getGamesSearchJsonp(@PathParam("session") String campusSession, @PathParam("text") String text,  @QueryParam("jsoncallback") String callback, @QueryParam("offset") Integer offset, @QueryParam("limit") Integer limit) throws UnsupportedEncodingException {	
		text = URLDecoder.decode(text, "UTF-8");
		return new JSONWithPadding(gBo.listGamesSearch(campusSession, text, offset, limit), callback);
	}
	
	/* categories */
	@GET
	@Path("/category/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getCategoriesJsonp (@PathParam("param") String campusSession, @QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(catBo.listCategories(campusSession), callback);
	}
	
	@GET
	@Path("/category/{param}/get/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getCategoryJsonp(@PathParam("param") String campusSession, @PathParam("id") int idCategory, @QueryParam("jsoncallback") String callback){	
		return new JSONWithPadding(catBo.getCategory(campusSession, idCategory),callback);
	}
	
	/* tag */
	@GET
	@Path("/tag/{param}/list/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getTagsGameJsonp(@PathParam("param") String campusSession, @PathParam("id") int idGame, @QueryParam("jsoncallback") String callback) {
		return new JSONWithPadding(tagBo.listTagsGame(campusSession, idGame), callback);
	}
	
	/* comment */
	@GET
	@Path("/comment/{param}/list/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getCommentsGameJsonp (@PathParam("param") String campusSession, @PathParam("id") int idGame, @QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(comBo.listCommentsGame(campusSession, idGame), callback);
	}
	
	/* likes */
	@GET
	@Path("/like/{param}/get/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getLikeGameJsonp (@PathParam("param") String campusSession,@PathParam("id") int idGame,@QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(lBo.getLikeGame(campusSession, idGame),callback);
	}
	
	@GET
	@Path("/like/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding likeGameJsonp(@QueryParam("secretSession") String campusSession,@PathParam("id") String idGame,@QueryParam("containerId") String containerId,@QueryParam("jsoncallback") String callback) throws JSONException{
		String s;		
		if(lBo.addDelLikeGame(campusSession, Integer.parseInt(idGame),containerId))s = "{\"like\" : OK}";
		else
			s = "{\"like\" : NO OK}";
		JSONObject o = new JSONObject(s);
		return new JSONWithPadding(o, callback);
	}

	@GET
	@Path("/like/{param}/list/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getLikesGameJsonp(@PathParam("param") String campusSession,@PathParam("id") int idGame,@QueryParam("jsoncallback") String callback){	
		return new JSONWithPadding(lBo.listLikeGames(campusSession,idGame),callback);
	}

	/* GAME INSTANCES */
	
	@GET
	@Path("/instance/init")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding initGameJsonp(@QueryParam("secretSession") String campusSession,@QueryParam("secretGame") String uidGame,@QueryParam("jsoncallback") String callback) throws JSONException{
		String s = "{\"instances\" : "+iBo.initGameInstance(campusSession, uidGame).getEntity()+"}";
		JSONObject o = new JSONObject(s);		
		return new JSONWithPadding(o, callback);
	}
	
	@GET
	@Path("/instance/end/score")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding endGameScoreJsonp(@QueryParam("secretSession") String campusSession,@QueryParam("secretGame") String uidGame,@QueryParam("points") String points,@QueryParam("jsoncallback") String callback) throws JSONException{
		if(iBo.entGameInstance(campusSession, uidGame).getStatus() == 200){
			String s = "{\"instances\" : "+scBo.addScoreGame(campusSession, uidGame,points).getEntity()+"}";
			JSONObject o = new JSONObject(s);		
			return new JSONWithPadding(o, callback);
		}else{
			String s = "{\"instances\" : Error end instance}";
			JSONObject o = new JSONObject(s);		
			return new JSONWithPadding(o, callback);
		}
			
	}
	
	/* GAME SCORE */
	@GET
	@Path("/score/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getScoresGameJsonp(@PathParam("param") String uidGame,@QueryParam("jsoncallback") String callback){	
		return new JSONWithPadding(scBo.listScoreGames(uidGame),callback);
	}
	
	/* platforms */
	@GET
	@Path("/platform/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getPlatformsJsonp (@PathParam("param") String campusSession, @QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(platBo.listPlatforms(campusSession), callback);
	}
	
	@GET
	@Path("/platform/{param}/get/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getPlatformJsonp(@PathParam("param") String campusSession, @PathParam("id") int idPlatform, @QueryParam("jsoncallback") String callback){	
		return new JSONWithPadding(platBo.getPlatform(campusSession, idPlatform),callback);
	}
	
	/* skills */
	@GET
	@Path("/skill/{param}/list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getskillsJsonp (@PathParam("param") String campusSession, @QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(skillBo.listSkills(campusSession), callback);
	}
	
	@GET
	@Path("/skills/{param}/get/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getskillJsonp(@PathParam("param") String campusSession, @PathParam("id") int idSkill, @QueryParam("jsoncallback") String callback){	
		return new JSONWithPadding(skillBo.getSkill(campusSession, idSkill),callback);
	}
	
	/* metadatas */
	@GET
	@Path("/metadata/{param}/list/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces("application/x-javascript")
	public JSONWithPadding getMetaDatasGame (@PathParam("param") String campusSession, @PathParam("id") int idGame, @QueryParam("jsoncallback") String callback){
		return new JSONWithPadding(mBo.listMetaDatasGame(campusSession, idGame), callback);
	}
	
	public void setgBo(GameBO gBo) {
		this.gBo = gBo;
	}

	public GameBO getgBo() {
		return gBo;
	}

	public void setlBo(GameLikeBO lBo) {
		this.lBo = lBo;
	}

	public GameLikeBO getlBo() {
		return lBo;
	}

	public void setiBo(GameInstanceBO iBo) {
		this.iBo = iBo;
	}

	public GameInstanceBO getiBo() {
		return iBo;
	}

	public void setScBo(GameScoreBO scBo) {
		this.scBo = scBo;
	}

	public GameScoreBO getScBo() {
		return scBo;
	}

	public TagBO getTagBo() {
		return tagBo;
	}

	public void setTagBo(TagBO tagBo) {
		this.tagBo = tagBo;
	}

	public CommentBO getComBo() {
		return comBo;
	}

	public void setComBo(CommentBO comBo) {
		this.comBo = comBo;
	}

	public CategoryBO getCatBo() {
		return catBo;
	}

	public void setCatBo(CategoryBO catBo) {
		this.catBo = catBo;
	}

	public PlatformBO getPlatBo() {
		return platBo;
	}

	public void setPlatBo(PlatformBO platBo) {
		this.platBo = platBo;
	}

	public SkillBO getSkillBO() {
		return skillBo;
	}

	public void setSkillBO(SkillBO skillBo) {
		this.skillBo = skillBo;
	}		
	
	public MetaDataBO getmBo() {
		return mBo;
	}

	public void setmBo(MetaDataBO mBo) {
		this.mBo = mBo;
	}

}