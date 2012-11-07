<?php

class Zend_View_Helper_ControllerAction extends Zend_View_Helper_Abstract {

    public function ControllerAction() {
        $ctrl_act = new stdClass();
        $request = Zend_Controller_Front::getInstance()->getRequest();
        $ctrl_act->controller = $request->getControllerName();
        $ctrl_act->action = $request->getActionName();
        return $ctrl_act;
    }

    
}

?>
