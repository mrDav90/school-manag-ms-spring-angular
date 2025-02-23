import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  let authService : AuthService = inject(AuthService);
  let router = inject(Router);
  if (!authService.getIsAuthenticated()) {
    router.navigate(['login']);
    return false;
  }
  return true;
};
