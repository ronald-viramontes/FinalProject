import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobPost } from 'src/app/models/job-post';
import { DateSortPipe } from 'src/app/pipes/date-sort.pipe';
import { JobPostService } from 'src/app/services/job-post.service';

@Component({
  selector: 'app-job-search',
  templateUrl: './job-search.component.html',
  styleUrls: ['./job-search.component.css'],
})
export class JobSearchComponent implements OnInit {
  constructor(
    private postService: JobPostService,
    private datePipe: DateSortPipe,
    private router: Router
  ) {}

  jobPosts: JobPost[] = [];
  keyword: string = '';
  searchResults: JobPost[] = [];
  returnNum: number = 10;

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts() {
    this.postService.index().subscribe((data) => {
      this.jobPosts = data;
    });
  }

  search(searchKeyword: string) {
    this.postService.indexByKeyword(this.keyword).subscribe(
      (data) => {
        this.jobPosts = data;
        console.log(this.searchResults);
      },
      (err) => {
        console.error(err);
      }
    );
  }

  goToJob(selected: JobPost) {
    this.router.navigateByUrl('/jobs', { state: selected });
  }
}
