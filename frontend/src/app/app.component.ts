import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormControl } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { LabSeqService } from '../services/lab-seq.service';
import { Subject, takeUntil } from 'rxjs';

interface LabSeqForm {
  index2Compute: FormControl<number | null>;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule, CommonModule],
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
