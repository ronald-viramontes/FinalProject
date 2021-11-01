import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';

@Pipe({
  name: 'dateSort'
})
export class DateSortPipe implements PipeTransform {

  transform(jobPosts: JobPost[], args?: any): JobPost[] {
    let results: JobPost[] = [];
    results = jobPosts.sort((a, b) => new Date(b.datePosted).getDate() - new Date(a.datePosted).getDate());
    return results;
  }
}

