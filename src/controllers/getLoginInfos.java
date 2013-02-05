
package controllers;
public class getLoginInfos {
   private String _login, _passwd;
   private Boolean _cancelled;

   public getLoginInfos() {
       this._cancelled = false;
   }
   public getLoginInfos(String login, String pass){
       this._cancelled = false;
       this._login = login;
       this._passwd = pass;
   }
      
   public String toString(){
      String str;
      if(this._login != null && this._passwd != null ){
        str = this._login + "#" + Encryptor.encode(this._passwd);
      }
      else{
         str = "-NA-";
      }
      return str;
   }

    public void setLogin(String login) {
        this._login = login;
    }

    public void setPasswd(String passwd) {
        this._passwd = passwd;
    }

    public Boolean getCancelled() {
        return _cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this._cancelled = cancelled;
    }
   
    
   
   
   public String getLogin() {
       return this._login;
   }
   
   public String getPasswd() {
       return this._passwd;
   }
   
}
