<?php
echo "<h2>".elgg_echo('kpax:listgames')."</h2>";

if($vars['categories'])
{
	//Capture category names
	$cats = array();

	foreach($vars['categories'] as $cat)
	{
		$cats[$cat->idCategory] = $cat->name;
	}

	//If there are games
	if ($vars['objGameList']) {	
		
		echo "<div class='contenedor_juegos'>";

			foreach ($vars['objGameList'] as $game) {

				$catview = "";
				if($game->idCategory != 0)
					$catview = $cats[$game->idCategory];

				$tagsview ="";

				foreach($game->tags as $tag){
					if ($tag != null){
						$tagsview .= $tag->tag." ";			
					}
				}
			echo "<div class='cuadro_juego'>";					
		      echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'></a>";
		      echo "<div class='juego_foto'>";
		         echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'>";
		         echo "<img src='".$game->urlImage."' alt='".$game->name."' width='140' height='160' />";
		         echo "<span class='transicion' />";	
		         echo "</a>";
		      echo "</div>";
		      echo "<div class='juego_texto'>";
		         echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'></a>";
		         echo "<h2><a href='"."view/" . $game->idGame."'>".$game->name."<span class='final_parrafo final_parrafo_titulo'></span></a>";
		            echo "</h2>";
		         echo "<div class='texto_descripcion'>";
		            echo "<p>".$game->descripGame."</p>";
		            echo "<span class='final_parrafo'></span>";
		            echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'></a>";
		         echo "</div>";
		         echo "<div class='texto_descripcion'>";
		            echo "<p>".$catview."</p>";
		            echo "<span class='final_parrafo'></span>";
		            echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'></a>";
		         echo "</div>";
		         echo "<div class='texto_descripcion'>";
		            echo "<p>".$tagsview."</p>";
		            echo "<span class='final_parrafo'></span>";
		            echo "<a class= 'juego_link' href='"."view/" . $game->idGame."'></a>";
		         echo "</div>";
		      echo "</div>";
		   echo "</div>";		
		   }
		echo "</div>";
	}
	//If there isn't any games
	else
	{ 
		elgg_echo('kpax:nogames');
	}
}
?> 
