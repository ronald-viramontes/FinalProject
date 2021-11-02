import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';

@Pipe({
  name: 'openJob'
})
export class OpenJobPipe implements PipeTransform {

  transform(value: JobPost[]): JobPost[] {
    let results: JobPost[] = [];
    value.forEach((value) => {
      if(value.status.id === 2){
        results.push(value);
      }
    });
    return results;
  }

}
