
package controllers;
public class getLoginInfos {
   private String login, pass;

   public getLoginInfos(){}
   public getLoginInfos(String login, String pass){
      this.login = login;
      this.pass = pass;
   }
      
   public String toString(){
      String str;
      if(this.login != null && this.pass != null ){
        str = this.login + "#" + Encryptor.encode(this.pass);
         
      }
      else{
         str = "-NA-";
      }
      return str;
   }
}
