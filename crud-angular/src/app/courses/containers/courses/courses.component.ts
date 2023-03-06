import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Course } from '../../model/course';
import { CoursesService } from '../../services/courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent {

  courses$: Observable<Course[]> | null = null;
  displayedColumns = ['name', 'category', 'actions'];

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.refresh();
  }

  refresh() {
    this.courses$ = this.coursesService.list()
      .pipe(
        catchError(error => {
          this.onError('Error loading course data');
          return of([])
        })
      );
  }

  onError(errorMessage: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMessage
    });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course._id], { relativeTo: this.route });
  }

  onRemove(course: Course) {

    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Are you sure you want to remove this course?'
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {

      if (result) {
        this.coursesService.remove(course._id).subscribe(
          () => {
            this.refresh();
            this.snackBar.open('Course deleted', 'X', { duration: 3000, verticalPosition: 'top', horizontalPosition: 'center' });
          }, () => this.onError('Error removing course')
        );
      }
    });
  }
}