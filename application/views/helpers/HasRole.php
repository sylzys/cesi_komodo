<?php

/**
 * Classe permetant de savoir si l'utilisateur connecté est autorisé a utilisé l'action du controleur.
 *
 */
class Zend_View_Helper_HasRole extends Zend_View_Helper_Abstract {

    /**
     * @$roles array contenant les roles
     * @$roleName string contenant le nom du roles
     * return true ou false
     */
    public function hasRole($roles, $roleName) {
        foreach ($roles as $role){
            if($role->getName() == $roleName){
                return true;
            }
        }
        return false;
    }

}

?>
