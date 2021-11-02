import { Pipe, PipeTransform } from '@angular/core';
import { of } from 'rxjs';
import { JobPost } from '../models/job-post';
import { User } from '../models/user';

@Pipe({
  name: 'userJob'
})
export class UserJobPipe implements PipeTransform {

  transform(value: JobPost[], user:User): JobPost[] {
    let results: JobPost[] = [];
    value.forEach((post) => {
      if(post.user.id === user.id){
        results.push(post);
      }
    });
    return results;
  }

}
