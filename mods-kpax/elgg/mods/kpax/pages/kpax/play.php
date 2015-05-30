<?php

//----------------
//AUTHOR: rviguera Modify by jcbrocca - ccamera
//----------------

$objKpax = new kpaxSrv(elgg_get_logged_in_user_entity()->username); //Get games list from kpaxSrv

//Get filter and order parameters
$idFilterer = $_SESSION['gameListFilter']; //Millor fer-ho com a post
$idOrderer = $_SESSION['gameListOrder'];
$fields = $_SESSION['gameListFields'];
$values = $_SESSION['gameListValues'];

if(!isset($idFilterer))
{
    $idFilterer = 0; //Default filterer: do not filter
    $_SESSION['gameListFilter'] = $idFilterer;
}
if(!isset($idOrderer))
{
    $idOrderer = 0; //Default orderer: do not order
    $_SESSION['gameListOrder'] = $idOrderer;
}
if(!isset($fields))
{
    $fields = array(); //Default fields array: no fields
    $values = array(); //Default fields array: no fields

    $_SESSION['gameListFields'] = $fields;
    $_SESSION['gameListValues'] = $values;
}

$userGameList = $objKpax->getDeveloperGames($_SESSION["campusSession"], elgg_get_logged_in_user_entity()->username);

$notUserGameList = $objKpax->getNotDeveloperGames($_SESSION["campusSession"], elgg_get_logged_in_user_entity()->username);

$unauthorizedGames = $objKpax->getUnauthorizedGames($_SESSION["campusSession"]);

$content = elgg_view(
		'kpax/games_list', 
		array(
			'objGameList' => $notUserGameList, 
			'objUserGameList' => $userGameList, 
			'objUnauthGameList' => $unauthorizedGames, 
			'template' => "list"
	   )
	);

$params = array(
'content' => $content,
'filter' => false,  // All, Mine and Friends tabs are not shown
'sidebar' => elgg_view('kpax/sidebar', array('template' => "list"))
);

$body = elgg_view_layout('one_sidebar', $params);
 
echo elgg_view_page($title, $body);
?>
