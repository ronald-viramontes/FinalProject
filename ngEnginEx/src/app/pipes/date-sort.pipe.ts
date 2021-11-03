import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';

@Pipe({
  name: 'dateSort'
})
export class DateSortPipe implements PipeTransform {

  transform(jobPosts: JobPost[], returnNum: number): JobPost[] {
    let results: JobPost[] = [];
    results = jobPosts.sort((a, b) => new Date(Date.parse(b.datePosted)).valueOf() - new Date(Date.parse(a.datePosted)).valueOf());
    let len = results.length;
    results.splice(returnNum,(len-returnNum));
    return results;
  }
}


