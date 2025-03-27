import { Routes } from '@angular/router';
import { LayoutComponent } from './layout/layout.component';
import { StudentsComponent } from './business/students/students.component';
import { CoursesComponent } from './business/courses/courses.component';
import { DashboardComponent } from './business/dashboard/dashboard.component';
import { ClassesComponent } from './business/classes/classes.component';
import { canActivateAuthRole } from './auth/guard/auth.guard';

export const routes: Routes = [
  {
    path : '',
    redirectTo : '/dashboard',
    pathMatch : 'full'
  },
  { 
    path: '',
    component : LayoutComponent,
    canActivate : [canActivateAuthRole],
    children : [
      { path: 'dashboard', component : DashboardComponent },
      { path: 'students', component : StudentsComponent },
      { path: 'courses', component : CoursesComponent },
      { path: 'classes', component : ClassesComponent },
    ]
  },
];
