import { Component, inject, Input, OnInit } from '@angular/core';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import Keycloak from "keycloak-js";
import { User } from '../models/user';

@Component({
  selector: 'app-header',
  imports: [NzLayoutModule , NzIconModule , NzButtonModule, NzDropDownModule, NzAvatarModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  @Input() isCollapsed : boolean = false;
  keycloak = inject(Keycloak)
  user: User | undefined ;
 
  async ngOnInit() {
    if (this.keycloak?.authenticated) {
      this.keycloak.loadUserProfile().then(userInfo => {
        console.log(userInfo);
        
      this.user = userInfo as User;
    });
    }

    
  }

  logout() {
    this.keycloak.logout();
  }
  
  
}
