import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DocauthService {

  constructor() { }

  authenticate(username: string, password: string){

    if(username=="tejas" && password=="Tejas@123"){
      sessionStorage.setItem('username',username);
      return true;
    }
    else{
      return false;

    }
  }

  isUserLoggedIn(){

    console.log("Doctor is login");
     let user=sessionStorage.getItem('username');
     return !(user==null)
  }


  logout(){
    console.log("Docter is logged out");
    sessionStorage.removeItem('username');
  }

}
