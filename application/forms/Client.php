<?php

class Application_Form_Client extends Zend_Form
{

    public function init()
    {
        // send method
        $this->setMethod('post');
        // set name
        $this->setName('auth');
        // set id
        $this->setAttrib('id', 'auth_form');
        // password field
        $this->addElement('password', 'password', array(
            'label' => 'Mot de passe :',
            'id' => 'auth_form_password',
            'required' => true,
            'filters' => array('StringTrim'),
            'validators' => array(
                array('validator' => 'StringLength', 'options' => array(0, 20)))
        ));
        // // password confirm field
        $this->addElement('password', 'password_conf', array(
            'label' => 'Confirmer :',
            'id' => 'auth_form_password_conf',
            'required' => true,
            'filters' => array('StringTrim'),
            'validators' => array(
                array('validator' => 'StringLength', 'options' => array(6, 20)))
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

