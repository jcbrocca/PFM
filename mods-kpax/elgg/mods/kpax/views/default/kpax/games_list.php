<!-- Modify by jcbrocca - ccamera --> 
<script >
    function toggle_visibility() 
    {
        var e = document.getElementById('myGames');
        if ( e.style.display == 'block' )
            e.style.display = 'none';
        else
            e.style.display = 'block';
    }
    function toggle_unauth_visibility() 
    {
        var e = document.getElementById('unauthGames');
        if ( e.style.display == 'block' )
            e.style.display = 'none';
        else
            e.style.display = 'block';
    }

</script>

<!-- 1. Link to jQuery -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> <!-- 33 KB -->

<!-- fotorama.css & fotorama.js. -->
<link  href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet"> <!-- 3 KB -->
<script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script> <!-- 16 KB -->


<?php

$objKpax = new kpaxSrv(elgg_get_logged_in_user_entity()->username);

if ( $vars['template'] == "list") { 

        // "Find games" button

        echo'<div style="float:right;"><input onclick=location.href="find"; class="elgg-button elgg-button-action" type="button" value="Find games" ></div>';

        // "Show my games" button

        echo'<div style="margin-right:10px; float:right;"><input onclick="toggle_visibility();" class="elgg-button elgg-button-action" type="button" value="Show my games" id="toggleGames" ></div>';

        if (elgg_is_admin_logged_in())

            // "Unauthorized games" button

            echo'<div style="margin-right:10px; float:right;"><input onclick="toggle_unauth_visibility();" class="elgg-button elgg-button-action" type="button" value="Unauthorized games" id="toggleUnauthGames" ></div>';       
        echo'<div style="clear:both;"></div>';
    
    ?>
    <div id="unauthGames" style="display: none;">
        <?php echo '<div style="font-weight:bold; margin-top: 10px; font-size:18px; text-align:center;">Unauthorized Games</div>';?>
        <div class='score' style='border:1px solid #cccccc; padding:10px; padding-right: 0px; text-align:left;'>
            <form method="post">
            <filedset>
            <?php
            if ($vars['objUnauthGameList']){         
                foreach ($vars['objUnauthGameList'] as $mygame) {
                    echo '<div class="game" style="float:left; margin-right:10px; margin-bottom:10px;"><a href=view/'. $mygame->idGame . '>';
                        echo '<div style="font-weight:bold; text-align:center;">' . $mygame->name . '</div>';
                        $objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $mygame->idGame);
                        if($objGameDet != null && $objGameDet->logo != null && $objGameDet->logo != ""){
                            echo '<div><img src="'. $objGameDet->logo .'" width="150" height="150" alt=""/></div>';
                        }
                        else{
                            echo '<div><img src="' . elgg_get_site_url() . 'mod/kpax/graphics/logo.png" width="150" height="150" alt=""/></div>';
                        }
                        echo '<div style="font-weight:bold;"></a>';
                        echo '<div style="float:left; margin-right:10px;">Authorize';echo'<input type="checkbox" name="authGame' . $mygame->idGame .'" />';echo '</div>';
                        echo '<div style="float:right;"><a href=edit/'. $mygame->idGame . '> Edit </a></div>';
                        echo '</div>';
                        echo'<div style="clear:both;"></div>';
                    echo '</div>';
                }
                if ($vars['objUnauthGameList']) {
                       echo'<div style="width:100px;"><input type="submit" class="elgg-button elgg-button-action" value="Authorize"></div>';
                }       
            echo'<div style="clear:both;"></div>';

            } else echo '<div style="text-align:left;">' . elgg_echo('kpax:none') . '</div>';

            ?>
            </filedset>
            </form>
        </div>
    </div>
    <div id="myGames" style="display: none;">
        <?php echo '<div style="font-weight:bold; margin-top: 10px; font-size:18px; text-align:center;">' . elgg_get_logged_in_user_entity()->username . ' Games</div>';?>
        <div class='score' style='border:1px solid #cccccc; padding:10px; padding-right: 0px; text-align:left;'>
            <?php
            if ($vars['objUserGameList']) {            
                foreach ($vars['objUserGameList'] as $mygame) {
                    echo '<div class="game" style="float:left; margin-right:10px; margin-bottom:10px;"><a href=view/'. $mygame->idGame . '>';
                        echo '<div style="font-weight:bold; text-align:center;">' . $mygame->name . '</div>';
                        $objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $mygame->idGame);
                        if($objGameDet != null && $objGameDet->logo != null && $objGameDet->logo != ""){
                            echo '<div><img src="'. $objGameDet->logo .'" width="150" height="150" alt=""/></div>';
                        }
                        else{
                            echo '<div><img src="' . elgg_get_site_url() . 'mod/kpax/graphics/logo.png" width="150" height="150" alt=""/></div>';
                        }
                        echo '<div style="font-weight:bold;">';
                            if($mygame->publicAcces)
                                echo'<div style="float:left; color:green;">Authorized</div>';
                            else
                                echo'<div style="float:left; color:red;">Unauthorized</div>';                                  
                            echo'<div style="float:right;"><a href=edit/'. $mygame->idGame . '> Edit </a></div>';
                        echo '</div>';
                        echo'<div style="clear:both;"></div>';
                    echo '</a></div>';
                }
                echo'<div style="clear:both;"></div>';
            } else echo '<div style="text-align:left;">' . elgg_echo('kpax:none') . '</div>';      
            ?>
        </div>
    </div>
    <?php echo '<div style="margin-top: 10px; font-size:18px; font-weight:bold; text-align:center;"> Other Games</div>';?>
    <div class='score' style='border:1px solid #cccccc; padding:10px; padding-right: 0px; text-align:left;'>
    <?php if(!$vars['objGameList']){ 
        echo '<div style="text-align:left;">' . elgg_echo('kpax:none') . '</div>';
    } 
        foreach ($vars['objGameList'] as $game) {
            echo '<div class="game" style="float:left; margin-right:10px; margin-bottom:10px;"><a href=view/'. $game->idGame . '>';
                echo '<div style="font-weight:bold; text-align:center;">' . $game->name . '</div>';
                $objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $game->idGame);
                if($objGameDet != null && $objGameDet->logo != null && $objGameDet->logo != ""){
                    echo '<div><img src="'. $objGameDet->logo .'" width="150" height="150" alt=""/></div>';
                }
                else{
                    echo '<div><img src="' . elgg_get_site_url() . 'mod/kpax/graphics/logo.png" width="150" height="150" alt=""/></div>';
                }
            echo '</a></div>';           
        }
        echo'<div style="clear:both;"></div>';
        ?>
    </div>
    <?php
}
else if ( $vars['objGame'] && $vars['template'] == "detail") { 
    ?><script>$(".elgg-breadcrumbs").hide(); </script><?php
	
	echo '<div style="margin-top: -25px;"><img src="'. $vars['objGameDet']->banner .'" width="540" alt=""/></div>';
	echo '<div style="margin-left: 20px; margin-top: -75px !important; border-top-style: solid;"><img src="'. $vars['objGameDet']->logo .'" width="150" height="150" alt=""/></div>';

	if($vars['objGameDet'] != null && $vars['objGameDet']->videourl != null && $vars['objGameDet']->videourl != ""){

		echo '<div style="margin-top:20px; float:left;"><iframe width="410" height="315" src="//www.youtube.com/embed/'. $vars['objGameDet']->videourl .'" frameborder="0" allowfullscreen></iframe></div>';
	}
   	echo '<div style="margin-top:20px; padding-left:20px;" class="fotorama" width="530" height="315">';
        if (count($vars['listGameImgs'])>0){
            for ($n = 0; $n < count($vars['listGameImgs']); $n++)
                echo '<img width="530" height="315" src="' . $vars['listGameImgs'][$n]->image . '">';
        }
    ?>
    </div>
    <div style="clear:both;"></div>
    <hr width="90%">
    <div id="description">
        <div class="title" style="font-size:25px; margin-bottom:20px;">Description</div>
        <?php 
        echo $vars['objGameDet']->description; 
        ?>
    </div>
    <hr width="90%">
    <div class="title" style="font-size:25px; margin-bottom:20px; margin-top:20px;">Other games from the same developer</div>
    <div class='score' style='border:1px solid #cccccc; padding:10px; padding-right: 0px; text-align:left;'>
        
        <?php
        foreach ($vars['objUserGameList'] as $mygame) {
            if ($vars['objGame']-> idGame != $mygame-> idGame){
                echo '<div class="game" style="float:left; margin-right:10px; margin-bottom:10px;"><a href='. $mygame->idGame . '>';
                    echo '<div style="font-weight:bold; text-align:center;">' . $mygame->name . '</div>';

                    $objGameDet = $objKpax->getGameDetail($_SESSION["campusSession"], $mygame->idGame);
                    if($objGameDet != null && $objGameDet->logo != null && $objGameDet->logo != ""){
                        echo '<div><img src="'. $objGameDet->logo .'" width="150" height="150" alt=""/></div>';
                    }
                    else{

                    echo '<div><img src="' . elgg_get_site_url() . 'mod/kpax/graphics/logo.png" width="150" height="150" alt=""/></div>';
                    }

                  if ($vars['username'] == $mygame->developer) { 
        
                        echo '<div style="font-weight:bold; text-align:center;"><a href=../edit/'. $mygame->idGame . '> Edit</a></div>';
                    }          

                echo '</a></div>';
             }    
        }
        echo'<div style="clear:both;"></div>';
        ?>
    </div>
    <?php

}
else
{ 
    elgg_echo('kPAX:noGames');
}

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $unauthorizedGames = $objKpax->getUnauthorizedGames($_SESSION["campusSession"]);
    foreach ($unauthorizedGames as $unauthgame) {
        $check = get_input('authGame' . $unauthgame->idGame);
        error_log(print_r($unauthgame->idGame, TRUE)); 
        error_log(print_r($check, TRUE)); 
        if ($check == 'on'){
            if($objKpax->authorizeGame($_SESSION["campusSession"], $unauthgame->idGame)!="OK"){
                register_error(elgg_echo('kpax:save:failed:service'));
            }
        }
    }
    forward('kpax/play');
}
?> 
