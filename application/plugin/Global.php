<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
class My_Controller_Plugin_Global extends Zend_Controller_Plugin_Abstract
{
     /**
    * Initialise toutes les actions en pre-dispatch
    *
    * @param  Zend_Controller_Request_Abstract $request
    * @return void
    */
    public function preDispatch(Zend_Controller_Request_Abstract $request)
    {
	if (Zend_Auth::getInstance ()->hasIdentity ()) 
	{
	    if ('logout' != $this->getRequest ()->getActionName ()) 
	    {
		//$redirector = new Zend_Controller_Action_Helper_Redirector;
		//$redirector->gotoUrlAndExit(ROOT_URL.'login/loginuser', array('exit' => true));
	    }
	}	
	elseif($_SERVER['REQUEST_URI'] != '/login' AND $_SERVER['REQUEST_URI'] != '/login/loginuser') 
	{
//	    if ('logout' == $this->getRequest ()->getActionName ()) 
//	    {
//		
//	    }
	    //$redirector = new Zend_Controller_Action_Helper_Redirector;
	    //$redirector->gotoUrlAndExit(ROOT_URL.'login/loginuser', array('exit' => true));
	}
    }
    
}
?>
