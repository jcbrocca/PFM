<?php
/**
 * Authors: jcbrocca - ccamera
*/

class ElggKpax extends ElggObject {

	/**
	 * Set subtype to kpax.
	 */
	protected function initializeAttributes() {
		parent::initializeAttributes();

		$this->attributes['subtype'] = "kpax";
	}
	
}
