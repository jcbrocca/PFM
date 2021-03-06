<?php

/**
 * View a bookmark
 *
 * @package ElggBookmarks
 */
$kpax = get_entity(get_input('guid'));


$page_owner = elgg_get_page_owner_entity();

$crumbs_title = $page_owner->name;


$title = $kpax->title;

elgg_push_breadcrumb($title);

$objKpax = new kpaxSrv(elgg_get_logged_in_user_entity()->username); 
$objGame = $objKpax->getGame($kpax->guid, $_SESSION["campusSession"]);

$objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $kpax->guid);

if ($objGameDet->logo == null || $objGameDet->logo == "") {
	$objGameDet->logo = elgg_get_site_url() . "mod/kpax/graphics/logo.png";
}

if ($objGameDet->banner == null || $objGameDet->banner == "") {
	$objGameDet->banner = elgg_get_site_url() . "mod/kpax/graphics/banner.png";
}

if ($objGameDet->description == null || $objGameDet->description == "") {
	$objGameDet->description = elgg_echo('kpax:nodescript');
}


$listGameImgs = $objKpax->getGameImages($_SESSION["campusSession"], $kpax->guid);

$userGameList = $objKpax->getDeveloperGames($_SESSION["campusSession"], $page_owner->username);

$tagsList = $objKpax->getTagsGame($_SESSION["campusSession"], $kpax->guid);

$content = elgg_view(
			'kpax/games_list', 
			array(
				'objGame' => $objGame, 
				'objGameDet' => $objGameDet, 
				'listGameImgs' => $listGameImgs, 
				'objUserGameList' => $userGameList, 
				'template' => "detail"
				)
			);

$content .= elgg_view_comments($kpax);

$body = elgg_view_layout(
		'content', array(
			'content' => $content,
			'title' => $title,
			'filter' => '',
			'header' => '',
			'sidebar' => elgg_view(
							'kpax/sidebar', 
							array(
								'user' => $page_owner, 
								'tagsList' =>$tagsList, 
								'template' => "detail")
					)
				)
			);

echo elgg_view_page($title, $body);
