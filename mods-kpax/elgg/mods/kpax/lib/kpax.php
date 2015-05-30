<?php
/**
 * kpax helper functions
 *
 * @package kpax
 */

/**
 * Prepare the add/edit form variables
 *
 * @param ElggObject $kpax A kpax object.
 * @return array
 */
function kpax_prepare_form_vars($kpax = null) {

	$values = array(
		'title' => get_input('title', ''), // bookmarklet support
		'description' => get_input('description', ''),
		'access_id' => ACCESS_LOGGED_IN,
		'tags' => '',
		'shares' => array(),
		'container_guid' => elgg_get_page_owner_guid(),
		'guid' => null,
		'entity' => $kpax,
		'category' => get_input('category', '1'), //NOU
		'creationDate' => get_input('category', date("d-m-Y H:i:s")) //NOU
	);

	if ($kpax) {
		foreach (array_keys($values) as $field) {
			if (isset($kpax->$field)) {
				$values[$field] = $kpax->$field;
			}
		}
	}

	if (elgg_is_sticky_form('kpax')) {
		$sticky_values = elgg_get_sticky_values('kpax');
		foreach ($sticky_values as $key => $value) {
			$values[$key] = $value;
		}
	}

	elgg_clear_sticky_form('kpax');

	return $values;
}


// Added by jcbrocca - ccamera
// Put into kpax/lib/kpax.php

/**
  * Takes in a space-separated string and returns an array of strings
  *
  * Adapted from elgg string_to_tag_array() function.
  *
  * @param string $string Space-separated URL string
  *
  * @return array|false An array of strings, or false on failure
  */

 function string_to_url_array($string) {
	if (is_string($string)) {
		$ar = explode(" ", $string); // Divide una cadena en varios string, que estén separados por un espacio

		return $ar;
	}
	return false;
}


/* Get categories, platforms and skills */

function  list_basic_features() {
    
    $objKpax = new kpaxSrv(elgg_get_logged_in_user_entity()->username);
    
    $categories = $objKpax->getCategories($_SESSION["campusSession"]);
    $platforms = $objKpax->getPlatforms($_SESSION["campusSession"]);
    $skills = $objKpax->getSkills($_SESSION["campusSession"]);

    $ar = array ($categories, $platforms, $skills );
    unset($objKpax);
    return $ar;
    }
?>
