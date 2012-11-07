<?php

/**
 * Classe permetant de savoir si l'utilisateur connecté est autorisé a utilisé l'action du controleur.
 *
 */
class Zend_View_Helper_ReplaceString extends Zend_View_Helper_Abstract {
    /**
     * @$toReplace array(string)
     * #$string string to replace
     * return string with words replacing
     */
    public function replaceString($toReplace, $string) {
        $ori = array();
        for($i = 0 , $l = count($toReplace); $i < $l ;$i++ ){
            array_push($ori,'%'.($i+1));
        }
        $output = str_replace($ori, $toReplace, $string);
        return $output;
    }

}

?>
