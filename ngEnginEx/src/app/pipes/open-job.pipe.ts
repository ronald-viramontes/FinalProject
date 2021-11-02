import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';

@Pipe({
  name: 'openJob'
})
export class OpenJobPipe implements PipeTransform {

  transform(value: JobPost[], statusId: number): JobPost[] {
    let results: JobPost[] = [];
    value.forEach((value) => {
      if(value.status.id === statusId){
        results.push(value);
      }
    });
    return results;
  }

}
