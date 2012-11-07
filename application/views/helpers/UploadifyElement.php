<?php
/**
 * Description of SP_View_Helper_Uploadify
 *
 */
class Zend_View_Helper_UploadifyElement extends Zend_View_Helper_FormElement 
{
    protected $html = '';
    protected $pathUpload = '';


    public function uploadifyElement($name, $value = null, $attribs = null)
    {
        $helper = new Zend_View_Helper_FormFile();
        $helper->setView($this->view);

        $this->html .= $helper->formFile($name ,$value,array());
        $this->html .= '<a href="#">'.$this->pathUpload.'</a>';

        return $this->html;
    }
    
     public function setPathUpload($valuePath){
         $this->pathUpload = $valuePath;
     }
    
}

?>
