import { Component, OnInit } from '@angular/core';
import { JobPost } from 'src/app/models/job-post';
import { JobPostService } from 'src/app/services/job-post.service';

@Component({
  selector: 'app-job-search',
  templateUrl: './job-search.component.html',
  styleUrls: ['./job-search.component.css']
})
export class JobSearchComponent implements OnInit {

  constructor(private postService: JobPostService) { }

  jobPosts: JobPost[] = [];

  ngOnInit(): void {
    this.loadPosts();
  }

  loadPosts(){
    this.postService.index().subscribe(
      data => {
        this.jobPosts = data;
      }
    )
  }
}
