import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';

@Pipe({
  name: 'dateSort'
})
export class DateSortPipe implements PipeTransform {

  transform(jobPosts: JobPost[], returnNum: number): JobPost[] {
    let results: JobPost[] = [];
    results = jobPosts.sort((a, b) => new Date(a.datePosted).getDate() - new Date(b.datePosted).getDate());
    let len = results.length;
    results.splice(returnNum,(len-returnNum));
    return results;
  }
}


