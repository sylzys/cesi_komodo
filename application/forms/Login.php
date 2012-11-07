<?php

class Application_Form_Login extends Zend_Form
{

    public function init()
    {
        // send method
        $this->setMethod('post');
        // set name
        $this->setName('auth');
        // set id
        $this->setAttrib('id', 'auth_form');
        // login field
        $this->addElement('text', 'login', array(
            'label' => 'Login :',
            'id' => 'auth_form_login',
            'required' => true,
            'filters' => array('StringTrim')
        ));
        // password field
        $this->addElement('password', 'password', array(
            'label' => 'Mot de passe :',
            'id' => 'auth_form_password',
            'required' => true,
            'filters' => array('StringTrim')
        ));
        // submit button
        $this->addElement('submit', 'auth', array(
            'ignore' => true,
            'id' => 'auth_form_submit',
            'label'  => 'Connexion'
        ));
        // CSRF protection
//        $this->addElement('hash', 'csrf', array(
//            'ignore' => true
//        ));
    }


}

