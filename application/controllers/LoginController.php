<?php

class LoginController extends Zend_Controller_Action
{

	protected $_logged;

	public function init()
	{
		parent::init();
        // check logged user
		$auth = Zend_Auth::getInstance();
		if (!$auth->hasIdentity()) {
			$this->_logged = false;
		} else {
			$this->_logged = true;
		}
	}

	public function indexAction()
	{
		$this->_redirect('/');
	}



	public function loginuserAction()
	{
		if ($this->_logged) {
			$this->_redirect('home/index');
		}
		$message = '';
		$error_auth = false;

        // on crée le formulaire
		$form = new Application_Form_Login();
	//on l'ajoute a la vue
		$this->view->formLogin = $form;
	//on regarde si on est en post
		if ($this->_request->isPost())
		{
	    // récuperation des données du formulaire
			$formData = $this->_request->getPost();
	    // on check si les donnée sont bien valid
			if ($form->isValid ( $formData ))
			{
		//récupération des valeurs
		$login = $form->getValue('login');
		$password = $form->getValue('password');
                $dbAdapter = Zend_Registry::get('dbAdapter');
                
		$authAdapter = new Application_Model_Common_Bcrypt($dbAdapter);
		$authAdapter->setTableName('utilisateur')
			    ->setIdentityColumn('utilogin')
			    ->setCredentialColumn('utimdp')
			    //->setCredentialTreatment('MD5(?)')
			    ->setIdentity($login)
			    ->setCredential($password);
                $o_Select = $authAdapter->getDbSelect();
                $o_Select->where('utisuppr = false');
                $authAuthenticate = $authAdapter->authenticate();
		//verification validité authentification
				if ($authAuthenticate->isValid())
				{
					$storage = Zend_Auth::getInstance()->getStorage();
					$storage->write ( $authAdapter->getResultRowObject(null, 'utimdp'));
					$this->_redirect('home/index');
				}
				else
				{
					$message = 'Erreur de connexion. Login/mot de passe incorrect.';
					$error_auth = true;
				}
			}
			$this->view->error_auth = $error_auth;
			$this->view->message = $message;
		}
	}

	public function loginclientAction()
	{
	//changement de layout pour la vue login du client
		$this->_helper->layout->setLayout('loginclient');

		if ($this->_logged) {
			$this->_redirect('clientinternet/index');
		}
		$message = '';
		$error_auth = false;

           // on crée le formulaire
		$form = new Application_Form_Login();
		$form->setAction('loginclient');
	//on l'ajoute a la vue
		$this->view->formLogin = $form;
	//on regarde si on est en post
		if ($this->_request->isPost())
		{
	    // récuperation des données du formulaire
			$formData = $this->_request->getPost();
	    // on check si les donnée sont bien valid
			if ($form->isValid ( $formData ))
			{
		//récupération des valeurs
				$login = $form->getValue('login');
				$password = $form->getValue('password');
				$dbAdapter = Zend_Registry::get('dbAdapter');
				$authAdapter = new Zend_Auth_Adapter_DbTable($dbAdapter);
				$authAdapter->setTableName('client')
				->setIdentityColumn('clilogin')
				->setCredentialColumn('climdp')
				->setCredentialTreatment('MD5(?)')
				->setIdentity($login)
				->setCredential($password);
				$o_Select = $authAdapter->getDbSelect();
				$o_Select->where('clisuppr = false');
				$o_Select->where('cliacces = true');
				$authAuthenticate = $authAdapter->authenticate();
		//verification validité authentification
				if ($authAuthenticate->isValid())
				{
					$storage = Zend_Auth::getInstance ()->getStorage();
					$storage->write ( $authAdapter->getResultRowObject ( null, 'climdp' ) );
					$this->_redirect('clientinternet/index');
				}
				else
				{
					$message = 'Erreur de connexion. Login/mot de passe incorrect ou compte non activé.';
					$error_auth = true;
					$this->_redirect('clientinternet/index');
				}
			}
			$this->view->error_auth = $error_auth;
			$this->view->message = $message;
		}
	}

	public function logoutuserAction()
	{
		if ($this->_logged) {
			Zend_Auth::getInstance()->clearIdentity();
		}
		$this->_redirect('home/index');
	}

	public function logoutclientAction()
	{
		if ($this->_logged) {
			Zend_Auth::getInstance()->clearIdentity();
		}
		$this->_redirect('clientinternet/index');
	}
}



