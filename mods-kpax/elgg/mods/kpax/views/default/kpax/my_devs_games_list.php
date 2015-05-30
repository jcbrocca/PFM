<?php

//
// Created by jcbrocca - ccamera
//

$content .= '<div id="Games">';
      
foreach ($vars['objGameList'] as $game) {


        $content .= '<div class="game" style="float:left; margin-right:10px; margin-bottom:10px;"><a href=view/'. $game->idGame . '>';
        $content .=  '<div style="font-weight:bold; text-align:center;">' . $game->name . '</div>';
        $content .= '<div><img src="'. $game->urlImage .'" width="140" height="140" alt=""/></div>';
        $content .= '<div style="font-weight:bold;">';
        if($game->publicAcces)
            $content .= '<div style="float:left; color:green;">Authorized</div>';
        else
            $content .= '<div style="float:left; color:red;">Unauthorized</div>';
        $content .= '<div style="float:right;"><a href=edit/'. $game->idGame . '> Edit </a></div>';
        $content .= '</div>';
        $content .= '<div style="clear:both;"></div>';
        $content .= '</a></div>';


    }           
    
    $content .= '<div style="clear:both;"></div>';
    $content .= '</div>';
    $content .= '</div>';

echo $content;
?>
