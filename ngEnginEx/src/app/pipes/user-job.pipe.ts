import { Pipe, PipeTransform } from '@angular/core';
import { JobPost } from '../models/job-post';
import { User } from '../models/user';

@Pipe({
  name: 'userJob'
})
export class UserJobPipe implements PipeTransform {

  transform(value: JobPost[], user:User): JobPost[] {
    let results: JobPost[] = [];
    for(let post of value){
      if(post.user.id == user.id){
        results.push(post);
      }
    }
    return results;
  }

}
