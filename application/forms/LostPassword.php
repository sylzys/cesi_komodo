<?php

class Application_Form_LostPassword extends Zend_Form
{

    public function init()
    {
        // send method
        $this->setMethod('post');
        // set name
        $this->setName('auth');
        // set id
        $this->setAttrib('id', 'auth_form');
        //mail
         $this->addElement('text', 'email', array(
            'label' => 'email :',
            'id' => 'auth_form_login',
            'required' => true,
            'filters' => array('StringTrim'),
            'validators' => array(
                array('validator' => 'EmailAddress'))
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

