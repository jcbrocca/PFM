<?php

//
// Modified by jcbrocca - ccamera
//

$username = elgg_get_logged_in_user_entity()->username;

$content = "";

$title = elgg_echo($username . " games");

elgg_register_title_button();

//DEFAULT OPTIONS FOR ELGG LISTING.
$options = array(
    'types' => 'object',
    'subtypes' => 'kpax',
    'limit' => 10,
    'full_view' => false
        );

//Get games list from kpaxSrv

$objKpax = new kpaxSrv($username); 

$gamesList = $objKpax->getDeveloperGames($_SESSION["campusSession"], $username);

if(isset($gamesList)){
     system_message(elgg_echo('kpax:list:success'));

        $content .= '<div id="myGames">';

        // Obtener datos desde GameDetail
        foreach ($gamesList as $game) {

            $objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $game->idGame);

            if($objGameDet != null) {

				if ($objGameDet->description != null && $objGameDet->description != ""){
					$game->descripGame = $objGameDet-> description;
				} 
				else {
					$game->descripGame = elgg_echo('kpax:nodescript');
				}

                if ($objGameDet->logo != null && $objGameDet->logo != ""){
					$game->urlImage = $objGameDet->logo;
				} else {
					$game->urlImage = elgg_get_site_url() . "mod/kpax/graphics/logo.png";
				}
            }
			else {
				$game->descripGame = elgg_echo('kpax:nodescript');
				$game->urlImage = elgg_get_site_url() . "mod/kpax/graphics/logo.png";
			}
    }
}

    /* Show results */

if(isset($gamesList) && sizeof($gamesList) > 0){
    $content .= elgg_view(
			'kpax/my_devs_games_list', 
			array('objGameList' => $gamesList)
		);

} else {
    $content .= '<div><p>' . elgg_echo('kpax:noGames') . '</p></div>';
}


//LISTING THE GAMES.


$params = array(
'content' => $content,
'filter' => false,  // All, Mine and Friends tabs are not shown
'sidebar' => elgg_view('kpax/sidebar', array('template' => "list"))
);

//$body = elgg_view_layout('one_sidebar', $params);


$body = elgg_view_layout(
    'content', array(
        'filter' => false,
        'content' => $content,
        'title' => $title,
        'sidebar' => elgg_view('kpax/sidebar', array('template' => "list"))
   ));
echo elgg_view_page($title, $body);

?>
