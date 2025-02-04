import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LabSeqService } from './LabSeqService';


interface LabSeqForm {
  index2Compute: FormControl<number | null>;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy {
  readonly title = 'LabSeq Frontend';
  result = '';
  isLoading = false;
  error: string | null = null;
  aForm!: FormGroup<LabSeqForm>;
  private destroy$ = new Subject<void>();

  constructor(
    private labSeqService: LabSeqService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private initForm(): void {
    this.aForm = this.fb.group<LabSeqForm>({
      index2Compute: new FormControl<number | null>(null, [
        Validators.required,
        Validators.min(0),
        Validators.max(20000)
      ])
    });
  }

  public postResult(): void {
    if (this.aForm.valid && this.aForm.value?.index2Compute !== null) {
      this.isLoading = true;
      this.error = null;

      this.labSeqService.postIndexValue(this.aForm.value?.index2Compute!.toString())
        .pipe(takeUntil(this.destroy$))
        .subscribe({
          next: (response) => {
            this.result = response;
            this.isLoading = false;
          },
          error: (error) => {
            this.error = 'Failed to compute sequence';
            this.isLoading = false;
            console.error('Error computing labseq:', error);
          }
        });
    }
  }
}
