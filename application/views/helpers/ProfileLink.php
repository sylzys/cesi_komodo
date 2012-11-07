<?php

class Zend_View_Helper_ProfileLink extends Zend_View_Helper_Abstract
{
    /**
     *
     * @return string
     */
    public function profileLink() 
    {
	//aide pour la contruction d'url 
	$helperUrl = new Zend_View_Helper_Url ( );
	// recuperation de l'authentification 
	$auth = Zend_Auth::getInstance ();
	if ($auth->hasIdentity ()) 
	{
	    //récuperation des donnée de session 
	    $username = $auth->getIdentity ()->UTILOGIN . ' ' . strtoupper ( substr ( $auth->getIdentity ()->UTILOGIN, 0, 1 ) ) . '.';
	    // contruction de l'url de logout
	    $logoutLink = $helperUrl->url ( array ('action' => 'logout', 'controller' => 'login' ) );
	    return 'Salut ' . $username . ' (<a href="' . $logoutLink . '">Se déconnecter</a>)';
	}
	$loginLink = $helperUrl->url ( array ('action' => 'loginuser', 'controller' => 'login' ) );
	return '<a href="' . $loginLink . '">Login</a>';
    }

}
?>
